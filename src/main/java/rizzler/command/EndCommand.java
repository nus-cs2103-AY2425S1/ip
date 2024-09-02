package rizzler.command;

public class EndCommand extends Command {
    EndCommand() {
        super();
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        setShouldEnd(true);
    }
}
