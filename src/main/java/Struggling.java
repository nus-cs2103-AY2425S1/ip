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
            default:
                addTask(cmd);
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

    private void addTask(String taskDescription) {
        this.taskArr.add(new Task(taskDescription));
        reply(String.format("added: %s", taskDescription));
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

