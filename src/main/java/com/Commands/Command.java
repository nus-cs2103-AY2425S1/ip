package com.Commands;
import com.Nimbus.Storage;
import com.Nimbus.Task;
import com.Nimbus.Ui;

import java.util.ArrayList;

abstract public class Command {
    public abstract void execute(Ui ui, Storage storage, ArrayList<Task> tasks);
}
