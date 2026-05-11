package com.example.ak47.network;

import com.example.ak47.AK47Mod;
import com.example.ak47.init.ModEntities;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ModNetwork {
    public static final ResourceLocation FIRE_CHANNEL =
            new ResourceLocation(AK47Mod.MOD_ID, "fire");

    public static void register() {
        NetworkManager.registerReceiver(
                NetworkManager.Side.C2S,
                FIRE_CHANNEL,
                (buf, context) -> {
                    double x = buf.readDouble();
                    double y = buf.readDouble();
                    double z = buf.readDouble();
                    double dx = buf.readDouble();
                    double dy = buf.readDouble();
                    double dz = buf.readDouble();
                    buf.readInt(); // hand (consumed)
                    context.queue(() -> {
                        Player player = context.getPlayer();
                        if (!(player instanceof ServerPlayer serverPlayer)) return;
                        Level level = serverPlayer.level();
                        if (level.isClientSide) return;

                        Vec3 dir = new Vec3(dx, dy, dz);

                        var bullet = new com.example.ak47.entity.AK47BulletEntity(
                                ModEntities.AK47_BULLET.get(), level);
                        bullet.setPos(x, y, z);
                        bullet.setOwner(serverPlayer);
                        bullet.shoot(dir.x, dir.y, dir.z, (float) AK47Mod.BULLET_SPEED, 0.0f);
                        level.addFreshEntity(bullet);
                    });
                }
        );
    }

    public static void sendFirePacket(ServerPlayer player, Vec3 spawnPos, Vec3 lookDir, InteractionHand hand) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeDouble(spawnPos.x);
        buf.writeDouble(spawnPos.y);
        buf.writeDouble(spawnPos.z);
        buf.writeDouble(lookDir.x);
        buf.writeDouble(lookDir.y);
        buf.writeDouble(lookDir.z);
        buf.writeInt(hand == InteractionHand.MAIN_HAND ? 0 : 1);
        NetworkManager.sendToPlayer(player, FIRE_CHANNEL, buf);
    }
}
