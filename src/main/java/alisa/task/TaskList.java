package alisa.task;

import java.util.ArrayList;

import alisa.exception.AlisaException;


public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an instance of TaskList.
     * Declares an empty ArrayList as taskList.
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs an instance of TaskList.
     * Declares an ArrayList with Tasks as taskList.
     *
     * @param tasks ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Deletes a task from taskList.
     *
     * @param index Index of task in taskList to delete.
     * @return String confirming task is removed and number of tasks left in the list.
     * @throws AlisaException If index input is out of bounds in taskList.
     */
    public String deleteTask(int index) throws AlisaException {
        try {
            Task removedTask = taskList.remove(index - 1);
            return "Noted. I've removed this task:\n" + removedTask
                    + "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new AlisaException("The task you want to delete doesn't exist!");
        }
    }

    /**
     * Marks a task as complete.
     *
     * @param index Index of task in taskList to mark as done.
     * @return String confirming task is marked as done.
     * @throws AlisaException If index input is out of bounds in taskList.
     */
    public String markTask(int index) throws AlisaException {
        try {
            taskList.get(index - 1).markAsDone();
            return "Nice! I've marked this task as done:\n" + taskList.get(index-1);
        } catch (IndexOutOfBoundsException e) {
            throw new AlisaException("The task you want to mark as done doesn't exist!");
        }
    }

    /**
     * Marks a task as incomplete.
     *
     * @param index Index of task in taskList to mark as not done.
     * @return String confirming task is marked as not done.
     * @throws AlisaException If index input is out of bounds in taskList.
     */
    public String unmarkTask(int index) throws AlisaException {
        try {
            taskList.get(index - 1).markAsNotDone();
            return "OK, I've marked this task as not done yet:\n" + taskList.get(index-1);
        } catch (IndexOutOfBoundsException e) {
            throw new AlisaException("The task you want to mark as not done doesn't exist!");
        }
    }

    /**
     * Adds a task into taskList.
     *
     * @param task Task to add into taskList
     * @return String confirming the task is added into taskList and number of tasks in the list.
     */
    public String addTask(Task task) {
        taskList.add(task);
        return "Got it. I've added this task:\n" + task + "\nNow you have "
                + taskList.size() + " tasks in the list.";
    }

    /**
     * Returns all the tasks in taskList.
     *
     * @return String of the list of all tasks and their details.
     */
    public String getList() {
        String message = "";
        if (taskList.isEmpty()) {
            message = "The list is empty, sorry :(";
        } else {
            for (int i = 1; i <= taskList.size(); i++) {
                message += i + "." + taskList.get(i-1) + "\n";
            }
        }
        return message;
    }

    /**
     * Returns all tasks in taskList in format for txt file.
     *
     * @return String of all tasks and their details in txt file format.
     */
    public String convertToFileString() {
        String fileText = "";
        for (Task task : taskList) {
            fileText += task.convertToFileString();
        }
        return fileText;
    }

    /**
     * Lists all the tasks that contains the keyword in their task descriptions.
     *
     * @param keyword Keyword to search for in each task description.
     * @return String listing all the tasks that contains the keyword in their descriptions.
     */
    public String findTasks(String keyword) {
        String tasks = "Here are the matching tasks in your list:\n";
        int counter = 1;
        for (Task task : taskList) {
            if (task.containsWord(keyword)) {
                tasks += counter + ". " + task + "\n";
                counter += 1;
            }
        }
        return tasks;
    }

    /**
     * Edits a task's details.
     *
     * @param index Index of task in taskList to edit.
     * @param featureToEdit Feature of the task to edit.
     * @param editedContent Content to edit the task to.
     * @return String that confirms editing the task has been completed.
     * @throws AlisaException If the index is out of bounds in taskList.
     */
    public String editTask(int index, String featureToEdit, String editedContent) throws AlisaException {
        try {
            Task taskToEdit = taskList.get(index - 1);
            if (taskToEdit.getClass() == Todo.class) {
                Todo editingTask = (Todo) taskToEdit;

                switch (featureToEdit) {
                case "description":
                    editingTask.changeDescription(editedContent);
                    break;
                default:
                    throw new AlisaException("That's not a feature that can be edited for this task");
                }
            } else if (taskToEdit.getClass() == Deadline.class) {
                Deadline editingTask = (Deadline) taskToEdit;

                switch (featureToEdit) {
                case "description":
                    editingTask.changeDescription(editedContent);
                    break;
                case "deadline":
                    editingTask.changeDueDate(editedContent);
                    break;
                default:
                    throw new AlisaException("That's not a feature that can be edited for this task");
                }
            } else {
                Event editingTask = (Event) taskToEdit;

                switch (featureToEdit) {
                case "description":
                    editingTask.changeDescription(editedContent);
                    break;
                case "start":
                    editingTask.changeStartTime(editedContent);
                    break;
                case "end":
                    editingTask.changeEndTime(editedContent);
                    break;
                default:
                    throw new AlisaException("That's not a feature that can be edited for this task");
                }
            }

            return "Task #" + index + " has been successfully edited. Now the task looks like this:\n"
                    + taskList.get(index);

        } catch (IndexOutOfBoundsException e) {
            throw new AlisaException("There isn't a task that belongs to the index in the task list.");
        }
    }

}
