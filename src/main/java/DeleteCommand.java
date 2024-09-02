public class DeleteCommand extends Command {
    private final int taskToDelete;

    DeleteCommand(int commandToDelete) {
        super();
        this.taskToDelete = commandToDelete;
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        try {
            Task deletedTask = taskLog.deleteTask(taskToDelete);
            storage.storeTasks(taskLog);
            speech.say("sure hon, i'll remove this task from the list.");
            speech.say("\t" + deletedTask);
            speech.say("now we have " + taskLog.getNumTasks() + " tasks left to work on.");
            speech.say();
        } catch (RizzlerException e) {
            speech.say(e.getMessage());
            speech.say();
        }
    }
}
