import java.util.List;

public class EventCommand extends Command {

    List<String> descriptionArray;
    List<String> fromArray;
    List<String> toArray;

    public EventCommand(List<String> descriptionArray, List<String> fromArray, List<String> toArray) {
        this.descriptionArray = descriptionArray;
        this.fromArray = fromArray;
        this.toArray = toArray;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Event newEvent = new Event(String.join(" ", descriptionArray), String.join(" ", fromArray), String.join(" ", toArray));
        tasklist.addTask(newEvent);
        try {
            storage.save(tasklist.getTaskList());
            output.append("Got it. I've added this event:\n").append(newEvent.toString()).append("\n").append("Now you have ")
                    .append(tasklist.size()).append(" tasks in your list.\n");
            ui.printString(output.toString());
        } catch (AsuraException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
