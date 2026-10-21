# VoidHearts

A client-side Fabric mod for Minecraft Java **1.21.11** focused on keeping the vanilla heart HUD size while avoiding the incorrect whole-health-bar scaling used in early builds.

## What this version does

- Keeps vanilla heart HUD dimensions; hearts are **not enlarged**.
- Removes the old H-to-cycle-scale control because it only enlarged the HUD and did not increase texture resolution.
- Leaves vanilla responsible for heart state selection: full, half, empty, hardcore, blinking, status-effect and absorption hearts.
- Client-only; no server mod is required.

## High-resolution texture packs

Minecraft resource packs can supply higher-resolution heart sprite artwork, but the HUD still draws those sprites into the same small screen area. A higher-resolution source can preserve smoother artwork and more source detail for sampling, but it cannot display more independent detail than the final screen pixels allow. VoidHearts 0.3.0 does not claim to bypass the monitor's physical pixel limit.

For truly finer visible detail at the same apparent heart size, the mod needs a custom render path (for example, controlled supersampling and downsampling). This release removes the incorrect scaling implementation rather than pretending that enlarging hearts increases their resolution.

## Build

Requires Java 21 and Gradle 8.14 or compatible.

```sh
gradle build
```

The remapped mod jar is produced in `build/libs/`. GitHub Actions builds the project and uploads jars as a workflow artifact.
