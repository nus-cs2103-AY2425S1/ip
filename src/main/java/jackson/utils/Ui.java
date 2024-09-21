package jackson.utils;

import java.util.ArrayList;

import jackson.tasks.Task;

/**
 * Class to handle UI interaction.
 * Includes printing most exceptions and displaying task lists, etc.
 */
public class Ui {

    /**
     * Constructs Ui instance.
     */
    public Ui() { }

    /**
     * Returns welcome message at the start of the chatbot program.
     * @return String representation of welcome.
     */
    public String printWelcome() {
        return "Oi! I'm Jackson!\nWhat you want me do today ah?";
    }

    /**
     * Returns goodbye message at the end of the chatbot program.
     * @return String representation of goodbye.
     */
    public String printGoodbye() {
        return "K k bye, see you!";
    }

    /**
     * Returns a list of available commands and basic formats.
     * @return String response.
     */
    public String printCommandList() {
        return """
                Here's the list of supported commands:
                1. todo [task-name]
                2. deadline [task-name] /by [due-date]
                3. event [task-name] /from [start-date] /to [end-date]
                4. mark [index]
                5. unmark [index]
                6. list
                7. find
                8. sort [attribute] [/a or /d]
                9. bye
                You can enter 'help [command]' for more details!""";
    }

    /**
     * Returns command list during an unrecognised command.
     * @return String response.
     */
    public String printUnrecognizedMessage() {
        return """
                Harh? What you talking about?
                """ + printCommandList();
    }

    /**
     * Returns format of command based on which command user incorrectly enters.
     * Unlike printFormatGuide, this is only if user enters command in incorrect format.
     * @param category Command category that is entered incorrectly.
     * @return String response.
     */
    public String printWrongFormat(String category) {
        String output = """
                Wrong format leh...
                """;
        return output + printFormatGuide(category);
    }

    /**
     * Returns format of command when user requests for help.
     * @param category Command category that is requested
     * @return String response.
     */
    public String printFormatGuide(String category) {
        String output = String.format("More about %s:\n", category);

        // category is the type of command that the user incorrectly enters the format of
        // print the correct command format based on the user input
        switch (category) {
        case "todo":
            output += """
                        --> todo [task-name]
                    Creates a todo task.
                    No dates allowed at all.""";
            break;
        case "deadline":
            output += """
                        --> deadline [task-name] /by [by-date]
                    Creates a deadline task.
                    All dates must be in DD-MM-YYYY HH:MM format (HH:MM is optional)""";
            break;
        case "event":
            output += """
                        --> event [task-name] /from [from-date] /to [to-date]
                    Creates an event class.
                    All dates must be in DD-MM-YYYY HH:MM format (HH:MM is optional)
                    [from-date] must be after [to-date]""";
            break;
        case "list":
            output += """
                        --> list
                    Lists all tasks.""";
            break;
        case "mark":
            output += """
                        --> mark [index]
                    Marks task at index [index] as complete.""";
            break;
        case "unmark":
            output += """
                        --> unmark [index]
                    Marks task at index [index] as incomplete.""";
            break;
        case "delete":
            output += """
                        --> delete [index]
                    Deletes task at index [index].""";
            break;
        case "find":
            output += """
                        --> find [keywords]
                    Finds tasks that contain the keyword(s) in their names.""";
            break;
        case "help":
            output += """
                        --> help [command]
                    Display usage help for [command]""";
            break;
        case "bye":
            output += """
                        --> bye
                    Exits the chatbot.""";
            break;
        case "sort":
            output += """
                        --> sort [attribute] [/a or /d]
                    where attribute is one of: 'name', 'startdate', 'enddate', 'tasktype', 'status'""" + """
                    and /a is ascending, whilst /d is for descending""";
            break;
        default:
            output += "Unknown error...";
            break;
        }
        return output;
    }

    /**
     * Returns valid indices when invalid index is entered.
     * @param taskList {@code OutOfListException} exception encountered.
     * @return String response.
     */
    public String printIndexGuide(TaskList taskList) {
        int size = taskList.getSize();
        String output = String.format("Alamak, you got %d items on the list only leh...\n", size);

        // different responses based on taskList size
        if (size == 0) {
            output += "    --> You've got no items in the list! Add some stuff first!";
        } else if (size == 1) {
            output += "    --> Enter 1 to mark/unmark/delete the task in the list!";
        } else {
            output += String.format("    --> Enter a number between 1 and %d when marking/unmarking/deleting tasks!",
                    size);
        }
        return output;
    }

