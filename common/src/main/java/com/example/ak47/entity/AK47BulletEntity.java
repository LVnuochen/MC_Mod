package com.example.ak47.entity;

import com.example.ak47.AK47Mod;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class AK47BulletEntity extends ThrowableProjectile {
    private int ticksAlive = 0;

    public AK47BulletEntity(EntityType<? extends AK47BulletEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    public void tick() {
        super.tick();
        ticksAlive++;

        if (this.level().isClientSide) {
            this.level().addParticle(
                    ParticleTypes.SMOKE,
                    this.getX(), this.getY(), this.getZ(),
                    0.0, 0.0, 0.0
            );
        }

        if (ticksAlive > AK47Mod.BULLET_RANGE) {
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if (!this.level().isClientSide) {
            if (result.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHit = (EntityHitResult) result;
                Entity entity = entityHit.getEntity();
                if (entity instanceof LivingEntity living) {
                    Entity owner = this.getOwner();
                    living.hurt(
                            this.level().damageSources().mobProjectile(this, owner instanceof LivingEntity le ? le : null),
                            AK47Mod.BULLET_DAMAGE
                    );
                }
            }
            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.SMOKE,
                        this.getX(), this.getY(), this.getZ(), 5, 0.1, 0.1, 0.1, 0.0);
            }
            this.discard();
        }
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        Entity owner = this.getOwner();
        if (owner != null) {
            if (entity == owner) return false;
            if (owner instanceof Player && entity instanceof Player) {
                return false;
            }
        }
        return super.canHitEntity(entity);
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}
