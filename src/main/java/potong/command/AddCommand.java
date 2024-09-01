package potong.command;

import potong.*;
import potong.exceptions.IllegalInputPotongException;
import potong.task.DeadlineTask;
import potong.task.EventTask;
import potong.task.ToDoTask;

public class AddCommand extends Command {

    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    private final Type type;
    private String task;
    private String deadline;
    private String eventStart;
    private String eventEnd;

    public AddCommand(String command, Type type) {
        super(command);
        this.type = type;
        switch (type) {
            case TODO -> this.task = command;
            case DEADLINE -> {
                String[] arr = command.split("/");
                this.task = arr[0].strip();
                this.deadline = arr[1].substring(3);
            }
            case EVENT -> {
                String[] arr = command.split("/");
                this.task = arr[0].strip();
                this.eventStart = arr[1].substring(5).strip();
                this.eventEnd = arr[2].substring(3).strip();
            }
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IllegalInputPotongException {
        switch (this.type) {
            case TODO -> tasks.add(new ToDoTask(this.task));
            case DEADLINE -> tasks.add(new DeadlineTask(this.task, this.deadline));
            case EVENT -> tasks.add(new EventTask(this.task, this.eventStart, this.eventEnd));
        }
    }
}
