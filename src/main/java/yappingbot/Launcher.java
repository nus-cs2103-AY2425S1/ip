package yappingbot;

import java.util.Arrays;

import yappingbot.storage.Storage;
import yappingbot.ui.UiCli;
import yappingbot.ui.gui.MainGuiApplication;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    // default savefile path is './savefile'
    private static String savefilePath = "./savefile";

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
        String[] jfxArgs = new String[0];

        // loops through arguments to parse them.
        // any arguments preceeding a '--' are not processed and directly passed on to javaFX
        for (int i = 0; i < args.length; i++) {
            if (stopTakingInputs) {
                // -- has been detected. Stop and just copy everything to be passed to javaFX
                jfxArgs = Arrays.copyOfRange(args, i + 1, args.length);
                break;
            } else {
                switch (args[i]) {
                case "-c":
                    isUsingGui = false;
                    continue;
                case "-s":
                case "--savefile":
                    // peek the next arguemnt to get the savefile name
                    int savefilePathIndex = i + 1;
                    if (savefilePathIndex >= args.length
                        || args[savefilePathIndex].startsWith("-")) {
                        System.out.printf("Error: %s missing argument: savefile path!\n", args[i]);
                    } else {
                        savefilePath = args[savefilePathIndex];
                        // only advance the pointer if there was a valid value for this flag
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

        if (isUsingGui) {
            launchGui(jfxArgs);
        } else {
            launchCli();
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
     */
    public static void launchCli() {
        YappingBot yp = new YappingBot(new UiCli(), new Storage(savefilePath));
        yp.start();
    }

    /**
     * Get savefile path captured by launcher.
     *
     * @return String of savefile path
     */
    public static String getSavefilePath() {
        return savefilePath;
    }
}
