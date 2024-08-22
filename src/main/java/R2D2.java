import java.util.Scanner;
import java.util.*;
public class R2D2 {
    public static void main(String[] args) {

        class Task {
            protected String description;
            protected boolean isDone;
            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }
            public String getStatusIcon() {
                return (isDone ? "X" : " "); // mark done task with X
            }
            public void setDone(boolean setter) {
                this.isDone = setter;
            }
            @Override
            public String toString() {
                return "[" + this.getStatusIcon() + "] " + this.description;
            }
        }

        class Deadline extends Task {
            protected String date;
            public Deadline(String description, String date) {
                super(description);
                this.date = date;
            }
            @Override
            public String toString() {
                return "[D]" + super.toString() + " (by: " + this.date + ")";
            }
        }

        class Event extends Task {
            protected String startDate;
            protected String endDate;
            public Event(String description, String startDate, String endDate) {
                super(description);
                this.startDate = startDate;
                this.endDate = endDate;
            }
            @Override
            public String toString() {
                return "[E]" + super.toString() + "(from: " + this.startDate + " to: " + this.endDate + ")";
            }
        }

        class Todo extends Task {
            public Todo(String description) {
                super(description);
            }
            @Override
            public String toString() {
                return "[T]" + super.toString();
            }
        }
        //Opening dialogue for the bot
        String hline = "____________________________________________________________";
        System.out.println(hline);
        System.out.println("Hello! I'm R2D2! *Beep* *Boop*");
        System.out.println("What can I do for you?");
        System.out.println(hline);

        // Reading input from user
        Scanner reader = new Scanner(System.in);

        // init a new database and counter for memory recall
        Task[] database = new Task[100];
        int counter = 1;

        // Main interaction
        while (true) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                database[index].setDone(true);
                System.out.println(hline);
                System.out.println("Mission accomplished! *bzzt*");
                System.out.println(database[index].toString());
                System.out.println(hline);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                database[index].setDone(false);
                System.out.println(hline);
                System.out.println("Argh next time! *bzzt*");
                System.out.println(database[index].toString());
                System.out.println(hline);
            } else if (input.equals("list")) {
                System.out.println(hline);
                for (int i = 1; i < counter; i++) {
                    System.out.println(i + "." + database[i].toString());
                }
                System.out.println(hline);
            } else if (input.startsWith("todo")) {
                try {
                    Task task = new Todo(input.substring(5));
                    database[counter] = task;
                    System.out.println("Understood boss. Added!");
                    System.out.println(task.toString());
                    System.out.println("You currently have " + counter + " missions available *reeeee* ");
                    System.out.println(hline);
                    counter++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("*BEEP Description empty! *zzzz*");
                }
            } else if (input.startsWith("deadline")) {
                String[] parts = input.split("/by");
                Task task = new Deadline(parts[0].substring(9), parts[1].substring(1));
                database[counter] = task;
                System.out.println("Understood boss. Added!");
                System.out.println(task.toString());
                System.out.println("You currently have " + counter + " missions available *reeeee* ");
                System.out.println(hline);
                counter++;
            } else if (input.startsWith("event")) {
                String[] parts = input.split("/");
                Task task = new Event(parts[0].substring(6), parts[1].substring(5), parts[2].substring(3));
                database[counter] = task;
                System.out.println("Understood boss. Added!");
                System.out.println(task.toString());
                System.out.println("You currently have " + counter + " missions available *reeeee* ");
                System.out.println(hline);
                counter++;
            } else {
                System.out.println(hline);
                System.out.println("ERROR404 I do not know what you are saying *weewoo*");
                System.out.println(hline);
            }
        }

        // standard exit when bye is typed
        System.out.println(hline);
        System.out.println("Bye. Hope to see you again soon! *bzzzt*");
        System.out.println(hline);
    }
}
