import java.util.ArrayList;

public interface Command {
    void execute(String userInput, ArrayList<Task> listOfTasks) throws NedException;
    String getRegex();
}
