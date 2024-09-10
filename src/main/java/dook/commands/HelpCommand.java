package dook.commands;

import dook.storage.Storage;
import dook.tasks.TaskList;
import dook.ui.Ui;

public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return printMessages(ui);
    }

    private String printMessages(Ui ui) {
        ui.separate();

        String message;

        String generalGuideline = "Dook is a chatbot that is able to help you keep track of all your tasks\n"
                + "Dook is able to track todos, deadlines, and events\n" + "Here is a list of commands: \n\n";

        String commandGuide = "Create a todo: \ntodo <description of your task>\n\n"
                + "Create a deadline: \ndeadline <description of your task> /by <due date of your task in dd/MM/yyyy hh:mm format>\n\n"
                + "Create an event: \nevent <description of your task> /from <start of your event in dd/MM/yyyy hh:mm format> "
                + "/to <end of your event in dd/MM/yyyy hh:mm format>\n\n"
                + "List all your tasks: list\n\n"
                + "Delete a task: delete <task number>\n\n"
                + "Find a task: find <keyword>\n\n"
                + "Marking a task as done/undone: mark <task number> / unmark <task number>\n\n"
                + "Exit the chatbot: bye";

        message = generalGuideline.concat(commandGuide);

        ui.showMessage(message);
        ui.separate();
        return message;
    }

}
