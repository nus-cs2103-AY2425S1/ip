package calebyyy;

public class Calebyyy {
    private Parser parser;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;


    public Calebyyy() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        taskList = new TaskList(ui);
        storage.loadTasks(taskList);
        parser = new Parser(this, taskList, storage, ui);
    }

    public void start() {
        parser.startCommandLoop();
    }

    public static void main(String[] args) {
        new Calebyyy().start();
    }
}