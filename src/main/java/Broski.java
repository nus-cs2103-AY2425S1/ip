import java.util.ArrayList;
import java.util.Scanner;

public class Broski {
    private String line = "_________________________________________";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>(100);
    public void start() {
        System.out.println(line);
        System.out.println("Wassup! I'm Broski!");
        System.out.println("What can I do for you bro?");
        System.out.println(line);
    }

    public void chatbot() {
        String reply = scanner.nextLine();
        if (reply.equals("list")) {
            System.out.println(line);
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
            System.out.println(line);
            this.chatbot();
        } else if (reply.equals("bye")) {
            System.out.println(line);
            this.exit();
        } else if (reply.length() > 5 && reply.startsWith("mark")) {
            System.out.println(line);
            int i = Integer.parseInt(reply.split(" ")[1]);
            list.get(i).mark();
            System.out.println("Solid! Marked as done for you:");
            System.out.println(list.get(i));
            System.out.println(line);
            this.chatbot();
        } else if (reply.length() > 7 && reply.startsWith("unmark")) {
            System.out.println(line);
            int i = Integer.parseInt(reply.split(" ")[1]);
            list.get(i).unmark();
            System.out.println("Alright, I've marked the task as undone:");
            System.out.println(list.get(i));
            System.out.println(line);
            this.chatbot();
        } else {
            System.out.println(line);
            if (reply.length() > 5 && reply.startsWith("todo")) {
                Todo todo = new Todo(reply.replaceFirst("todo ", ""));
                list.add(todo);
                System.out.println("Gotcha! I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (reply.length() > 9 && reply.startsWith("deadline")) {
                Deadline deadline = new Deadline(
                        reply.replaceFirst("deadline ", "").split(" /")[0],
                        reply.split(" /")[1]);
                list.add(deadline);
                System.out.println("Gotcha! I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else {
                String[] splitter = reply.split(" /");
                Event event = new Event(
                        splitter[0].replaceFirst("event ", ""),
                        splitter[1],
                        splitter[2]);
                list.add(event);
                System.out.println("Gotcha! I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
            System.out.println(line);
            this.chatbot();
        }
    }

    public void exit() {
        System.out.println("Bye, bro. See ya around!");
        System.out.println(line);
    }

    public static void main(String[] args) {
        Broski bot = new Broski();
        bot.start();
        bot.chatbot();
    }
}
