import java.io.FileNotFoundException;

public class Gary {
    protected Storage storage;
    protected TaskList taskLists;
    protected Ui ui;

    public Gary(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskLists = new TaskList(this.storage.loadTaskList());
        } catch (FileNotFoundException e) {
            this.taskLists = new TaskList();
        }
    }

    public void start() {
        boolean isBye = false;
        this.ui.greet();
        while (!isBye) {
            try {
                String commandInput = this.ui.read();
                Command c = Parser.parse(commandInput);
                c.execute(this.taskLists, this.ui, this.storage);
                isBye = c.isBye();
            } catch (GaryException ge) {
                this.ui.showError(ge.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Gary("src/textFile/gary.txt").start();
    }
}

