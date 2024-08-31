package main.java.util;

public class Parser {

    private static enum Keywords {
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        BYE,
        UNKNOWN;
    }

    public static String parse(String input) {
        //TODO future plan to return array of Command instead of String
        String[] arr = input.split(" ", 2);
        Keywords keyword;
        try {
            keyword = Keywords.valueOf(arr[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            keyword = Keywords.UNKNOWN;
        }
        String result = "";

        switch (keyword) {
        case BYE:
            result = "bye";
            break;
        case LIST:
            result = "list";
            break;
        case TODO:
            result = "todo";
            break;
        case DEADLINE:
            result = "deadline";
            break;
        case EVENT:
            result = "event";
            break;
        case MARK:
            result = "mark";
            break;
        case UNMARK:
            result = "unmark";
            break;
        case DELETE:
            result = "delete";
            break;
        case UNKNOWN:
            result = "unknown";
            break;
        }
        return result;
    }
}
