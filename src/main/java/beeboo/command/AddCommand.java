package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.InvalidDateException;
import beeboo.exception.NoDescriptionException;
import beeboo.task.Deadlines;
import beeboo.task.Events;
import beeboo.task.ToDos;

public class AddCommand extends Command{
    private String type;
    private String command;
    public AddCommand(String type, String command) {
        super(command);
        this.command = command;
        this.type = type;
    }

    /**
     *
     * @param tasks tasklist of the chatbot
     * @param ui ui of the chatbot
     * @param storage storage function of chatbot
     * @throws InvalidDateException if date formatting is wrong
     * @throws NoDescriptionException if there is no description
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException, NoDescriptionException {
        switch(type) {
        case "e":
            Events event = Events.createEvent(command);
            tasks.addList(event);
            ui.addList(event, tasks.getSize());
            storage.saveItem(tasks);
            break;
        case "t":
            ToDos todo = ToDos.createToDo(command);
            tasks.addList(todo);
            ui.addList(todo, tasks.getSize());
            storage.saveItem(tasks);
            break;
        case "d":
            Deadlines deadline = Deadlines.createDeadline(command);
            tasks.addList(deadline);
            ui.addList(deadline, tasks.getSize());
            storage.saveItem(tasks);
            break;
        }

    }

    /**
     *
     * @return true if is exit command false if not exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
