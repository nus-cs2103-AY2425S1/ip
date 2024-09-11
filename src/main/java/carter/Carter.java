package carter;

/**
 * The main application for Carter chat box system.
 */
public class Carter {
    private Storage storage;
    private TaskList tasks;
    private Response res;

    /**
     * Constructs a new instance of Carter with specified file path for storage.
     *
     * @param filePath The file path to store the tasks.
     */
    public Carter(String filePath) {
        res = new Response();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (CarterException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Displays the welcome message when user execute the application.
     * @return the string representation of welcome message.
     */
    public String showWelcomeMessage() {
        return res.showWelcomeMessage();
    }

    /**
     * Returns String of response with specified input
     * @param input
     * @return String of response
     */
    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                saveData();
                return res.showEndingMessage();
            } else if (input.equals("archive")) {
                return archiveTask();
            } else {
                return Parser.parse(input, tasks, res);
            }
        } catch (CarterException e) {
            return res.showError(e.getMessage());
        }
    }

    private String archiveTask() throws CarterException {
        storage.archive(tasks.getTasks());
        TaskList temp = tasks;
        tasks = tasks.archiveTask();
        return res.showArchiveTask(temp.getTasks().toArray(new Task[0]));
    }
    /**
     * Save the current TaskList before the application terminate.
     */
    public void saveData() {
        try {
            storage.save(tasks.getTasks());
        } catch (CarterException e) {
            System.out.println(e.getMessage());
        }
    }
}
