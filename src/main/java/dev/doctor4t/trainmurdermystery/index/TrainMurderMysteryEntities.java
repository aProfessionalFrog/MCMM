package dev.doctor4t.trainmurdermystery.index;

import dev.doctor4t.ratatouille.util.registrar.EntityTypeRegistrar;
import dev.doctor4t.trainmurdermystery.TrainMurderMystery;
import dev.doctor4t.trainmurdermystery.block.entity.SeatEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public interface TrainMurderMysteryEntities {
    EntityTypeRegistrar registrar = new EntityTypeRegistrar(TrainMurderMystery.MOD_ID);

    EntityType<SeatEntity> SEAT = registrar.create("seat", EntityType.Builder.create(SeatEntity::new, SpawnGroup.MISC)
            .dimensions(1f, 1f)
            .maxTrackingRange(128)
            .disableSummon()
    );

    static void initialize() {
        registrar.registerEntries();
    }
}
