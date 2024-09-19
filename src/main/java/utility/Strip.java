package utility;

import java.util.Arrays;

/**
 * For now, Strip class provides a single functionality to manipulate strings in an array
 */
public class Strip {

    /**
     * Using Stream, this function seeks to remove all prepending and appending white
     * spaces from each string in the array.
     *
     * @param strArray a String Array
     */
    public static String[] stripStringArray(String[] strArray) {
        return Arrays.stream(strArray)
                .map(String::strip)
                .toArray(String[]::new);
    }

}