    /**
     * Returns response from Jackson to deconflict identical task names.
     * @param taskName String of task name that clashes with pre-existing one in task list.
     * @return String response.
     */
    public String printDeconflictAdvice(String taskName) {
        return String.format("""
                Eh I think you already got this task name already right???
                    --> %s already exists
                To deconflict, consider deleting the respective task in the task list"""
                + "or renaming the current task before adding!", taskName);
    }

    /**
     * Returns response from Jackson when inputted from date is after the inputted to date for the event task.
     * @return String response.
     */
    public String printInvalidDates() {
        return "[from-date] cannot be after [to-date]}!\n" + printFormatGuide("event");
    }

    /**
     * Returns generic error message when other Exceptions occur.
     * @param e {@code Exception} encountered during runtime.
     * @return String response.
     */
    public String printUnknownError(Exception e) {
        System.out.println(e.toString());
        return "Oops! Something went wrong! Check System.out!";
    }

    /**
     * Returns chatbot response when adding task to list.
     * @param task {@code Task} object to be added to taskList.
     * @param taskList {@code TaskList} object task is to be added to.
     * @return String response.
     */
    public String printAfterAddList(Task task, TaskList taskList) {
        String output = "";
        output += "Ya la, adding this task to your list!\n";
        output += String.format("\t%s\n", task);
        output += String.format("You now got %s tasks in your list leh\n", taskList.getSize());
        return output;
    }

    /**
     * Returns chatbot response when deleting task from list.
     * @param task {@code Task} object to be deleted from taskList.
     * @param taskList {@code TaskList} object task is to be deleted from.
     * @return String response.
     */
    public String printAfterDeleteList(Task task, TaskList taskList) {
        String output = "";
        output += "Deleting now hor!\n";
        output += String.format("\t%s\n", task);
        output += String.format("You now got %s tasks in your list leh\n", taskList.getSize());
        return output;
    }

    /**
     * Returns chatbot response when finding tasks containing keyword.
     * @param tasks {@code ArrayList} of tasks that contain keyword.
     * @param keyword String keyword.
     * @return String response.
     */
    public String printAfterFindList(ArrayList<Task> tasks, String keyword) {
        String output = "";
        output += "Ok ok, find for you already...\n";
        if (tasks.isEmpty()) {
            output += String.format("Don't have tasks that match '%s' leh....\n", keyword);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                output += String.format("%d. %s\n", i + 1, tasks.get(i));
            }
        }
        return output;
    }

    /**
     * Returns chatbot response when marking task in list.
     * @param task {@code Task} object to be marked.
     * @return String response.
     */
    public String printAfterMark(Task task) {
        String output = "";
        output += "Solid lah, marked already\n";
        output += String.format("\t%s\n", task);
        return output;
    }

    /**
     * Returns chatbot response when unmarking task in list.
     * @param task {@code Task} object to be unmarked.
     * @return String response.
     */
    public String printAfterUnmark(Task task) {
        String output = "";
        output += "Walao, ok la I unmark already...\n";
        output += String.format("\t%s\n", task);
        return output;
    }

    /**
     * Returns chatbot response when requesting to display the list.
     * @param taskList {@code TaskList} object to print out the string representation of.
     * @return String response.
     */
    public String printList(TaskList taskList) {
        return "Ya la wait la, here's your list!\n" + taskList.toString();
    }

    /**
     * Returns chatbot response after sorting list.
     * @param taskList {@code TaskList} object to print out the string representation of.
     * @return String response.
     */
    public String printSortedList(TaskList taskList) {
        return "Super troublesome but sort your list liao!\n" + taskList.toString();
    }

    /**
     * Returns chatbot response when save file cannot be saved to.
     * @return String response.
     */
    public String printFileIssue() {
        return "File error! Please exit the chatbot and check your file permissions and format!\n";
    }
}
