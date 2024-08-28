import java.util.Scanner;
import java.io.File;

public class Delphi {
    private TaskList t;
    private Storage storage;

    private Parser p;

    private UI ui;

    public Delphi(String filePath) {
        storage = new Storage(filePath);
        t = new TaskList(this.storage);
        p = new Parser(this.t);
        ui = new UI();
    }

    public void run() {
        ui.welcomeMessage();
        System.out.println("current tasks:");
        t.loadStorageToTasks();
        t.printTasks();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (p.parseInput(input)) {
                break;
            }
        }
        ui.goodbyeMessage();
    }


    public static void main(String[] args) {
        new Delphi("ip/src/main/HardDisk.txt").run();
    }
}
