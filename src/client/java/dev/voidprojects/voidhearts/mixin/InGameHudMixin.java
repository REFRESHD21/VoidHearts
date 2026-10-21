package dev.voidprojects.voidhearts.mixin;

import dev.voidprojects.voidhearts.VoidHeartsClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import org.joml.Matrix3x2fStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
abstract class InGameHudMixin {
    // Keep the right and bottom edges anchored so larger hearts grow away from the hunger bar.
    @Inject(method = "renderHealthBar", at = @At("HEAD"))
    private void voidhearts$scaleHealthBar(
            DrawContext context,
            PlayerEntity player,
            int x,
            int y,
            int lines,
            int regeneratingHeartIndex,
            float maxHealth,
            int lastHealth,
            int health,
            int absorption,
            boolean blinking,
            CallbackInfo ci
    ) {
        float scale = VoidHeartsClient.getHeartScale();
        Matrix3x2fStack matrices = context.getMatrices();
        matrices.pushMatrix();
        matrices.translate((float) (x + 91), (float) (y + lines * 10));
        matrices.scale(scale, scale);
        matrices.translate((float) -(x + 91), (float) -(y + lines * 10));
    }

    @Inject(method = "renderHealthBar", at = @At("RETURN"))
    private void voidhearts$restoreHealthBarMatrix(
            DrawContext context,
            PlayerEntity player,
            int x,
            int y,
            int lines,
            int regeneratingHeartIndex,
            float maxHealth,
            int lastHealth,
            int health,
            int absorption,
            boolean blinking,
            CallbackInfo ci
    ) {
        context.getMatrices().popMatrix();
    }
}
