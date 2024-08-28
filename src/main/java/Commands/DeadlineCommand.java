package commands;

import java.util.ArrayList;
import applemazer.Storage;
import tasks.Task;
import tasks.Deadline;
import java.time.DateTimeException;

public class DeadlineCommand extends Command {
    private final String desc, deadline;

    public DeadlineCommand(String desc, String deadline) {
        this.desc = desc;
        this.deadline = deadline;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) {
        try {
            Task task = new Deadline(desc, deadline);
            tasks.add(task);
            task.printTaskAddedMessage();
            storage.save();
        } catch (DateTimeException e) {
            System.err.println("""
                               OOPS!!! The description of deadline is wrong.
                               Try 'deadline <description> /by <yyyy-mm-dd> <HHmm>'
                                   'deadline <description> /by <dd/MM/yyyy> <HHmm>'.
                               It is not necessary to input the time!
                               """);
        }
    }

    @Override
    public boolean continueProcessing() {
        return true;
    }
}
