package yappingbot.commands.commands;

/**
 * Interface to define how Argument Enums should be defined.
 *  Example implementation:
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

    static <T extends Enum<T> & ArgEnums<T>> T valueOf(Class<T> enums, String value) {
        return Enum.valueOf(enums, value);
    }

}
