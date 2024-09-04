package alexer.command;

import alexer.Alexer;
import alexer.Prompter;
import alexer.command.Command;

public class ListTaskCommand extends Command {
    public ListTaskCommand() {
        super("list");
    }

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

        prompter.printTaskList(response.toString());
    }
}
