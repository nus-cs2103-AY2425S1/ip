import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SortDeadlineCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> deadlineTasks = tasks.getTasks().stream()
                .filter(task -> task instanceof DeadlineTask)
                .sorted(Comparator.comparing(task -> ((DeadlineTask) task).getBy()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (deadlineTasks.isEmpty()) {
            ui.showMessage("No deadline tasks to sort");
        } else {
            ui.showMessage("Deadline tasks sorted by due date:");
            for (int i = 0; i < deadlineTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + deadlineTasks.get(i));
            }
        }
    }
}
