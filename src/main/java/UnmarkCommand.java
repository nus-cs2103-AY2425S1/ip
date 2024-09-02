public class UnmarkCommand extends Command {
    private final int taskToUnmark;

    UnmarkCommand(int taskToUnmark) {
        super();
        this.taskToUnmark = taskToUnmark;
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        try {
            Task unmarkedTask = taskLog.undoTask(taskToUnmark);
            storage.storeTasks(taskLog);
            speech.say("no worries, we'll circle back around to this.");
            speech.say("\t" + unmarkedTask.toString());
            speech.say();
        } catch (RizzlerException e) {
            speech.say(e.getMessage());
            speech.say();
        }
    }
}
