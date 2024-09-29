package Commands;

import prince.Prince;

public class GeneralTaskCommand extends Command {

    public GeneralTaskCommand(String c) {
        super(c);
    }

    @Override
    public String commandAction() {

        System.out.println("Command passed checkUnknownCommand: " + this.cmd);

        String[] split = this.cmd.split(" ", 2);

        String type = split[0];
        String stringTask = split[1];

        if (type.equals(Prince.TaskType.todo.toString())) {

            ToDoCommand c = new ToDoCommand(stringTask);
            return c.commandAction();

        } else if (type.equals(Prince.TaskType.deadline.toString())) {

            DeadlineCommand c = new DeadlineCommand(stringTask);
            return c.commandAction();

        } else {

            EventCommand c = new EventCommand(stringTask);
            return c.commandAction();

        }
    }
}
