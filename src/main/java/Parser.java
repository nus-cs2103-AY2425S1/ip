public class Parser {

    public static String[] parseCommand(String input) {
        String[] command = input.split(" ", 2);
        command[0] = command[0].toUpperCase();
        return command;
    }

    public static int parseMarkUnmark(String input) {
        return Integer.parseInt(input.trim());
    }


}