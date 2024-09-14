package alexer.command;

import alexer.Alexer;
import alexer.Prompter;
import alexer.command.Command;

/**
 * Command to list all the tasks in the chatbot
 *
 * @author sayomaki
 */
public class ListTaskCommand extends Command {
    /**
     * Creates the list task command
     */
    public ListTaskCommand() {
        super("list");
    }

    /**
     * Runs the list task command to print all the tasks to the
     * output after formatting.
     *
     * @param arguments string array of arguments given
     */
    @Override
    public void run(String[] arguments) {
        Alexer alexer = Alexer.getInstance();
        Prompter prompter = alexer.getPrompter();

        int taskCount = alexer.getTaskManager().getTaskCount();
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            String task = alexer.getTaskManager().getTask(i).toString();
            response.append(String.format("\t%d: %s\n", i + 1, task));
        }

        prompter.buildTaskList(response.toString()).printToConsole();
    }
}
