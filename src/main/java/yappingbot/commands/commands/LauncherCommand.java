package yappingbot.commands.commands;

import yappingbot.YappingBot;
import yappingbot.commands.CommandBase;
import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.storage.Storage;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.ui.UiCli;
import yappingbot.ui.gui.MainGuiApplication;

/**
 * Command class for the Launcher of this entire application.
 */
public class LauncherCommand extends CommandBase<LauncherCommand.Args, LauncherCommand> {

    /**
     * Enum for the possible Arguments of this command.
     */
    protected enum Args implements ArgEnums<Args> {
        FIRST_ARG("", false),
        JAVAFX_ARGS_PASSTHRU("--", false),
        SAVE_FILE_PATH("-s", false),
        CLI_MODE("-c", false);

        private final String keyword;
        private final boolean isRequired;

        Args(String keyword, boolean isRequired) {
            this.keyword = keyword;
            this.isRequired = isRequired;
        }

        @Override
        public String getKeyword() {
            return keyword;
        }

        @Override
        public boolean isRequired() {
            return isRequired;
        }
    }

    // default savefile path is './savefile'
    private static String savefilePath = "./savefile";

    /**
     * Constructs Command object with arguments to prepare for execution.
     *
     * @param argSlices ordered array of strings with argument flags followed by argument values.
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown argument
     *                                             flag given.
     */
    public LauncherCommand(String[] argSlices) throws YappingBotIncorrectCommandException {
        super(argSlices);
    }

    @Override
    protected String getArgumentSeperator() {
        return "-";
    }

    @Override
    protected Class<Args> getArgumentClass() {
        return Args.class;
    }

    @Override
    protected Args getFirstArgumentType() {
        return Args.FIRST_ARG;
    }

    @Override
    protected LauncherCommand run() throws YappingBotException {
        boolean isUsingCli = arguments.containsKey(Args.CLI_MODE);
        boolean isUsingAltSavefile = arguments.containsKey(Args.SAVE_FILE_PATH);
        String[] javafxArgs = arguments.getOrDefault(Args.JAVAFX_ARGS_PASSTHRU, null);

        if (isUsingAltSavefile) {
            savefilePath = arguments.get(Args.SAVE_FILE_PATH)[0];
        }

        if (isUsingCli) {
            launchCli();
        } else {
            launchGui(javafxArgs);
        }
        return this;
    }

    @Override
    public String getHelpText() {
        // TODO: add help text for launcher
        return ReplyTextMessages.LAUNCHER_HELP_TEXT;
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
