package dev.voidprojects.voidhearts;

import net.fabricmc.api.ClientModInitializer;

/**
 * VoidHearts deliberately leaves vanilla HUD geometry untouched. High-resolution
 * heart sprites supplied by resource packs are rendered at vanilla HUD dimensions;
 * scaling the health-bar matrix would only make the hearts larger.
 */
public final class VoidHeartsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // The vanilla HUD already resolves heart sprites through the active resource pack.
        // No HUD transform is applied, preserving the normal on-screen heart size.
    }
}
