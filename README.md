# VoidHearts

Client-side Fabric mod for Minecraft Java Edition 1.21.11. The goal is to make high-resolution heart texture packs look cleaner and more readable in the HUD without replacing the user's art or changing health mechanics.

## Current status

**Prototype / not ready to install.** The repository now has the Fabric project structure and an automated build workflow. The custom HUD rendering implementation and in-game validation are still in progress. Do not treat the current artifact as a finished visual fix.

## Build

Requires Java 21. Run `gradle build` (or `./gradlew build` after the workflow generates the Gradle wrapper). Output JARs are in `build/libs/`. GitHub Actions uploads build artifacts when the workflow succeeds.

## Planned behaviour

- Improve display of high-resolution heart sprites at HUD scale.
- Preserve vanilla full, half, empty, hardcore, absorption, and blinking/damage states.
- Keep the mod client-only and compatible with resource packs.
- Provide an option to enable/disable the rendering adjustment.

## Repository

https://github.com/REFRESHD21/VoidHearts

## License

MIT
