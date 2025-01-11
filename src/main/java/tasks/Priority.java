package tasks;

import exceptions.InvalidPriorityException;

/**
 * Enum for priority level of task, consisting of HIGH, MEDIUM and LOW.
 */
public enum Priority {
    HIGH, MEDIUM, LOW;

    /**
     * Returns priority from given string.
     *
     * @param input string representing a priority.
     * @return the priority associated with the string.
     * @throws InvalidPriorityException when string does not specify a priority.
     */
    public static Priority stringToPriority(String input) throws InvalidPriorityException {
        switch (input) {
        case "high":
            return HIGH;
        case "medium":
            return MEDIUM;
        case "low":
            return LOW;
        default:
            throw new InvalidPriorityException(input);
        }
    }
}
