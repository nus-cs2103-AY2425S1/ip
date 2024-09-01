import java.util.ArrayList;
import java.util.Scanner;

public class FutureYou {

    static ArrayList<Task> taskList = new ArrayList<Task>();


    public static void addTask(String taskName) {
        Task newTask = new Task(taskName);
        taskList.add(newTask);
        System.out.println("Added this task: ");
        System.out.println(newTask.print());
        System.out.println(taskList.size() + " tasks in the list");
    }

    public static void addDeadline(String taskName, String deadline) {
        Deadline newDeadline = new Deadline(taskName, deadline);
        taskList.add(newDeadline);
        System.out.println("Added this task: ");
        System.out.println(newDeadline.print());
        System.out.println(taskList.size() + " tasks in the list");
    }

    public static void addEvent(String taskName, String startDT, String endDT) {
        Events newEvent = new Events(taskName, startDT, endDT);
        taskList.add(newEvent);
        System.out.println("Added this task: ");
        System.out.println(newEvent.print());
        System.out.println(taskList.size() + " tasks in the list");
    }

    public static void markTask(int n) {
        taskList.get(n).markTask();
        System.out.println("Marked as Done:");
        System.out.println(taskList.get(n).print());
    }

    public static void deleteTask(int n) {
        Task removed = taskList.remove(n);
        System.out.println("Task Deleted:");
        System.out.println(removed.print());
        System.out.println(taskList.size() + " tasks left in the list");
    }

    public static void main(String[] args) {

        boolean flag = true;
        while (flag) {
            @SuppressWarnings("resource")
            Scanner scanInput = new Scanner(System.in); // Create a Scanner object
            System.out.print("Command: ");

            String input = scanInput.nextLine(); // Read user input

            if (input.toLowerCase().trim().equals("bye")) {
                // bye();
                scanInput.close();
                break;
            } else if (input.toLowerCase().trim().contains("todo")) {
                try {
                    String taskName = input.substring(5);

                    if (taskName.trim().length() <= 0) {

                        System.out.println("Please enter a valid task name!");
                    } else {
                        addTask(taskName);
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid task name!");
                }

            } else if (input.toLowerCase().trim().contains("deadline")) {
                try {
                    String[] inputs = input.split("/");

                    String taskName = inputs[0].substring(9);
                    String date = inputs[1].substring(3);
                    if (taskName.trim().length() <= 0) {
                        System.out.println("Please enter a valid task name!");
                    } else if (date.trim().length() <= 0) {
                        System.out.println("Please enter a valid deadline!");
                    } else {
                        addDeadline(taskName, date);
                    }
                } catch (Exception e) {
                    System.out.println(
                            "Please enter in a valid deadline command format! (deadname <task name> /by <Date>)");
                }

            } else if (input.toLowerCase().trim().contains("event")) {
                try {
                    String[] inputs = input.split("/");

                    String taskName = inputs[0].substring(6);
                    String startDT = inputs[1].substring(5);
                    String endDT = inputs[2].substring(3);

                    if (taskName.trim().length() <= 0) {
                        System.out.println("Please enter a valid task name!");
                    } else if (startDT.trim().length() <= 0) {
                        System.out.println("Please enter a valid start datetime!");
                    } else if (endDT.trim().length() <= 0) {
                        System.out.println("Please enter a valid end datetime!");
                    } else {
                        addEvent(taskName, startDT, endDT);
                    }
                } catch (Exception e) {
                    System.out.println(
                            "Please enter in a valid event command format! (event <task name> /from <DateTime> /to <DateTime>)");
                }

            } else if (input.toLowerCase().trim().equals("list")) {
                // printList();
            } else if (input.toLowerCase().trim().contains("mark")) {
                try {
                    int num = Integer.parseInt(input.substring(5));
                    markTask(num - 1);
                } catch (Exception e) {
                    System.out.println(
                            "Please enter in a valid mark task format! (mark <task number>)");
                }
            } else if (input.toLowerCase().trim().contains("delete")) {
                try {
                    int num = Integer.parseInt(input.substring(7));
                    deleteTask(num - 1);
                } catch (Exception e) {
                    System.out.println(
                            "Please enter in a valid delete command format! (delete <task number>)");
                }

            } else { // If user inputs an unrecognized command
                System.err.println("Please enter a valid command!");
            }
        }
    }
}
