package yappingbot;

import java.util.ArrayList;
import java.util.List;

import yappingbot.commands.commands.LauncherCommand;

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
        ArrayList<String> argsWtihVerb = new ArrayList<>();
        argsWtihVerb.add("run");
        argsWtihVerb.addAll(List.of(args));
        new LauncherCommand(argsWtihVerb.toArray(String[]::new)).runCommand();
    }
}
