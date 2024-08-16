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
        StringBuilder allItems = new StringBuilder("Got your cheese:");
        for (int i = 0; i < ls.size(); i++) {
            allItems.append("\n").append(i + 1).append(". ").append(ls.get(i).toString());
        }
        say(String.valueOf(allItems));
    }
    /**
     * Method to say a newly added task
     * @param t Task
     */
    private static void say(Task t, int i) {
        String s = "Added new cheese ;)\n" + t.toString() + "\n" + i + " cheese in the shelf";
        say(s);
    }

    private static Task mark(ArrayList<Task> tasks, String[] inputTokens, boolean done) throws CheeseException {
        int idx;
        if (inputTokens.length < 2) throw new CheeseException("Need location of cheese");

        try {
            idx = Integer.parseInt(inputTokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new CheeseException(e.getMessage());
        }

        if (idx >= tasks.size() || idx < 0) throw new CheeseException("Incorrect location of cheese");
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

            try {
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
                        say("Beep bop. Cheese melted:\n" + markT);
                        break;
                    case "unmark":
                        Task unmarkT = mark(list, inputTokens, false);
                        say("Bop beep. Unmelted cheese:\n" + unmarkT);
                        break;
                    case "todo":
                        ToDo todo = ToDo.of(input);
                        list.add(todo);
                        say(todo, list.size());
                        break;
                    case "deadline":
                        Deadline deadline = Deadline.of(input);
                        list.add(deadline);
                        say(deadline, list.size());
                        break;
                    case "event":
                        Event event = Event.of(input);
                        list.add(event);
                        say(event, list.size());
                        break;
                    default:
                        list.add(new Task(input));
                        say("added: " + input);
                }
            }catch (CheeseException e) {
                say(e.getMessage());
            }
        } while (!exitChat);

        say(ending);
    }
}
