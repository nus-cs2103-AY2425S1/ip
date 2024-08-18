import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String greeting = "   *        *        *        __o    *       *\n"
                + "*      *       *        *    /_| _     *\n"
                + "  FF  *    FF      *        O'_)/ \\  *    *\n"
                + "  o')____  o')____    __*   V   \\  ) __  *\n"
                + "   \\ ___ )--\\ ___ )--( (    (___|__)/ /*     *\n"
                + " *  |   |    |   |  * \\ \\____| |___/ /  *\n"
                + "    |*  |    |   |     \\____________/       *\n";
        String festiveMessage =
                "Merry★* 。 • ˚ ˚ ˛ ˚ ˛ •\n" +
                        "•。★Christmas★ 。* 。\n" +
                        "° 。 ° ˛˚˛ * Π__。˚\n" +
                        "˚ ˛ •˛•˚ */__/~＼。˚ ˚ ˛\n" +
                        "˚ ˛ •˛• ˚ ｜ 田田 ｜門｜ ˚\n" +
                        "And a happy new year!";
        System.out.println(greeting + "Hello, I am Rudolf, Santa's trusty red-nose reindeer");
        System.out.println("Christmas is nearing, and I am here to help you with your christmas preparations.");
        System.out.println("Here's how you can chat with me:");
        System.out.println("1. Add a ToDo: todo <description>");
        System.out.println("2. Add a Deadline: deadline <description> /by <date/time>");
        System.out.println("3. Add an Event: event <description> /from <start date/time> /to <end date/time>");
        System.out.println("4. List all tasks: list");
        System.out.println("5. Mark a task as done: mark <task number>");
        System.out.println("6. Unmark a task: unmark <task number>");
        System.out.println("7. Delete a task: delete <task number>");
        System.out.println("8. Exit: bye");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input = "";
        List<Task> tasks = new ArrayList<>();

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (input.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("Ho Ho Ho! No tasks in your list yet. Add some tasks to get started.");
                } else {
                    System.out.println("Ho Ho Ho! Here are the tasks in your Christmas list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
            } else if (input.startsWith("mark")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        System.out.println("Sleigh! I've marked this task as done:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        System.out.println("Oops! That task number doesn't exist. Please try again.");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Sorry, I don't understand. Did you mean: mark <task number>");
                }
            } else if (input.startsWith("unmark")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsNotDone();
                        System.out.println("Alright-o, I've marked this task as not done yet:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        System.out.println("Oops! That task number doesn't exist. Please try again.");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Sorry, I don't understand. Did you mean: unmark <task number>");
                }
            } else if (input.startsWith("todo")) {
                if (input.length() <= 5) {
                    System.out.println("Oops! The description of a todo cannot be empty.");
                } else {
                    String description = input.substring(5).trim();
                    tasks.add(new ToDo(description));
                    if (description.isEmpty()) {
                        System.out.println("Oops! The description of a todo cannot be empty.");
                    } else {
                        System.out.println("Gotcha. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list. Jingle bells!");
                    }
                }
            } else if (input.startsWith("deadline")) {
                String[] substrings = input.split(" /by ");
                if (substrings.length == 2) {
                    if (substrings[0].length() <= 9) {
                        System.out.println("Oops! The description of a todo cannot be empty.");
                    } else {
                        String description = substrings[0].substring(9).trim();
                        String by = substrings[1].trim();
                        if (description.isEmpty()) {
                            System.out.println("Oops! The description of a deadline cannot be empty.");
                        } else {
                            tasks.add(new Deadline(description, by));
                            System.out.println("Gotcha. I've added this task:");
                            System.out.println("  " + tasks.get(tasks.size() - 1));
                            System.out.println("Now you have " + tasks.size() + " tasks in the list. Let it snow!");
                        }
                    }
                } else {
                    System.out.println("Sorry, I don't understand. Did you mean: deadline <description> /by <date/time>");
                }
            } else if (input.startsWith("event")) {
                String[] substrings = input.split(" /from ");
                if (substrings.length == 2) {
                    if (substrings[0].length() <= 6) {
                        System.out.println("Oops! The description of a todo cannot be empty.");
                    } else {
                        String description = substrings[0].substring(6).trim();
                        String[] fromTo = substrings[1].split(" /to ");
                        if (fromTo.length == 2) {
                            String from = fromTo[0].trim();
                            String to = fromTo[1].trim();
                            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                                System.out.println("Oops! The description, start time, or end time of an event cannot be empty.");
                            } else {
                                tasks.add(new Event(description, from, to));
                                System.out.println("Gotcha. I've added this task:");
                                System.out.println("  " + tasks.get(tasks.size() - 1));
                                System.out.println("Now you have " + tasks.size() + " tasks in the list. Feliz Navidad!");
                            }

                        } else {
                            System.out.println("Sorry, I don't understand. Did you mean: event <description> /from <start date/time> /to <end date/time>");
                        }
                    }
                } else {
                    System.out.println("Sorry, I don't understand. Did you mean: event <description> /from <start date/time> /to <end date/time>");
                }
            } else if (input.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        Task removedTask = tasks.remove(index);
                        System.out.println("Aww okay. I've removed this task:");
                        System.out.println("  " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println("Oops! That task number doesn't exist. Please try again.");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Sorry, I don't understand. Did you mean: delete <task number>");
                }
            } else if (input.equals("bye")) {
                System.out.println("Bye~ Hope to see you again! \n" + festiveMessage);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("____________________________________________________________");
        }
    }
}
