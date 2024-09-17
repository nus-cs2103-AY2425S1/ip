package bob.command;

import bob.Storage;
import bob.TaskList;

/**
 * Represents a command that displays a help list.
 */
public class HelpCommand extends Command {

    /**
     * Executes the task, returning the help list.
     */
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
                + "9. summarise {(week) OR (/from <date> /to <date>)}\n\t- returns the number of tasks completed"
                + " in the past week OR date range when /from and /to are specified)\n"
                + "10. (CLI only) bye\n\t- exits the program";
    }
}