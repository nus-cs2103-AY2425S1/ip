import java.io.IOException;

public abstract class Command {
    public abstract void execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException, IOException;

    public boolean isExit() {
        return false;
    }
}
