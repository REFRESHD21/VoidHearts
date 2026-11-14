package dev.voidprojects.voidhearts;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Properties;

/**
 * Selects optional high-resolution HUD sprites. Textures are still drawn into
 * vanilla's 9x9 GUI-unit heart slot; the selected density is the source-art tier.
 */
public final class HeartTextureResolver {
    private static final Properties CONFIG = new Properties();
    private static int density = 4;

    private HeartTextureResolver() {}

    public static void loadConfig() {
        Path file = MinecraftClient.getInstance().runDirectory.toPath()
            .resolve("config").resolve("voidhearts.properties");
        try {
            Files.createDirectories(file.getParent());
            if (Files.notExists(file)) {
                CONFIG.setProperty("density", "4");
                try (var out = Files.newOutputStream(file)) {
                    CONFIG.store(out, "VoidHearts source texture density: 1, 2, 4, or 8");
                }
            } else {
                try (InputStream in = Files.newInputStream(file)) {
                    CONFIG.load(in);
                }
            }
            int requested = Integer.parseInt(CONFIG.getProperty("density", "4").trim());
            density = switch (requested) {
                case 1, 2, 4, 8 -> requested;
                default -> 4;
            };
        } catch (IOException | NumberFormatException ignored) {
            density = 4;
        }
    }

    public static Identifier resolve(Object type, boolean hardcore, boolean half,
                                     boolean blinking, Identifier vanilla) {
        String kind = type instanceof Enum<?> e
            ? e.name().toLowerCase(Locale.ROOT) : "normal";
        String state = half ? "half" : "full";
        if (kind.equals("container")) state = "container";
        if (blinking) state += "_blinking";
        if (hardcore && !kind.equals("container")) state = "hardcore_" + state;

        Identifier candidate = Identifier.of("voidhearts",
            "textures/gui/heart/" + density + "x/" + kind + "_" + state + ".png");
        return MinecraftClient.getInstance().getResourceManager()
            .getResource(candidate).isPresent() ? candidate : vanilla;
    }
}
