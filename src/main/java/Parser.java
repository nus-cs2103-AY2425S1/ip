public class Parser {
    private String command;
    private String[] descriptions;
    private boolean isValid = true;

    public Parser(String input) throws ArrayIndexOutOfBoundsException {
        String[] split = input.split(" ", 2);
        this.command = split[0];


        if (command.equals("bye") || command.equals("list")) {
            // no descriptions
        } else if (command.equals("mark") || command.equals("unmark") || command.equals("todo")) {
            descriptions = split[1].split(" "); // Create an array with 1 element
            if (descriptions.length != 1) {
                this.isValid = false;
            }
        } else if (command.equals("deadline")) {
            descriptions = split[1].split(" /by ", 2);
            if (descriptions.length != 2) {
                this.isValid = false;
            }
        } else if (command.equals("event")) {
            descriptions = split[1].split(" /from | /to ", 3);
            if (descriptions.length != 3) {
                this.isValid = false;
            }
        } else {
            this.isValid = false;
        }

    }

    public String getCommand() {
        return this.command;
    }

    public String[] getDescriptions() {
        return this.descriptions;
    }

    public boolean getIsValid() {
        return isValid;
    }
}
