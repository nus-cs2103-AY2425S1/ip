public class ListCommand extends Command {
    ListCommand() {
        super();
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        Task[] tasks = taskLog.getLog();
        if (tasks.length == 0) {
            speech.say("our list is empty right now dear, no tasks to list!");
        } else {
            speech.say("these are the things we gotta do:");
            for (int i = 0; i < tasks.length; i++) {
                speech.say((i + 1) + ". " + tasks[i]);
            }
        }
        speech.say();
    }
}
