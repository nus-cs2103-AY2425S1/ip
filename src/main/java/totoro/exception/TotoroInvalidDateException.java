package totoro.exception;

import totoro.command.CommandType;

public class TotoroInvalidDateException extends TotoroException {
    private CommandType command;

    public TotoroInvalidDateException(CommandType command) {
        this.command = command;
    }

    @Override
    public String toString() {
        String message = "%s Invalid date. Please follow: %s";
        switch (command) {
            case DEADLINE:
                return String.format(message, super.toString(), "deadline <description> /by <dd/MM/yy HH:mm>");
            case EVENT:
                return String.format(message, super.toString(), "event <description> /from <dd/MM/yy HH:mm> /to <dd/MM/yy HH:mm>");
            default:
                return super.toString() + "Invalid date";
        }
    }
}
