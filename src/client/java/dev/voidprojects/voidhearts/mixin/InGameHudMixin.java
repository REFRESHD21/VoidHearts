package dev.voidprojects.voidhearts.mixin;

import dev.voidprojects.voidhearts.HeartTextureResolver;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
abstract class InGameHudMixin {
    @Redirect(
        method = "drawHeart",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/hud/InGameHud$HeartType;getTexture(ZZZ)Lnet/minecraft/util/Identifier;"
        )
    )
    private Identifier voidhearts$useHighDensityTexture(
        @Coerce Object heartType,
        boolean hardcore,
        boolean half,
        boolean blinking
    ) {
        Identifier vanilla = ((InGameHud.HeartTypeAccessor) (Object) heartType)
            .voidhearts$getTexture(hardcore, half, blinking);
        return HeartTextureResolver.resolve(heartType, hardcore, half, blinking, vanilla);
    }
}
