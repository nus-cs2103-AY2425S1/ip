import java.util.Scanner;

public class Mel {
    private final TaskList taskList;
    private final UI ui;

    public Mel() {
        Storage storage = new Storage();
        taskList = new TaskList(this, storage);
        ui = new UI(this);
    }

    public void taskAction(String input) {
        try {
            taskList.taskAction(input);
        } catch (MelException | TaskException e) {
            ui.println(e.toString());
        }
    }

    public void println(String s) {
        ui.println(s);
    }

    public void run() {
        ui.hello();
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye) {
            String input = scanner.nextLine();
            isBye = ui.read(input);
        }
    }

    public static void main(String[] args) {
        new Mel().run();
    }
}
