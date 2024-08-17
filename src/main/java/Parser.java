public class Parser {

    public static String inputToCommand(String input) {
       return input.split(" ", 2)[0];
    }

    public static String inputToArgs(String input) {
        return input.split(" ", 2)[1];
    }

}
