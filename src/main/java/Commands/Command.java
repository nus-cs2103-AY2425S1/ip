package Commands;
import Exceptions.DelphiException;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;

public abstract class Command {
    private boolean isExit;
    public String input;
    public Command(String s) {
        input = s;
    }
    public abstract void execute(TaskList t, Storage s, UI ui) throws DelphiException;


    public boolean exitBot() {
        return false;
    }
}
