package tecna.command;

import tecna.collection.TaskList;
import tecna.storage.Storage;
import tecna.task.ToDo;
import tecna.exception.WrongFormatException;
import tecna.ui.Ui;

public class ToDoCommand extends Command {
    public ToDoCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        ToDo toDo;
        try {
            toDo = parseToDoCommand();
        } catch (WrongFormatException e) {
            return ui.printError(e.getMessage());
        }

        assert toDo != null;
        setIsSuccessful(true);

        try {
            taskList.addItem(toDo);
        } catch (tecna.exception.TaskDuplicateException e) {
            return ui.printError(e.getMessage());
        }

        return ui.printAddItemMsg(taskList, toDo);
    }

    public ToDo parseToDoCommand() throws WrongFormatException {
        String[] description = message.split("todo");
        if (description[1].isBlank()) {
            throw new WrongFormatException("todo", "ToDo task should be of type \"todo [description]\"");
        }

        return new ToDo(description[1].trim());
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("");
        Ui ui = new Ui();
        ToDoCommand command = new ToDoCommand("todo go shopping");
        System.out.println(command.execute(taskList, storage, ui));
    }
}
