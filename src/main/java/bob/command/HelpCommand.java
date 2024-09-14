package bob.command;

import bob.Storage;
import bob.TaskList;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Here are the list of commands:\n" +
                "1. list\n\t- lists tasks\n" +
                "2. mark <task number>\n\t- marks the task as done\n" +
                "3. unmark <task number>\n\t- unmarks the task\n" +
                "4. deadline <task description> /by <by>\n\t- Creates a deadline\n" +
                "5. todo <task description>\n\t- Creates a todo\n" +
                "6. event <task description> /from <from> /to <to>\n\t- Creates an event\n" +
                "7. delete <task number>\n\t- deletes the task\n" +
                "8. find <keyword>\n\t- finds all tasks whose descriptions contain the keyword\n"
                + "9. Summarise (/from <date> /to <date>)\n\t- returns the number of tasks completed"
                + " in the past week (or date range if /from and /to are specified)\n"
                + "10. (CLI only) bye\n\t- exits the program";
    }
}