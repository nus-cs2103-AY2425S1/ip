import java.util.Arrays;

public class UpdateStatusCommand extends UserCommand {
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) throws LevelHundredException {
        String[] words = userInput.split(" ");
        String command = words[0];

        if (words.length == 1) {
            ui.printException(new MissingArgumentException(command, "task index"));
            return;
        }
        if (words.length > 2) {
            String invalidString = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            ui.printException(new InvalidArgumentException(command, invalidString));
            return;
        }

        try {
            int idx = Integer.parseInt(words[1]) - 1;
            Task t = taskList.getTaskList().get(idx);
            if (command.equals("mark")) {
                t.mark();
                ui.printSuccessfulMark(t);
            } else {
                t.unmark();
                ui.printSuccessfulUnmark(t);
            }
            storage.update(taskList.getTaskList());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printException(new InvalidArgumentException("task index", words[1]));
        }
    }
}