package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;

import java.util.List;

public class PrintTasksCommand extends Command {

    public PrintTasksCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        List<String> taskDescriptions = tasks.getTaskDescriptions();
        ui.printTaskList(taskDescriptions);
    }
}
