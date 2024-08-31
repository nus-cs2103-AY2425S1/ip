package Bob;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Here are the list of commands:\n" +
                "1. list\n\t- lists tasks\n" +
                "2. mark <task number>\n\t- marks the task as done\n" +
                "3. unmark <task number>\n\t- unmarks the task\n" +
                "4. deadline <task description> /by <by>\n\t- Creates a deadline\n" +
                "5. todo <task description>\n\t- Creates a todo\n" +
                "6. event <task description> /from <from> /to <to>\n\t- Creates an event\n" +
                "7. delete <task number>\n\t- deletes the task\n" +
                "8. bye\n\t- exits the program");
    }
}