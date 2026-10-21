package dev.voidprojects.voidhearts;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

/** Client-only bootstrap for VoidHearts. Rendering hooks are added in follow-up commits. */
public final class VoidHeartsClient implements ClientModInitializer {
    private static boolean enhancedRenderingEnabled = true;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Reserved for client-side settings/keybind handling.
        });
    }

    public static boolean isEnhancedRenderingEnabled() {
        return enhancedRenderingEnabled;
    }

    public static void setEnhancedRenderingEnabled(boolean enabled) {
        enhancedRenderingEnabled = enabled;
    }
}
