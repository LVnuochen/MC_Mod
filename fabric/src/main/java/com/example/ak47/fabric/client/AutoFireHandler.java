package com.example.ak47.fabric.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.example.ak47.item.AK47Item;

public class AutoFireHandler {
    private static long lastFireTick = 0;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Player player = client.player;
            if (player == null) return;

            if (client.options.keyUse.isDown()) {
                long gameTime = client.level.getGameTime();
                if (gameTime - lastFireTick < AK47Item.getFireRate()) {
                    return;
                }
                lastFireTick = gameTime;

                ItemStack mainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (mainHand.getItem() instanceof AK47Item) {
                    mainHand.use(client.level, player, InteractionHand.MAIN_HAND);
                    return;
                }

                ItemStack offhand = player.getItemInHand(InteractionHand.OFF_HAND);
                if (offhand.getItem() instanceof AK47Item) {
                    offhand.use(client.level, player, InteractionHand.OFF_HAND);
                }
            }
        });
    }
}
