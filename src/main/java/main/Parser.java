package main;

import exception.DukeException;
import task.Task;

public class Parser {
    TaskList taskList;
    Ui ui;

    Storage storage;
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    };

    public boolean parse(String response) throws DukeException{
        String[] splitResponse = response.split(" ",2);
        switch (splitResponse[0]) {
        case "todo", "event", "deadline":
            handleAdd(splitResponse);
            break;
        case "list" :
            taskList.list();
            break;
        case "mark" :
            handleMark(splitResponse[1]);
            break;
        case "unmark" :
            handleUnmark(splitResponse[1]);
            break;
        case "delete" :
            handleDelete(splitResponse[1]);
            break;
        case "bye" :
            return handleBye();
        default : {
            try {
                throw new DukeException("I dont understand what you are trying to say :(");
            } catch (DukeException e) {
                System.out.println("________________________________");
                System.out.println(e.getMessage() + "\n________________________________");
            }
        }
        }
        return true;
    }
    public void handleAdd(String[] splitResponse) {
        Task currentTask = null;
        try {
            switch (splitResponse[0]) {
            case "todo":
                currentTask = taskList.addTodo(splitResponse[1]);
                ui.displayAddMessage(currentTask, taskList.size());
                break;
            case "event":
                currentTask = taskList.addEvent(splitResponse[1]);
                ui.displayAddMessage(currentTask, taskList.size());
                break;
            case "deadline":
                currentTask = taskList.addDeadline(splitResponse[1]);
                ui.displayAddMessage(currentTask, taskList.size());
                break;
            }
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "________________________________");
        }
    }

    public void handleMark(String description) {
        int index = Integer.parseInt(description) - 1;
        Task marked = taskList.mark(index);
        if (marked != null) {
            ui.displayMarkedMessage(marked);
        }
    }

    public void handleUnmark(String description) {
        int index = Integer.parseInt(description) - 1;
        Task unmarked = taskList.unmark(index);
        if (unmarked != null) {
            ui.displayUnmarkedMessage(unmarked);
        }
    }

    public void handleDelete(String description) {
        int index = Integer.parseInt(description) - 1;
        Task deleted = taskList.delete(index);
        if (deleted != null) {
            ui.displayDeletedMessage(deleted, taskList.size());
        }
    }

    public boolean handleBye() {
        this.storage.writeStorage(taskList.getTaskList());
        ui.displayByeMessage();
        return false;
    }
}
