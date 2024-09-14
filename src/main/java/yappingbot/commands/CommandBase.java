package yappingbot.commands;

import java.util.ArrayList;
import java.util.HashMap;

import yappingbot.commands.commands.ArgEnums;
import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIncorrectCommandException;


/**
 * Base Class for Commands to extend. Each subclass will define their own validArguments enum,
 * the core funtion in the run() method, and help usage text.
 *
 * @param <A> Enum defining the valid Arguments, implementing ArgEnums.
 */
public abstract class CommandBase<A extends Enum<A> & ArgEnums> {
    protected HashMap<A, String[]> arguments = new HashMap<>();
    protected ArrayList<String> firstArg = new ArrayList<>();
    private boolean argumentsLoaded = false;
    protected String argumentSeperator = "/";

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
     * @param argSlices ordered array of strings with argument flags followed by argument values.
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown
     *                                             argument flag given.
     */
    public CommandBase(String[] argSlices) throws YappingBotIncorrectCommandException {
        parseArguments(argumentSeperator, argSlices);

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

    /**
     * Parses the userInputSlices into Argument Type and value, and store in hashmap.
     *
     * @param flagMarker String of the character used to denote a flag.
     * @param userInputSlices User input sliced by space, including command verb.
     */
    private void parseArguments(String flagMarker, String[] userInputSlices) {
        boolean firstArgumentCollected = false;


        for ()
    }
}
