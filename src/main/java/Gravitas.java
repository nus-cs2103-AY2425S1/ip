public class Gravitas {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Gravitas() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        this.ui.greet();
        while(this.ui.display(tasks)) {
            try {
                this.storage.saveTask(this.tasks);
            } catch (DukeException e) {
                ui.showLoadingError();
            }
        }
    }
    public static void main(String[] args) {
        new Gravitas().run();
    }
}
