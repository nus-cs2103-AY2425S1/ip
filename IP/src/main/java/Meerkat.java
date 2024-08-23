import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Meerkat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String lines = "____________________________________________________________";
        String greeting = """
                Hello! I'm a meerkat
                What can I do for you?
                """;
        String bye = """
                Goodnight, sleep tight, Hope I don't ever see you again!
                """;
        System.out.println(lines + "\n" + greeting + lines);
        List<Task> listOfTasks = new ArrayList<>();

        while (true) {
            String taskName = sc.nextLine();
            // splits string based on space
            String[] strArray = taskName.split(" ", 2);

            // create new specific task with appropriate params
            if (strArray[0].equalsIgnoreCase("todo")) {
                try {
                    String name = strArray[1];
                    Task thisTask = new Todo(name);
                    listOfTasks.add(thisTask);
                    System.out.println(lines + "\nGot it. I've added this task:\n  " + thisTask + "\nNow you have " + listOfTasks.size() + " tasks in the list\n" + lines);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(lines + "\nbruh. i need more info to create your todo task.\n" + lines);
                }

            // create new deadline task
            } else if (strArray[0].equalsIgnoreCase("deadline")) {
                try {
                    String[] todoStringArray = taskName.split(" /by ");
                    String duedate = todoStringArray[1];
                    String name = todoStringArray[0].split(" ", 2)[1];
                    Task thisTask = new Deadline(name, duedate);
                    listOfTasks.add(thisTask);
                    System.out.println(lines + "\nGot it. I've added this task:\n  " + thisTask + "\nNow you have " + listOfTasks.size() + " tasks in the list\n" + lines);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(lines + "\nbruh. i need more info to create your deadline task.\n" + lines);
                }


            // create new event task
            } else if (strArray[0].equalsIgnoreCase("event")) {
                try {
                    String[] eventStringArray = taskName.split(" /from ");
                    String[] duration = eventStringArray[1].split(" /to ");
                    String start = duration[0];
                    String end = duration[1];
                    String name = eventStringArray[0].split(" ", 2)[1];
                    Task thisTask = new Event(name, start, end);
                    listOfTasks.add(thisTask);
                    System.out.println(lines + "\nGot it. I've added this task:\n  " + thisTask + "\nNow you have " + listOfTasks.size() + " tasks in the list\n" + lines);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(lines + "\nbruh. i need more info to create your event task.\n" + lines);
                }
            }

            // to end program
            else if (taskName.equalsIgnoreCase("bye")) {
                System.out.println(lines + "\n" + bye + lines);
                break;

            // to display list of items
            } else if (taskName.equalsIgnoreCase("list")) {
                System.out.println(lines);
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < listOfTasks.size() + 1; i++) {
                    System.out.println(i + "." + listOfTasks.get(i-1).toString());
                }
                System.out.println(lines);

            // mark item as done
            } else if (strArray.length == 2 && strArray[0].equals("mark")) {
                try {
                    int num = Integer.parseInt(strArray[1]);
                    if (num > 0 && num <= listOfTasks.size()) {
                        listOfTasks.get(num - 1).markAsCompleted();
                        System.out.println(lines + "\nNice! I've marked this task as done:\n" + listOfTasks.get(num-1) + "\n" + lines);
                    // task number is not within range
                    } else {
                        System.out.println(lines + "\nThis task does not exist! Unable to unmark.\n" + lines);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(lines + "\nThis task does not exist! Unable to mark.\n" + lines);
                }

            // mark item as not done
            } else if (strArray.length == 2 && strArray[0].equals("unmark")) {
                try {
                    int num = Integer.parseInt(strArray[1]);
                    if (num > 0 && num <= listOfTasks.size()) {
                        listOfTasks.get(num - 1).markAsIncomplete();
                        System.out.println(lines + "\nOK, I've marked this task as not done yet:\n" + listOfTasks.get(num - 1) + "\n" + lines);
                        // task number is not within range
                    } else {
                        System.out.println(lines + "\nThis task does not exist! Unable to unmark.\n" + lines);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(lines + "\nThis task does not exist! Unable to unmark.\n" + lines);
                }
            } else if (strArray.length == 2 && strArray[0].equals("delete")) {
                try {
                    int num = Integer.parseInt(strArray[1]);
                    if (num > 0 && num <= listOfTasks.size()) {
                        System.out.println(lines + "\nroger that sir, I've removed this task:\n" + listOfTasks.get(num - 1) + "\n" + lines);
                        listOfTasks.remove(num - 1);
                        // task number is not within range
                    } else {
                        System.out.println(lines + "\nThis task does not exist! Unable to delete.\n" + lines);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(lines + "\nThis task does not exist! Unable to delete.\n" + lines);
                }
            } else {
                System.out.println(lines + "\ni have nooo idea what you are sayin\n" + lines);
            }
        }
        System.exit(0);
    }
}
