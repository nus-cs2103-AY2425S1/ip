package yappingbot.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import yappingbot.commands.commands.ArgEnums;
import yappingbot.commands.commands.Pair;
import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIncorrectCommandException;


/**
 * Base Class for Commands to extend. Each subclass will define their own validArguments enum,
 * the core funtion in the run() method, and help usage text.
 *
 * @param <A> Enum defining the valid Arguments, implementing ArgEnums.
 */
public abstract class CommandBase<A extends Enum<A> & ArgEnums> {
    protected HashMap<A, String> arguments = new HashMap<>();
    protected A firstArg;
    private boolean argumentsLoaded = false;

    /**
     * Subclasses must override this, linking it to an Enum of possible Argument Types.
     * Usually <i>return A.class</i>.
     *
     * @return The Enum defined in the subclass holding the possible Argument Types.
     */
    protected abstract Class<A> getArgumentTypes();

    /**
     * Gets the Enum that is spevified by its corresponding String type.
     *
     * @param key String representing the Argument enum.
     * @return Enum of the Argument type
     * @throws IllegalArgumentException If there is no Arg Enum matching the given key.
     */
    private A getArgTypeFromString(String key) throws IllegalArgumentException {
        return Enum.valueOf(getArgumentTypes(), key);
    }

    /**
     * Constructs Command object with arguments to prepare for execution.
     *
     * @param firstArg String of the first argument passed in that is not demacated by flags.
     * @param argPairs array of String-String Pairs that is ordered with all given
     *                                  argument flags followed by argument values.
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown
     *                                             argument flag given.
     */
    public CommandBase(String firstArg, Pair<String, String>[] argPairs)
    throws YappingBotIncorrectCommandException {
        try {
            this.firstArg = getArgTypeFromString(firstArg);

            for (Pair<String, String> argPair : argPairs) {
                A argFlag = getArgTypeFromString(argPair.getKey());
                arguments.put(argFlag, argPair.getValue());
            }

        } catch (Exception e) {
            throw new YappingBotIncorrectCommandException(getHelpText(), e.getMessage());
        }

        // command id ready to be run
        argumentsLoaded = true;
    }

    /**
     * Abstract class to be implemented. Called internally by public runCommand() if ready.
     *
     * @throws YappingBotException Any Exception that occurs during execution.
     */
    protected abstract void run();

    /**
     * Runs the implemented command, after checking if command is ready to run.
     *
     * @throws YappingBotException Any Exception that occurs during execution.
     */
    public void runCommand() throws YappingBotException {
        assert argumentsLoaded : "Arguments Not Loaded!";
        this.run();
    }

    /**
     * Returns the usage help text, helpful for guiding user on correct usage.
     *
     * @return String of help text.
     */
    public abstract String getHelpText();

}
