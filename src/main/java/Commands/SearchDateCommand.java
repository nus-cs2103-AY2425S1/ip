package Commands;

import Exceptions.AvoException;
import Tasks.TaskManager;
import Utils.DateTime;

import java.time.LocalDate;

public class SearchDateCommand extends Command {
    private final TaskManager manager;
    public SearchDateCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ");
        if (inputs.length < 2) {
            throw new AvoException("OOPS!!! The date cannot be empty.");
        }
        LocalDate searchDate = DateTime.parse(inputs[1]);
        manager.getTasksByDate(searchDate);
    }
}
