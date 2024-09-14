package yappingbot.commands.commands;

/**
 * Interface to define how Argument Enums should be defined.
 */
public interface ArgEnums<T extends Enum<T>> {
    String keyword = "";

    String getKeyword();

    static <T extends Enum<T> & ArgEnums<T>> T valueOf(Class<T> enums, String value) {
        return Enum.valueOf(enums, value);
    }
}
