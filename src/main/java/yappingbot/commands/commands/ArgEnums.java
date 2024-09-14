package yappingbot.commands.commands;

/**
 * Interface to define how Argument Enums should be defined.
 *  Example implementation:
 *  {@code
 *   private final String keyword;
 *   Args(String keyword) {
 *     this.keyword = keyword;
 *   }
 *   @Override
 *   public String getKeyword() {
 *     return keyword;
 *   }
 *   }
 */
public interface ArgEnums<T extends Enum<T>> {
    String getKeyword();

    static <T extends Enum<T> & ArgEnums<T>> T valueOf(Class<T> enums, String value) {
        return Enum.valueOf(enums, value);
    }

}
