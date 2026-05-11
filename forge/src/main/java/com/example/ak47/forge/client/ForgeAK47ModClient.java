package com.example.ak47.forge.client;

import com.example.ak47.AK47Mod;
import com.example.ak47.init.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(modid = AK47Mod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeAK47ModClient {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.AK47_BULLET.get(),
                context -> new ForgeBulletRenderer(context));
        AK47Mod.LOG.info("AK-47 entity renderer registered on Forge");
    }
}
