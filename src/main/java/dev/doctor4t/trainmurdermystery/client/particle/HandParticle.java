package dev.doctor4t.trainmurdermystery.client.particle;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.function.Function;

public class HandParticle {
    public float x, y, z;
    public float vx, vy, vz;
    public float age;
    public float maxAge;
    public float size;
    public Identifier texture;
    public int light;
    public int frames;
    public boolean loop;
    public float u0, v0, u1, v1;
    public float r = 1f, g = 1f, b = 1f, a = 1f;


    public Function<Identifier, RenderLayer> renderLayerFactory;

    public HandParticle() {
        this.u0 = 0f; this.v0 = 0f; this.u1 = 1f; this.v1 = 1f;
        this.age = 0f;

        this.x = 0f;
        this.y = 0f;
        this.z = 0f;
        this.vx = 0f;
        this.vy = 0f;
        this.vz = 0f;

        this.loop = false;
        this.frames = 1;
        this.size = 1f;

        this.renderLayerFactory = RenderLayer::getEntityTranslucent;
    }

    public boolean tick(float dt) {
        age += dt;
        if (age >= maxAge) return false;
        x += vx * dt;
        y += vy * dt;
        z += vz * dt;
        return true;
    }

    // ----------------------------
    // Chainable setters
    // ----------------------------
    public HandParticle setPos(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public HandParticle setVelocity(float vx, float vy, float vz) {
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        return this;
    }

    public HandParticle setTexture(Identifier texture) {
        this.texture = texture;
        return this;
    }

    public HandParticle setMaxAge(float maxAge) {
        this.maxAge = maxAge + 1f;
        return this;
    }

    public HandParticle setAnimation(int frames, boolean shouldLoop) {
        this.frames = frames;
        this.loop = shouldLoop;
        return this;
    }

    public HandParticle setLight(int block, int sky) {
        block = Math.max(0, Math.min(15, block));
        sky   = Math.max(0, Math.min(15, sky));

        this.light = (sky << 20) | (block << 4);
        return this;
    }

    public HandParticle setSize(float size) {
        this.size = size;
        return this;
    }

    public HandParticle setRenderLayer(Function<Identifier, RenderLayer> factory) {
        this.renderLayerFactory = factory;
        return this;
    }

    public HandParticle setColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        return this;
    }

    public HandParticle setColor(float r, float g, float b) {
        return setColor(r, g, b, 1f);
    }

    public HandParticle setColor(int hex) {
        if ((hex & 0xFF000000) != 0) {
            this.r = ((hex >> 24) & 0xFF) / 255f;
            this.g = ((hex >> 16) & 0xFF) / 255f;
            this.b = ((hex >> 8) & 0xFF) / 255f;
            this.a = (hex & 0xFF) / 255f;
        } else {
            this.r = ((hex >> 16) & 0xFF) / 255f;
            this.g = ((hex >> 8) & 0xFF) / 255f;
            this.b = (hex & 0xFF) / 255f;
            this.a = 1f;
        }
        return this;
    }

    public HandParticle setColor(Color color) {
        this.r = color.getRed() / 255f;
        this.g = color.getGreen() / 255f;
        this.b = color.getBlue() / 255f;
        this.a = color.getAlpha() / 255f;
        return this;
    }
}