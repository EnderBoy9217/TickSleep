package com.enderboy9217.ticksleep.mixin.ticksleep;

import com.enderboy9217.ticksleep.custom.interfaces.SleepManagerInterface;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.SleepManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

import static com.enderboy9217.ticksleep.EndersTicksleep.LOGGER;

@Mixin(SleepManager.class)
public abstract class SleepManagerMixin implements SleepManagerInterface {
	/**
	 * @author EnderBoy9217
	 * @reason Prevents Beds from skipping the night
	 */
	@Overwrite
	public boolean canResetTime(int percentage, List<ServerPlayerEntity> players) {
		return false;
		/* We never want time to reset
		int i = (int)players.stream().filter(PlayerEntity::canResetTimeBySleeping).count();
		return i >= ((SleepManager)(Object)this).getNightSkippingRequirement(percentage);
		 */
	}

	@Unique
	public boolean enders_ticksleep$canSkipTime(int percentage, List<ServerPlayerEntity> players) {
		int i = (int)players.stream().filter(PlayerEntity::canResetTimeBySleeping).count();
        LOGGER.info("Players: {}", i);
        LOGGER.info("Percentage: {}", ((SleepManager)(Object) this).getNightSkippingRequirement(percentage));
		return i >= ((SleepManager)(Object)this).getNightSkippingRequirement(percentage);
	}
}