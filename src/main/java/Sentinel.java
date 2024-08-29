import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
public class Sentinel {

    enum Command{todo, deadline, event, list, mark, unmark, delete, help, bye }

    private static ArrayList<Task> list;

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
        try {
            list = new FileLoader().loadTasks();
        } catch (Exception e) {
            System.err.println("Error loading file: "+ e);
            list = new ArrayList<>();
        }
        Scanner sc = new Scanner(System.in);
        Command input = null;
        do {
            System.out.println("____________________________________________________________\n");
            try { input = Command.valueOf(sc.next().toLowerCase());}
            catch (IllegalArgumentException e) {System.out.println("Unrecognised command. Type \"help\" to list all commands."); continue;}
            System.out.println("____________________________________________________________\n");
            new FileWriter(list).saveTasks();
            switch (input) {
                case list -> {
                    System.out.println("Here " + (list.size() == 1 ? "is" : "are") + " the " + (list.size() == 1 ? "task" : "tasks") + " in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("\t" + (i + 1) + "." + list.get(i).listedString());
                    }
                }
                case mark, unmark, delete -> {
                    int num = sc.nextInt();
                    if (num > list.size()) {System.out.println("No such item in the list!"); break;}
                    else if (input.equals(Command.delete)) {System.out.println("I have deleted the following task:\n\t" + list.remove(num-1).listedString() + "\n You have " + list.size() + " remaining " + (list.size() == 1 ? "task" : "tasks") + "."); break;}
                    else if (input.equals(Command.mark) && list.get(num-1).isDone()) {System.out.println(list.get(num-1) + " has already been marked as done."); break;}
                    else if (input.equals(Command.unmark) && !list.get(num-1).isDone()) {System.out.println(list.get(num-1) + " has already been marked as undone."); break;}
                    if (list.get(num-1).isDone()) list.get(num - 1).setUndone(); else list.get(num - 1).setDone();
                    System.out.println("Alright! I've marked this task as " + (list.get(num-1).isDone() ? "done" : "undone") + ":");
                    System.out.println("\t" + list.get(num - 1).getStatusIcon() + " " + list.get(num - 1));
                }
                case todo, deadline, event -> {
                    String input2 = sc.nextLine().trim();
                    if (input2.isEmpty()) {System.out.println(input.toString().substring(0, 1).toUpperCase() + input.toString().substring(1) + " name cannot be empty"); continue;}
                    switch (input){
                        case todo -> list.add(new ToDo(input2));

                        case deadline, event -> {
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
                                LocalDateTime _from = GeminiAPI.formatDateTime(from), _to = GeminiAPI.formatDateTime(to);
                                switch (input){
                                    case deadline -> {
                                        if (_to == null) {
                                            System.out.println("Please state the deadline using /by 'YYYY-MM-DD Time' (eg: deadline return book /by 2024-12-25 2:30pm)");
                                            continue;
                                        }
                                        list.add(new Deadline(taskName, _to));
                                    }
                                    case event -> {
                                        if (_from == null || _to == null) {
                                            System.out.println("Please state the start and end date using /from 'YYYY-MM-DD Time' and /to 'YYYY-MM-DD Time' respectively (eg: event project meeting /from 2024-12-25 2:30pm /to 2024-12-25 6:30pm)");
                                            continue;
                                        }
                                        list.add(new Event(taskName, _from, _to));
                                    }
                                }
                        }
                    }
                    System.out.println("Got it. I've added this task: " + list.get(list.size()-1));
                    System.out.println("\t" + list.get(list.size()-1).listedString());
                }
                case help -> {
                    String helpText = """
                    1. todo <task>                                Adds tasks without any date/time attached to list.
                    2. deadline <task> /by <date>                 Adds tasks that need to be done before a specific date/time to list.
                    3. event <event> /from <date> /to <date>      Adds tasks that start at a specific date/time and ends at a specific date/time to list.
                    4. list                                       List all tasks.
                    5. mark <index>                               Mark task as done.
                    6. unmark <index>                             Mark task as undone.
                    7. delete <index>                             Deletes task.
                    8. bye                                        Ends the chatbot.
                    """;
                    System.out.println(helpText);
                }
            }
        } while (input == null || !input.equals(Command.bye));

        System.out.println("Bye. Hope to see you again soon!");
    }
}
