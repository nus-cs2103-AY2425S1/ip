public class HelpCommand extends Command {
    HelpCommand() {
        super();
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        speech.say("T");
        speech.say();
    }

}
