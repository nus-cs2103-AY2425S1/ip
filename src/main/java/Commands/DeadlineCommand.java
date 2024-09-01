package Commands;

import Exceptions.BrockException;
import Storage.Storage;
import Tasks.Deadlines;
import Tasks.Task;
import Tasks.TaskList;
import Ui.Ui;
import Utility.Utility;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String command) {
        super(command);
    }

    private Task createDeadline() throws BrockException {
        String command = super.getCommand();
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        StringBuilder description = new StringBuilder();
        for (int i = 1; i < commandLength; i++) {
            if (commandWords[i].equalsIgnoreCase("/by")) {
                break;
            }
            description.append(commandWords[i])
                    .append(" ");
        }

        StringBuilder dateTime = new StringBuilder();
        boolean isSeeingDateTime = false;
        int dateTimeWords = 0;
        for (String word : commandWords) {
            if (isSeeingDateTime) {
                dateTimeWords += 1;
                dateTime.append(word)
                        .append(" ");
            }
            if (word.equalsIgnoreCase("/by")) {
                isSeeingDateTime = true;
            }
        }

        if (description.isEmpty()) {
            throw new BrockException("Description is missing!");
        }
        if (dateTime.isEmpty()) {
            throw new BrockException("Missing due date! Remember it is specified after /by!");
        }

        String[] dateTimeValues = Utility.validateDateTime(dateTime.toString()
                , dateTimeWords, Utility.Context.DUE);
        if (dateTimeWords == 1) {
            return new Deadlines(description.toString()
                    , dateTimeValues[0]);
        } else {
            return new Deadlines(description.toString()
                    , dateTimeValues[0]
                    , dateTimeValues[1]);
        }
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException {
        Task deadlineTask = createDeadline();
        tasks.addToList(deadlineTask);
        ui.displayResponse("Got it. I've added this task:\n"
                + "  "
                + tasks.getTaskDetails(deadlineTask)
                + '\n'
                + tasks.getTasksSummary());

        storage.writeToFile(tasks.numTasks()
                        + ". "
                        + tasks.getTaskDetails(deadlineTask)
                        + '\n'
                , true);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
