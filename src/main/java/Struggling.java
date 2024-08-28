import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Struggling {
    final private String name = "struggling";

    private ArrayList<Task> taskArr = new ArrayList<>();

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Struggling(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.show("Failed to create saveFile, contact the developer!");
        } catch (StrugglingException e) {
            ui.show(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String cmd = ui.readCommand();
                Command c = Parser.parse(cmd);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (StrugglingException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Fail to save task, please contact developer!");
            }
        }
    }

    public static void main(String[] args) {
        new Struggling("data/Struggling.txt").run();
    }

//    Struggling() {
//
//        this.isActive = true;
//
//        try {
//
//        } catch (FileNotFoundException e) {
//            reply("Save file not found, please try again!");
//            this.isActive = false;
//        } catch (IOException e) {
//            reply("Failed to create a save file, please contact the developer!");
//            this.isActive = false;
//        } catch (StrugglingException e) {
//            reply("Save file corrupted, using a fresh save file");
//            resetSaveFile();
//            this.taskArr.clear();
//        }
//    }
}

