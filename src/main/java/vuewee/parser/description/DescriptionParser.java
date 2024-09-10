package vuewee.parser.description;

/**
 * Converts String description to a specific type.
 */
public abstract class DescriptionParser<T> {

    /**
     * Parses the input string to extract the description.
     *
     * @return The parsed description.
     */
    public abstract T parse(String descriptionStr);

    /**
     * Returns the name of the field that should appear in usage error messages
     */
    public String getUsageFieldName() {
        return " <description>";
    }
}
