public class Parser {

    // This method will return true if the user wants to exit the program
    public static boolean checkCommand(String input, TaskList taskList) {
        if (Command.checkEqualCommand(input, "bye")) {
            FormattedPrint.bye();
            return true;

        } else if (Command.checkCommand(input, "mark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsDone(index);
                FormattedPrint.doneTask(taskList.getTask(index));
            } catch (Exception e) {
                FormattedPrint.invalidMarkCommand();
            }

        } else if (Command.checkCommand(input, "unmark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsUndone(index);
                FormattedPrint.undoneTask(taskList.getTask(index));
            } catch (Exception e) {
                FormattedPrint.invalidUnmarkCommand();
            }

        } else if (Command.checkCommand(input, "delete")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                FormattedPrint.deleteTask(taskList.getTask(index), taskList.getSize());
                taskList.deleteItem(index);
            } catch (Exception e) {
                FormattedPrint.invalidDeleteCommand();
            }

        } else if (Command.checkCommand(input, "deadline")) {
            try {
                // GitHub Copilot suggested the following code snippet
                String description = input.split(" ", 2)[1].split(" /by ")[0];
                String by = input.split(" /by ")[1];
                taskList.addItem(new Deadline(description, by));
                FormattedPrint.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                FormattedPrint.invalidDeadlineCommand();
            }

        } else if (Command.checkCommand(input, "event")) {
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

        } else if (Command.checkCommand(input, "todo")) {
            try {
                String description = input.split(" ", 2)[1];
                taskList.addItem(new Todo(description));
                FormattedPrint.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                FormattedPrint.invalidTodoCommand();
            }

        } else if (Command.checkEqualCommand(input, "list")) {
            FormattedPrint.listTasks(taskList.getList());
        } else {
            // Any other command will be considered invalid
            FormattedPrint.invalidCommand();
        }
        return false;
    }
}
