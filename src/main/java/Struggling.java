import java.util.ArrayList;
import java.util.Scanner;

enum Commands
{
    bye, list, mark, unmark, todo, deadline, event, delete;
}

public class Struggling {
    final private String name = "struggling";
    private ArrayList<Task> taskArr = new ArrayList<>();

    Struggling() {
        reply(String.format("Hello! I'm %s\nWhat can I do for you?", this.name));
    }

    public boolean read(String cmd) {

        try {
            String[] args = cmd.split(" ");

            switch (Commands.valueOf(args[0])) {
                case bye:
                    reply("Bye. Hope to see you again soon!");
                    return false;
                case list:
                    list();
                    break;
                case mark:
                    markTask(Integer.parseInt(args[1]));
                    break;
                case unmark:
                    unmarkTask(Integer.parseInt(args[1]));
                    break;
                case todo:
                    try {
                        addTask(new ToDo(cmd.substring(5)));
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new StrugglingException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                case deadline:
                    int byIndex = cmd.indexOf("/by ");
                    String dDescription = cmd.substring(9, byIndex).trim();
                    String dBy = cmd.substring(byIndex + 4);
                    addTask(new Deadline(dDescription, dBy));
                    break;
                case event:
                    int fromIndex = cmd.indexOf("/from ");
                    int toIndex = cmd.indexOf("/to ");
                    String eDescription = cmd.substring(6, fromIndex).trim();
                    String eFrom = cmd.substring(fromIndex + 6, toIndex).trim();
                    String eTo = cmd.substring(toIndex + 4);
                    addTask(new Event(eDescription, eFrom, eTo));
                    break;
                case delete:
                    deleteTask(Integer.parseInt(args[1]));
                    break;
            }
        } catch (IllegalArgumentException e) {
            reply("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (StrugglingException e) {
            reply(e.getMessage());
        }

        return true;
    }

    private void reply(String str) {
        String line = "____________________________________________________________";
        StringBuilder indent = new StringBuilder();
        for(String s : str.split("\\R")) {
            indent.append(" ").append(s).append("\n");
        }
        String box = String.format("%s\n%s%s", line, indent, line);
        for(String s : box.split("\\R")) {
            System.out.printf("\t%s\n", s);
        }
        System.out.println();
    }

    private void addTask(Task task) {
        this.taskArr.add(task);

        reply(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                task, this.taskArr.size()));
    }

    private  void list() {
        StringBuilder ans = new StringBuilder("Here are the tasks in your list:\n");
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

    private void deleteTask(int i) {
        int index = i - 1;
        Task t = this.taskArr.remove(index);

        reply(String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.",
                t, this.taskArr.size()));
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

