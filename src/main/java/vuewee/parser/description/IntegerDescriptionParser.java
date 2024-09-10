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
     * Returns <value> instead to better represent the expected input.
     */
    @Override
    public String getUsageFieldName() {
        return " <value>";
    }
}
