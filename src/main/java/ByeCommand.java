import java.io.IOException;

public class ByeCommand extends Command {
    public ByeCommand(){};
    @Override
    public boolean isExit(){
        return true;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {

    }
}
