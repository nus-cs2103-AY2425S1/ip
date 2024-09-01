package jbot.command;

import jbot.JBotException;

public class InvalidCommandException extends JBotException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
