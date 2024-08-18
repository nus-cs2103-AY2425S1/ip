import java.util.Scanner; // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class

public class FutureYou {
        static ArrayList<String> list = new ArrayList<String>();

        public static void bye() {
                System.out.println("Bye. Hope to see you again soon!\n" +
                                "____________________________________________________________\n");
        }

        public static void printList() {
                int count = 1;
                System.out.println("Items in List:");
                for (String item : list) {
                        System.out.println(count++ + ". " + item);
                }
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
                        } else {
                                list.add(input);
                                System.out.println("Item: " + input + " added to list"); // Add user input to list
                        }
                }
        }
}
