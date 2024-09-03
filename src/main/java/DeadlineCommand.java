public class DeadlineCommand extends Command {

    private String description;

    public DeadlineCommand(String description) {
        this.description = description;
    }
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if(description.isEmpty()) {
            throw new NoDescription("deadline", "deadline <description> /by <timing>");
        } else if(!description.contains("/by")) {
            throw new NoTimeline("deadline", "deadline <description> /by <timing>");
        }
        String[] splitDeadline = description.split("/by");
        if(splitDeadline.length != 2) {
            throw new NoTimeline("deadline", "deadline <description> /by <timing>");
        }
        taskList.addTask(new Deadline(splitDeadline[0], splitDeadline[1]));
        storage.saveTask(taskList.list);
        ui.addTaskMessage(taskList.list.getLast(), taskList.list.size());
    }
}
