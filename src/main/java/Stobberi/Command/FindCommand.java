package Stobberi.Command;

import Stobberi.StobberiException.StobberiException;
import Stobberi.StobberiException.WrongDateTimeStobberiException;
import Stobberi.components.TaskList;

import java.time.format.DateTimeParseException;

public class FindCommand extends Command {
    private TaskList taskList;
    private String word;
    public FindCommand(TaskList taskList, String word) {
        this.taskList = taskList;
        this.word = word;
    }

    @Override
    public void execute() throws StobberiException {
        taskList.filterListByWord(word);
    }
}