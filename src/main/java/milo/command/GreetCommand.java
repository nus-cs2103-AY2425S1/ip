package milo.command;

import milo.lists.ClientsList;
import milo.lists.TaskList;
import milo.ui.ClientUi;
import milo.ui.TaskUi;

import java.util.Objects;

public class GreetCommand extends Command {

    private final String greetType;

    public GreetCommand(String greetType) {
        this.greetType = greetType;
    }

    @Override
    public void execute(TaskList taskList, ClientsList clientsList) {
        return;
    }

    @Override
    public String commandToString(TaskUi ui, ClientUi cUi, TaskList taskList, ClientsList clientsList) {
        return Objects.equals(this.greetType, "hi") ? ui.greetUser() : ui.byeUser();
    }
}
