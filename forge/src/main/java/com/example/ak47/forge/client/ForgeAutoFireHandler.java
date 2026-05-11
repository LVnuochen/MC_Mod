package com.example.ak47.forge.client;

import com.example.ak47.AK47Mod;
import com.example.ak47.item.AK47Item;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AK47Mod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeAutoFireHandler {
    private static long lastFireTick = 0;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        if (mc.options.keyUse.isDown()) {
            long gameTime = player.level().getGameTime();
            if (gameTime - lastFireTick < AK47Mod.FIRE_RATE_TICKS) {
                return;
            }
            lastFireTick = gameTime;

            ItemStack mainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
            if (mainHand.getItem() instanceof AK47Item) {
                player.use(mainHand, InteractionHand.MAIN_HAND);
                return;
            }

            ItemStack offhand = player.getItemInHand(InteractionHand.OFF_HAND);
            if (offhand.getItem() instanceof AK47Item) {
                player.use(offhand, InteractionHand.OFF_HAND);
            }
        }
    }
}
