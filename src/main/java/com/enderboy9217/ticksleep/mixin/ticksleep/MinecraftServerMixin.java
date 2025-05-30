package com.enderboy9217.ticksleep.mixin.ticksleep;

import com.enderboy9217.ticksleep.custom.TickSleepController;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

	@ModifyConstant(method = "runServer", constant = @Constant(longValue = 50L))
	private long modifyTickDuration(long original) {
		// Convert nanoseconds to milliseconds (1ms = 1,000,000ns)
		long customTickDurationMs = TickSleepController.getTickDurationNs() / 1_000_000;
		// Ensure the tick duration is at least 1ms to avoid breaking the server
		return Math.max(1, customTickDurationMs);
	}
}