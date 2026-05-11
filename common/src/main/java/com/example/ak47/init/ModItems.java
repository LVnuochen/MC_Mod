package com.example.ak47.init;

import com.example.ak47.AK47Mod;
import com.example.ak47.item.AK47Item;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(AK47Mod.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> AK47 = ITEMS.register("ak47", () ->
            new AK47Item(new Item.Properties().durability(AK47Mod.MAX_AMMO))
    );

    public static void register() {
        ITEMS.register();
    }
}
