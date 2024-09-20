package johncena.commands;

import johncena.exceptions.CenaException;

public interface Command {

    void execute() throws CenaException;
}
