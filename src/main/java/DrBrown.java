import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class DrBrown {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list;
        String greeting = "\nRoads? Where We're Going, We Don't Need Roads?! So, what can I help you with today?";
        String ending = "Your future is whatever you make it, so make it a good one! Until next time, keep the DeLorean ready!";
        String divider = "-------------------------------------------------------------------------------------------------------------------------------------------";


        System.out.println(greeting);
        User user = new User();

        while (true) {
            System.out.println(divider);
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ", 2);
            System.out.println(divider);
            switch (inputSplit[0]) {
                case "todo":
                    Task todo = new Todo(inputSplit[1].trim());
                    System.out.println("Doc, you don't just walk into a store and buy plutonium! But you sure can add this task to your list!\n");
                    System.out.println(todo.toString());
                    user.addList(todo);
                    System.out.println("\nWhy don’t you make like a tree and get outta here? But not before you see that you have " + User.getCount() + " tasks in the list.");
                    break;
                case "deadline":
                    String[] deadlineSplit = inputSplit[1].split("/by");
                    Task deadline = new Deadline(deadlineSplit[0].trim(),deadlineSplit[1].trim());
                    System.out.println("Last night, Darth Vader came down from Planet Vulcan and told me that if you don't meet this deadline... he'd melt your brain! So, better get moving!\n");
                    System.out.println(deadline.toString());
                    user.addList(deadline);
                    System.out.println("\nIf you're gonna build a time machine into a car, why not do it with some style? And speaking of style, you now have " + User.getCount() + " tasks in the list.");
                    break;
                case "event":
                    String[] eventSplit = inputSplit[1].split("/from | /to");
                    Task event = new Event(eventSplit[0].trim(),eventSplit[1].trim(),eventSplit[2].trim());
                    System.out.println("The appropriate question is: ‘When the hell are they?’ Your event is now set in time!\n");
                    System.out.println(event.toString());
                    user.addList(event);
                    System.out.println("\nMarty, don't be such a square. Now you have " + User.getCount() + " tasks in the list.");
                    break;
                case "mark":
                    System.out.println("Task complete! If my calculations are correct, when this baby hits 88 miles per hour... you're gonna see some serious progress!\n");
                    list = user.getList();
                    Task markTask = list.get(parseInt(inputSplit[1]) - 1);
                    markTask.setStatus(true);
                    System.out.println(markTask.toString());
                    break;
                case "unmark":
                    System.out.println("Looks like we're going back to the future—task unmarked! Time to revisit this one.\n");
                    list = user.getList();
                    Task unmarkTask = list.get(parseInt(inputSplit[1]) - 1);
                    unmarkTask.setStatus(false);
                    System.out.println(unmarkTask.toString());
                    break;
                case "list":
                    list = user.getList();
                    int listCount = 1;
                    System.out.println("Here’s your list! Like Doc Brown’s flux capacitor, this will help keep everything in perfect sync!\n");
                    for (Task item: list) {
                        System.out.println(listCount + ". " + item.toString());
                        listCount++;
                    }
                    break;
                case "bye":
                    System.out.println(ending);
                    break;
                default:
                    System.out.println("Wait a minute, Doc. Are you telling me you said: " + input + "? Whoa, that's heavy!");
            }

            if (input.equals("bye")) {
                break;
            }

        }
    }
}
