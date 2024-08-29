import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Duke {

    public static class Task {

        Task(String taskName) {
            this.taskName = taskName;
            this.marked = false;
        }

        public void mark() {
            this.marked = true;
        }

        public void unmark() {
            this.marked = false;
        }

        public void print() {
            String message = "";

            if(this.marked) {
                message += "[X] ";
            } else {
                message += "[ ] ";
            }

            message += taskName;

            System.out.println(message);
        }

        public void print(int rank) {
            String message = rank + ".";

            if(this.marked) {
                message += "[X] ";
            } else {
                message += "[ ] ";
            }

            message += taskName;

            System.out.println(message);
        }
        private String taskName;
        private boolean marked;
    }
    public static String horizontalLine = "----------------------------------------------------------";
    public static String defaultProperties = "0";
    public static ArrayList<Task> tasks = new ArrayList<Task>();
    public static void printOpening() {
        String openingText = "Hello! I'm Jeff\n " +
                "What can I do for you?";

        System.out.println(horizontalLine);
        System.out.println(openingText);
        System.out.println(horizontalLine);
    }

    public static void printMessage(String message) {
        System.out.println(horizontalLine);
        System.out.println(message);
        System.out.println(horizontalLine);
    }

    public static void printMessageWithProperties(int rank) {

    }

    public static void printClosing() {
        String closingText = "Bye. Hope to see you again soon!";

        System.out.println(horizontalLine);
        System.out.println(closingText);
        System.out.println(horizontalLine);
    }

    public static void addMessage(String message) {
        tasks.add(new Task(message));
        printMessage("added: " + message);
    }

    public static void listMessages()
    {
        int numberOfMessages = tasks.size();

        System.out.println(horizontalLine);

        for(int i=0; i<numberOfMessages; i++) {
            tasks.get(i).print(i+1);
        }

        System.out.println(horizontalLine);
    }

    public static void markMessage(int rank) {
        Task task = tasks.get(rank - 1);

        task.mark();

        System.out.println(horizontalLine);
        System.out.println("Nice! I've marked this task as done:");
        task.print();
        System.out.println(horizontalLine);
    }

    public static void unmarkMessage(int rank) {
        Task task = tasks.get(rank - 1);

        task.unmark();

        System.out.println(horizontalLine);
        System.out.println("OK, I've marked this task as not done yet:");
        task.print();
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {

        String exitCommand = "bye";
        String listCommand = "list";

        Scanner inputReader = new Scanner(System.in);

        printOpening();

        while(true)
        {
            String input = inputReader.nextLine();

            if(input.equals(exitCommand)) {
                printClosing();
                break;
            } else if(input.equals("list")) {
                listMessages();
            } else if(input.length() >= 4 && input.substring(0,4).equals("mark")) {
                int rankToMark = Integer.valueOf(input.substring(5));
                markMessage(rankToMark);
            } else if(input.length() >= 6 && input.substring(0,6).equals("unmark")) {
                int rankToUnmark = Integer.valueOf(input.substring(7));
                unmarkMessage(rankToUnmark);
            } else {
                addMessage(input);
            }
        }
    }
}
