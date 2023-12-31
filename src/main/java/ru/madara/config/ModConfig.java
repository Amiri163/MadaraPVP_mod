package ru.madara.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class ModConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;
    public static ForgeConfigSpec.BooleanValue renderEffectsConfig, renderArmorConfig, test;


    static {
        BUILDER.comment("General settings").push("general");

        renderEffectsConfig = BUILDER
                .comment("Enable or disable effects rendering")
                .define("render_effects", true);
        renderArmorConfig = BUILDER
                .comment("Enable or disable armors rendering")
                .define("armors_render", true);

        BUILDER.pop();

        CONFIG = BUILDER.build();
    }
    public static void saveConfig() {
        CONFIG.save();
    }
}
