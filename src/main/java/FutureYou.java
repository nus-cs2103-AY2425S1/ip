import java.util.Scanner; // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class

public class FutureYou {
        static ArrayList<Task> taskList = new ArrayList<Task>();

        public static void bye() {
                System.out.println("Bye. Hope to see you again soon!\n" +
                                "____________________________________________________________\n");
        }

        public static void printList() {
                int count = 1;
                System.out.println("Items in List:");
                for (Task item : taskList) {
                        String cross = (item.taskStatus() ? "X" : " ");
                        System.out.println(count++ + ". " + "[" + cross + "] " + item.taskName());
                }
        }

        public static void markTask(int n) {
                taskList.get(n).markTask();
                System.out.println("Marked as Done:");
                System.out.println("[X] " + taskList.get(n).taskName());
        }

        public static void main(String[] args) {
                String logo = " _____      _                   __   __          \n"
                                + "|  ___|   _| |_ _   _ _ __ ___  \\ \\ / /__  _   _ \n"
                                + "| |_ | | | | __| | | | '__/ _ \\  \\ V / _ \\| | | |\n"
                                + "|  _|| |_| | |_| |_| | | |  __/   | | (_) | |_| |\n"
                                + "|_|   \\__,_|\\__|\\__,_|_|  \\___|   |_|\\___/ \\__,_|\n";

                System.out.println("____________________________________________________________\n" +
                                "Yo! It's \n" + logo + "\n" +
                                "What can I do for you?\n" +
                                "____________________________________________________________\n");

                boolean flag = true;
                while (flag) {
                        @SuppressWarnings("resource")
                        Scanner scanInput = new Scanner(System.in); // Create a Scanner object
                        System.out.print("Command: ");

                        String input = scanInput.nextLine(); // Read user input

                        if (input.toLowerCase().trim().equals("bye")) {
                                bye();
                                break;
                        } else if (input.toLowerCase().trim().equals("list")) {
                                printList();
                        } else if (input.toLowerCase().trim().contains("mark")) {
                                int num = Integer.parseInt(input.substring(5, input.length()));
                                markTask(num - 1);
                        } else {
                                Task newTask = new Task(input);
                                taskList.add(newTask);
                                System.out.println("Item: " + input + " added to list"); // Add user input to list
                        }
                }
        }
}
