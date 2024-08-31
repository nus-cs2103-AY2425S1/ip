package utility;

import java.util.Arrays;

public class Strip {

    public static String[] stripStringArray(String[] strArray) {
        return Arrays.stream(strArray)
                .map(String::strip)
                .toArray(String[]::new);
    }

}
