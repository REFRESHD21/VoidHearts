# VoidHearts

Client-side Fabric mod for Minecraft Java Edition 1.21.11. Goal: improve how high-resolution heart texture packs appear in the HUD while retaining vanilla health states and animations.

## Status
Early development scaffold. The initial commit sets up the Fabric project and CI; the custom heart-rendering hook and in-game validation are not yet complete. Do not treat this as a finished rendering fix.

## Build
Requires Java 21. Run `gradle build`; output JARs appear in `build/libs/`. GitHub Actions builds the project and uploads JARs as workflow artifacts.

## Install
Once validated, install Fabric Loader for Minecraft 1.21.11 and place `voidhearts-*.jar` in `.minecraft/mods`. Client-side only.

## Goals
- Crisp high-resolution heart resource-pack textures at normal HUD sizes.
- Preserve full, half, empty, hardcore, absorption, and animated states.
- Avoid changing unrelated GUI textures.

Repository: https://github.com/REFRESHD21/VoidHearts

License: MIT
