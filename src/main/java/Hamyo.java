import java.util.Scanner;

public class Hamyo {

    private final UI ui;
    private final Storage storage;
    private final TaskList tasks;
    private boolean active;

    public Hamyo() {
        this.ui = new UI();
        this.tasks = new TaskList();
        this.active = true;
        this.storage = new Storage("./savedTasks.txt");

        this.ui.greet();
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            storage.loadData(this, this.tasks);

            while (this.active) {
                try {
                    this.active = Parser.parse(scanner.nextLine(), this.tasks);
                    storage.saveData(this, this.tasks);
                } catch (HamyoException e) {
                    UI.printException(e);
                }
            }
            ui.terminate();
            scanner.close();
        } catch (HamyoException e) {
            UI.printException(e);
        }
    }

    public static void main(String[] args) {
        new Hamyo().run();
    }
}
