import java.util.ArrayList;
import java.util.Scanner;
public class Sentinel {

    public static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo
                = """
                   _____                                                                                      _____\s
                  ( ___ )                                                                                    ( ___ )
                   |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   |\s
                   |   |                                                                                      |   |\s
                   |   |   ________  _______   ________   _________  ___  ________   _______   ___            |   |\s
                   |   |  |\\   ____\\|\\  ___ \\ |\\   ___  \\|\\___   ___\\\\  \\|\\   ___  \\|\\  ___ \\ |\\  \\           |   |\s
                   |   |  \\ \\  \\___|\\ \\   __/|\\ \\  \\\\ \\  \\|___ \\  \\_\\ \\  \\ \\  \\\\ \\  \\ \\   __/|\\ \\  \\          |   |\s
                   |   |   \\ \\_____  \\ \\  \\_|/_\\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|/_\\ \\  \\         |   |\s
                   |   |    \\|____|\\  \\ \\  \\_|\\ \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\____    |   |\s
                   |   |      ____\\_\\  \\ \\_______\\ \\__\\  \\__\\   \\ \\__\\ \\ \\__\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\  |   |\s
                   |   |     |\\_________\\|_______|\\|__| \\|__|    \\|__|  \\|__|\\|__| \\|__|\\|_______\\|_______|   |   |\s
                   |   |     \\|_________|                                                                     |   |\s
                   |   |                                                                                      |   |\s
                   |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___|\s
                  (_____)                                                                                    (_____)""";

        System.out.println("Hello from\n" + logo);
        System.out.println("\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            input = sc.next().toLowerCase();
            System.out.println("____________________________________________________________\n");
            switch (input) {
                case "mark" -> {
                    int num = sc.nextInt();
                    if (num > list.size()) {System.out.println("No such item in the list!"); break;}
                    else if (list.get(num-1).isDone()) {System.out.println(list.get(num-1) + " has already been marked as done."); break;}
                    list.get(num - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + list.get(num - 1).getStatusIcon() + " " + list.get(num - 1));
                }
                case "unmark" -> {
                    int num = sc.nextInt();
                    if (num > list.size()) {System.out.println("No such item in the list!"); break;}
                    else if (!list.get(num-1).isDone()) {System.out.println(list.get(num-1) + " has already been marked as undone."); break;}
                    list.get(num - 1).setUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("\t" + list.get(num - 1).getStatusIcon() + list.get(num - 1));
                }
                case "list" -> {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("\t" + (i + 1) + "." + list.get(i).classFirstChar() + list.get(i).getStatusIcon() + " " + list.get(i).listedString());
                    }
                }
                case "todo", "deadline", "event" -> {
                    String input2 = sc.nextLine().trim();
                    if (input2.isEmpty()) {System.out.println(input.substring(0, 1).toUpperCase() + input.substring(1) + " name cannot be empty"); continue;}
                    switch (input){
                        case "todo" -> list.add(new ToDo(input2));

                        case "deadline", "event" -> {
                            String[] stringArr = input2.split(" ");
                            String taskName = "", from = "", to = "";
                            boolean task = true, fr = false;
                            for (String word: stringArr){
                                if (word.equalsIgnoreCase("/by") || word.equalsIgnoreCase("/to")) {task = false; fr = false;}
                                else if (word.equalsIgnoreCase("/from")) {task = false; fr = true;}

                                else if (task && !fr) taskName = taskName.concat(word + " ");
                                else if (!task && fr) from = from.concat(word + " ");
                                else to = to.concat(word + " ");
                            }
                            taskName = taskName.trim(); from = from.trim(); to = to.trim();
                             switch (input){
                                case "deadline" -> {
                                    if (to.isEmpty()) {
                                        System.out.println("Please state the deadline using /by (eg: deadline return book /by Sunday)");
                                        continue;
                                    }
                                    list.add(new Deadline(taskName, to));
                                }
                                case "event" -> {
                                    if (from.isEmpty() || to.isEmpty()) {
                                        System.out.println("Please state the start and end date using /from and /to respectively (eg: event project meeting /from Mon 2pm /to 4pm)");
                                        continue;
                                    }
                                    list.add(new Event(taskName, from, to));
                                }
                            }
                        }
                    }
                    System.out.println("Got it. I've added this task: " + list.get(list.size()-1));
                    System.out.println("\t" + list.get(list.size()-1).classFirstChar() + list.get(list.size()-1).getStatusIcon() + " " + list.get(list.size()-1).listedString());
                }
                case "/help" ->{
                    String helpText = """
                    1. todo <task>                                Adds tasks without any date/time attached to list.
                    2. deadline <task> /by <date>                 Adds tasks that need to be done before a specific date/time to list.
                    3. event <event> /from <date> /to <date>      Adds tasks that start at a specific date/time and ends at a specific date/time to list.
                    4. list                                       List all tasks.
                    5. mark <index>                               Mark task as done.
                    6. unmark <index>                             Mark task as undone.
                    7. bye                                        Ends the chatbot.
                    """;
                    System.out.println(helpText);
                }
                default -> {
                    if (!input.equals("bye"))
                        System.out.println("Unrecognised command. /help to list all commands.");
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
