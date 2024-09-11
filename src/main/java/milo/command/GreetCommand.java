package milo.command;

import milo.tasks.TaskList;
import milo.ui.Ui;

import java.util.Objects;

public class GreetCommand extends Command {

    private final String greetType;

    public GreetCommand(String greetType) {
        this.greetType = greetType;
    }

    @Override
    public void execute(TaskList taskList) {
        return;
    }

    @Override
    public String commandToString(Ui ui, TaskList taskList) {
        return Objects.equals(this.greetType, "hi") ? ui.greetUser() : ui.byeUser();
    }
}
