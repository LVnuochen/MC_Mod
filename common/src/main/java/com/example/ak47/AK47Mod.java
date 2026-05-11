package com.example.ak47;

import com.example.ak47.init.ModEntities;
import com.example.ak47.init.ModItems;
import com.example.ak47.network.ModNetwork;
import dev.architectury.injectables.annotations.ExpectPlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AK47Mod {
    public static final String MOD_ID = "ak47-mod";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    public static final int FIRE_RATE_TICKS = 2;
    public static final int MAX_AMMO = 512;
    public static final float BULLET_DAMAGE = 8.0f;
    public static final double BULLET_SPEED = 3.0;
    public static final int BULLET_RANGE = 80;

    @ExpectPlatform
    public static void init() {
        throw new AssertionError("Should be implemented in platform-specific module!");
    }
}
