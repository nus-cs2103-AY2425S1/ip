package bro.command;

import bro.BroException;
import bro.ui.UI;

public interface Command {
    void execute(UI ui) throws BroException;
    boolean isExit();
}
