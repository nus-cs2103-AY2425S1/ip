package com.exceptions;

/**
 * Invalid command error for Nimbus
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String msg) {
        super(msg + ": Not a valid command.");
    }
}
