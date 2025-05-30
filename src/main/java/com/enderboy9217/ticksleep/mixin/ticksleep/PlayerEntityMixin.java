package com.enderboy9217.ticksleep.mixin.ticksleep;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    /**
     * @author EnderBoy9217
     * @reason Removing sleepTimer check from reset time
     */
    @Overwrite
    public boolean canResetTimeBySleeping() {
        return ((PlayerEntity)(Object)this).isSleeping();
    }
}
