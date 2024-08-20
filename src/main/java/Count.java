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

    private void addToList(String description) {
        reply("Added: " + description);
        ls.add(new Task(description));
    }

    // Given an index, returns the completion state and the task description
    private String taskFormat(int i) {
        Task curr = ls.get(i);
        return String.format("[%s] ", curr.getStatusIcon()) + curr.getDescription();
    }

    private void listReply() {
        String ans = "";
        for (int i = 0 ; i < ls.size() ; i++) {
            if (i != ls.size() - 1) {
                ans += (i + 1) + "." + taskFormat(i) + "\n";
            } else {
                ans += (i + 1) + "." + taskFormat(i);
            }
        }
        reply(ans);
    }

    // Sets the task to complete
    // TODO: exception for not existing index for mark and unmark
    private void mark(int i) {
        ls.get(i - 1).setCompletion(true);
        reply("Good job, I have marked this task as complete:\n" + taskFormat(i - 1));
    }

    private void unmark(int i) {
        ls.get(i - 1).setCompletion(false);
        reply("No problem, I have marked this task as incomplete:\n" + taskFormat(i - 1));
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
                addToList(command);
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
            default:
                addToList(command);
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
