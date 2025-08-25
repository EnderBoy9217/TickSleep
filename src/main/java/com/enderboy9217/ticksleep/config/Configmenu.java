package com.enderboy9217.ticksleep.config;

import com.terraformersmc.modmenu.api.*;
import me.shedaniel.clothconfig2.api.*;
import net.minecraft.text.Text;

public class Configmenu implements ModMenuApi {

    public static int tps = 2000;
    public static long extraTicks = 4L;

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("com.enderboy9217.config"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            // General
            ConfigCategory general = builder.getOrCreateCategory(Text.translatable("com.enderboy9217.config.general"));
            general.addEntry(entryBuilder.startIntField(Text.translatable("com.enderboy9217.config.tps"), 2000)
                    .setDefaultValue(2000)
                    .setTooltip(Text.translatable("com.enderboy9217.config.tps.tooltip"))
                    .setSaveConsumer(newValue -> tps = newValue)
                    .build()
            );
            general.addEntry(entryBuilder.startLongField(Text.translatable("com.enderboy9217.config.extraticks"), 4L )
                    .setDefaultValue(4L)
                    .setTooltip(Text.translatable("com.enderboy9217.config.extraticks.tooltip"))
                    .setSaveConsumer(newValue -> extraTicks = newValue)
                    .build()
            );


            return builder.build();
        };
    }
}
