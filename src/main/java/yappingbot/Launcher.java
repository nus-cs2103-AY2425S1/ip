package yappingbot;

import java.util.Arrays;

import yappingbot.storage.Storage;
import yappingbot.ui.gui.MainGuiApplication;
import yappingbot.ui.UiCli;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    public static String savefilePath;

    /**
     * MainGuiApplication entry point. Parses arguments and launches YappingBot appropriately.
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
                        i = i + 1;
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

        Launcher.savefilePath = customSavefilePath.isEmpty() ? "./savefile" : customSavefilePath;
        if (isUsingGui) {
            launchGui(jfxArgs);
        } else {
            launchCli(jfxArgs);
        }
    }

    /**
     * Static method to launchGui MainGuiApplication without GUI.
     * Use as fallback method.
     *
     * @param args String ArrayList of arguments passed in via CLI when launching this app.
     */
    public static void launchGui(String[] args) {
        MainGuiApplication.launch(MainGuiApplication.class, args);
    }

    /**
     * Static method to launchGui MainGuiApplication with JavaFX GUI.
     *
     * @param args String ArrayList of arguments passed in via CLI when launching this app.
     */
    public static void launchCli(String[] args) {
        YappingBot yp = new YappingBot(new UiCli(), new Storage(savefilePath));
        yp.start();
    }
}
