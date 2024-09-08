package hinlok.command;

import hinlok.exceptions.HinlokException;
import hinlok.file.TaskFile;
import hinlok.tasks.TaskList;
import hinlok.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, TaskFile taskFile) throws HinlokException;

    public boolean isExit(){
        return false;
    }
}

