import java.util.Scanner;

public class Friday {

    private Storage storage;
    private TaskList master;
    private UI ui;

    public Friday(String filePath) {
        this.storage = new Storage(filePath);
        this.master = new TaskList(this.storage.initList());
        this.ui = new UI();
    }

    public void run() {
        this.ui.init();
        boolean bye = false;

        while (!bye) {
            System.out.print("Your input > ");
            String[] parsed = this.ui.getInput();
            Command command = Parser.parse(parsed);
            boolean isBye = command.execute(this.storage, this.master, this.ui);
            if (isBye) {
                bye = true;
            }
        }

        this.ui.terminate();
        this.storage.saveList(this.master.getParent());

    }

    public static void main(String[] args) {
        new Friday("./data/friday.txt").run();
    }
}