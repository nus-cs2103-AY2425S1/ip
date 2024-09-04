import java.nio.file.Paths;
import java.util.Scanner;

public class Diomon {
    private Storage storage;
    private Commands commands;
    private static void greeting() {
        String greetingMessage = "________________________________________________________________\nHello! I'm Diomon\nWhat do you need recorded?\n________________________________________________________________\n";
        System.out.print(greetingMessage);
    }
    private void run() {
        // Initialise instance
        Storage storage = new Storage("data/data.txt");
        commands = new Commands();
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        greeting();
        while (true) {
            String input = scanner.nextLine();
            System.out.println("________________________________________________________________");
            commands.run(input,taskList);
            System.out.println("________________________________________________________________");
            if (commands.isExit()) {
                storage.save(taskList.toStorageString());
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Diomon().run();
    }
}
