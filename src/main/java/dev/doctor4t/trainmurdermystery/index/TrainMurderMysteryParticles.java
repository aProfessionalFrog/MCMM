package dev.doctor4t.trainmurdermystery.index;

import dev.doctor4t.ratatouille.util.registrar.ParticleTypeRegistrar;
import dev.doctor4t.trainmurdermystery.TrainMurderMystery;
import dev.doctor4t.trainmurdermystery.client.particle.SnowflakeParticle;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;

public interface TrainMurderMysteryParticles {
    ParticleTypeRegistrar registrar = new ParticleTypeRegistrar(TrainMurderMystery.MOD_ID);

    SimpleParticleType SNOWFLAKE = (SimpleParticleType) registrar.create("snowflake", FabricParticleTypes.simple(true));

    static void initialize() {
        registrar.registerEntries();
    }

    static void registerFactories() {
        ParticleFactoryRegistry.getInstance().register(SNOWFLAKE, SnowflakeParticle.Factory::new);
    }
}
