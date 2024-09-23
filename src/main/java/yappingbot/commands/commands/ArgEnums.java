package yappingbot.commands.commands;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Defines the contract of how each Command class should establish their Arguements.
 * Argument Enums are to be defined as {@code ARG_FLAG("keyword", isRequired)}.
 * Example implementation:
 *  {@code
 *    private final String keyword;
 *    private final boolean isRequired;
 *    Args(String keyword, boolean isRequired) {
 *        this.keyword = keyword;
 *        this.isRequired = isRequired;
 *    }
 *    @Override
 *    public String getKeyword() {
 *        return keyword;
 *    }
 *    @Override
 *    public boolean isRequired() {
 *        return isRequired;
 *    }
 *   }
 */
public interface ArgEnums<T extends Enum<T>> {
    String getKeyword();

    boolean isRequired();

    /**
     * Finds the ArgType that is same name as the value.
     *
     * @param enums The class of the Argument Enum
     * @param value Name to search
     * @param <T> The Argument Enum itself
     * @return The Argument in the Enum that matches the criteria
     */
    static <T extends Enum<T> & ArgEnums<T>> T valueOf(Class<T> enums, String value) {
        return Enum.valueOf(enums, value);
    }

    /**
     * Finds the ArgType with the keyword embedded that matches the value given.
     *
     * @param enums The class of the Argument Enum
     * @param value Name to search
     * @param <T> The Argument Enum itself
     * @return The Argument in the Enum that matches the criteria
     * @throws NoSuchElementException if the keyword is not found
     */
    static <T extends Enum<T> & ArgEnums<T>> T findKeyword(Class<T> enums, String value)
    throws NoSuchElementException {
        return Arrays.stream(enums.getEnumConstants())
                     .filter(t -> t.getKeyword().equals(value))
                     .findFirst()
                     .orElseThrow();
    }

}
