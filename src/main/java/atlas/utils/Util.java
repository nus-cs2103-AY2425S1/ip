package atlas.utils;

/**
 * Represents a util class that contains util functions.
 */
public class Util {
    /**
     * Checks if the string provided is not a number.
     *
     * @param s The string to be checked.
     * @return boolean The boolean value of whether the string is not a number.
     */
    public static boolean isNotNumber(String s) {
        try {
            Integer.parseInt(s);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
