package main;

import exception.DukeException;
import task.Task;

/**
 * The parser is used to breakdown user input and does different actions depending
 * on the input.
 */
public class Parser {
    TaskList taskList;
    Ui ui;

    Storage storage;
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    };

    /**
     * Takes in response from user and runs different functions based on the input
     * If it is a terminating command, returns false to break the loop
     * @param response user input
     * @return whether the program continues to run
     */
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
        case "find" :
            handleSearch(splitResponse[1]);
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
    /**
     * Handles addition of task into tasklist depending on the input
     * @param splitResponse details of the task
     */
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

    /**
     * Handles the searching of word
     * @param word String to be found
     */
    public void handleSearch(String word) {
        taskList.searchTask(word);
    }


    /**
     * Handles the logic of marking a task as done
     * @param description index of task in list
     */
    public void handleMark(String description) {
        int index = Integer.parseInt(description) - 1;
        Task marked = taskList.mark(index);
        if (marked != null) {
            ui.displayMarkedMessage(marked);
        }
    }

    /**
     * Handles the logic of unmarking a task as done
     * @param description index of task in list
     */
    public void handleUnmark(String description) {
        int index = Integer.parseInt(description) - 1;
        Task unmarked = taskList.unmark(index);
        if (unmarked != null) {
            ui.displayUnmarkedMessage(unmarked);
        }
    }
    /**
     * Handles the logic of deleting a task from task list
     * @param description index of task in list
     */
    public void handleDelete(String description) {
        int index = Integer.parseInt(description) - 1;
        Task deleted = taskList.delete(index);
        if (deleted != null) {
            ui.displayDeletedMessage(deleted, taskList.size());
        }
    }

    /**
     * Handles the logic when program terminating input is given
     * @return whether the program should continue to run
     */
    public boolean handleBye() {
        this.storage.writeStorage(taskList.getTaskList());
        ui.displayByeMessage();
        return false;
    }
}
