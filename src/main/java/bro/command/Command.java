package bro.command;

import bro.BroException;

public interface Command {
    void execute() throws BroException;
    boolean isExit();
}
