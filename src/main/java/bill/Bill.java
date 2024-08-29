package bill;

public class Bill {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Bill() {
        storage = new Storage();
        ui = new Ui();
        tasks = new TaskList();
    }

    public void start() {
        ui.silentLoadingData(storage, tasks.getUserList(), tasks);
        ui.introduce();
        ui.handleUserCommands(storage, tasks);
        ui.conclude();
    }

    public static void main(String[] args) {
        new Bill().start();
    }
}