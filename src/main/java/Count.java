import java.util.*;

public class Count {
    private ArrayList<Task> ls;

    public Count() {
        this.ls = new ArrayList<>();
    }
    private void reply(String output) {
        String spacer = "\n____________________________________________________________\n";
        System.out.println(spacer + output + spacer);
    }
    private void greet() {
        reply("Hello! I'm Count!\nWhat can I do for you?");
    }

    private void farewell() {
        reply("Bye. Hope to see you again soon!");
    }

    private void addTask(Task t) {
        ls.add(t);
        reply("Added the following task:\n" + t.toString() +"\nYou now have " + ls.size() + " task(s) in your list");
    }

    private void listReply() {
        String ans = "Here are the tasks in your list:\n";
        for (int i = 0 ; i < ls.size() ; i++) {
            if (i != ls.size() - 1) {
                ans += (i + 1) + "." + ls.get(i).toString() + "\n";
            } else {
                ans += (i + 1) + "." + ls.get(i).toString();
            }
        }
        reply(ans);
    }

    // Sets the task to complete
    // TODO: exception for not existing index for mark and unmark
    private void mark(int i) {
        ls.get(i - 1).setCompletion(true);
        reply("Good job, I have marked this task as complete:\n" + ls.get(i - 1).toString());
    }

    private void unmark(int i) {
        ls.get(i - 1).setCompletion(false);
        reply("No problem, I have marked this task as incomplete:\n" + ls.get(i - 1).toString());
    }

    // single word command parser
    private void parser(String command) {
        switch (command.toLowerCase()) {
            case "hello":
                greet();
                break;
            case "bye":
                farewell();
                break;
            case "list":
                listReply();
                break;
            default:
                reply("I'm sorry, I did not understand that, could you say that again?");
        }
    }

    // multi-world parser
    // TODO: catch exception for valueof
    private void parser(String command, String firstWord) {
        String temp[] = command.split(" ", 2);
        String rest = temp[1];
        switch (firstWord.toLowerCase()) {
            case "mark":
                mark(Integer.valueOf(rest));
                break;
            case "unmark":
                unmark(Integer.valueOf(rest));
                break;
            case "todo":
                addTask(new ToDos(rest));
                break;
            case "deadline":
                String commandSplitD[] = rest.split(" /by ", 2);
                addTask(new Deadlines(commandSplitD[0], commandSplitD[1]));
                break;
            case "event":
                String commandSplitE[] = rest.split(" /from ", 2);
                String startEndTime[] = commandSplitE[1].split(" /to ", 2);
                addTask(new Events(commandSplitE[0], startEndTime[0], startEndTime[1]));
                break;
            default:
                reply("I'm sorry, I did not understand that, could you say that again?");
        }
    }

    public static void main(String[] args) {
        Count c = new Count();
        Scanner sc = new Scanner(System.in);
        c.greet();

        while (true) {
            String command = sc.nextLine();

            // If there is more than 1 word,
            if (command.indexOf(" ") > -1) {
                c.parser(command, command.substring(0, command.indexOf(" ")).trim());
            } else {
                c.parser(command);
            }
        }

    }
}
