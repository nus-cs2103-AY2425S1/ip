package elara.command;

import elara.storage.Storage;

import elara.task.*;

import elara.ui.Ui;

public class AddCommand implements Command {
    private final String commandType;
    private final String fullInput;

    public AddCommand(String commandType, String fullInput) {
        this.commandType = commandType;
        this.fullInput = fullInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        Task newTask = null;

        switch (commandType) {
            case "todo":
                if (fullInput.equals("todo")) {
                    throw new ToDoException();
                }
                newTask = new ToDoTask(fullInput.split(" ", 2)[1]);
                break;
            case "deadline":
                if (fullInput.equals("deadline")) {
                    throw new DeadlineException();
                }
                String[] deadlineArgs = fullInput.split(" ", 2)[1].split(" /by ");
                if (deadlineArgs.length == 2) {
                    newTask = new DeadlineTask(deadlineArgs[0].trim(), deadlineArgs[1].trim());
                }
                throw new DeadlineException();
            case "event":
                if (fullInput.equals("event")) {
                    throw new EventException();
                }
                String[] eventArgs = fullInput.split(" ", 2)[1].split("/from |/to ");
                if (eventArgs.length == 3) {
                    newTask = new EventTask(eventArgs[0].trim(), eventArgs[1].trim(), eventArgs[2].trim());
                }
                throw new EventException();

        }

        taskList.addTask(newTask);
        ui.showAddTaskMessage(newTask, taskList);
        storage.write(taskList);
    }
}