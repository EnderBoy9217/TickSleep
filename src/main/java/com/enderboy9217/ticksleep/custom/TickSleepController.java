package com.enderboy9217.ticksleep.custom;

import com.enderboy9217.ticksleep.config.Configmenu;

public class TickSleepController {
    private static boolean speedup = false;

    public static void setSpeedup(boolean value) {
        speedup = value;
    }

    public static boolean getSpeedup() {
        return speedup;
    }

    public static long getTickDurationNs() {
        return speedup ? (1_000_000_000L / Configmenu.tps) : (1_000_000_000L / 20); // The divided by is the TPS
    }
}