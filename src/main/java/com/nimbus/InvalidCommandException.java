package com.nimbus;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String msg) {
        super(msg + ": Not a valid command.");
    }
}
