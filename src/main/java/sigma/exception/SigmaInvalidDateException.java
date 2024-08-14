package sigma.exception;

import sigma.command.CommandType;

public class SigmaInvalidDateException extends SigmaException {
    private CommandType command;


    public SigmaInvalidDateException(CommandType command) {
        this.command = command;
    }


    @Override
    public String toString() {
        String message = "%s Invalid Date format. Usage: %s";
        switch (command) {
        case DEADLINE:
            return String.format(message, super.toString(), "deadline <description> /by DD/MM/YY HH:MM");
        case EVENT:
            return String.format(message, super.toString(),
                    "event <description> /from DD/MM/YY HH:MM /to DD/MM/YY HH:MM");
        default:
            return super.toString() + " Invalid date.";
        }
    }
}
