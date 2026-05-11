package com.example.ak47.fabric;

import com.example.ak47.AK47Mod;
import com.example.ak47.init.ModEntities;
import com.example.ak47.init.ModItems;
import com.example.ak47.network.ModNetwork;

public class AK47ModImpl {
    public static void init() {
        ModItems.register();
        ModEntities.register();
        ModNetwork.register();
        AK47Mod.LOG.info("AK47Mod initialized on Fabric");
    }
}
