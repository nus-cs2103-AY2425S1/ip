import java.util.Scanner;
import java.util.ArrayList;

public class MrYapper {
    private static final String GREETING_MESSAGE = " Hello! I'm MrYapper\n"
            + " What can I do for you?";
    private static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";
    private static ArrayList<Task> taskList = new ArrayList<>(100);

    private static class Task {
        private final String TASK_NAME;

        private Task(String name) {
            this.TASK_NAME = name;
        }

        @Override
        public String toString() {
            return TASK_NAME;
        }
    }

    // Inserts line indentation in response messages
    private static void say(String message) {
        System.out.println("____________________________________________________________\n"
                + message
                + "\n____________________________________________________________");
    }

    private static void addTask(String name) {
        taskList.add(new Task(name));
        say( "added: " + name);
    }

    public static void main(String[] args) {
        say(GREETING_MESSAGE);
        boolean conversationIsOngoing = true;
        Scanner userInputReader = new Scanner(System.in);

        do {
            String userInput = userInputReader.nextLine();

            if (userInput.equals("bye")) {
                conversationIsOngoing = false;
                userInputReader.close();
                say(GOODBYE_MESSAGE);
            } else {
                addTask(userInput);
            }
        } while (conversationIsOngoing);
    }
}
