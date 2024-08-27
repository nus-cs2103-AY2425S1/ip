/**
 * This class is responsible for handling user input commands.
 */
public class Paser {
    
    /**
     * Creates a Paser object.
     */
    public Paser() {

    }

    /**
     * Retrieves the next input command by the user.
     * @param sc A scanner object used to get the user input.
     * @throws InvalidCommandException Signals that the command given is invalid.
     */
    public Command getUserInput(Quack quack) throws InvalidCommandException{
        try {
            return this.processCommand(quack.sc.nextLine());
        } catch (InvalidCommandException commandError) {
            throw commandError;
        }
    }

    /**
     * Processes the command entered by the user.
     * @param input The command in string form which the user inputed.
     * @throws InvalidCommandException Signals that the command given is invalid.
     */
    private Command processCommand(String input) throws InvalidCommandException {
        input = input.toLowerCase();
        Command command;
        switch (input) {
        case "list":
            command = new ListCommand();
            return command;

        case "bye":
        case "exit":
            command = new ExitCommand();
            return command;
        
        case "add":
            command = new AddTaskCommand();
            return command;

        case "mark":
        case "unmark":
            command = new UpdateTaskCommand(input);
            return command;
        
        case "delete":
            command = new DeleteTaskCommand();
            return command;
        
        default:
            throw new InvalidCommandException(input);
        }
    }
}
