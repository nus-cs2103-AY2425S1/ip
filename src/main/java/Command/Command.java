package Command;

/* My import */
import Blitz.Storage;
import Blitz.TaskList;
import Blitz.Ui;
import Exception.BlitzException;

public abstract class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws BlitzException;

    public boolean isExit() {
        return this.command.equalsIgnoreCase("bye");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Command c = (Command) o;
        return this.command.equals((c.command));
    }
}
