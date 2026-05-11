package com.example.ak47.init;

import com.example.ak47.AK47Mod;
import com.example.ak47.entity.AK47BulletEntity;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    private static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(AK47Mod.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<AK47BulletEntity>> AK47_BULLET = ENTITIES.register("ak47_bullet",
            () -> EntityType.Builder.<AK47BulletEntity>of(AK47BulletEntity::new, MobCategory.MISC)
                    .sized(0.1f, 0.1f)
                    .clientTrackingRange(64)
                    .updateInterval(1)
                    .fireImmune()
                    .build(AK47Mod.MOD_ID + ":ak47_bullet")
    );

    public static void register() {
        ENTITIES.register();
    }
}
