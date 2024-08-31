import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Dawn {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

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

}
