package mittens.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class RawCommandElements {
    private final String command;
    private final String argument;
    private final Map<String, String> flagMap;

    /**
     * Initializes the String elements of the command, including the
     * command name, argument, and a map of flags to their values.
     * Command and flag names are converted to lowercase.
     *
     * @param input The user input string
     */
    public RawCommandElements(String input) {
        this.flagMap = new HashMap<>();

        String[] parts = input.split("\\s+", 2);
        this.command = parts[0].toLowerCase();

        if (parts.length > 1) {
            String[] elements = parts[1].split("\\s+\\\\");
            this.argument = elements[0];

            for (int i = 1; i < elements.length; i++) {
                String[] flag = elements[i].split("\\s+", 2);
                this.flagMap.put(flag[0].toLowerCase(), flag[1]);
            }
        } else {
            this.argument = null;
        }
    }

    public String getCommand() {
        return this.command;
    }

    public String getArgument() {
        return this.argument;
    }

    /**
     * Returns the argument as an integer.
     *
     * @return The integer value of the argument if it exists, otherwise null
     * @throws BadInputException If the argument is not an integer
     */
    public Integer getArgumentAsInteger() throws BadInputException {
        if (this.argument == null) {
            return null;
        }
        try {
            return Integer.parseInt(this.argument);
        } catch (NumberFormatException e) {
            throw new BadInputException("Argument must be an integer");
        }
    }

    /**
     * Requires the argument to exist and returns it as string.
     *
     * @return The string value of the argument
     * @throws BadInputException If the argument is missing
     */
    public String getArgumentOrThrow() throws BadInputException {
        String value = getArgument();
        if (value == null) {
            throw new BadInputException("Command must include an argument");
        }
        return value;
    }

    /**
     * Requires the argument to exist and returns it as integer.
     *
     * @return The string value of the argument
     * @throws BadInputException If the argument is missing or is not an integer
     */
    public int getArgumentAsIntegerOrThrow() throws BadInputException {
        Integer value = getArgumentAsInteger();
        if (value == null) {
            throw new BadInputException("Command must include an argument");
        }
        return value;
    }

    /**
     * Returns the value of the flag as string.
     *
     * @param flag The flag name in lowercase
     * @return The string value of the flag if it exists, otherwise null
     */
    public String getFlag(String flag) {
        return this.flagMap.get(flag);
    }

    /**
     * Returns the value of the flag as integer.
     *
     * @param flag The flag name in lowercase
     * @return The integer value of the flag if it exists, otherwise null
     * @throws BadInputException If the value is not an integer
     */
    public Integer getFlagAsInteger(String flag) throws BadInputException {
        String value = this.flagMap.get(flag);
        if (value == null) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new BadInputException("Flag '%s' must be an integer".formatted(flag));
        }
    }

    /**
     * Returns the value of the flag as date.
     *
     * @param flag The flag name in lowercase
     * @return The date value of the flag if it exists, otherwise null
     * @throws BadInputException If the value is not a date in the format 'yyyy-mm-dd'
     */
    public LocalDate getFlagAsDate(String flag) throws BadInputException {
        String value = this.flagMap.get(flag);
        if (value == null) {
            return null;
        }
        try {
            return LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            throw new BadInputException("Flag '%s' must be a date in the format 'yyyy-mm-dd'".formatted(flag));
        }
    }

    /**
     * Requires the flag to exist and returns its value as string.
     *
     * @param flag The flag name in lowercase
     * @return The string value of the flag
     * @throws BadInputException If the flag is missing
     */
    public String getFlagOrThrow(String flag) throws BadInputException {
        String value = getFlag(flag);
        if (value == null) {
            throw new BadInputException("Command must include flag '%s'".formatted(flag));
        }
        return value;
    }

    /**
     * Requires the flag to exist and returns its value as integer.
     *
     * @param flag The flag name in lowercase
     * @return The integer value of the flag
     * @throws BadInputException If the flag is missing or is not an integer
     */
    public int getFlagAsIntegerOrThrow(String flag) throws BadInputException {
        Integer value = getFlagAsInteger(flag);
        if (value == null) {
            throw new BadInputException("Command must include flag '%s'".formatted(flag));
        }
        return value;
    }

    /**
     * Requires the flag to exist and returns its value as date.
     *
     * @param flag The flag name in lowercase
     * @return The date value of the flag
     * @throws BadInputException If the flag is missing or is not a date in the format 'yyyy-mm-dd'
     */
    public LocalDate getFlagAsDateOrThrow(String flag) throws BadInputException {
        LocalDate value = getFlagAsDate(flag);
        if (value == null) {
            throw new BadInputException("Command must include flag '%s'".formatted(flag));
        }
        return value;
    }

    /**
     * Checks if the command has all the mandatory flags.
     *
     * @param flags The flag strings to check for, in lowercase
     * @throws BadInputException If any of the flags are missing
     */
    public void checkIfHasAllFlags(String... flags) throws BadInputException {
        List<String> missingFlags = new ArrayList<>();
        for (String flag : flags) {
            if (!this.flagMap.containsKey(flag)) {
                missingFlags.add(flag);
            }
        }

        if (!missingFlags.isEmpty()) {
            throw new BadInputException("Command must include the following flags: %s"
                    .formatted(String.join(", ", missingFlags)));
        }
    }

    /**
     * Checks if all flags for this command are part of the given set.
     *
     * @param flags The flag strings to check for, in lowercase
     * @throws BadInputException If any of the flags are outside the set
     */
    public void checkIfAllFlagsIn(String... flags) throws BadInputException {
        List<String> invalidFlags = new ArrayList<>();
        HashSet<String> flagSet = new HashSet<>(List.of(flags));
        for (String flag : this.flagMap.keySet()) {
            if (!flagSet.contains(flag)) {
                invalidFlags.add(flag);
            }
        }

        if (!invalidFlags.isEmpty()) {
            throw new BadInputException("Found unknown flags: %s"
                    .formatted(String.join(", ", invalidFlags)));
        }
    }
}
