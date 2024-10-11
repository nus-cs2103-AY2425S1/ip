package miku.utility;

/**
 * Represents the priority of the tasks.
 */
public enum Priority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private final String displayValue;

    Priority(String string) {
        this.displayValue = string;
    }

    /**
     * Parse string to Priority is applicable
     * @param stringValue the string to be parsed.
     * @return The matched priority value.
     */
    public static Priority parsePriority(String stringValue) {
        if (stringValue.equals("HIGH")) {
            return HIGH;
        } else if (stringValue.equals("MEDIUM")) {
            return MEDIUM;
        } else if (stringValue.equals("LOW")) {
            return LOW;
        }
        throw new IllegalArgumentException("Not a valid priority value.");
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
