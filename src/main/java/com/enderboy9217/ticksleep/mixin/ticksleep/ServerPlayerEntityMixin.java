package com.enderboy9217.ticksleep.mixin.ticksleep;

import com.enderboy9217.ticksleep.custom.TickSleepController;
import com.enderboy9217.ticksleep.custom.interfaces.ServerWorldInterface;
import com.mojang.datafixers.util.Either;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.enderboy9217.ticksleep.EndersTicksleep.LOGGER;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

	// Will cause multiplayer to skip when only one person sleeps.
	@Inject(method = "trySleep", at = @At("TAIL"))
	private void onTrySleep(BlockPos pos, CallbackInfoReturnable<Either<PlayerEntity.SleepFailureReason, Unit>> cir) {
		ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
		ServerWorld world = player.getServerWorld();
		ServerWorldInterface accessor = (ServerWorldInterface)world;
		if ( accessor.enders_ticksleep$canSkipTime() ) {
			TickSleepController.setSpeedup(true);
			LOGGER.info("Enough Players asleep, speeding up tickrate - EnderBoy9217's Time Sleep"); // This mod may cause issues with others, so we log behavior
		}
	}


	@Inject(method = "wakeUp", at = @At("TAIL"))
	private void onWakeUp(boolean sleepTimerExpired, boolean updateSleepingPlayers, CallbackInfo ci) {
		TickSleepController.setSpeedup(false);
		LOGGER.info("Player woke up, slowing down tickrate - EnderBoy9217's Time Sleep");
	}
}