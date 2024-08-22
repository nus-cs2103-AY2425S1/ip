public class Parser {

    // This method will return true if the user wants to exit the program
    public static boolean checkCommand(String input, TaskList taskList) {
        if (input.equalsIgnoreCase("bye")) {
            FormattedPrint.bye();
            return true;

        } else if (input.split(" ")[0].equalsIgnoreCase("mark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsDone(index);
                FormattedPrint.doneTask(taskList.getTask(index));
            } catch (Exception e) {
                FormattedPrint.invalidMarkCommand();
            }

        } else if (input.split(" ")[0].equalsIgnoreCase("unmark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsUndone(index);
                FormattedPrint.undoneTask(taskList.getTask(index));
            } catch (Exception e) {
                FormattedPrint.invalidUnmarkCommand();
            }

        } else if (input.split(" ")[0].equalsIgnoreCase("deadline")) {
            try {
                // GitHub Copilot suggested the following code snippet
                String description = input.split(" ", 2)[1].split(" /by ")[0];
                String by = input.split(" /by ")[1];
                taskList.addItem(new Deadline(description, by));
                FormattedPrint.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                FormattedPrint.invalidDeadlineCommand();
            }

        } else if (input.split(" ")[0].equalsIgnoreCase("event")) {
            try {
                // GitHub Copilot suggested the following code snippet
                String description = input.split(" ", 2)[1].split(" /from ")[0];
                // from is between /from and /to
                String from = input.split(" /from ")[1].split(" /to ")[0];
                String to = input.split(" /to ")[1];
                taskList.addItem(new Event(description, from, to));
                FormattedPrint.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                FormattedPrint.invalidEventCommand();
            }

        } else if (input.split(" ")[0].equalsIgnoreCase("todo")) {
            try {
                String description = input.split(" ", 2)[1];
                taskList.addItem(new Todo(description));
                FormattedPrint.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                FormattedPrint.invalidTodoCommand();
            }

        } else if (input.equalsIgnoreCase("list")) {
            FormattedPrint.listTasks(taskList.getList());
        } else {
            // Any other command will be considered invalid
            FormattedPrint.invalidCommand();
        }
        return false;
    }
}
