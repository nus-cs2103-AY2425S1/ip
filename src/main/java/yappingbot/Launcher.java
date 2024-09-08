package yappingbot;

import java.util.Arrays;

import yappingbot.storage.Storage;
import yappingbot.ui.Main;
import yappingbot.ui.UiCli;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    static String savefilePath;

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
            launchGui(customSavefilePath, jfxArgs);
        } else {
            launchCli(customSavefilePath, jfxArgs);
        }
    }

    /**
     * Static method to launchGui Main without GUI.
     * Use as fallback method.
     *
     * @param savefilePath String path to use for savefile, or "" to use default.
     * @param args String ArrayList of arguments passed in via CLI when launching this app.
     */
    public static void launchGui(String savefilePath, String[] args) {
        Launcher.savefilePath = savefilePath.isEmpty() ? "./savefile" : savefilePath;
        Main.launch(Main.class, args);
    }

    /**
     * Static method to launchGui Main with JavaFX GUI.
     *
     * @param customSavefilePath String path to use for savefile, or "" to use default.
     * @param args String ArrayList of arguments passed in via CLI when launching this app.
     */
    public static void launchCli(String customSavefilePath, String[] args) {
        savefilePath = customSavefilePath.isEmpty() ? "./savefile" : customSavefilePath;
        YappingBot yp = new YappingBot(new UiCli(), new Storage(savefilePath));
        yp.start();
    }
}
