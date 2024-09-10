package vuewee.parser.description;

/**
 * The StringDescriptionParser class is responsible for parsing and extracting
 * information from a description input string. This should be the default
 * parser for descriptions.
 */
public class StringDescriptionParser extends DescriptionParser<String> {
    /**
     * Return the description string as is.
     */
    @Override
    public String parse(String descriptionStr) {
        return descriptionStr;
    }
}
