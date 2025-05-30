package com.enderboy9217.ticksleep.custom.interfaces;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public interface SleepManagerInterface {
    boolean enders_ticksleep$canSkipTime(int percentage, List<ServerPlayerEntity> players);
}
