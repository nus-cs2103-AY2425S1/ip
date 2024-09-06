import diomon.Commands;
import diomon.Storage;
import diomon.TaskList;

import java.util.Scanner;

public class Main {
    private static void greeting() {
        String greetingMessage = "________________________________________________________________\nHello! I'm Diomon\nWhat do you need recorded?\n________________________________________________________________\n";
        System.out.print(greetingMessage);
    }
    private void run() {
        // Initialise instance
        Storage storage = new Storage("data/data.txt");
        Commands commands = new Commands();
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList(storage.load());
        greeting();
        while (true) {
            String input = scanner.nextLine();
            System.out.println("________________________________________________________________");
            commands.run(input,taskList);
            System.out.println("________________________________________________________________");
            if (commands.isCanExitExit()) {
                storage.save(taskList.toStorageString());
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
