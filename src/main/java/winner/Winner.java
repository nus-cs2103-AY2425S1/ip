package winner;

/**
 * Main class of the Winner task tracking bot.
 */
public class Winner {

    /**
     * Main entry point of the application.
     * Initialises the task list, handles loading and saving of tasks and starts the Winner bot loop.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage.checkAndCreateFile();
        Storage.loadTasks(taskList.getTasks());
        WinnerTaskBotCLI.winnerTaskBot(taskList);
        Storage.saveTasks(taskList.getTasks());
    }

    public String getResponse(String input) {
        TaskList taskList = new TaskList();
        Storage.checkAndCreateFile();
        Storage.loadTasks(taskList.getTasks());
        Ui.winnerSaysHi();
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
