package com.commands;
import com.nimbus.*;

abstract public class Command {
    public abstract void execute(Ui ui, Storage storage, TaskList tasks)
            throws InvalidArgumentException;
}
