import java.util.ArrayList;
import java.util.Scanner;

public class WheelyBigCheese {
    private static final String greeting = "Hello! I'm Wheely Big Cheese\nWhat can I do for you?";
    private static final String ending = "Schwooo Weeeeee!!! Shutting down.....";

    /**
     * Method to format the output of the bot
     * @param s String to say
     */
    private static void say(String s){
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println("____________________________________________________________");
    }
    /**
     * Method to say a list
     * @param ls ArrayList
     */
    private static void say(ArrayList<?> ls) {
        StringBuilder allItems = new StringBuilder("Got your cheese:\n");
        for (int i = 0; i < ls.size(); i++) {
            allItems.append(i + 1).append(". ").append(ls.get(i).toString());
        }
        say(String.valueOf(allItems));
    }
    /**
     * Method to say a newly added task
     * @param t Task
     */
    private static void say(Task t, int i) {
        String s = "Added new cheese!:\n" + t.toString() + "\n" + i + " cheese in the shelf";
        say(s);
    }

    private static Task mark(ArrayList<Task> tasks, String[] inputTokens, boolean done){
        int idx = Integer.parseInt(inputTokens[1]) - 1;
        Task t = tasks.get(idx);
        t.setDone(done);
        return t;
    }

    public static void main(String[] args) {
        //Variables for bot
        Scanner sc = new Scanner(System.in); // Scanner to get user input
        String input;
        ArrayList<Task> list = new ArrayList<>();
        boolean exitChat = false;

        say(greeting);

        //Main logic for bot
        do {
            //Get user input and basic manipulation of input
            input = sc.nextLine();
            String[] inputTokens = input.split(" ");
            String command = inputTokens[0];

            //Switch statement for different responses to different commands
            switch (command) {
                case "bye":
                    exitChat = true;
                    break;
                case "list":
                    say(list);
                    break;
                case "mark":
                    Task markT = mark(list, inputTokens, true);
                    say("Beep bop. Melted:\n" + markT);
                    break;
                case "unmark":
                    Task unmarkT = mark(list, inputTokens, false);
                    say("Bop beep. Unmelted cheese:\n" + unmarkT);
                    break;
                case "todo":
                    ToDo todo = new ToDo(input.replace("todo ", ""));
                    list.add(todo);
                    say(todo, list.size());
                    break;
                case "deadline":
                    String[] tokens = input.replace("deadline ", "").split("/by");
                    Deadline deadline = new Deadline(tokens[0], tokens[1].strip());
                    list.add(deadline);
                    say(deadline, list.size());
                    break;
                case "event":
                    String[] words = input.replace("event ", "").split("/from");
                    String[] dates = words[1].split("/to");
                    Event event = new Event(words[0], dates[0].strip(), dates[1].strip());
                    list.add(event);
                    say(event, list.size());
                    break;
                default:
                    list.add(new Task(input));
                    say("added: " + input);
            }
        } while (!exitChat);

        say(ending);
    }
}
