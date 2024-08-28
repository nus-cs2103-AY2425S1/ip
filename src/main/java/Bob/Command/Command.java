package Bob.Command;

import Bob.Exception.BobException;
import java.util.ArrayList;
import Bob.Tasks.Task;
import Bob.Storage.Storage;
import Bob.Ui.Ui;

public interface Command {
    void execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException;
}

