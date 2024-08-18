import java.util.Scanner;

public class Infinity {

    private static final String BOTNAME = "Infinity";
    private static final String BREAKLINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    private String[] tasks = new String[100]; 
    private int nextTastIndex = 0;

    private static String botReply(String input) {
        return String.format("%s: %s", BOTNAME, input);
    }

    private void addTask(String task) {
        if (nextTastIndex >= tasks.length) {
            System.out.println(botReply("I'm sorry, but I can't remember more tasks."));
            return;
        }
        tasks[nextTastIndex] = task;
        nextTastIndex++;
        System.out.println(botReply(String.format("I've added '%s'", task)));
    }

    private void listTasks() {
        if (nextTastIndex == 0) {
            System.out.println(botReply("Task list is empty :("));
        } else {
            System.out.println(botReply(""));
            for (int i = 0; i < nextTastIndex; i++) {
                System.out.println(String.format("    %d. %s", i + 1, tasks[i]));
            }
        }
    }

    public Infinity() {
        Scanner userInputs = new Scanner(System.in);

        System.out.println(BREAKLINE);
        System.out.println(botReply(String.format("Hello, I'm a dummy bot called %s", BOTNAME)));
        System.out.println(botReply("What can I not do for you?"));
        System.out.println(BREAKLINE);

        while (true) {
            String currentInput = userInputs.nextLine();
            System.out.println(BREAKLINE);
            switch (currentInput) {
                case "bye":
                    System.out.println(botReply("Well, if you are leaving, then I must be infinitely too dumb :("));
                    System.out.println(BREAKLINE);
                    userInputs.close();
                    System.exit(0);
                    break;
                case "list":
                    this.listTasks();
                    System.out.println(BREAKLINE);
                    break;
                default:
                    this.addTask(currentInput);
                    System.out.println(BREAKLINE);
            }
        }
    }

    public static void main(String[] args) {
        Infinity infinityBot = new Infinity();
    }

}
