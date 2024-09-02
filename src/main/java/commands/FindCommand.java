package commands;
import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.UI;

public class FindCommand implements Command {
    private final String desc;

    public FindCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean execute(Storage storage, TaskList master, UI ui) {
        String s = "";
        for (int i = 0; i < master.getSize(); i++) {
            Task task = master.getParent().get(i);
            if (task.containsWord(this.desc)) {
                s += "\n" + task.toString();
            }
        }
        if (!s.isEmpty()) {
            System.out.printf("Here are the tasks containing \"%s\"!%n", this.desc);
            System.out.println(s);
        } else {
            System.out.printf("Sorry! There are no tasks containing \"%s\".%n", this.desc);
        }
        UI.printLine();
        return false;
    }
}
