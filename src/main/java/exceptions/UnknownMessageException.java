package exceptions;

import config.Config;

public class UnknownMessageException extends Exception {
    public UnknownMessageException() {
        super("Unknown message :(. Please see below for the list of available commands:\n\n" + Config.commands);
    }
}
