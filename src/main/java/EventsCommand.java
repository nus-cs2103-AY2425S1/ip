public class EventsCommand extends Command{

    private String description;

    public EventsCommand(String description) {
        this.description = description;
    }
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if(description.isEmpty()) {
            throw new NoDescription("event", "event <description> /from <start time> /to <end time>");
        } else if(!description.contains("/from") || !description.contains("/to")) {
            throw new NoTimeline("event", "event <description> /from <start time> /to <end time>");
        }
        String[] splitEvent = description.split("/from");
        if (splitEvent.length != 2) {
            throw new NoTimeline("event", "event <description> /from <start time> /to <end time>");
        }
        String[] splitEventTime = splitEvent[1].split("/to");
        if (splitEventTime.length != 2) {
            throw new NoTimeline("event", "event <description> /from <start time> /to <end time>");
        }
        taskList.addTask(new Events(splitEvent[0], splitEventTime[0], splitEventTime[1]));
        storage.saveTask(taskList.list);
        ui.addTaskMessage(taskList.list.getLast(), taskList.list.size());
    }
}
