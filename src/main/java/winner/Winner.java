package winner;

/**
 * Main class of the Winner task tracking bot.
 */
public class Winner {

    public String getHelloMessage() {
        return Ui.winnerSaysHi();
    }

    public String getResponse(String input) {
        TaskList taskList = new TaskList();
        Storage.checkAndCreateFile();
        Storage.loadTasks(taskList.getTasks());
        String reply = "";

        try {
            reply = Parser.parseInput(input, taskList);
        } catch (WinnerException e) {
            reply = e.getMessage();
        }
        Storage.saveTasks(taskList.getTasks());
        return reply;
    }
}
