package yappingbot.commands.commands;

import java.util.Arrays;
import java.util.HashMap;

import yappingbot.exceptions.YappingBotIncorrectCommandException;

/**
 * Base Class for Commands to extend. Each subclass will define their own validArguments enum,
 * the core funtion in the run() method, and help usage text.
 */
abstract class CommandBase {
    private enum ValidArgument {}
    private HashMap<ValidArgument, String[]> flaggedArguments = new HashMap<>();
    private String fistArg;

    /**
     * Constructor for a Command object that will take in arguments to prepare for execution.
     *
     * @param firstArg String of the first argument passed in that is not demacated by flags.
     * @param flattenedFlaggedArguments a flat String array that is ordered with all given
     *                                  argument flags followed by argument values.
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown
     *                                             argument flag given.
     */
    public CommandBase(String firstArg, String[] ... flattenedFlaggedArguments)
    throws YappingBotIncorrectCommandException {

        this.fistArg = firstArg;

        assert flattenedFlaggedArguments != null;
        for (String[] argValues : flattenedFlaggedArguments) {
            try {
                ValidArgument argFlag = ValidArgument.valueOf(argValues[0]);
                flaggedArguments.put(argFlag, Arrays.copyOfRange(argValues, 1, argValues.length));
            } catch (IllegalArgumentException e) {
                throw new YappingBotIncorrectCommandException(getHelpText(), argValues[0]);
            }
        }
    }

    /**
     * Runs the implemented command.
     */
    public abstract void run();


    /**
     * Returns
     *
     * @return
     */
    public abstract String getHelpText();

}
