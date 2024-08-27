package com.Commands;
import com.Nimbus.*;

import java.util.ArrayList;

abstract public class Command {
    public abstract void execute(Ui ui, Storage storage, TaskList tasks)
            throws InvalidArgumentException;
}
