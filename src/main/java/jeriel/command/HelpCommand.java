package jeriel.command;

import jeriel.util.*;

public class HelpCommand extends Command {
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String helpMessage = "Here are the available commands:\n"
            + "1. todo <task description> - Adds a todo task\n"
            + "2. deadline <task description> /by <date> - Adds a deadline task\n"
            + "3. event <task description> /from <start> /to <end> - Adds an event task\n"
            + "4. list - Lists all tasks\n"
            + "5. mark <task number> - Marks a task as done\n"
            + "6. unmark <task number> - Unmarks a task\n"
            + "7. delete <task number> - Deletes a task\n"
            + "8. help - Shows this help message\n"
            + "9. bye - Exits the program";
        
        return helpMessage;
    }
}
