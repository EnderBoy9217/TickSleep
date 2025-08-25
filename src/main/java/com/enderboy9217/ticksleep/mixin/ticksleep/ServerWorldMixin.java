package com.enderboy9217.ticksleep.mixin.ticksleep;

import com.enderboy9217.ticksleep.config.Configmenu;
import com.enderboy9217.ticksleep.custom.TickSleepController;
import com.enderboy9217.ticksleep.custom.interfaces.ServerWorldInterface;
import com.enderboy9217.ticksleep.custom.interfaces.SleepManagerInterface;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.SleepManager;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin implements ServerWorldInterface {
    @Final
    @Shadow
    List<ServerPlayerEntity> players;

    @Final
    @Shadow
    private SleepManager sleepManager;

    @ModifyConstant(method = "tickTime", constant = @Constant(longValue = 1L))
    private long shouldAddTime(long original) {
        return TickSleepController.getSpeedup() ? 0L : 1L;
    }

    @Inject(method = "tickTime", at= @At("TAIL"))
    private void skipTime(CallbackInfo ci) {
        increaseTimeOfDay(Configmenu.extraTicks);
    }

    @Unique
    @Override
    public boolean enders_ticksleep$canSkipTime() {

        int percentage = ((World)(Object)this).getGameRules().getInt(GameRules.PLAYERS_SLEEPING_PERCENTAGE);
        SleepManager sleepManager = this.sleepManager;
        SleepManagerInterface accessor = (SleepManagerInterface)sleepManager;
        return (this.sleepManager.canSkipNight(percentage) && accessor.enders_ticksleep$canSkipTime(percentage, this.players));
    }

    @Shadow
    public abstract void setTimeOfDay(long timeOfDay);

    @Unique
    public void increaseTimeOfDay(long value) {
        this.setTimeOfDay( ((ServerWorld)(Object)this).getTimeOfDay() + value);
    }
}