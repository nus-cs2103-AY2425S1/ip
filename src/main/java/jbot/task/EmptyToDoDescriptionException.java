package jbot.task;

import jbot.JBotException;

public class EmptyToDoDescriptionException extends JBotException {
    public EmptyToDoDescriptionException(String message) {
        super(message);
    }
}
