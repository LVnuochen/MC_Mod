package com.example.ak47.item;

import com.example.ak47.AK47Mod;
import com.example.ak47.entity.AK47BulletEntity;
import com.example.ak47.init.ModEntities;
import com.example.ak47.network.ModNetwork;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AK47Item extends Item {
    private long lastFireTime = 0;

    public AK47Item(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isCreative() || stack.getDamageValue() < stack.getMaxDamage()) {
            fire(level, player, hand, stack);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    public void fire(Level level, Player player, InteractionHand hand, ItemStack stack) {
        long gameTime = level.getGameTime();
        if (gameTime - lastFireTime < AK47Mod.FIRE_RATE_TICKS) {
            return;
        }
        lastFireTime = gameTime;

        Vec3 lookVec = player.getLookAngle();
        Vec3 spawnPos = new Vec3(
                player.getX() + lookVec.x * 1.5,
                player.getY() + player.getEyeHeight() * 0.9,
                player.getZ() + lookVec.z * 1.5
        );

        if (!level.isClientSide) {
            spawnBullet(level, player, spawnPos, lookVec, hand, stack);
        } else {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            writeFirePacket(buf, spawnPos, lookVec, hand);
            NetworkManager.sendToServer(ModNetwork.FIRE_CHANNEL, buf);
        }
    }

    private void spawnBullet(Level level, Player player, Vec3 spawnPos, Vec3 lookVec, InteractionHand hand, ItemStack stack) {
        AK47BulletEntity bullet = new AK47BulletEntity(ModEntities.AK47_BULLET.get(), level);
        bullet.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
        bullet.setOwner(player);
        bullet.shoot(lookVec.x, lookVec.y, lookVec.z, (float) AK47Mod.BULLET_SPEED, 0.0f);
        level.addFreshEntity(bullet);

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.playSound(SoundEvents.FIREWORK_ROCKET_SHOOT, 1.0f, 1.2f);
        }
        if (!player.isCreative()) {
            stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(hand));
        }
    }

    private static void writeFirePacket(FriendlyByteBuf buf, Vec3 spawnPos, Vec3 lookVec, InteractionHand hand) {
        buf.writeDouble(spawnPos.x);
        buf.writeDouble(spawnPos.y);
        buf.writeDouble(spawnPos.z);
        buf.writeDouble(lookVec.x);
        buf.writeDouble(lookVec.y);
        buf.writeDouble(lookVec.z);
        buf.writeInt(hand == InteractionHand.MAIN_HAND ? 0 : 1);
    }

    public static int getFireRate() {
        return AK47Mod.FIRE_RATE_TICKS;
    }
}
