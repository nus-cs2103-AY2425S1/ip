package greetbot;

public class Parser {

    public static String[] parseCommand(String input) {
        String[] command = input.split(" ", 2);
        command[0] = command[0].toUpperCase();
        return command;
    }

    public static int parseMarkUnmarkDelete(String input) {
        return Integer.parseInt(input.trim());
    }

    public static String parseTodo(String input) {
        return input.trim();
    }

    public static String[] parseEvent(String input) {
        String[] args = input.trim().split("/");

        args[0] = args[0].trim();
        args[1] = args[1].trim().substring(5);
        args[2] = args[2].trim().substring(3);
        return args;
    }

    public static String[] parseDeadline(String input) {
        String[] args = input.trim().split("/");
        args[0] = args[0].trim();
        args[1] = args[1].trim().substring(3);
        return args;
    }

    public static String parseFind(String input) {
        return input.trim();
    }

}