import java.util.ArrayList;
import java.util.Scanner;

public class Struggling {
    final private String name = "struggling";
    private ArrayList<Task> taskArr = new ArrayList<>();

    Struggling() {
        reply(String.format("Hello! I'm %s\nWhat can I do for you?", this.name));
    }

    public boolean read(String cmd) {

        String[] args = cmd.split(" ");

        switch (args[0]) {
            case "bye":
                reply("Bye. Hope to see you again soon!");
                return false;
            case "list":
                list();
                break;
            case "mark":
                markTask(Integer.parseInt(args[1]));
                break;
            case "unmark":
                unmarkTask(Integer.parseInt(args[1]));
                break;
            case "todo":
                addTask(new ToDo(cmd.substring(5)));
                break;
            case "deadline":
                int byIndex = cmd.indexOf("/by ");
                String dDescription = cmd.substring(9, byIndex).trim();
                String dBy = cmd.substring(byIndex + 4);
                addTask(new Deadline(dDescription, dBy));
                break;
            case "event":
                int fromIndex = cmd.indexOf("/from ");
                int toIndex = cmd.indexOf("/to ");
                String eDescription = cmd.substring(6, fromIndex).trim();
                String eFrom = cmd.substring(fromIndex + 6, toIndex).trim();
                String eTo = cmd.substring(toIndex + 4);
                addTask(new Event(eDescription, eFrom, eTo));
                break;
            default:
                reply("INVALID INPUT");
                break;
        }

        return true;
    }

    private void reply(String str) {
        String line = "____________________________________________________________";
        String box = String.format("%s\n%s\n%s", line, str, line);
        for(String s : box.split("\\R")) {
            System.out.println(String.format("\t%s", s));
        }
        System.out.println();
    }

    private void addTask(Task task) {
        this.taskArr.add(task);

        reply(String.format("Got it. I've added this task: \n\t%s\nNow you have %d tasks in the list.",
                task, this.taskArr.size()));
    }

    private  void list() {
        StringBuilder ans = new StringBuilder();
        int count = 0;
        for(Task t : this.taskArr) {
            ans.append(String.format("%d. %s\n", ++count, t));
        }

        if(!ans.isEmpty()) {
            ans.deleteCharAt(ans.length() - 1);
        }

        reply(ans.toString());
    }

    private void markTask(int i) {
        int index = i - 1;
        Task t = this.taskArr.get(index);
        t.mark();

        reply(String.format("Nice! I've marked this task as done:\n\t%s", t));
    }

    private void unmarkTask(int i) {
        int index = i - 1;
        Task t = this.taskArr.get(index);
        t.unmark();

        reply(String.format("OK, I've marked this task as not done yet:\n\t%s", t));
    }

    public static void main(String[] args) {
        Struggling bot = new Struggling();
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;

        do {
            isActive = bot.read(sc.nextLine());
        } while (isActive);

    }
}

