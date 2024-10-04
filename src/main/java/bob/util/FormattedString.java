package bob.util;

/**
 * Represents an instance of a formatted string. FormattedStrings can be colored, highlighted, underlined, etc.
 */
public class FormattedString {
    public enum COLOR {
        RED("\u001B[31m"),
        BG_YELLOW("\u001B[43m");

        private final String VAL;

        COLOR(String val) {
            this.VAL = val;
        }
    }
    private static final String RESET = "\u001B[0m";

    // TODO: replace String here with StringBuilder?
    private String str;
    private String formattedStr;

    /**
     * Constructs a {@code FormattedString} with the given string.
     *
     * @param str the string to be formatted
     */
    public FormattedString(String str) {
        this.str = str;
        this.formattedStr = str;
    }

    /**
     * Returns the unformatted version of this {@code FormattedString}.
     */
    public String getUnformatted() {
        return this.str;
    }

    /**
     * Append the given string to the back of this {@code FormattedString}.
     *
     * @param str the string to append
     * @return a reference to this object
     */
    public FormattedString append(String str) {
        this.str += str;
        this.formattedStr += str;
        return this;
    }

    /**
     * Append the given {@code FormattedString} to the back of this {@code FormattedString}.
     * The unformatted version of the two objects are also concatenated.
     *
     * @param str the string to append
     * @return a reference to this object
     */
    public FormattedString append(FormattedString str) {
        this.str += str.getUnformatted();
        this.formattedStr += str.toString();
        return this;
    }

    /**
     * Applies the given color to the whole string.
     *
     * @param color the color to be applied
     * @return a reference to this object
     */
    public FormattedString color(COLOR color) {
        assert color != null : "color should not be null";
        this.formattedStr = color.VAL + formattedStr + RESET;
        return this;
    }

    /**
     * Applies the given color to each occurrence of the given substring in this string.
     *
     * @param color the color to be applied
     * @param substring the substring to apply color to
     * @return a reference to this object
     */
    public FormattedString color(COLOR color, String substring) {
        assert color != null : "color should not be null";
        assert substring != null : "substring should not be null";

        this.formattedStr = formattedStr.replace(substring, color.VAL + substring + RESET);
        return this;
    }

    /**
     * Applies the given color to each occurrence of the given substring in this string.
     * If {@code ignoreCase} is true, the color is applied to each substring without matching case.
     *
     * @param color the color to be applied
     * @param substring the substring to apply color to
     * @param ignoreCase matches occurrences of the substring ignoring the case if true
     * @return a reference to this object
     */
    public FormattedString color(COLOR color, String substring, boolean ignoreCase) {
        assert color != null : "color should not be null";
        assert substring != null : "substring should not be null";

        if (!ignoreCase) {
            return color(color, substring);
        }

        this.formattedStr = formattedStr.replaceAll(
                "(?i)(" + substring + ")", color.VAL + "$1" + RESET);
        return this;
    }

    /**
     * Returns this {@code FormattedString} as a string.
     */
    @Override
    public String toString() {
        return this.formattedStr;
    }
}
