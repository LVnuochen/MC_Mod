package com.example.ak47.forge.client;

import com.example.ak47.AK47Mod;
import com.example.ak47.entity.AK47BulletEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ForgeBulletRenderer extends EntityRenderer<AK47BulletEntity> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(AK47Mod.MOD_ID, "textures/entity/ak47_bullet.png");

    public ForgeBulletRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(AK47BulletEntity entity) {
        return TEXTURE;
    }
}
