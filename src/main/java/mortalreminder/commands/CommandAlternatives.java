package mortalreminder.commands;

import java.util.HashMap;

import mortalreminder.backend.CommandAlternativesStorage;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.io.FormattedOutput;
import mortalreminder.io.Parser;

/**
 * Helps map user input to commands more easily to ensure more flexibility.
 */
public class CommandAlternatives {
    private final HashMap<String, CommandType> alternativeCommands;

    /**
     * This method is used when creating the hashmap using long term storage.
     *
     * @param alternativeCommands list of saved alternative commands from previous sessions.
     */
    public CommandAlternatives(HashMap<String, CommandType> alternativeCommands) {
        this.alternativeCommands = alternativeCommands;
        for (CommandType type : CommandType.values()) {
            this.alternativeCommands.put(type.name().toLowerCase(), type);
        }
    }

    /**
     * Adds a new alternative to call on the specified command type.
     *
     * @param commandDetails commandString and types to be mapped.
     * @throws MortalReminderException if the command type specified is unknown.
     */
    public String addCommandAlternative(String commandDetails) throws MortalReminderException {
        String[] splitInput = commandDetails.split(" ");
        if (splitInput.length != 2) {
            throw new MortalReminderException(MortalReminderException.getExtraSpaceInAlternativeCommandErrorMessage());
        }

        String commandAlternative = splitInput[0];
        CommandType commandType = Parser.parseCommandWord(splitInput[1]);

        if (commandType == CommandType.UNKNOWN) {
            throw new MortalReminderException(MortalReminderException.getUnknownCommandWordErrorMessage(splitInput[1]));
        }

        this.alternativeCommands.put(commandAlternative.toLowerCase(), commandType);
        CommandAlternativesStorage.appendToAlternativeCommandFile(commandAlternative.toLowerCase(), commandType);

        return FormattedOutput.printNewAlternativeAdded(commandAlternative.toLowerCase(), commandType);
    }

    public CommandType getCommandType(String commandString) {
        return this.alternativeCommands.get(commandString.toLowerCase());
    }
}
