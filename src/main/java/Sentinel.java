import java.util.Scanner;

public class Sentinel {

    public static int index = 0;
    public static Task[] items = new Task[100];

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
        try (Scanner sc = new Scanner(System.in)) {
            String input = "";
            while (!input.equals("bye")) {
                System.out.println("____________________________________________________________\n");
                input = sc.next();
                System.out.println("____________________________________________________________\n");
                switch (input) {
                    case "mark" -> {
                        int num = sc.nextInt();
                        if (num > index) {System.out.println("No such item in the list!"); break;}
                        else if (items[num-1].isDone()) {System.out.println(items[num-1] + " has already been marked as done."); break;}
                        items[num - 1].setDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("\t" + items[num - 1].getStatusIcon() + items[num - 1]);
                    }
                    case "unmark" -> {
                        int num = sc.nextInt();
                        if (num > index) {System.out.println("No such item in the list!"); break;}
                        else if (!items[num-1].isDone()) {System.out.println(items[num-1] + " has already been marked as undone."); break;}
                        items[num - 1].setUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("\t" + items[num - 1].getStatusIcon() + items[num - 1]);
                    }
                    case "list" -> {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < index; i++) {
                            System.out.println("\t" + (i + 1) + "." + items[i].classFirstChar() + items[i].getStatusIcon() + " " + items[i].listedString());
                        }
                    }
                    case "todo", "deadline", "event" -> {
                        String input2 = sc.nextLine().trim();
                        switch (input){
                            case "todo" -> items[index] = new ToDo(input2);

                            case "deadline", "event" -> {
                                String[] stringArr = input2.split(" ");
                                String taskName = "", from = "", to = "";
                                boolean task = true, fr = false;
                                for (String word: stringArr){
                                    if (word.contains("/by") || word.contains("/to")) {task = false; fr = false;}
                                    else if (word.contains("/from")) {task = false; fr = true;}

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
                                        items[index] = new Deadline(taskName, to);
                                    }
                                    case "event" -> {
                                        if (from.isEmpty() || to.isEmpty()) {
                                            System.out.println("Please state the start and end date using /from and /to respectively (eg: event project meeting /from Mon 2pm /to 4pm)");
                                            continue;
                                        }
                                        items[index] = new Event(taskName, from, to);
                                    }
                                }
                            }
                        }
                        System.out.println("Got it. I've added this task: " + items[index]);
                        System.out.println("\t" + items[index].classFirstChar() + items[index].getStatusIcon() + " " + items[index++].listedString());
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
}
