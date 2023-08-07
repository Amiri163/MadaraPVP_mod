package ru.madara;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import ru.madara.common.Lang;
import ru.madara.common.font.StyledFont;

public interface Wrapper {
    StyledFont font = new StyledFont("FTR.ttf", 35, 0.0f, 2.0f, 0.5f, Lang.ENG_RU);
    String FONT_DIR = "/assets/" + Madara.MOD_ID + "/font/";
    Minecraft mc = Minecraft.getInstance();
    PlayerEntity playerEntity = mc.player;
    FontRenderer fontRenderer = mc.font;

}