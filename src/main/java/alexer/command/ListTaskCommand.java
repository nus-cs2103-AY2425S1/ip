package alexer.command;

import alexer.Alexer;
import alexer.Prompter;
import alexer.ui.Response;

/**
 * Command to list all the tasks in the chatbot,
 * with ordered numbering prefix for the tasks
 *
 * @author sayomaki
 */
public class ListTaskCommand extends Command {
    public ListTaskCommand() {
        super("list");
    }

    /**
     * Runs the list task command to print all the tasks to the
     * output after formatting.
     *
     * @param arguments string array of arguments given
     * @return the response of the command
     */
    @Override
    public Response run(String... arguments) {
        Alexer alexer = Alexer.getInstance();
        Prompter prompter = alexer.getPrompter();

        int taskCount = alexer.getTaskManager().getTaskCount();
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            String task = alexer.getTaskManager().getTask(i).toString();
            response.append(String.format("\t%d: %s\n", i + 1, task));
        }

        return prompter.buildTaskList(response.toString());
    }
}
