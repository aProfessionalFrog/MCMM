package dev.doctor4t.trainmurdermystery.index;

import dev.doctor4t.ratatouille.util.registrar.BlockEntityTypeRegistrar;
import dev.doctor4t.trainmurdermystery.TrainMurderMystery;
import dev.doctor4t.trainmurdermystery.block_entity.SmallDoorBlockEntity;
import dev.doctor4t.trainmurdermystery.block_entity.SprinklerBlockEntity;
import net.minecraft.block.entity.BlockEntityType;

public interface TrainMurderMysteryBlockEntities {
    BlockEntityTypeRegistrar registrar = new BlockEntityTypeRegistrar(TrainMurderMystery.MOD_ID);

    BlockEntityType<SprinklerBlockEntity> SPRINKLER = registrar.create("sprinkler", BlockEntityType.Builder.create(SprinklerBlockEntity::new, TrainMurderMysteryBlocks.GOLD_SPRINKLER, TrainMurderMysteryBlocks.STAINLESS_STEEL_SPRINKLER));
    BlockEntityType<SmallDoorBlockEntity> SMALL_GLASS_DOOR = registrar.create("small_glass_door", BlockEntityType.Builder.create(SmallDoorBlockEntity::createGlass, TrainMurderMysteryBlocks.SMALL_GLASS_DOOR));
    BlockEntityType<SmallDoorBlockEntity> SMALL_WOOD_DOOR = registrar.create("small_wood_door", BlockEntityType.Builder.create(SmallDoorBlockEntity::createWood, TrainMurderMysteryBlocks.SMALL_WOOD_DOOR));

    static void initialize() {
        registrar.registerEntries();
    }
}
