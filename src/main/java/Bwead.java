import java.util.ArrayList;
import java.util.Scanner;

public class Bwead {

    public static String name = "Bwead";
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Task> texts = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello from " + name + "!");

        while (true) {
            String input = scanner.nextLine();

            boolean oneword = input.indexOf(" ") == -1;

            if (oneword && input.equals("todo")) {
                System.out.println("The description of a todo cannot be empty.");
            } else if (oneword && input.equals("deadline")) {
                System.out.println("The description of a deadline cannot be empty.");
            } else if (oneword && input.equals("event")) {
                System.out.println("The description of a event cannot be empty.");
            } else if (oneword && !input.equals("bye")) {
                System.out.println("I'm sorry, I don't know what that means.");
            }


            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printlist();
            } else if (input.startsWith("mark")) {
                int toadd = Integer.valueOf(input.split(" ")[1]);
                Task task = texts.get(toadd - 1);
                task.setDone(true);
                System.out.println("Nice! I've marked this task as done: " + task.text);
            } else if (input.startsWith("unmark")) {
                int toadd = Integer.valueOf(input.split(" ")[1]);
                Task task = texts.get(toadd - 1);
                task.setDone(false);
                System.out.println("OK, I've marked this task as not done yet: " + task.text);
            } else if (input.startsWith("todo ")) {
                input = input.replace("todo ", "");
                Todo task = new Todo(input);
                texts.add(task);
                System.out.println("Got it. I've added this task: " + task.toString());
                System.out.println("Now you have " + texts.size() + " tasks in the list.");
            } else if (input.startsWith("deadline")) {
                String input1 = input.replace("deadline ", "");
                String date = input.split("/")[1];
                int slash = input1.indexOf("/");
                Deadline task = new Deadline(input1.substring(0, slash -1), date);
                texts.add(task);
                System.out.println("Got it. I've added this task: " + task.toString());
                System.out.println("Now you have " + texts.size() + " tasks in the list.");
            } else if (input.startsWith("event")) {
                input = input.replace("event ", "");
                String name = input.split("/from")[0];
                String start = input.split("/from")[1].split("/to")[0];
                String end = input.split("/from")[1].split("/to")[1];
                Event task = new Event(name, start, end);
                texts.add(task);
                System.out.println("Got it. I've added this task: " + task.toString());
                System.out.println("Now you have " + texts.size() + " tasks in the list.");
            }
        }
    }

    public static void printlist() {
        for (int i = 1; i <= texts.size(); i++) {
            String done;
            Task task = texts.get(i-1);
            System.out.println(i + "." + task.toString());
        }
    }

}
