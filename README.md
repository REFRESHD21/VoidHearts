# VoidHearts

A client-side Fabric mod for Minecraft Java **1.21.11** that lets high-resolution heart resource-pack textures render at a larger, configurable HUD size.

## Current implementation

- Mixes into Minecraft 1.21.11's `InGameHud.renderHealthBar` so the complete health bar is scaled together; heart spacing, rows, and vanilla heart states stay under vanilla's renderer.
- Defaults to **1.5x**. Press **H** in-game to cycle through **1x, 1.25x, 1.5x, 1.75x, and 2x**. The key can be rebound in Options → Controls.
- Saves the chosen scale to `config/voidhearts.properties`.
- Client-only; no server plugin or server install required.
- Keeps vanilla's full/half/empty, hardcore, blinking, poison/wither, and absorption sprite selection by letting vanilla draw the sprites.

## Build

Requires Java 21 and Gradle 8.14 or compatible.

```sh
gradle build
```

The remapped mod jar is produced in `build/libs/`. GitHub Actions builds the project and uploads the jars as a workflow artifact.

## Notes

This changes the rendered size of the vanilla health bar, not the source artwork in your resource pack. A texture pack's quality still depends on its own sprite dimensions and design. Test different sizes with your pack and GUI scale; larger hearts can use more HUD space.

Repository: https://github.com/REFRESHD21/VoidHearts
