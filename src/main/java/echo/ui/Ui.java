package echo.ui;

import java.util.ArrayList;

import echo.task.Task;
import echo.tasklist.TaskList;

/**
 * Represents user's user interface when using the Chat Bot Echo
 *
 * @author Ernest Lim
 */
public class Ui {
    /**
     * Returns a string of greeting message
     *
     * @return a string of greeting message
     */
    public String greet() {
        String output = "Meow, I'm Echo\n";
        output += "What can I do for you?\n";
        return output;
    }

    /**
     * Returns a goodbye message
     *
     * @return a string of message saying goodbye
     */
    public String bye() {
        String output = "The tasks in the list have been saved successfully!\n";
        output += "Goodnight! Hope to see you again soon!\n";
        return output;
    }

    /**
     * Returns the list of all tasks stored in taskList as a string
     *
     * @param taskList taskList containing all the tasks stored
     * @return string od list of all tasks in taskList
     */
    public String listAllTask(TaskList taskList) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.sizeOfTaskList(); i++) {
            String task = (i + 1) + ". " + taskList.getTask(i).toString() + "\n";
            output += task;
        }
        return output;
    }

    /**
     * Returns a string of message about successful marking of task
     *
     * @param task task to be displayed in the message
     * @return string of message about successful marking of task
     */
    public String printMarkMessage(Task task) {
        String output = "Meow-velous! I've marked this task as done:\n";
        output += task.toString();
        return output;
    }

    /**
     * Returns a string of message about successful unmarking of task
     *
     * @param task task to be displayed in the message
     * @return string of message about successful unmarking of task
     */
    public String printUnmarkMessage(Task task) {
        String output = "Meow-nificent! I've marked this task as not done yet:\n";
        output += task.toString();
        return output;
    }

    /**
     * Returns a string of message of the task being added and the number of task in the list
     *
     * @param task task to be added
     * @param taskList taskList that the task is being added to
     * @return string of message after successful adding of ToDos task
     */
    public String printAddTaskMessage(Task task, TaskList taskList) {
        int numOfTask = taskList.sizeOfTaskList();
        String output = "Got it! I've added this task:\n";
        output += (task.toString() + "\n");
        output += ("Now you have " + numOfTask + " tasks in the list.\n");
        return output;
    }

    /**
     * Returns a string of message of the task being deleted and the number of task left in the list
     *
     * @param task task to be deleted
     * @param taskList taskList that the task is being deleted from
     * @return a string of message after successfully deleting task
     */
    public String printDeleteMessage(Task task, TaskList taskList) {
        int numOfTask = taskList.sizeOfTaskList();
        String output = "Got it!. I've removed this task:";
        output += (task.toString() + "\n");
        output += ("Now you have " + numOfTask + " tasks in the list.\n");
        return output;
    }

    /**
     * Returns a string of list of task found in an arrayList of Task
     *
     * @param arrayList arrayList containing a list of task
     * @return string of list of task found in an arrayList of Task
     */
    public String printFoundTask(ArrayList<Task> arrayList) {
        if (arrayList.isEmpty()) {
            return "There are no task found with the keyword.\n";
        } else {
            String output = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < arrayList.size(); i++) {
                String task = (i + 1) + "." + arrayList.get(i).toString() + "\n";
                output += task;
            }
            return output;
        }
    }

    /**
     * Return string of error message of the respective exceptions being caught
     *
     * @param exception exceptions that are caught while running the program
     * @return string of error message of the respective exceptions being caught
     */
    public String printErrorMessage(Exception exception) {
        switch (exception.getClass().getSimpleName()) {
        case "DateTimeParseException":
            String dateTimeParseOutput = "Sorry! You have provided an invalid date and time.\n";
            dateTimeParseOutput += "Try again in this format dd-MM-yyyy HHmm";
            return dateTimeParseOutput;
        case "FileNotFoundException":
            return "Sorry! File is not found, please check your file path again.";
        case "IOException":
            return "Sorry! There has been an invalid input or output found.";
        case "EchoException":
            return exception.getMessage();
        case "NumberFormatException":
            return "Sorry! You have provided an invalid number.";
        default:
            return exception.getMessage();
        }
    }

    /**
     * Returns a string of list of valid commands and how to use them
     *
     * @return string of message containing valid commands
     */
    public String printValidCommands() {
        return ("""
                1. list --> list
                   - Prints the list of task you have
                2. mark --> mark [index]
                   - Marks the task at index to be completed                   
                3. unmark --> unmark [index]
                   - Unmark the task at index to be not completed
                4. todo --> todo [task description]
                   - Adds a todo task with task description into your tasklist
                5. deadline --> deadline [task description] /by[dd-MM-yyyy HHmm]
                   - Adds a deadline task with task description 
                     and deadline into your tasklist
                6. event --> event [task description] /from [dd-MM-yyyy HHmm] /to [dd-MM-yyyy HHmm]
                   - Adds an event task with task description, start time 
                     and end time to your tasklist
                7. delete --> delete [index]
                   - Delete a task at index
                8. find --> find [keyword]
                   - Prints a list of task containing keyword in the task description
                9. bye --> bye
                   - Terminate the chat bot and save task list into txt file
                10. help --> help
                   - Prints a list of valid commands
                """);
    }
}
