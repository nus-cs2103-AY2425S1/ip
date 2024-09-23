Add Continuous Integration (CI) Support and Cross-Platform JavaFX Setup

- Added GitHub Actions CI workflow (`ci.yml`) to automate builds and tests.
  - The workflow runs on different OS environments: Windows, macOS, and Linux.
  - Ensures consistent testing across platforms using JavaFX.
  - Included job steps for setting up JavaFX SDK and Gradle for each OS.
  
- Updated `build.gradle` to support cross-platform JavaFX compilation.
  - Added detection logic for OS to set the correct JavaFX SDK path (`javaFxSdkPath`) based on the system’s operating system (Windows, macOS, Linux).
  - Configured Gradle to include JavaFX modules during both compile and runtime phases.
  - Ensured compatibility by adjusting `jvmArgs` and `compilerArgs` to include JavaFX modules dynamically based on the OS.
  - Enhanced `shadowJar` configuration to package the JAR with the necessary JavaFX runtime dependencies.
  
This setup enables CI to run tests on various platforms, ensuring that the project works across Windows, macOS, and Linux, and that JavaFX-based applications are tested properly.

I used AI to dynamicallyy set JavaFX paths based on the operating system, to ensure it works across paltforms.