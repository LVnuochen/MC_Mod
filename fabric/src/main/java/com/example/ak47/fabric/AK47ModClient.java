package com.example.ak47.fabric;

import com.example.ak47.AK47Mod;
import com.example.ak47.entity.client.AK47BulletRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class AK47ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(
                com.example.ak47.init.ModEntities.AK47_BULLET.get(),
                context -> new AK47BulletRenderer(context)
        );
        com.example.ak47.item.AK47ItemImpl.registerAutoFire();
        AK47Mod.LOG.info("AK-47 client initialized on Fabric");
    }
}
