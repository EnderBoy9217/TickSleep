package com.enderboy9217.ticksleep.custom;

public class TickSleepController {
    private static boolean speedup = false;

    public static void setSpeedup(boolean value) {
        speedup = value;
    }

    public static boolean isSpeedup() {
        return speedup;
    }

    public static long getTickDurationNs() {
        return speedup ? (1_000_000_000L / 2000) : (1_000_000_000L / 20); // The divided by is the TPS
    }
}