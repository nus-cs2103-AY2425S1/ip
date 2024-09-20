package lewis;

public class HelpCommand extends Command {

    /** The default help that Lewis will give */
    private String helpDescription = "Hi, I'm Lewis the chatbot, and I love helping people\n" +
            "Here is a list of what I can do!\n" +
            Parser.VALID_COMMANDS.toString() +
            "\nType help <command> for more information on each command!";

    /**
     * Private constructor for HelpCommand
     * @param helpDescription the help description that this command should output
     */
    private HelpCommand(String helpDescription) {
        this.helpDescription = helpDescription;
    }

    /**
     * Private constructor for HelpCommand with
     */
    private HelpCommand() {
    }
    public static HelpCommand of(String input) {
        if (!input.trim().equals("help")) {
            String[] arguments = input.split("help");
            String commandToSearch = arguments[1].trim();
            switch (commandToSearch) {
                case "mark", "unmark" -> {
                    String helpDescription = MarkUnmarkCommand.getHelpDescription();
                    return new HelpCommand(helpDescription);
                }
                case "echo" -> {
                    String helpDescription = EchoCommand.getHelpDescription();
                    return new HelpCommand(helpDescription);
                }
                case "todo" -> {
                    String helpDescription = TodoCommand.getHelpDescription();
                    return new HelpCommand(helpDescription);
                }
                case "deadline" -> {
                    String helpDescription = DeadlineCommand.getHelpDescription();
                    return new HelpCommand(helpDescription);
                }
                case "event" -> {
                    String helpDescription = EventCommand.getHelpDescription();
                    return new HelpCommand(helpDescription);
                }
                case "bye", "exit" -> {
                    String helpDescription = ByeCommand.getHelpDescription();
                    return new HelpCommand(helpDescription);
                }
                case "list" -> {
                    String helpDescription = ListCommand.getHelpDescription();
                    return new HelpCommand(helpDescription);
                }
                case "delete" -> {
                    String helpDescription = DeleteCommand.getHelpDescription();
                    return new HelpCommand(helpDescription);
                }
                default -> {
                    String helpDescription = "I'm sorry, I can't help you with that. Did you type the " +
                            "command correctly? \nUsage: help <command>";
                }
            }
        }

        return new HelpCommand();
    }
    /**
     * Prints to the terminal what the user is looking for help for
     */
    @Override
    public void execute() {
        Ui.printString(this.helpDescription);
    }

}
