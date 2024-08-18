import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {
    private final Scanner input;
    private ArrayList<String> tasks;
    private boolean isRunning;

    public Bobby() {
        this.input = new Scanner(System.in);
        this.tasks = new ArrayList<>();
        this.isRunning = true;
    }

    public String getInput() {
        return this.input.nextLine();
    }

    public void processInput(String s) {
        if (s.equals("bye")) {
            this.isRunning = false;
            System.out.println("Bye. Hope to see you again soon!");
        } else if (s.equals("list")) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        } else {
            tasks.add(s);
            String text = String.format("Added: %s", s);
            System.out.println(text);
        }
    }

    public void sayHi() {
        System.out.println("Hello, I'm Bobby");
        System.out.println("What can I do for you?");
    }

    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        chatBot.sayHi();
        while (chatBot.isRunning) {
            chatBot.processInput(chatBot.getInput());
        }
    }
}
