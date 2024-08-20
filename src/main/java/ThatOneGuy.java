import java.util.*;

public class ThatOneGuy {
    private static String line = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    public static void greet() {
        String name = "that one guy";
        System.out.println(line + "\nI'm " + name + ".");
        System.out.println("Make it quick, I don't have much time.\n" + line);
    }

    public static void farewell() {
        System.out.println(line + "\nWhatever. Hope you never come back.\n"  + line);
    }

    public static void list() {
        System.out.println(line);
        int len = tasks.size();
        if (len == 0) {
            System.out.println("You really don't have anything better to do?");
        }
        else {
            System.out.println("Here are your damned tasks. Complete them or something.");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
        System.out.println(line);
    }

    public static void markTask(String next) {
        Integer id = Integer.parseInt(next.substring(5)) - 1;
        if (id < 0 || id >= tasks.size()) {
            System.out.println(line + '\n' + "That's not a valid ID, bozo.");
        } else {
            tasks.get(id).mark();
            System.out.println(line);
            System.out.println("Eh. Consider this task done:");
            System.out.println(tasks.get(id).toString());
            System.out.println(line);
        }
    }

    public static void unmarkTask(String next) {
        Integer id = Integer.parseInt(next.substring(7)) - 1;
        if (id < 0 || id >= tasks.size()) {
            System.out.println(line + '\n' + "That's not a valid ID, bozo.");
        } else {
            tasks.get(id).unmark();
            System.out.println(line);
            System.out.println("Sucks to be you. Looks like you haven't done this task:");
            System.out.println(tasks.get(id).toString());
            System.out.println(line);
        }
    }
    /*
    public static void addTask(String next)  {
        Task nextTask = new Task(next);
        tasks.add(nextTask);
        System.out.println(line + "\n" + "added: " + next);
        System.out.println("That's " + tasks.size() + " tasks for your ass to handle.\n" + line);
    }

     */

    public static int locate(String keyword, String[] input) {
        for (int i = 0; i < input.length; i++) {
            if (keyword.equals(input[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String chain(String[] input, int start, int end) {
        String res = "";
        for (int i = start; i < end; i++) {
            res += input[i] + " ";
        }
        res += input[end];
        return res;
    }

    public static void addToDo(String[] splitStr) {
        int len = splitStr.length;
        String task = chain(splitStr, 1, len - 1);
        ToDo res = new ToDo(task);
        tasks.add(res);
        System.out.println(line + "\n" + "added: " + res);
        System.out.println("That's " + tasks.size() + " tasks for your ass to handle.\n" + line);
    }

    public static void addDeadline(String[] splitStr) {
        int len = splitStr.length;
        int byId = locate("/by", splitStr);
        String task = chain(splitStr, 1, byId - 1);
        String deadline = chain(splitStr, byId + 1, len - 1);

        Deadline res = new Deadline(task, deadline);
        tasks.add(res);
        System.out.println(line + "\n" + "added: " + res);
        System.out.println("That's " + tasks.size() + " tasks for your ass to handle.\n" + line);
    }

    public static void addEvent(String[] splitStr) {
        int len = splitStr.length;
        int fromId = locate("/from", splitStr);
        int toId = locate("/to", splitStr);
        String task = chain(splitStr, 1, fromId - 1);
        String from = chain(splitStr, fromId + 1, toId - 1);
        String to = chain(splitStr, toId + 1, len - 1);

        Event res = new Event(task, from, to);
        tasks.add(res);
        System.out.println(line + "\n" + "added: " + res);
        System.out.println("That's " + tasks.size() + " tasks for your ass to handle.\n" + line);
    }

    public static void cmd() {
        Scanner sc = new Scanner(System.in);
        String next, command;
        String[] splitStr;
        while (true) {
            next = sc.nextLine();
            if (next.equals("bye")) {
                break;
            } else if (next.equals("list")) {
                list();
            } else {
                splitStr = next.split(" ");
                command = splitStr[0];

                if (command.equals("mark")) {
                    markTask(next);
                } else if (command.equals("unmark")) {
                    unmarkTask(next);
                } else if (command.equals("todo")){
                    addToDo(splitStr);
                } else if (command.equals("deadline")){
                    addDeadline(splitStr);
                } else if (command.equals("event")){
                    addEvent(splitStr);
                }
            }
        }
        sc.close();
    }
    public static void main(String[] args) {
        greet();
        cmd();
        farewell();
    }
}
