public class StringParser {

    public static String parseStringToCommand(String s) {
        String command = s.split("\s", 2)[0];
        String commmandToUpperCase = command.toUpperCase();
        return commmandToUpperCase;
    }

    public static String parseStringToArguments(String s) {
        String input = s.split("\s", 2)[1];
        return input;
    }
}
