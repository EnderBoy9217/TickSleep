package com.enderboy9217.ticksleep.mixin.ticksleep;

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

import java.util.List;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin implements ServerWorldInterface {
    @Final
    @Shadow
    List<ServerPlayerEntity> players;

    @Final
    @Shadow
    private SleepManager sleepManager;

    @Unique
    @Override
    public boolean enders_ticksleep$canSkipTime() {

        int percentage = ((World)(Object)this).getGameRules().getInt(GameRules.PLAYERS_SLEEPING_PERCENTAGE);
        SleepManager sleepManager = this.sleepManager;
        SleepManagerInterface accessor = (SleepManagerInterface)sleepManager;
        return (this.sleepManager.canSkipNight(percentage) && accessor.enders_ticksleep$canSkipTime(percentage, this.players));
    }
}