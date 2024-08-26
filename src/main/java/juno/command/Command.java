package juno.command;

import juno.manager.exception.TaskManagerException;

public abstract class Command {
    public abstract void runCommand() throws TaskManagerException;

    public boolean isInWhileLoop() {
        return true;
    }
}
