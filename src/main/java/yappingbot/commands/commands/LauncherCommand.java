package yappingbot.commands.commands;

import java.util.ArrayList;
import java.util.Arrays;

import yappingbot.YappingBot;
import yappingbot.commands.CommandBase;
import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.storage.Storage;
import yappingbot.ui.UiCli;
import yappingbot.ui.gui.MainGuiApplication;

/**
 * Command class for the Launcher of this entire application.
 */
public class LauncherCommand extends CommandBase<LauncherCommand.Args> {

    /**
     * Enum for the possible Arguments of this command.
     */
    protected enum Args implements ArgEnums {
        JAVAFX_ARGS_PASSTHRU("--"),
        SAVE_FILE_PATH("-s"),
        SAVE_FILE_PATH_LONG("--savefile"),
        CLI_MODE("-c");

        private final String keyword;

        Args(String keyword) {
            this.keyword = keyword;
        }

        @Override
        public String getKeyword() {
            return keyword;
        }
    }

    // default savefile path is './savefile'
    private static String savefilePath = "./savefile";

    /**
     * Constructs Command object with arguments to prepare for execution.
     *
     * @param firstArg String of the first argument passed in that is not demacated by flags.
     * @param argPairs array of String-String Pairs that is ordered with all given argument flags
     *                 followed by argument values.
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown
     *                                             argument flag given.
     */
    public LauncherCommand(String firstArg, Pair<String, String>[] argPairs)
    throws YappingBotIncorrectCommandException {
        super(firstArg, argPairs);
    }

    @Override
    protected Class<Args> getArgumentTypes() {
        return Args.class;
    }

    @Override
    public void run() throws YappingBotException {
        boolean isUsingGui = arguments.containsKey(Args.CLI_MODE);
        boolean isUsingAltSavefile = arguments.containsKey(Args.SAVE_FILE_PATH)
                                     || arguments.containsKey(Args.SAVE_FILE_PATH_LONG);
        String[] javafxArgs = arguments.getOrDefault(Args.JAVAFX_ARGS_PASSTHRU, "").split(" ");

        if (isUsingAltSavefile) {
            this.savefilePath = arguments.containsKey(Args.SAVE_FILE_PATH)
                                ? arguments.get(Args.SAVE_FILE_PATH)
                                : arguments.get(Args.SAVE_FILE_PATH_LONG);
        }

        if (isUsingGui) {
            launchGui(javafxArgs);
        } else {
            launchCli();
        }
    }

    @Override
    public String getHelpText() {
        return "";
    }

    /**
     * Static method to launchGui MainGuiApplication without GUI.
     * Use as fallback method.
     *
     * @param args String ArrayList of arguments passed in via CLI when launching this app.
     */
    public void launchGui(String[] args) {
        MainGuiApplication.launch(MainGuiApplication.class, args);
    }

    /**
     * Static method to launchGui MainGuiApplication with JavaFX GUI.
     */
    public void launchCli() {
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
