import java.util.Scanner;
import java.util.ArrayList;

public class Applemazer {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> items = new ArrayList<>();

    private void greeting() {
        String greeting = "Hello! I'm Applemazer.\nWhat can I do for you?\n";
        System.out.println(greeting);
    }

    private void farewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    private void process() {
        boolean processing = true;
        while (processing) {
            String command = sc.nextLine();

            switch (command) {
                case "bye" :
                    processing = false;
                    break;
                case "list" :
                    for (int i = 0; i < items.size(); ++i) {
                        System.out.println((i+1) + ". " + items.get(i));
                    }
                    System.out.println(); // Leave empty line.
                    break;
                default:
                    items.add(command);
                    System.out.println("added: " + command + "\n");
            }
        }
    }

    public static void main(String[] args) {
        Applemazer chatBot = new Applemazer();
        chatBot.greeting();
        chatBot.process();
        chatBot.farewell();
    }
}
