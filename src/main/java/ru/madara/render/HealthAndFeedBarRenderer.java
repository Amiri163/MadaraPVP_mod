package ru.madara.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.madara.Wrapper;

@Mod.EventBusSubscriber()
public class HealthAndFeedBarRenderer implements Wrapper {
    static ResourceLocation textureLocation = new ResourceLocation("minecraft", "textures/particle/heart.png");
    static ResourceLocation textureLocationFeed = new ResourceLocation("minecraft", "textures/item/cooked_beef.png");

    @SubscribeEvent
    public static void onRenderLiving(RenderLivingEvent.Post<?, ?> event) {

        if (!(event.getEntity() instanceof PlayerEntity) || event.isCanceled()) return;
        if (mc.screen != null) return;
        assert mc.player != null;
        if(event.getEntity().hasEffect(Effects.INVISIBILITY)) return;
        PlayerEntity player = (PlayerEntity) event.getEntity();

        MatrixStack matrixStack = event.getMatrixStack();

        int currentHealth = (int) Math.ceil(player.getHealth());
        int currentFeed = player.getFoodData().getFoodLevel();

        matrixStack.pushPose();
        matrixStack.translate(0, 2.6, 0);
        matrixStack.mulPose(mc.getEntityRenderDispatcher().cameraOrientation());
        matrixStack.scale(-0.025f, -0.025f, 0.025f);

        int feedWidth = fontRenderer.width(String.valueOf(currentFeed));
        int healthWidth = fontRenderer.width(String.valueOf(currentHealth));

        int totalWidth = feedWidth + healthWidth + 5;
        int startX = -totalWidth / 2;
        int texture = 11;

        int textureX = startX - 3 - texture;
        int textureXFeed = startX + healthWidth + 3;
        int textX = startX + (totalWidth - (healthWidth + feedWidth + 6)) / 2;
        int yPosition = -fontRenderer.lineHeight + 2;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();

        mc.getTextureManager().bind(textureLocation);
        AbstractGui.blit(matrixStack, textureX, yPosition, 0, 0, texture, texture, texture, texture);
        mc.getTextureManager().bind(textureLocationFeed);
        AbstractGui.blit(matrixStack, textureXFeed, yPosition, 0, 0, texture, texture, texture, texture);

        fontRenderer.drawShadow(matrixStack, String.valueOf(currentHealth), textX, yPosition, 0xDC143C);
        fontRenderer.drawShadow(matrixStack, String.valueOf(currentFeed), textX + healthWidth + 15, yPosition, 0xcd853f);

        RenderSystem.disableDepthTest();
        RenderSystem.enableDepthTest();

        matrixStack.popPose();
    }
}