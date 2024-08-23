package duke.commands;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.EmptyTodoDescriptionException;
import duke.exceptions.UnknownMessageException;
import duke.tasks.Task;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }
}
