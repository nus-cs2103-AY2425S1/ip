import java.awt.geom.NoninvertibleTransformException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Derrick {

    private final ArrayList<Task> toDo = new ArrayList<>();
    public void greetings() {
        System.out.println("Hello, I am Derrick");
        System.out.println("What can I do for you?");
    }


    public void addTodo() {

        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            String input = scanner.nextLine();
            String instructions = input.split(" ")[0];

            switch (instructions) {
                case "bye":
                    exit();
                    break label;
                case "list":
                    System.out.println(("Here are the items in your list:"));
                    for (int i = 0; i < this.toDo.size(); i++) {
                        System.out.println((i + 1) + ". " + this.toDo.get(i));
                    }
                    break;
                case "mark": {
                    int position = Integer.parseInt(input.split(" ")[1]);
                    Task task = this.toDo.get(position - 1);
                    task.changeStatus();
                    System.out.println("I have marked this task as done!");
                    System.out.println(task);
                    break;
                }
                case "unmark": {
                    int position = Integer.parseInt(input.split(" ")[1]);
                    Task task = this.toDo.get(position - 1);
                    task.changeStatus();
                    System.out.println("I have marked this task as not done yet!");
                    System.out.println(task);
                    break;
                }
                case "todo": {
                    Todo todo = new Todo(input.split(" ", 2)[1]);
                    this.toDo.add(todo);
                    System.out.println("Got it. I have added this todo");
                    System.out.println(todo);
                    System.out.println("You have " + this.toDo.size() + " items in your list");
                    break;
                }
                case "deadline": {
                    String time = input.split("/by")[1];
                    String description = input.split("/by")[0].split(" ", 2)[1];
                    Deadline deadline = new Deadline(description, time);
                    this.toDo.add(deadline);
                    System.out.println("Got it. I have added this deadline");
                    System.out.println(deadline);
                    System.out.println("You have " + this.toDo.size() + " items in your list");
                    break;
                }
                case "event": {
                    String start = input.split("/from")[1].split("/to")[0];
                    String end = input.split("/from")[1].split("/to")[1];
                    String description = input.split("/from")[0].split(" ", 2)[1];
                    Event event = new Event(description, start, end);
                    this.toDo.add(event);
                    System.out.println("Got it. I have added this event");
                    System.out.println(event);
                    System.out.println("You have " + this.toDo.size() + " items in your list");
                    break;
                }
                default: {
                    Task task = new Task(input);
                    this.toDo.add(task);
                    System.out.println("added: " + task);
                    break;
                }
            }
        }
    }


    public static void exit() {
        System.out.println("Goodbye!");
    }
    public static void main(String[] args) {
        Derrick chatbot = new Derrick();
        chatbot.greetings();
        chatbot.addTodo();
    }
}
