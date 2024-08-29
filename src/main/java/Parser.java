public class Parser {
    public Parser() {

    }

    public static Command parse(String command) throws TuesdayException {
        String[] userInputArr = command.split(" ", 2); // separate the command by " " into 2
        if (userInputArr[0].equals("bye")) {
            return new Command(command);
        }
        else if (userInputArr[0].equals("list")){
            return new Command(command);
        } else {
            return new Command(command);
        }
    }
}
