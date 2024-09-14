package yappingbot;

import java.util.Arrays;

import yappingbot.commands.Parser;
import yappingbot.commands.commands.LauncherCommand;
import yappingbot.storage.Storage;
import yappingbot.ui.UiCli;
import yappingbot.ui.gui.MainGuiApplication;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

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
        new LauncherCommand(args).runCommand();
    }
}
