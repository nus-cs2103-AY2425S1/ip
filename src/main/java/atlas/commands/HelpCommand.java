package atlas.commands;

import atlas.storage.Storage;
import atlas.tasks.TaskList;

/**
 * Lists all the possible commands when this class is instantiated.
 */
public class HelpCommand extends Command {
    /**
     * Lists all possible commands and their uses for the user.
     *
     * @param tasks The current list of tasks in the chatbot.
     * @param storage The storage object the chatbot uses to store and load tasks
     * @return String The message returned to be displayed on the chatbot GUI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return """
                ------------- Atlas Help Page -------------

                Here are all the commands you can try with Atlas!

                bye, b - exits the Atlas chatbot application

                list, l - lists all tasks in your task list

                mark, m <taskNumber> - marks a task in your task list as done

                unmark, u <taskNumber> - unmarks a task in your task list as not done

                delete, r <taskNumber> - removes a task from your task list

                find, f <pattern> - displays tasks whose names match this pattern

                todo, t <name> - adds a new todo to the task list

                deadline, d <name> /by <date (YYYY-MM-DD HHMM)> - adds a new deadline to the task list

                event, e <name> /from <date (YYYY-MM-DD HHMM)> /to <date (YYYY-MM-DD HHMM)> - adds a new event

                """;
    }
}
