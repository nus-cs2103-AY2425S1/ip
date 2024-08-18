import java.util.Arrays;

public class FormattedPrinting {

    public static void FormatPrint(String message) {
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

    public static void Welcome() {
        String welcomeMessage = "Hello I'm Mortal Reminder!\n"
                + "What can I do for you?";
        FormattedPrinting.FormatPrint(welcomeMessage);

    }

    public static void GoodBye() {
        String goodByeMessage = "Bye. Hope to see you again soon!";
        FormattedPrinting.FormatPrint(goodByeMessage);
    }

    public static void printList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            emptyList();
        }
        else {
            StringBuilder currentList = new StringBuilder();
            currentList.append("Here are the tasks in your list:\n");
            for (int i = 1; i < taskList.getSize() + 1; i++) {
                currentList.append(Integer.toString(i)).append(".").append(printTask(taskList.getTask(i - 1)));
                if (i < taskList.getSize()) {
                    currentList.append("\n");
                }
            }
            FormatPrint(currentList.toString());
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
        FormatPrint(message);
    }

    public static void printMarked(Task task) {
        FormatPrint("Nice!  I've marked this task as done:\n" + printTask(task));
    }

    public static void printUnmarked(Task task) {
        FormatPrint("OK, I've marked this task as not done yet:\n" + printTask(task));
    }

    public static void descriptionEmptyError() {
        FormatPrint("You have an empty description. Please try again.");
    }

    public static void markError() {
        FormatPrint("This task has already been marked as done.");
    }

    public static void unmarkError() {
        FormatPrint("This task has already been marked as not done.");
    }

    public static void unknownCommand() {
        String outputMessage = "I do not recognise this command, please check again! \n"
                + "Available commands are:\n"
                + Arrays.toString(CommandTypes.class.getEnumConstants()).toLowerCase();
        FormatPrint(outputMessage);
    }

    public static void unknownNumber() {
        FormatPrint("Please enter a valid number after the command!");
    }

    public static void emptyList() {
        FormatPrint("You have no tasks in your list.");
    }

    public static void outOfListBounds(TaskList taskList) {
        FormatPrint("Invalid task number!\n"
                + "Please input a number between 1 and "
                + taskList.getSize());
    }

    public static void invalidNumberOfDetails() {
        FormatPrint("Please give the correct amount of information for the command!");
    }

}
