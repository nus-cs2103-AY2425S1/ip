package commands;

import exceptions.BottyException;

public interface Command {
    String execute(ParsedInput parsedInput) throws BottyException;
}
