enum Command {
    BYE,
    MARK,
    UNMARK,
    DELETE,
    DEADLINE,
    EVENT,
    TODO,
    LIST,
}


public class Parser {
    // This method will return true if the user wants to exit the program
    public static boolean checkCommand(String input, TaskList taskList) {
        if (CommandParser.checkEqualCommand(input, Command.BYE.toString())) {
            FormattedPrint.bye();
            return true;

        } else if (CommandParser.checkCommand(input, Command.MARK.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsDone(index);
                FormattedPrint.doneTask(taskList.getTask(index));
            } catch (Exception e) {
                FormattedPrint.invalidMarkCommand();
            }

        } else if (CommandParser.checkCommand(input, Command.UNMARK.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsUndone(index);
                FormattedPrint.undoneTask(taskList.getTask(index));
            } catch (Exception e) {
                FormattedPrint.invalidUnmarkCommand();
            }

        } else if (CommandParser.checkCommand(input, Command.DELETE.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                FormattedPrint.deleteTask(taskList.getTask(index), taskList.getSize());
                taskList.deleteItem(index);
            } catch (Exception e) {
                FormattedPrint.invalidDeleteCommand();
            }

        } else if (CommandParser.checkCommand(input, Command.DEADLINE.toString())) {
            try {
                // GitHub Copilot suggested the following code snippet
                String description = input.split(" ", 2)[1].split(" /by ")[0];
                String by = input.split(" /by ")[1];
                taskList.addItem(new Deadline(description, by));
                FormattedPrint.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                FormattedPrint.invalidDeadlineCommand();
            }

        } else if (CommandParser.checkCommand(input, Command.EVENT.toString())) {
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

        } else if (CommandParser.checkCommand(input, Command.TODO.toString())) {
            try {
                String description = input.split(" ", 2)[1];
                taskList.addItem(new Todo(description));
                FormattedPrint.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                FormattedPrint.invalidTodoCommand();
            }

        } else if (CommandParser.checkEqualCommand(input, Command.LIST.toString())) {
            FormattedPrint.listTasks(taskList.getList());
        } else {
            // Any other command will be considered invalid
            FormattedPrint.invalidCommand();
        }
        return false;
    }
}
