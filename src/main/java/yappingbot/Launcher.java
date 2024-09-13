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
