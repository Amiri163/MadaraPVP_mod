package ru.amiri.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import ru.amiri.Madara;

public class CollorSettingsScreen extends AbstractScreen {
    FontRenderer fontRender = Minecraft.getInstance().font;
    private static final ResourceLocation LOLOLOSHKA = new ResourceLocation(Madara.MOD_ID, "textures/gui/lololoshka.jpg");
    public CollorSettingsScreen(ITextComponent p_i51108_1_) {
        super(p_i51108_1_);
    }
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.renderBackground(matrixStack);

        int textureWidth = 140;
        int textureHeight = 150;
        int x = (this.width - textureWidth) / 2;
        int y = (this.height - textureHeight) / 2;

        Minecraft.getInstance().textureManager.bind(LOLOLOSHKA);

        blit(matrixStack, x, y, 0, 0, 140, 150);
        fontRender.drawShadow(matrixStack, "Ололлошка", x, y, 0x40cfff);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }


}