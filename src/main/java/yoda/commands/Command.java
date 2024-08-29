package yoda.commands;

import yoda.exceptions.InvalidInputException;

public abstract class Command {
    public abstract void run() throws InvalidInputException;
}
