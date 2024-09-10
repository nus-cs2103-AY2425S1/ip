package vuewee.parser.description;

/**
 * The IntegerDescriptionParser class is responsible for parsing and extracting
 * information from a description input string and converting it to an integer.
 */
public class IntegerDescriptionParser extends DescriptionParser<Integer> {
    /**
     * Return the parsed string as an integer.
     */
    @Override
    public Integer parse(String descriptionStr) {
        return Integer.parseInt(descriptionStr);
    }

    /**
     * Override getUsageFieldName to better represent the expected integer input.
     */
    @Override
    public String getUsageFieldName() {
        return " <value>";
    }
}
