# VoidHearts

A client-side Fabric mod for Minecraft Java **1.21.11** that enlarges the vanilla heart HUD so detailed resource-pack heart art can be seen instead of being squeezed into the tiny default HUD.

## Features

- **2x default scale** with **1x, 2x, 3x and 4x** options.
- Press **H** in-game to cycle scales. Rebind the key in **Options → Controls**.
- Uses **whole-number scaling only**. Fractional scaling can distribute texels unevenly and make fine pixel-art detail look inconsistent.
- Anchors the health bar's right and bottom edges while scaling to help keep enlarged hearts away from the hunger bar.
- Saves the selected scale in `config/voidhearts.properties`.
- Client-only; no server mod is required.
- Leaves vanilla responsible for full, half, empty, hardcore, blinking, status-effect and absorption heart selection.

## Build

Requires Java 21 and Gradle 8.14 or compatible.

```sh
gradle build
```

The remapped mod jar is produced in `build/libs/`. GitHub Actions builds the project and uploads the jars as a workflow artifact.

## Important

VoidHearts enlarges the actual heart draw calls, allowing a high-resolution resource-pack sprite to use more on-screen pixels. It does **not** invent detail that is absent from the pack's source image, and it cannot repair blurry artwork. Try **H** to compare 2x, 3x and 4x with your pack. Higher scales use more HUD space; if a pack's sprite is only 9x9 pixels, it will not gain genuine extra texture detail just by being enlarged.
 
