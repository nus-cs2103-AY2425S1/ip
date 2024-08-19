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
                    if (this.toDo.isEmpty()) {
                        System.out.println("You have nothing in your list.");
                    } else {
                        System.out.println(("Here are the items in your list:"));
                        for (int i = 0; i < this.toDo.size(); i++) {
                            System.out.println((i + 1) + ". " + this.toDo.get(i));
                        }
                    }
                    break;
                case "mark": {
                    try {
                        int position = Integer.parseInt(input.split(" ")[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No item specified for marking. Please input the number indicating the item.");
                        break;
                    }
                    int position = Integer.parseInt(input.split(" ")[1]);
                    Task task = this.toDo.get(position - 1);
                    task.changeStatus();
                    System.out.println("I have marked this task as done!");
                    System.out.println(task);
                    break;
                }
                case "unmark": {
                    try {
                        int position = Integer.parseInt(input.split(" ")[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No item specified for unmarking. Please input the number indicating the item.");
                        break;
                    }
                    int position = Integer.parseInt(input.split(" ")[1]);
                    Task task = this.toDo.get(position - 1);
                    task.changeStatus();
                    System.out.println("I have marked this task as not done yet!");
                    System.out.println(task);
                    break;
                }
                case "todo": {
                    try {
                        Todo todo = new Todo(input.split(" ", 2)[1]);
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("You have not added any description for the todo task. Please try again.");
                        break;
                    }
                    Todo todo = new Todo(input.split(" ", 2)[1]);
                    this.toDo.add(todo);
                    System.out.println("Got it. I have added this todo.");
                    System.out.println(todo);
                    System.out.println("You have " + this.toDo.size() + " items in your list.");
                    break;
                }
                case "deadline": {
                    try {
                        String time = input.split("/by")[1];
                        String description = input.split("/by")[0].split(" ", 2)[1];
                    }
                    catch(IndexOutOfBoundsException e) {
                        System.out.println("You have not added any description or deadline for the deadline task. Please try again.");
                        break;
                    }
                    String time = input.split("/by")[1];
                    String description = input.split("/by")[0].split(" ", 2)[1];
                    Deadline deadline = new Deadline(description, time);
                    this.toDo.add(deadline);
                    System.out.println("Got it. I have added this deadline.");
                    System.out.println(deadline);
                    System.out.println("You have " + this.toDo.size() + " items in your list");
                    break;
                }
                case "event": {
                    try {
                        String start = input.split("/from")[1].split("/to")[0];
                        String end = input.split("/from")[1].split("/to")[1];
                        String description = input.split("/from")[0].split(" ", 2)[1];
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You have not added any description or timings for the event task. Please try again.");
                        break;
                    }
                    String start = input.split("/from")[1].split("/to")[0];
                    String end = input.split("/from")[1].split("/to")[1];
                    String description = input.split("/from")[0].split(" ", 2)[1];
                    Event event = new Event(description, start, end);
                    this.toDo.add(event);
                    System.out.println("Got it. I have added this event.");
                    System.out.println(event);
                    System.out.println("You have " + this.toDo.size() + " items in your list.");
                    break;
                }
                default: {
                    System.out.println("Please specify the type of item that you wish to add ( Todo / Event / Deadline )");
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
