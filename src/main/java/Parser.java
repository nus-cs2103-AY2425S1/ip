public class Parser {
    private String command;
    private String[] descriptions;

    public Parser() {

    }

    public void setUpUserInput(String input) throws JudeException {

        // Reset the fields.
        command = null;
        descriptions = null;

        // Handle null input.
        if (input == null) {
            throw new JudeException("Poyo, invalid input. Try again...");
        }

        // Perform split of command, and a description, if present.
        String[] split = input.split(" ", 2);
        this.command = split[0];

        // Handle inputs with only a command (and without a description).
        if (command.equals("bye") || command.equals("list")) {

            if (split.length > 1) {
                throw new JudeException("Poyo,  The description of a " + command + " must be empty.");
            }
            return;
        }

        // Handle inputs with a command and a description/s.
        else if (split.length <= 1) {
            throw new JudeException("Poyo, the command either does not exist, or"
                    + " the description of a " + command + " cannot be empty.");
        }

        if (command.equals("mark") || command.equals("unmark") || command.equals("to-do") || command.equals("delete")) {
            // Create an array with 1 element (without using split.)
            descriptions = new String[1];
            descriptions[0] = split[1];

        } else if (command.equals("deadline")) {

            descriptions = split[1].split(" /by ", 2);

            if (descriptions.length != 2) {
                throw new JudeException("Poyo,  the command "
                        + command + " must be provided with a description with the use of /by command.");
            }

        } else if (command.equals("event")) {

            descriptions = split[1].split(" /from | /to ", 3);

            if (descriptions.length != 3) {
                throw new JudeException("Poyo,  the command " + command
                        + " must be provided with a description with the use of /from followed by /to command.");
            }

        // Handle non-existing commands.
        } else {

            throw new JudeException("Poyo, the command provided does not exist.");

        }
    }

    public String getCommand() {
        return this.command;
    }

    public String[] getDescriptions() {
        return this.descriptions;
    }
}
