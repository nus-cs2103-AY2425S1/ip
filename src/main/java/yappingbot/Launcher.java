package yappingbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Main entry point. Parses arguments and launches YappingBot appropriately.
     * Currently supported flags:
     * -c: use CLI instead of JavaFX GUI.
     * -s or --savefile [SAVEFILE_PATH]: use a custom savefile path.
     *
     * @param args Commandline arguments passed in.
     */
    public static void main(String[] args) {
        // NOTE: args DOES NOT INCLUDE FILENAME AT ARGS[0]
        boolean isUsingGui = true;
        boolean stopTakingInputs = false;
        String customSavefilePath = "";
        String[] jfxArgs = new String[0];

        for (int i = 0; i < args.length; i++) {
            if (stopTakingInputs) {
                jfxArgs = Arrays.copyOfRange(args, i + 1, args.length);
                break;
            } else {
                switch (args[i]) {
                case "-c":
                    isUsingGui = false;
                    continue;
                case "-s":
                case "--savefile":
                    int savefilePathIndex = i + 1;
                    if (savefilePathIndex >= args.length
                        || args[savefilePathIndex].startsWith("-")) {
                        System.out.printf("Error: %s missing argument: savefile path!\n", args[i]);
                    } else {
                        customSavefilePath = args[savefilePathIndex];
                    }
                    continue;
                case "--":
                    stopTakingInputs = true;
                    continue;
                default:
                    System.out.printf("Error: unknown flag %s!\n", args[i]);
                }
            }
        }

        if (isUsingGui) {
            Main.launch(customSavefilePath, jfxArgs);
        } else {
            Main.launchCli(customSavefilePath, jfxArgs);
        }
    }
}
