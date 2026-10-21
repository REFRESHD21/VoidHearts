package dev.voidprojects.voidhearts;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

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
    private static KeyBinding cycleScaleKey;

    @Override
    public void onInitializeClient() {
        loadConfig();

        cycleScaleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.voidhearts.cycle_scale",
                InputUtil.Type.KEYSYM,
                InputUtil.GLFW_KEY_H,
                KeyBinding.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (cycleScaleKey.wasPressed()) {
                cycleScale();
                saveConfig();
                if (client.player != null) {
                    client.player.sendMessage(
                            Text.literal("VoidHearts scale: " + formatScale(heartScale)),
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
