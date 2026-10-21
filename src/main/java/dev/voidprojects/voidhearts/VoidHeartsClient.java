package dev.voidprojects.voidhearts;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class VoidHeartsClient implements ClientModInitializer {
    private static final float[] SCALE_STEPS = {1.0F, 1.25F, 1.5F, 1.75F, 2.0F};
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("voidhearts.properties");
    private static volatile float heartScale = 1.5F;
    private static KeyMapping cycleScaleKey;

    @Override
    public void onInitializeClient() {
        loadConfig();

        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath("voidhearts", "controls")
        );
        cycleScaleKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.voidhearts.cycle_scale",
                InputConstants.Type.KEYSYM,
                InputConstants.KEY_H,
                category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (cycleScaleKey.consumeClick()) {
                cycleScale();
                saveConfig();
                if (client.player != null) {
                    client.player.displayClientMessage(
                            Component.literal("VoidHearts scale: " + formatScale(heartScale)),
                            true
                    );
                }
            }
        });
    }

    public static float getHeartScale() {
        return heartScale;
    }

    private static void cycleScale() {
        int next = 0;
        for (int i = 0; i < SCALE_STEPS.length; i++) {
            if (Math.abs(SCALE_STEPS[i] - heartScale) < 0.001F) {
                next = (i + 1) % SCALE_STEPS.length;
                break;
            }
        }
        heartScale = SCALE_STEPS[next];
    }

    private static void loadConfig() {
        if (!Files.isRegularFile(CONFIG_PATH)) {
            return;
        }
        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(CONFIG_PATH)) {
            properties.load(input);
            float configured = Float.parseFloat(properties.getProperty("heartScale", "1.5"));
            for (float step : SCALE_STEPS) {
                if (Math.abs(step - configured) < 0.001F) {
                    heartScale = step;
                    return;
                }
            }
        } catch (IOException | NumberFormatException ignored) {
            heartScale = 1.5F;
        }
    }

    private static void saveConfig() {
        Properties properties = new Properties();
        properties.setProperty("heartScale", Float.toString(heartScale));
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (OutputStream output = Files.newOutputStream(CONFIG_PATH)) {
                properties.store(output, "VoidHearts client settings");
            }
        } catch (IOException ignored) {
            // The current session still works if the config cannot be written.
        }
    }

    private static String formatScale(float scale) {
        return (scale == (int) scale ? Integer.toString((int) scale) : Float.toString(scale)) + "x";
    }
}
