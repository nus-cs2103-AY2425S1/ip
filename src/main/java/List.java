import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class List {
    public static void run() {

        String initialise = """
                -----------------------------------------------
                Initialising List Bot...
                Type 'List' to show full list
                -----------------------------------------------
                """;

        System.out.print(initialise);

        Scanner scanner = RapGod.scanner;
        String input = "";
        ArrayList<Task> list = new ArrayList<Task>();

        while (true) {
            System.out.print("Add task:\n");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("List")) {
                System.out.println("-----------------------------------------------");
                System.out.println("Displaying List:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, list.get(i));
                }
                System.out.println("-----------------------------------------------");
            } else if (input.length() > 5 && input.substring(0, 5).equalsIgnoreCase("mark ")) {
                try {
                    int index = Integer.parseInt(input.substring(5));
                    list.get(index- 1).setIsDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index - 1));
                } catch (NumberFormatException exc) {
                    System.out.println("Enter a valid number after 'Mark ' to mark a task as completed. Eg. Mark 4");
                } catch (IndexOutOfBoundsException exc) {
                    System.out.println("No such task exists.");
                }
            } else if (input.length() > 7 && input.substring(0, 7).equalsIgnoreCase("unmark ")) {
                try {
                    int index = Integer.parseInt(input.substring(7));
                    list.get(index - 1).setIsDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(index - 1));
                } catch (NumberFormatException exc) {
                    System.out.println("Enter a valid number after 'Mark ' to mark a task as completed. Eg. Mark 4");
                } catch (IndexOutOfBoundsException exc) {
                System.out.println("No such task exists.");
                }
            } else if (input.toLowerCase().contains("/from") ^ input.toLowerCase().contains("/to")) {
                System.out.println("Please add both /from and /to.");
            } else if (input.equalsIgnoreCase("Bye")) {
                break;
            } else {

                if (input.toLowerCase().contains("/from") && input.toLowerCase().contains("/to")) {
                    String description  = input.substring(0, input.toLowerCase().indexOf("/from"));
                    String from = input.substring(input.toLowerCase().indexOf("/from") + 6, input.toLowerCase().indexOf("/to"));
                    String to = input.substring(input.toLowerCase().indexOf("/to") + 4);
                    list.add(new Event(description, from, to));
                } else if (input.toLowerCase().contains("/by")) {
                    String description = input.substring(0, input.toLowerCase().indexOf("/by"));
                    String due = input.substring(input.toLowerCase().indexOf("/by") + 4);
                    list.add(new Deadline(description, due));
                } else {
                    String description = input;
                    list.add(new ToDo(description));
                }

                System.out.println("-----------------------------------------------");
                System.out.printf("Got it. I've added this task: \n%s\n", list.get(list.size() - 1));
                System.out.printf("Now you have %d tasks in the list\n", list.size());
                System.out.println("-----------------------------------------------");

            }


        }

        System.out.println("-----------------------------------------------");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("-----------------------------------------------");
    }
}
