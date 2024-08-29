package Stobberi.Command;

import Stobberi.StobberiException.StobberiException;

public class Command {
    private boolean isExit;
    public Command() {
        this.isExit = false;
    }
    public void execute() throws StobberiException {};
    public void setExitTrue() {
        this.isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }
}