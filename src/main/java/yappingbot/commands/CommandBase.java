package yappingbot.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIncorrectCommandException;


/**
 * Base Class for Commands to extend. Each subclass will define their own validArguments enum,
 * the core funtion in the run() method, and help usage text.
 *
 * @param <A> Enum defining the valid Arguments.
 */
abstract class CommandBase<A extends Enum<A>> {
    HashMap<A, String[]> arguments = new HashMap<>();
    A firstArg;

    /**
     * Subclasses must override this, linking it to an Enum of possible Argument Types.
     *
     * @return The Enum defined in the subclass holding the possible Argument Types.
     */
    protected abstract Class<A> getArgumentTypes();

    private A getArgTypeFromString(String key) throws IllegalArgumentException {
        return Enum.valueOf(getArgumentTypes(), key);
    }

    /**
     * Constructs Command object with arguments to prepare for execution.
     *
     * @param firstArg String of the first argument passed in that is not demacated by flags.
     * @param flattenedFlaggedArguments a flat String array that is ordered with all given
     *                                  argument flags followed by argument values.
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown
     *                                             argument flag given.
     */
    public CommandBase(String firstArg, ArrayList<String[]> flattenedFlaggedArguments)
    throws YappingBotIncorrectCommandException {
        try {
            this.firstArg = getArgTypeFromString(firstArg);
            for (String[] argValues : flattenedFlaggedArguments) {
                A argFlag = getArgTypeFromString(argValues[0]);
                arguments.put(argFlag, Arrays.copyOfRange(argValues, 1, argValues.length));
            }
        } catch (Exception e) {
            throw new YappingBotIncorrectCommandException(getHelpText(), e.getMessage());
        }
    }

    /**
     * Runs the implemented command.
     *
     * @throws YappingBotException Any Exception that occurs during execution.
     */
    public abstract void run() throws YappingBotException;


    /**
     * Returns the usage help text, helpful for guiding user on correct usage.
     *
     * @return String of help text.
     */
    public abstract String getHelpText();

}
