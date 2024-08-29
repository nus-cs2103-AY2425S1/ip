import java.util.Scanner;
import java.util.ArrayList;

public class Twilight {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Storage storage = new Storage("data/Twilight.txt");
        System.out.println("Hello! I am Twilight your personal assistant\nWhat can I do for you?" );
        TaskList tasks =  new TaskList(storage.getStoredTasks());
        String command = input.nextLine();
        while (!command.equals("bye")) {
            Command c = Parser.parse(command);
            c.execute(tasks, storage);
            command = input.nextLine();
        }
        System.out.println("See you");
    }
}
