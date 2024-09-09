package Mentos;

import Mentos.MentosException.MentosException;
import Mentos.components.*;
import Mentos.task.Task;
import Mentos.task.TaskList;

public class Mentos {
    private TaskList tasks;
    private final Storage storage;

    private final GuiResponse gui;

    private final Parser parser = new Parser();

    public Mentos(String FILE_PATH) {
        this.storage = new Storage(FILE_PATH);
        this.gui = new GuiResponse();
        try {
            this.tasks = new TaskList(storage.loadTasksFromFile());
        } catch (MentosException error) {
            this.tasks = new TaskList();
        }
        assert this.tasks != null : "TaskList should be initialized.";
    }

    /**
     * Checks if the given task index is valid.
     * <p>
     * This method verifies whether the provided index is within the valid range of
     * task indices in the task list. If the index is greater than the size of the task list
     * or is zero, it throws a {@code MentosException} indicating that no such task exists.
     *
     * @param index The index of the task to check.
     * @return {@code true} if the index is valid.
     * @throws MentosException if the index is out of bounds or zero.
     */
    public boolean checkIndex(int index) throws MentosException {
        if (index > tasks.size() || index == 0) {
            throw new MentosException("No Such Tasks!");
        }
        return true;
    }

    /**
     * Marks the task at the specified index as done.
     * <p>
     * This method checks if the given index is valid and then marks the task at
     * that index as completed. The index provided is 1-based, so the method
     * internally adjusts for zero-based indexing.
     *
     * @param index The 1-based index of the task to mark as done.
     * @throws MentosException if the index is invalid.
     */
    public void markTask(int index) throws MentosException {
        if (checkIndex(index)) {
            this.tasks.get(index - 1).markAsDone();
        }
    }

    /**
     * Unmarks the task at the specified index as not done.
     * <p>
     * This method checks if the given index is valid and then marks the task at
     * that index as not completed. The index provided is 1-based, so the method
     * internally adjusts for zero-based indexing.
     *
     * @param index The 1-based index of the task to unmark as not done.
     * @throws MentosException if the index is invalid.
     */
    public void unmarkTask(int index) throws MentosException {
        if (checkIndex(index)) {
            // Assert that the task exists before marking
            assert this.tasks.get(index - 1) != null : "Task to mark as undone should exist.";
            this.tasks.get(index - 1).markAsNotDone();
        }
    }

    /**
     * Deletes the task at the specified index.
     * <p>
     * This method checks if the given index is valid and then removes the task
     * at that index from the task list. The index provided is 1-based, so the method
     * internally adjusts for zero-based indexing.
     *
     * @param index The 1-based index of the task to delete.
     * @throws MentosException if the index is invalid.
     */
    public Task deleteTask(int index) throws MentosException {
        if (checkIndex(index)) {
            Task task = this.tasks.get(index - 1);
            // Assert that the task exists before marking
            assert task != null : "Task to mark as done should exist.";
            this.tasks.remove(index - 1);
            return task;
        }
        return null;
    }

    /**
     * Searches for tasks that contain the specified description and formats the result for the GUI.
     * <p>
     * This method iterates over the list of tasks and checks if each task's description contains the
     * specified keyword. If matching tasks are found, they are formatted and appended to the result string.
     * If no matching tasks are found, a message indicating this is appended instead.
     *
     * @param description The keyword to search for in the tasks' descriptions.
     * @return A formatted string containing the matching tasks or a message indicating no matches found.
     */
    public String searchTaskGUI(String description) {
        int index = 0;
        StringBuilder res = new StringBuilder();
        for (Task t : this.tasks.getTasks()) {
            assert t != null : "Tasks in TaskList should not be null.";
            if (t.getDescription().contains(description)) {
                index++;
                res.append(gui.printEvent(index, t));
            }
        }
        res.append("\n");
        if (index == 0) {
            res.append(gui.noMatchingEvents());
        } else {
            res.append(gui.printMatchingEvents());
        }
        return res.toString();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return responseHandler(input);
    }

    /**
     * Handles user input and returns the corresponding response for the chatbot.
     *
     * <p>This method processes the input command by calling the {@code taskHandler} to determine the action
     * to be performed (e.g., list, mark, unmark, delete, find, or adding a task). It generates an appropriate
     * response based on the action and updates the task list accordingly. The response is constructed using
     * the GUI and saved to the file.</p>
     *
     * @param input The user input command, which could be "bye", "list", "mark", "unmark", "delete", "find", or a task-related command.
     * @return A string representing the chatbot's response to the user, which may include task details, error messages, or confirmation messages.
     */
    public String responseHandler(String input) {
        String response = "";
        if (input.equals("bye")) {
            return this.gui.byeUser();
        }
        try {
            ActionTaskIndexTuple actionTaskIndexTuple = parser.taskHandler(input);
            if (actionTaskIndexTuple == null) {
                return this.gui.invalidCommand();
            }
            String action = actionTaskIndexTuple.getAction();
            if (action.equals("list")) {
                response = this.gui.displayTasks(this.tasks);
            } else if (action.equals("mark")) {
                markTask(actionTaskIndexTuple.getIndex());
                response = this.gui.markEvent(this.tasks.get(actionTaskIndexTuple.getIndex() - 1));
            } else if (action.equals("unmark")) {
                unmarkTask(actionTaskIndexTuple.getIndex());
                response = this.gui.unmarkEvent(this.tasks.get(actionTaskIndexTuple.getIndex() - 1));
            } else if (action.equals("delete")) {
                Task task = deleteTask(actionTaskIndexTuple.getIndex());
                response = this.gui.deleteEvent(task, this.tasks.size());
            } else if (action.equals("find")) {
                String keyword = actionTaskIndexTuple.getTask().getDescription();
                response = searchTaskGUI(keyword);
            } else {
                this.tasks.add(actionTaskIndexTuple.getTask());
                response = this.gui.printRemainingEvents(action, actionTaskIndexTuple.getTask(), this.tasks.size());
            }
            storage.saveTasksToFile(this.tasks);
            return response;

        } catch (Exception err) {
            return err.toString();
        }
    }

}

