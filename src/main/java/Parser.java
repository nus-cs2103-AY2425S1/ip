public class Parser {
    public static Command parse(String userInput) {

        switch (userInput) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        default:
            return new AddCommand(new Task(userInput));
        }
    }
}
