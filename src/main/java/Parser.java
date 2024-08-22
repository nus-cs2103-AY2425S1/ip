public class Parser {

    // This method will return true if the user wants to exit the program
    public static boolean checkCommand(String input, TaskList taskList) {
        if (input.equalsIgnoreCase("bye")) {
            FormattedPrint.bye();
            return true;

        } else if (input.split(" ")[0].equalsIgnoreCase("mark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            taskList.markAsDone(index);
            FormattedPrint.doneTask(taskList.getTask(index));

        } else if (input.split(" ")[0].equalsIgnoreCase("unmark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            taskList.markAsUndone(index);
            FormattedPrint.undoneTask(taskList.getTask(index));

        } else if (input.toLowerCase().startsWith("deadline")) {

            // GitHub Copilot suggested the following code snippet
            String description = input.split(" ", 2)[1].split(" /by ")[0];
            String by = input.split(" /by ")[1];
            taskList.addItem(new Deadline(description, by));
            FormattedPrint.addTask(description, taskList.getSize());

        } else if (input.toLowerCase().startsWith("todo")) {
            String description = input.split(" ", 2)[1];
            taskList.addItem(new Todo(description));
            FormattedPrint.addTask(description, taskList.getSize());

        } else if (input.equalsIgnoreCase("list")) {
            FormattedPrint.listTasks(taskList.getList());
        } else {
            taskList.addItem(input);
            FormattedPrint.addTask(input, taskList.getSize());
        }
        return false;
    }
}
