public class Parser {

    public static String inputToCommand(String input) {
       return input.split(" ", 2)[0];
    }

    public static String inputToArgs(String input) throws MissingArgumentException{
        try {
            return input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        }
    }

}
