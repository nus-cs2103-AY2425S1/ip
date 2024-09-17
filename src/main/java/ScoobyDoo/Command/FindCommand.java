package ScoobyDoo.Command;

import java.time.LocalDateTime;

import ScoobyDoo.UI.UI;
import ScoobyDoo.exception.InputFormatException;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.Event;
import ScoobyDoo.task.Task;
import ScoobyDoo.task.TaskList;

public class FindCommand extends Command{
    private final String targetWord;
    public FindCommand (String targetWord) {
        this.targetWord = targetWord;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.printFindMessage(taskList.find(targetWord).printList());
    }
}