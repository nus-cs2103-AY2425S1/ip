package Commands;

import Applemazer.*;
import Tasks.*;
import java.time.DateTimeException;
import java.util.ArrayList;

public class EventCommand extends Command {
    private final String desc, from, to;

    public EventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) {
        try {
            Task task = new Event(desc, from, to);
            tasks.add(task);
            task.printTaskAddedMessage();
            storage.save();
        } catch (DateTimeException e) {
            System.out.println("""
                               OOPS!!! The description of event is wrong.
                               Try 'event <description> /from <date1> /to <date2>'.
                               <date> should be <yyyy-mm-dd> <HHmm> or <dd/MM/yyyy> <HHmm>.
                               It is not necessary to input the time!
                               """);
        }
    }

    @Override
    public boolean continueProcessing() {
        return true;
    }
}
