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
                    try {
                        if (inputSplit[1].trim().isEmpty()) {
                            throw new DrBrownException("Great Scott! You can't add a to-do without a description!\n\nUse the format: todo {description}");
                        }
                        Task todo = new Todo(inputSplit[1].trim());
                        System.out.println("Doc, you don't just walk into a store and buy plutonium! But you sure can add this task to your list!\n");
                        System.out.println(todo.toString());
                        user.addList(todo);
                        System.out.println("\nWhy don’t you make like a tree and get outta here? But not before you see that you have " + user.getCount() + " tasks in the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Great Scott! You can't add a to-do without a description!\n\nUse the format: todo {description}");
                    } catch (DrBrownException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "deadline":
                    try {
                        if (inputSplit.length == 1) {
                            throw new DrBrownException("Great Scott! You can't add a deadline without a description and date!\nUse the format: deadline {description} /by {date}");
                        }
                        String[] deadlineSplit = inputSplit[1].split("/by");
                        if (deadlineSplit[0].isEmpty()) {
                            throw new DrBrownException("Hello? Hello? Anybody home? Looks like something's missing here!\nUse the format: deadline {description} /by {date}");
                        }
                        Task deadline = new Deadline(deadlineSplit[0].trim(), deadlineSplit[1].trim());
                        System.out.println("Last night, Darth Vader came down from Planet Vulcan and told me that if you don't meet this deadline... he'd melt your brain! So, better get moving!\n");
                        System.out.println(deadline.toString());
                        user.addList(deadline);
                        System.out.println("\nIf you're gonna build a time machine into a car, why not do it with some style? And speaking of style, you now have " + user.getCount() + " tasks in the list.");
                    } catch (DrBrownException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Looks like your Uncle Joey didn't make parole again... and you missed the date! Let's fix that deadline!\nUse the format: deadline {description} /by {date}");
                    }
                    break;

                case "event":
                    try {
                        if (inputSplit.length == 1) {
                            throw new DrBrownException("Great Scott! You can't add a event without a description and from and to date!\nUse the format: event {description} /from {date} /to {date}");
                        }
                        if (!input.contains("/from") || !input.contains("/to") || input.indexOf("/from") > input.indexOf("/to")) {
                            throw new DrBrownException("Looks like your Uncle Joey didn't make parole again... and you missed the date! Let's fix that event!\nUse the format: event {description} /from {date} /to {date}");
                        }
                        String[] eventSplit = inputSplit[1].split("/from | /to");
                        Task event = new Event(eventSplit[0].trim(), eventSplit[1].trim(), eventSplit[2].trim());
                        System.out.println("The appropriate question is: ‘When the hell are they?’ Your event is now set in time!\n");
                        System.out.println(event.toString());
                        user.addList(event);
                        System.out.println("\nMarty, don't be such a square. Now you have " + user.getCount() + " tasks in the list.");
                    } catch (DrBrownException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Looks like your Uncle Joey didn't make parole again... and you missed the date! Let's fix that event!\nUse the format: event {description} /from {date} /to {date}");
                    }
                    break;

                case "mark":
                    try {
                        if (inputSplit.length == 1) {
                            throw new DrBrownException("Great Scott! You can't complete a task without a count!\nUse the format: mark {count}");
                        }
                        list = user.getList();
                        Task markTask = list.get(parseInt(inputSplit[1]) - 1);
                        System.out.println("Task complete! If my calculations are correct, when this baby hits 88 miles per hour... you're gonna see some serious progress!\n");
                        markTask.setStatus(true);
                        System.out.println(markTask.toString());
                    } catch (DrBrownException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("That's not a number! Without the right input, we're never going to get this DeLorean off the ground!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You got the count wrong! That’s not how you calculate time travel – you're off by a few gigawatts!");
                    }
                    break;

                case "unmark":
                    try {
                        if (inputSplit.length == 1) {
                            throw new DrBrownException("Great Scott! You can't go back in time without a count!\nUse the format: unmark {count}");
                        }
                        list = user.getList();
                        Task unmarkTask = list.get(parseInt(inputSplit[1]) - 1);
                        System.out.println("Looks like we're going back to the future—task unmarked! Time to revisit this one.\n");
                        unmarkTask.setStatus(false);
                        System.out.println(unmarkTask.toString());
                    } catch (DrBrownException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("That's not a number! Without the right input, we're never going to get this DeLorean off the ground!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You got the count wrong! That’s not how you calculate time travel – you're off by a few gigawatts!");
                    }
                    break;

                case "list":
                    try {
                        if (inputSplit.length != 1) {
                            throw new DrBrownException("Whoa, hold on! You've written more letters than necessary! It's like trying to fit a flux capacitor into a toaster – it just doesn't belong!");
                        }
                        if (user.getCount() == 0) {
                            throw new DrBrownException("Wait a minute, Doc! There's nothing here! We can't go anywhere until you add something to the list!");
                        }
                        list = user.getList();
                        int listCount = 1;
                        System.out.println("Here’s your list! Like Doc Brown’s flux capacitor, this will help keep everything in perfect sync!\n");
                        for (Task item: list) {
                            System.out.println(listCount + ". " + item.toString());
                            listCount++;
                        }
                    } catch (DrBrownException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "bye":
                    try {
                        if (inputSplit.length != 1) {
                            throw new DrBrownException("Whoa, hold on! You've written more letters than necessary! It's like trying to fit a flux capacitor into a toaster – it just doesn't belong!");
                        }
                        System.out.println(ending);
                        scanner.close();
                    } catch (DrBrownException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "delete":
                    try {
                        if (inputSplit.length == 1) {
                            throw new DrBrownException("You can't erase something from history without a count!\nUse the format: delete {count}");
                        }
                        list = user.getList();
                        Task deleteTask = list.get(parseInt(inputSplit[1]) - 1);
                        System.out.println("That task's history has been erased! Just like Marty’s fading photo—it's gone, like it never existed!\n");
                        System.out.println(deleteTask.toString());
                        user.removeItem(parseInt(inputSplit[1]) - 1);
                        System.out.println("\nYour total count is now " + user.getCount() + "! Like the time circuits, everything's in sync – keep those tasks ticking along!\"");
                    } catch (DrBrownException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("That's not a number! Without the right input, we're never going to get this DeLorean off the ground!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You got the count wrong! That’s not how you calculate time travel – you're off by a few gigawatts!");
                    }
                    break;

                default:
                    System.out.println("I’m from the future, and even I don’t know what that means.");

            }

            if (input.equals("bye")) {
                break;
            }

        }

    }

}
