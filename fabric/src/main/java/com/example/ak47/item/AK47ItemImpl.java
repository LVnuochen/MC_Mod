package com.example.ak47.item;

import com.example.ak47.AK47Mod;

public class AK47ItemImpl {
    public static void registerAutoFire() {
        com.example.ak47.fabric.client.AutoFireHandler.register();
        AK47Mod.LOG.info("AK-47 auto-fire registered on Fabric");
    }
}
