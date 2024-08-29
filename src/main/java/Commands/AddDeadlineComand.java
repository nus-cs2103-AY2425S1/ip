package Commands;
import Exceptions.DelphiException;
import Exceptions.EmptyInputException;
import Parser.DateParser;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Deadline;
import UI.UI;

public class AddDeadlineComand extends Command {
    public AddDeadlineComand(String s) {
        super(s);
    }
    public void execute (TaskList t, Storage s, UI ui) throws DelphiException {
        DateParser d = new DateParser();
        Deadline newDeadline = new Deadline(input.substring(9), d);
        t.addTask(newDeadline);
        s.writeToHardDisk(t.getTasks());
        UI.taskMessage(newDeadline, t.getTasks().size());
    }
}
