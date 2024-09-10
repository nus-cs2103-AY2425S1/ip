package Dawn;
public class Dawn {
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a new instance of the chatbot Dawn with the specified file path where it will store current tasks
     */
    public Dawn() {
        this.storage = new Storage("data/Dawn.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DawnException ex) {
            this.taskList = new TaskList();
        }
    }

    public static void main(String[] args) {}

    public String getResponse(String input) {
        try {
            return Parser.parse(input);
        } catch (DawnException e) {
            return e.getMessage();
        }
    }
}
