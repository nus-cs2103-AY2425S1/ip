package blacknut.ui;

import blacknut.ui.BlacknutExceptions.*;

class Parser {
    public String parseCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }

    public int parseIndex(String input) throws BlacknutExceptions.InvalidTaskNumberException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new BlacknutExceptions.InvalidTaskNumberException("Invalid task number. Please provide a valid number.");
        }
    }

    public String parseDescription(String input, String command) throws BlacknutExceptions.EmptyDescriptionException {
        String description = input.substring(command.length()).trim();
        if (description.isEmpty()) {
            throw new BlacknutExceptions.EmptyDescriptionException("The description cannot be empty.");
        }
        return description;
    }

    public String[] parseDeadline(String input) throws BlacknutExceptions.IncorrectFormatException {
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BlacknutExceptions.IncorrectFormatException("The format for a deadline should be: deadline <description> /by <yyyy-MM-dd HH:mm>");
        }
        return new String[]{parts[0].trim(), parts[1].trim()};
    }

    public String[] parseEvent(String input) throws BlacknutExceptions.IncorrectFormatException {
        String[] parts = input.substring(6).split(" /from | /to ");
        if (parts.length != 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new BlacknutExceptions.IncorrectFormatException("The format for an event should be: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        }
        return new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim()};
    }

    public String parseKeyword(String input) {
        return input.substring(5).trim();
    }
}
