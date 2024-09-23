package yappingbot.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import yappingbot.commands.commands.ArgEnums;
import yappingbot.commands.commands.CreateDeadlineCommand;
import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.stringconstants.ReplyTextMessages;


/**
 * Base Class for Commands to extend. Each subclass will define their own validArguments enum,
 * the core funtion in the run() method, and help usage text.
 *
 * @param <A> Enum defining the valid Arguments, implementing ArgEnums.
 * @param <C> The Command class extending this. For casting convenience.
 */
public abstract class CommandBase<A extends Enum<A> & ArgEnums<A>, C extends CommandBase<A, ?>> {
    protected HashMap<A, String[]> arguments = new HashMap<>();
    private boolean argumentsLoaded = false;

    protected abstract String getArgumentSeperator();

    /**
     * Subclasses must override this, linking it to an Enum of possible Argument Types.
     * Usually <i>return A.class</i>.
     *
     * @return The Class of th Enums defined in the subclass holding the possible Argument Types.
     */
    protected abstract Class<A> getArgumentClass();

    /**
     * Subclasses must override this, linking it to the value in the Enum referring to the first
     * Argument (unmarked argument).
     *
     * @return The Enum defined in the subclass holding the possible Argument Types.
     */
    protected abstract A getFirstArgumentType();

    /**
     * Gets the Enum that is specified by its corresponding String type.
     *
     * @param key String representing the Argument enum.
     * @return Enum of the Argument type
     * @throws IllegalArgumentException If there is no Arg Enum matching the given key.
     */
    private A getArgTypeFromString(String key) throws IllegalArgumentException {
        return ArgEnums.valueOf(getArgumentClass(), key);
    }

    /**
     * Gets the Enum that is spevified by its corresponding String type in the keyword.
     *
     * @param key String representing the Argument enum keyword.
     * @return Enum of the Argument type
     * @throws IllegalArgumentException If there is no Arg Enum matching the given key.
     */
    private A getArgTypeFromKeyword(String key) throws IllegalArgumentException {
        return ArgEnums.findKeyword(getArgumentClass(), key);
    }

    /**
     * Constructs Command object with arguments to prepare for execution.
     *
     * @param argSlices ordered array of strings with argument flags followed by argument values.
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown
     *                                             argument flag given.
     */
    public CommandBase(String[] argSlices) throws YappingBotIncorrectCommandException {
        parseArguments(getArgumentSeperator(), argSlices);

        // command id ready to be run
        argumentsLoaded = true;
    }

    /**
     * Abstract class to be implemented. Called internally by public runCommand() if ready.
     *
     * @throws YappingBotException Any Exception that occurs during execution.
     */
    protected abstract C run();

    /**
     * Runs the implemented command, after checking if command is ready to run.
     *
     * @return this object, useful for chainning.
     * @throws YappingBotException Any Exception that occurs during execution.
     */
    public C runCommand() throws YappingBotException {
        assert argumentsLoaded : "Arguments Not Loaded!";
        return this.run();
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
    private void parseArguments(String flagMarker, String[] userInputSlices)
    throws IllegalArgumentException {
        A currentArgument = this.getFirstArgumentType();
        ArrayList<String> valueCollector = new ArrayList<>();
        boolean isCommandVerb = true;

        for (String slice : userInputSlices) {
            if (isCommandVerb) {
                isCommandVerb = false;
                continue;
            }

            if (slice.startsWith(flagMarker)) {
                // Slice starts with flag.
                // Push valueCollector's values and current flag into arguements hashmap
                // and start new cycle
                arguments.put(currentArgument, valueCollector.toArray(String[]::new));
                currentArgument = getArgTypeFromKeyword(slice);
                valueCollector.clear();
            } else {
                // add value to collector for current arg flag
                valueCollector.add(slice);
            }
        }

        // Check if current arg is pushed, because the last argument might be valueless flag
        arguments.put(currentArgument, valueCollector.toArray(String[]::new));

        // check if arguments satisfied
        boolean areRequiredArgsSatisfied = Arrays.stream(getArgumentClass().getEnumConstants())
                                                 .filter(ArgEnums::isRequired)
                                                 .map(arguments::get)
                                                 .map(a -> a != null && a.length > 0)
                                                 .reduce(Boolean::logicalAnd)
                                                 .orElse(true);
        if (!areRequiredArgsSatisfied) {
            throw new YappingBotException(ReplyTextMessages.NOT_ENOUGH_ARGUMENTS);
        }
    }


    protected String getArgValueJoined(A arg) {
        return String.join(" ", arguments.get(arg)).trim();
    }
}
