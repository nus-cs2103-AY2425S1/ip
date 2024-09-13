package commands;

import exceptions.CenaException;

public interface Command {

    void execute() throws CenaException;
}
