# VoidHearts

A client-side Fabric mod for Minecraft Java **1.21.11** that renders the vanilla heart HUD at a larger configurable size so detailed resource-pack heart art is easier to see.

## Features

- **2x default size** for high-resolution heart textures.
- Press **H** in-game to cycle through **1x, 1.5x, 2x, and 3x**. Rebind the key in Options → Controls.
- Whole-number scales are included for more consistent pixel-art edges; 1.5x is available if you prefer a smaller HUD.
- Saves your selection in `config/voidhearts.properties`.
- Client-only; no server mod is required.
- Vanilla remains responsible for full, half, empty, hardcore, blinking, status-effect and absorption heart selection.

## Build

Requires Java 21 and Gradle 8.14 or compatible.

```sh
gradle build
```

The remapped mod jar is produced in `build/libs/`. GitHub Actions builds the project and uploads the jars as a workflow artifact.

## Important

This scales the vanilla heart HUD; it does not redraw or repair a poorly authored resource-pack texture. If the artwork itself is blurry, check the pack's heart sprites and texture filtering. Larger HUD sizes can also take up more screen space, especially at 3x.

Repository: https://github.com/REFRESHD21/VoidHearts
