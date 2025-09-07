package dev.doctor4t.trainmurdermystery.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.dimension.DimensionOptions;

import java.util.concurrent.CompletableFuture;

public class TrainMurderMysteryDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        // this is so that the dimension options can actually generate
        DynamicRegistries.register(RegistryKeys.DIMENSION, DimensionOptions.CODEC);

        FabricDataGenerator.Pack pack = dataGenerator.createPack();
        pack.addProvider(TrainMurderMysteryModelGen::new);
        pack.addProvider(TrainMurderMysteryBlockTagGen::new);
        pack.addProvider(TrainMurderMysteryLangGen::new);
        pack.addProvider(TrainMurderMysteryBlockLootTableGen::new);
    }
}
