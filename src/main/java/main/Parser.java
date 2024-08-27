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
    public boolean parse(String response) {
        String[] splited = response.split(" ",2);
        if (splited[0].equals("todo") || splited[0].equals("event") || splited[0].equals("deadline")) {
            handleAdd(splited);
        } else if (splited[0].equals("list")) {
            taskList.list();
        } else if (splited[0].equals("mark")) {
            handleMark(splited[1]);
        } else if (splited[0].equals("unmark")) {
            handleUnmark(splited[1]);
        } else if (splited[0].equals("delete")) {
            handleDelete(splited[1]);
        } else if (splited[0].equals("bye")) {
            return handleBye();
        } else {
            try {
                throw new DukeException("I dont understand what you are trying to say :(");
            } catch (DukeException e) {
                System.out.println("________________________________");
                System.out.println(e.getMessage() + "\n________________________________");
            }
        }
        return true;
    }

    /**
     * Handles addition of task into tasklist depending on the input
     * @param splits details of the task
     */
    public void handleAdd(String[] splits) {
        Task current = null;
        try {
            switch (splits[0]) {
                case "todo":
                    current = taskList.addTodo(splits[1]);
                    ui.addMessage(current,taskList.size());
                    break;
                case "event":
                    current = taskList.addEvent(splits[1]);
                    ui.addMessage(current,taskList.size());
                    break;
                case "deadline":
                    current = taskList.addDeadline(splits[1]);
                    ui.addMessage(current,taskList.size());
                    break;
            }
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "________________________________");
        }
    }

    /**
     * Handles the logic of marking a task as done
     * @param description index of task in list
     */
    public void handleMark(String description) {
        int index = Integer.parseInt(description) - 1;
        Task marked = taskList.mark(index);
        if (marked != null) {
            ui.markedMessage(marked);
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
            ui.unmarkedMessage(unmarked);
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
            ui.deletedMessage(deleted, taskList.size());
        }
    }

    /**
     * Handles the logic when program terminating input is given
     * @return whether the program should continue to run
     */
    public boolean handleBye() {
        this.storage.writeStorage(taskList.getTaskList());
        ui.byeMessage();
        return false;
    }
}
