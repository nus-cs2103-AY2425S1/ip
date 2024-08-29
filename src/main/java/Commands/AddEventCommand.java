package Commands;
import Exceptions.DelphiException;
import Exceptions.EmptyInputException;
import Parser.DateParser;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Deadline;
import Tasks.Event;
import UI.UI;

public class AddEventCommand extends Command {
    public AddEventCommand(String s) {
        super(s);
    }
    public void execute (TaskList t, Storage s, UI ui) throws DelphiException {
        Event newEvent = new Event(input.substring(9), new DateParser());
        t.addTask(newEvent);
        s.writeToHardDisk(t.getTasks());
        UI.taskMessage(newEvent, t.getTasks().size());
    }
}
