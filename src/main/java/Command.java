public class Command {
    private final int commandCode;
    private final String commandDetails;

    // Constructor
    public Command(int commandCode, String commandDetails) {
        this.commandCode = commandCode;
        this.commandDetails = commandDetails;
    }

    // Getter methods (optional)
    public int getCommandCode() {
        return commandCode;
    }

    public String getCommandDetails() {
        return commandDetails;
    }

    // Initialise method
    public static Command initialise(String command, int commandCode) {
        // Split the command and remove the first word
        String[] splitCommand = command.split(" ", 2);
        String commandDetails = splitCommand.length > 1 ? splitCommand[1] : " ";

        // Return a new Commands object with commandCode and commandDetails
        return new Command(commandCode, commandDetails);
    }
}
