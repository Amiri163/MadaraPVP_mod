package ru.amiri;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import ru.amiri.config.ModConfigMy;

@Mod("amiri")
public class Madara {
    public static final String MOD_ID = "amiri";
    public Madara() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigMy.CONFIG);

    }
}
