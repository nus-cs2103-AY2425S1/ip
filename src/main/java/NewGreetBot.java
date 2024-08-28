public class NewGreetBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public NewGreetBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            tasks.printTasks();
        } finally {
            /*catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }*/
        }
    }

    public void run() {
        System.out.println(ui.greetUser());
    }

    public static void main(String[] args) {
        new NewGreetBot("data/greetbot.txt").run();
    }
}
