import java.util.ArrayList;
import java.util.Scanner;

class King {
    public static void main(String[] args) {
        String logo = " _  __ _             \n" +
                "| |/ /(_)_ __   __ _ \n" +
                "| ' / | | '_ \\ / _` |\n" +
                "| . \\ | | | | | (_| |\n" +
                "|_|\\_\\|_|_| |_|\\__, |\n" +
                "               |___/ ";

        System.out.println("Behold the wrath of the\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("What do you have for me?");
        System.out.println("____________________________________________________________");

        //Take in user input
        Scanner scanner = new Scanner(System.in);

        //Array to store tasks
        ArrayList<Task> tasks = new ArrayList<>();
        //int taskcounter = 0;

        while(true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("delete")) {
                String[] parts = input.split(" ");
                //prone to error if format is off or value after " " is not an int
                int num = Integer.parseInt(parts[1]);
                System.out.println("I have relieved your suffering of:\n" + tasks.get(num - 1).toString());
                tasks.remove(num - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                //prone to error if format is off or value after " " is not an int
                int num = Integer.parseInt(parts[1]);
                tasks.get(num - 1).markAsDone();
                System.out.println("Good job on the completion my minion!\n" + tasks.get(num - 1).toString());
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                //prone to error if format is off or value after " " is not an int
                int num = Integer.parseInt(parts[1]);
                tasks.get(num - 1).markAsUndone();
                System.out.println("Better get to work before I execute you!\n" + tasks.get(num - 1).toString());
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("todo")) {
                String description = input.substring(5);
                tasks.add(new Todo(description));
                System.out.println("____________________________________________________________");
                System.out.println("Approved. I've added this to your list of duties:\n" + tasks.get(tasks.size() - 1).toString());
                //taskcounter++;
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("deadline")) {
                String[] parts = input.split(" /by ");
                if (parts.length == 2) {
                    String description = parts[0].substring(9); // "deadline " is 9 chars
                    String by = parts[1];
                    tasks.add(new Deadline(description, by));
                    System.out.println("____________________________________________________________");
                    System.out.println("Approved. I've added this to your list of duties:\n" + tasks.get(tasks.size() - 1).toString());
                    //taskcounter++;
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("Invalid deadline format.");
                }
            } else if (input.startsWith("event")) {
                String[] parts = input.split(" /from | /to ");
                if (parts.length == 3) {
                    String description = parts[0].substring(6); // "event " is 6 chars
                    String from = parts[1];
                    String to = parts[2];
                    tasks.add(new Event(description, from, to));
                    System.out.println("____________________________________________________________");
                    System.out.println("Approved. I've added this to your list of duties:\n" + tasks.get(tasks.size() - 1).toString());
                    //taskcounter++;
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("Invalid event format.");
                }
            } else {
                System.out.println("Invalid Command");
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("You are dismissed my humble servant.");
        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
