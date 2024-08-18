public class FormattedPrinting {

    public static void FormatPrint(String message) {
        String indentation = "   ";
        String separatorLine = indentation + "__________________________________";
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

    public static void printList(TaskList taskList) {
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

}
