package Dawn;
public class Dawn {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Creates a new instance of the chatbot Dawn with the specified file path where it will store current tasks
     *
     * @param filePath File path of the file in which the chatbot will store current tasks
     */
    public Dawn(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DawnException ex) {
            ui.showLoadingError(ex);
            this.taskList = new TaskList();
        }
    }

    public Dawn() {
        this("data/Dawn.txt");
    }

    /**
     * Starts the interaction between the chatbot and the user by greeting the user
     *
     * @throws DawnException
     */
    public void run() throws DawnException {
        ui.greet();
    }

    public static void main(String[] args) {
        try {
            new Dawn("data/Dawn.txt").run();
        } catch (DawnException e) {
            System.out.println(e);
        }
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
