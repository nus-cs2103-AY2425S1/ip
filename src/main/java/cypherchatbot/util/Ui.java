package cypherchatbot.util;

import java.util.ArrayList;

import cypherchatbot.task.Task;


/**
 *  The UI class handles all interactions with the user for the Cypher Chat Bot Application such as
 *  reading user commands from the console, displaying various outputs depending on the command. All user
 *  interaction in the ChatBot occurs in this class.
 * */
public class Ui {

    /**
     * Initializes a new scanner instance for reading user input from the console.
     * */
    public Ui() {
    }


    /**
     * Displays a goodbye message to the user. The method being called indicates
     * that the application has ended and the scanner is closed.
     */
    public String goodBye() {
        return "Bye! See you again. This application will close in 3 seconds";
    }

    /**
     * Displays an error message if there was an issue with the specified file path.
     *
     * @param filePath The file path that could not be loaded/found/created.
     */
    public String showLoadingError(String filePath) {
        return String.format("Given filepath [%s] does not work. Please try again", filePath);
    }

    /**
     * Displays an error message to the user.
     *
     * @param e The String error message to be displayed.
     */
    public String showError(String e) {
        return e;
    }

    /**
     * Displays a confirmation message to the user that a task has been added.
     *
     * @param addedTask the task that was added.
     * @param totalTaskSize the number of total tasks in the tasklist
     */
    public String showAddMessage(Task addedTask, int totalTaskSize) {
        return String.format("Got it. I have added this task:\n  %s\nNow you have %d tasks in the list\n\n",
                addedTask, totalTaskSize);
    }

    /**
     * Displays a confirmation message to the user that a task has been deleted.
     *
     * @param deletedTask the task that was deleted.
     * @param totalTaskSize the number of total tasks in the tasklist
     */
    public String showDeleteMessage(Task deletedTask, int totalTaskSize) {
        return "Noted! I have removed this task:\n " + deletedTask + "\n\n"
                + String.format("Now you have %d tasks in the list%n", totalTaskSize);
    }

    /**
     * Displays a filtered task list to the user
     *
     * @param filteredList the filtered tasklist that was deleted.
     */
    public String showFilterMessage(ArrayList<Task> filteredList) {
        if (filteredList.isEmpty()) {
            return "You have no items in your list matching the given string";
        } else {
            StringBuilder str = new StringBuilder();
            str.append("Here are the items in your list that match the search:\n");
            for (int i = 0; i < filteredList.size(); i++) {
                str.append((i + 1)).append(". ").append(filteredList.get(i)).append("\n");
            }
            str.append("\n");
            return str.toString();
        }
    }

    /**
     * Displays a help message to the user that shows all avaiable commands,
     * a brief summary and the correct format for the command.
     */
    public String showHelpMessage() {
        StringBuilder str = new StringBuilder();
        String linebreak = "========================================================================="
                                + "=====================================================================\n";
        str.append("Here are the list of available commands with format and a short description:\n");
        str.append("\n");
        str.append("Use the todo command in order to add a todo task (no time)\n");
        str.append("Format of the command: todo <task description>\n");
        str.append(linebreak);
        str.append("Use the deadline command in order to add a deadline task (have a certain time to finish by)\n");
        str.append("Format of the command: deadline <Description of task> /by yyyy-MM-dd HH:mm\n");
        str.append(linebreak);
        str.append("Use the event command in order to add a event task (have a certain time frame for the task)\n");
        str.append("Format of the command: event <Description of task> /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm\n");
        str.append(linebreak);
        str.append("Use the list command to view a list of all the tasks that have been added\n");
        str.append("Format of the command: list\n");
        str.append(linebreak);
        str.append("Use the sort command to the view a list of all the tasks sorted according to time");
        str.append("Format of the 2 different sort commands:\n");
        str.append(" > sort ascending (earliest task at the top)\n");
        str.append(" > sort descending (latest task at the top)\n");
        str.append(linebreak);
        str.append("Use the mark command to mark a certain task as completed\n");
        str.append("Format of the mark command is: mark <task number (based on the list view)>\n");
        str.append(linebreak);
        str.append("Use the unmark command to mark a certain task as not complete\n");
        str.append("Format of the unmark command is: unmark <task number (based on the list view)>\n");
        str.append(linebreak);
        str.append("Use the delete command to delete a certain task");
        str.append("Format of the unmark command is: unmark <task number (based on the list view)>\n");
        str.append(linebreak);
        str.append("CAUTION: FOR MARK,UNMARK AND DELETE COMMANDS USE THE NUMBER SHOWN IN THE LIST COMMAND,");
        str.append("DO NOT USE THE NUMBER SHOWN AFTER USING THE SORT COMMAND\n");
        str.append(linebreak);
        str.append("Use the find command to a find a certian task using a phrase/time");
        str.append("Format of the find command is: find <phrase/time that you want to find>");
        str.append("\n");
        return str.toString();
    }

    /**
     * Displays the entire tasklist to the user
     *
     * @param taskList the entire tasklist to be displayed.
     */
    public String showList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "You have no items in your list:";
        } else {
            StringBuilder str = new StringBuilder();
            str.append("Here are the items in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                str.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
            }
            str.append("\n");
            return str.toString();
        }
    }

    /**
     * Displays a confirmation message to the user that a task has been marked as complete.
     *
     * @param markedTask the task that was marked as complete.
     */
    public String showMarkedMessage(Task markedTask) {
        return "Nice! I have marked this task as completed:\n " + markedTask;
    }

    /**
     * Displays a confirmation message to the user that a task has been marked as uncompleted.
     *
     * @param unmarkedTask the task that was marked as not completed.
     */
    public String showUnmarkedMessage(Task unmarkedTask) {
        return "Ok! I have marked this task as incomplete:\n " + unmarkedTask;
    }

    /**
     * Displays the entire tasklist to the user in ascending order based on time
     *
     * @param sortedList the tasklist to be displayed
     */
    public String showAscendingMessage(ArrayList<Task> sortedList) {
        if (sortedList.isEmpty()) {
            return "You have no tasks to sort. Add some tasks";
        } else {
            StringBuilder str = new StringBuilder();
            str.append("\nHere is your tasks sorted in ascending order separated by type:\n");
            for (int i = 0; i < sortedList.size(); i++) {
                str.append((i + 1)).append(". ").append(sortedList.get(i)).append("\n");
            }
            str.append("\n");
            return str.toString();
        }
    }


    /**
     * Displays the entire tasklist to the user in descending order based on time
     *
     * @param sortedList the tasklist to be displayed
     */
    public String showDescendingMessage(ArrayList<Task> sortedList) {
        if (sortedList.isEmpty()) {
            return "You have no tasks to sort. Add some tasks";
        } else {
            StringBuilder str = new StringBuilder();
            str.append("\nHere is your tasks sorted in descending order separated by type:\n");
            for (int i = 0; i < sortedList.size(); i++) {
                str.append((i + 1)).append(". ").append(sortedList.get(i)).append("\n");
            }
            str.append("\n");
            return str.toString();
        }
    }


}
