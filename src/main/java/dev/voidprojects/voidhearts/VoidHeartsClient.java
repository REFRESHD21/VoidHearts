package dev.voidprojects.voidhearts;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

/** Client-only entry point for VoidHearts. */
public final class VoidHeartsClient implements ClientModInitializer {
    private static KeyBinding toggleKey;
    private static boolean enhancedRenderingEnabled = true;

    @Override
    public void onInitializeClient() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.voidhearts.toggle", InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN, KeyBinding.Category.MISC));
        ClientLifecycleEvents.CLIENT_STARTED.register(client ->
                client.getToastManager().add(new net.minecraft.client.toast.SystemToast(
                        net.minecraft.client.toast.SystemToast.Type.PERIODIC_NOTIFICATION,
                        net.minecraft.text.Text.literal("VoidHearts loaded"),
                        net.minecraft.text.Text.literal("Rendering improvements are in development"))));
    }

    public static boolean isEnhancedRenderingEnabled() { return enhancedRenderingEnabled; }
}
