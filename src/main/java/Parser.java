package main.java;

import java.util.Arrays;

public class Parser {
    public static String parseCmd(String input) {
        String[] parts = input.split(" ");
        return parts[0];
    }

    public static String parseTaskDetails(String input) {
        String[] parts = input.split(" ");
        return String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
    }
}
