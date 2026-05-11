package com.example.ak47.fabric;

import com.example.ak47.AK47Mod;
import net.fabricmc.api.ModInitializer;

public class FabricAK47Mod implements ModInitializer {
    @Override
    public void onInitialize() {
        AK47Mod.init();
    }
}
