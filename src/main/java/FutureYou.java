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
                        System.out.println(count++ + ". " + item.print());
                }
        }

        public static void addTask(String taskName) {
                Task newTask = new Task(taskName);
                taskList.add(newTask);
                System.out.println("Added this task: ");
                System.out.println(newTask.print());
                System.out.println(taskList.size() + " tasks in the list");
        }

        public static void addDeadline(String taskName, String deadline) {
                Deadline newDeadline = new Deadline(taskName, deadline);
                taskList.add(newDeadline);
                System.out.println("Added this task: ");
                System.out.println(newDeadline.print());
                System.out.println(taskList.size() + " tasks in the list");
        }

        public static void markTask(int n) {
                taskList.get(n).markTask();
                System.out.println("Marked as Done:");
                System.out.println(taskList.get(n).print());
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
                        } else if (input.toLowerCase().trim().contains("todo")) {
                                String taskName = input.substring(5, input.length());
                                addTask(taskName);
                        } else if (input.toLowerCase().trim().contains("deadline")) {
                                String[] inputs = input.split("/");
                                for (String i : inputs) {
                                        System.out.println(i);
                                }
                                String taskName = inputs[0].substring(8, inputs[0].length());
                                String date = inputs[1].substring(3, inputs[1].length());
                                addDeadline(taskName, date);
                        } else if (input.toLowerCase().trim().equals("list")) {
                                printList();
                        } else if (input.toLowerCase().trim().contains("mark")) {
                                int num = Integer.parseInt(input.substring(5, input.length()));
                                markTask(num - 1);
                        } else {
                                System.err.println(input);
                                // break;
                        }
                }
        }
}
