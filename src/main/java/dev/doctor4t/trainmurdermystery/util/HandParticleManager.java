package dev.doctor4t.trainmurdermystery.util;

import dev.doctor4t.trainmurdermystery.client.particle.HandParticle;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.concurrent.CopyOnWriteArrayList;

public class HandParticleManager {
    private final CopyOnWriteArrayList<HandParticle> particles = new CopyOnWriteArrayList<>();

    public void spawn(HandParticle p) {
        particles.add(p);
    }

    public void tick() {
        if (particles.isEmpty()) return;

        particles.forEach(handParticle -> {
            if (!handParticle.tick(1f)) particles.remove(handParticle);
        });
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (particles.isEmpty()) return;

        MatrixStack.Entry entry = matrices.peek();
        Matrix4f model = entry.getPositionMatrix();

        Vector3f right = new Vector3f(1, 0, 0);
        Vector3f up = new Vector3f(0, 1, 0);

        for (HandParticle p : particles) {
            RenderLayer rl = p.renderLayerFactory.apply(p.texture);
            VertexConsumer consumer = vertexConsumers.getBuffer(rl);

            float half = p.size * 0.5f;
            Vector3f center = new Vector3f(p.x, p.y, p.z);

            float frameHeight = 1f / p.frames;
            int currentFrame;

            if (p.loop) {
                currentFrame = (int)((p.age / p.maxAge) * p.frames) % p.frames;
            } else {
                currentFrame = Math.min((int)((p.age / p.maxAge) * p.frames), p.frames - 1);
            }

            float v0 = frameHeight * currentFrame;
            float v1 = v0 + frameHeight;

            float u0 = 0f;
            float u1 = 1f;

            Vector3f c1 = new Vector3f(center).add(new Vector3f(right).mul(-half)).add(new Vector3f(up).mul(-half));
            Vector3f c2 = new Vector3f(center).add(new Vector3f(right).mul(-half)).add(new Vector3f(up).mul( half));
            Vector3f c3 = new Vector3f(center).add(new Vector3f(right).mul( half)).add(new Vector3f(up).mul( half));
            Vector3f c4 = new Vector3f(center).add(new Vector3f(right).mul( half)).add(new Vector3f(up).mul(-half));

            putVertex(consumer, model, c1, u0, v1, p);
            putVertex(consumer, model, c2, u0, v0, p);
            putVertex(consumer, model, c3, u1, v0, p);
            putVertex(consumer, model, c4, u1, v1, p);
        }
    }

    private static void putVertex(VertexConsumer consumer, Matrix4f model, Vector3f pos, float u, float v, HandParticle p) {
        consumer.vertex(model, pos.x, pos.y, pos.z)
                .color(p.r, p.g, p.b, p.a)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(p.light)
                .normal(0f, 1f, 0f);
    }
}
