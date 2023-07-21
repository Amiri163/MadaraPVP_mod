package ru.amiri.pvp_visual_display;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.renderer.texture.PotionSpriteUploader;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;

@Mod.EventBusSubscriber
public class RenderEffecrsOnScreen {
    private static final int FLASH_TICKS = 10; // Количество кадров для одного полного мигания
    private static final int FLASH_DURATION = 5 * 20; // Длительность мигания в тиках (5 секунд)

    @SubscribeEvent
    public static void onPostRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.POTION_ICONS) {
            event.setCanceled(true);
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            Minecraft mc = Minecraft.getInstance();
            assert mc.player != null;
            Collection<EffectInstance> effects = mc.player.getActiveEffects();
            FontRenderer fontRenderer = mc.font;

            int x = 100;
            int y = 100;
            int yOffset = 30;

            for (EffectInstance effectInstance : effects) {
                Effect effect = effectInstance.getEffect();
                int duration = effectInstance.getDuration();

                PotionSpriteUploader potionspriteuploader = mc.getMobEffectTextures();
                TextureAtlasSprite sprite = potionspriteuploader.get(effect);
                mc.getTextureManager().bind(sprite.atlas().location());

                if (isFlashing(duration)) {
                    float alpha = getAlphaValue(duration);
                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, alpha);
                    AbstractGui.blit(event.getMatrixStack(), x, y, 0, 18, 18, sprite);
                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                } else {
                    AbstractGui.blit(event.getMatrixStack(), x, y, 0, 18, 18, sprite);
                }

                String timeRemaining = getTimeRemaining(duration);
                int textWidth = fontRenderer.width(timeRemaining);
                int textX = x + (sprite.getWidth() - textWidth) / 2;
                int textY = y + sprite.getHeight() + 2;
                fontRenderer.drawShadow(event.getMatrixStack(), timeRemaining, textX, textY, effect.getColor());

                x += yOffset;
            }
        }
    }

    private static boolean isFlashing(int duration) {
        return duration <= FLASH_DURATION;
    }

    private static float getAlphaValue(int duration) {
        float fadeProgress = (float) (FLASH_DURATION - duration) / (float) FLASH_DURATION;
        // Используем функцию интерполяции для сглаживания анимации (ease-out функция)
        return 1.0F - (fadeProgress * fadeProgress);
    }

    private static String getTimeRemaining(int duration) {
        int seconds = duration / 20;
        int minutes = seconds / 60;
        seconds %= 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}