package lemon.command;

import lemon.exception.InvalidFormatException;

public abstract class CommandWithInput extends Command {
    protected String input;
    public CommandWithInput(CommandType ct, String input) {
        super(ct);
        this.input = input;
    }
    
    public abstract String[] processInput() throws InvalidFormatException;
}
