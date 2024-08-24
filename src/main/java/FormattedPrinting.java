import java.util.ArrayList;
import java.util.Arrays;

public class FormattedPrinting {

    public static void formatPrint(String message) {
        String indentation = "   ";
        String separatorLine = indentation + "______________________________________________________";
        StringBuilder outputMessage = new StringBuilder(separatorLine);
        String[] messageLines = message.split("\n");
        for (String line : messageLines) {
            outputMessage.append("\n")
                    .append(indentation)
                    .append(line);
        }
        outputMessage.append("\n")
                .append(separatorLine);
        System.out.println(outputMessage.toString());
    }

    public static void welcome() {
        String welcomeMessage = "Hello I'm Mortal Reminder!\n"
                + "What can I do for you?";
        formatPrint(welcomeMessage);

    }

    public static void goodbye() {
        formatPrint("Bye. Hope to see you again soon!");
    }

    public static void printList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            emptyList();
        } else {
            StringBuilder currentList = new StringBuilder();
            currentList.append("Here are the tasks in your list:\n");
            for (int i = 1; i < taskList.getSize() + 1; i++) {
                currentList.append(i).append(".").append(printTask(taskList.getTask(i - 1)));
                if (i < taskList.getSize()) {
                    currentList.append("\n");
                }
            }
            formatPrint(currentList.toString());
        }
    }

    public static String printTask(Task task) {
        return "[" + task.getType() + "]"
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription();
    }

    public static void addTask(Task task, TaskList taskList) {
        String message = "Got it. I've added this task:\n"
                + printTask(task)
                + "\n"
                + "Now you have "
                + taskList.getSize()
                + " task(s) in the list.";
        formatPrint(message);
    }

    public static void deleteTask(Task task, TaskList taskList) {
        String message = "Got it. I've deleted this task:\n"
                + printTask(task)
                + "\n"
                + "Now you have "
                + taskList.getSize()
                + " task(s) in the list.";
        formatPrint(message);
    }

    public static void clearList() {
        formatPrint("List has been cleared.");
    }

    public static void printMarked(Task task) {
        formatPrint("Nice!  I've marked this task as done:\n" + printTask(task));
    }

    public static void printUnmarked(Task task) {
        formatPrint("OK, I've marked this task as not done yet:\n" + printTask(task));
    }

    public static void descriptionEmptyError() {
        formatPrint("You have an empty description. Please try again.");
    }

    public static void markError() {
        formatPrint("This task has already been marked as done.");
    }

    public static void unmarkError() {
        formatPrint("This task has already been marked as not done.");
    }

    public static void unknownCommand() {
        String outputMessage = "I do not recognise this command, please check again!\n"
                + "Available commands are:\n"
                + Arrays.toString(CommandTypes.class.getEnumConstants()).toLowerCase();
        formatPrint(outputMessage);
    }

    public static void unknownNumber() {
        formatPrint("Please enter a valid number after the command!");
    }

    public static void emptyList() {
        formatPrint("You have no tasks in your list.");
    }

    public static void outOfListBounds(TaskList taskList) {
        formatPrint("Invalid task number!\n"
                + "Please input a number between 1 and "
                + taskList.getSize());
    }

    public static void invalidNumberOfDetails() {
        formatPrint("Please give the correct amount of information for the command!");
    }

    public static void taskUnableToBeStoredInFile() {
        formatPrint("There was an error in adding the task to the storage file!");
    }

    public static void fileCorrupted() {
        formatPrint("The storage file has been corrupted.\n" +
                "Use the clear_tasks command to get rid of this!");
    }

    public static void invalidDate() {
        formatPrint("Please enter a valid date in dd-MM-yyy HHmm (24hr format)!.");
    }

    public static void upcomingDeadlinesEvents(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            formatPrint("There are no upcoming tasks.");
        } else {
            StringBuilder output = new StringBuilder("The following tasks are due soon:\n");
            for (Task task : tasks) {
                output.append(printTask(task)).append("\n");
            }
            formatPrint(output.toString());
        }
    }

}
