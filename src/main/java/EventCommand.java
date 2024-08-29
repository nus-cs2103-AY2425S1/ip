public class EventCommand extends Command {
    private String info;

    public EventCommand(String info) {
        this.info = info;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        String[] eventInfo = extractInfo();
        Task newTask = new Event(eventInfo[0], eventInfo[1], eventInfo[2]);
        tasks.addTask(newTask);
        ui.hasAddedTask(newTask, tasks.size());
        storage.save(tasks);
    }

    public String[] extractInfo() throws LictException {
        String[] messageParts = info.split("/from", 2);
        String description = messageParts[0].trim();
        if (description.isEmpty()) {
            throw new LictException("OOPS!!! The description of an event cannot be empty. Please ensure that your input is in the format: event {description} /from {start} /to {end}");
        }
        if (messageParts.length != 2) {
            throw new LictException("OOPS!!! The start of the event needs to be indicated. Please ensure that your input is in the format: event {description} /from {start} /to {end}");
        }
        String start = messageParts[1].trim();
        String[] eventInfo = start.split("/to", 2);
        if (eventInfo.length != 2) {
            throw new LictException("OOPS!!! The end of the event needs to be indicated. Please ensure that your input is in the format: event {description} /from {start} /to {end}");
        }
        return new String[] {description, eventInfo[0].trim(), eventInfo[1].trim()};
    }
}