package yappingbot.commands.commands;

import java.util.ArrayList;

import yappingbot.commands.CommandBase;
import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIncorrectCommandException;

/**
 * Command class for the Launcher of this entire application.
 */
public class LauncherCommand extends CommandBase<LauncherCommand.Args> {

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

    /**
     * Enum for the possible Arguments of this command.
     */
    protected enum Args implements ArgEnums{
        SAVE_FILE_PATH("-s"),
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

    @Override
    protected Class<Args> getArgumentTypes() {
        return Args.class;
    }

    @Override
    public void run() throws YappingBotException {

    }

    @Override
    public String getHelpText() {
        return "";
    }

}
