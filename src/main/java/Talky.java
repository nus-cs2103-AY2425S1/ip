import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;
public class Talky {
    public static void printSeperator(String content) {
        String seperator = "---------------------------------------";
        System.out.println(seperator);
        System.out.println(content);
        System.out.println(seperator);
    }
    public static void main(String[] args) {
        class Task {
            protected String name;
            protected boolean marked;

            public Task(String name) {
                this.name = name;
                this.marked = false;
            }

            public String getName() {
                return this.name;
            }

            public void setMark(boolean marked) {
                this.marked = marked;
            }

            @Override
            public String toString() {
                String marked = this.marked ? "[X]" : "[ ]";
                return marked + name;
            }
        }

        class ToDo extends Task {
            public ToDo(String name) {
                super(name);
            }
            @Override
            public String toString() {
                return "ToDo: " + super.toString();
            }
        }
        class Deadline extends Task {
            protected String by;
            public Deadline(String name, String by) {
                super(name);
                this.by = by;
            }
            @Override
            public String toString() {
                return "Deadline: " + super.toString() + " (by: " + this.by + ")";
            }
        }
        class Event extends Task {
            protected String from;
            protected String to;
            public Event(String name, String from, String to) {
                super(name);
                this.from = from;
                this.to = to;
            }
            @Override
            public String toString() {
                return "Event: " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
            }
        }

        ArrayList<Task> userTasks = new ArrayList<Task>();

        printSeperator("Hello I'm Talky\n" + "How may I help you?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] commandLine = input.split(" ", 2);
            String command = commandLine[0];
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                String textsList = "";
                int rank = 1;
                for (Task task : userTasks) {
                    textsList += rank + "." + task.toString() + "\n";
                    rank++;
                }
                printSeperator(textsList);
            } else if (command.equals("mark")){
                int indexToChange = Integer.parseInt(commandLine[1]) - 1;
                Task changedTask = userTasks.get(indexToChange);
                changedTask.setMark(true);
                printSeperator("I've marked this task as done: " + changedTask.getName());
            }  else if (command.equals("unmark")){
                int indexToChange = Integer.parseInt(commandLine[1]) - 1;
                Task changedTask = userTasks.get(indexToChange);
                changedTask.setMark(false);
                printSeperator("I've marked this task as not done: " + changedTask.getName());
            } else if (command.equals("todo")) {
                userTasks.add(new ToDo(commandLine[1]));
                printSeperator("Added ToDo: " + commandLine[1]);
            } else if (command.equals("deadline")) {
                String[] params = commandLine[1].split(" /by ");
                userTasks.add(new Deadline(params[0], params[1]));
                printSeperator("Added Deadline: " + params[0]);
            } else if (command.equals("event")) {
                String[] params = commandLine[1].split(" /from | /to ");
                userTasks.add(new Event(params[0], params[1], params[2]));
                printSeperator("Added Event: " + params[0]);
            } else {
                userTasks.add(new Task(command));
                printSeperator("added: " + command);
            }
        }

        printSeperator("Bye. Do let me know if there's anything else!");
    }
}
