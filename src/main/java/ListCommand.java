/**
 * This class is responsible for handling the list command in Quack.
 */
public class ListCommand extends Command{
    
    /**
     * Creates a ListCommand object.
     */
    public ListCommand() {

    }

    @Override
    public void execute(Quack chatBot, TaskList taskList, Storage storage) {
        System.out.println(taskList.toString());
    }
}
