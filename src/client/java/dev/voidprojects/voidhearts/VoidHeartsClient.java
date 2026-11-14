package dev.voidprojects.voidhearts;

import net.fabricmc.api.ClientModInitializer;

public final class VoidHeartsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HeartTextureResolver.loadConfig();
    }
}
