import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pixy {

    private List<Task> list = new ArrayList<>(); // Array List to store the tasks
    Scanner sc = new Scanner(System.in);
    private String inputTask() {    // method to input tasks
        return sc.nextLine();
    }
    private void addToList(Task task) { // add to list method
        list.add(task);
        System.out.println("Got it. I've added this task:\n " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list." +
                "\n____________________________________________________________\n");
    }
    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }
    private boolean isValidTaskNumber(int index) {  // method to check whether the task number is valid
        return index > 0 && index <= list.size();
    }
    private void markTask(int taskNumber, boolean done) {   // method to mark or unmark the specified task
        if (isValidTaskNumber(taskNumber)) {
            Task task = list.get(taskNumber - 1);
            task.markAsDone(done);
            System.out.println( task.toString() +
                    "\n____________________________________________________________\n");
        } else {
            System.out.println("Invalid task number entered!Input again.");
        }
    }
    private void deleteTask(int taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            Task task = list.get(taskNumber - 1);
            list.remove(task);
            System.out.println( task.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list." +
                    "\n____________________________________________________________\n");
        } else {
            System.out.println("Invalid task number entered!Input again.");
        }
    }
    public void run() { // method to execute all the commands inputted by user until bye encountered
        Scanner sc = new Scanner(System.in);
        while (true) {

            try {
                String command = inputTask();
                System.out.println("____________________________________________________________\n");
                if (command.equalsIgnoreCase("list")) {
                    printList();
                    System.out.println("____________________________________________________________\n");
                } else if (command.equalsIgnoreCase("Bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n" +
                            "____________________________________________________________\n");
                    break;
                } else if (command.startsWith("mark")) {
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    System.out.println("OK, I have marked this task as done:");
                    markTask(taskNumber, true);
                } else if (command.startsWith("unmark")) {
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    System.out.println("OK, I have marked this task as not done yet:");
                    markTask(taskNumber, false);
                } else if (command.startsWith("delete")){
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    System.out.println("Noted. I've removed this task:");
                    deleteTask(taskNumber);
                }else {
                    if (command.startsWith("todo ")) {
                        String description = command.substring(5);
                        if (description.isEmpty()) {
                            throw new PixyExceptions("OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task todo = new ToDos(description);
                        addToList(todo);
                    } else if (command.startsWith("deadline ")) {
                        String[] parts = command.substring(9).split(" /by");
                        if (parts.length != 2) {
                            throw new PixyExceptions("OOPS!!! The description of a deadline not in correct format.");
                        }
                        Task deadline = new Deadlines(parts[0], parts[1]);
                        addToList(deadline);
                    } else if (command.startsWith("event ")) {
                        String[] parts = command.substring(6).split(" /from | /to");
                        if (parts.length != 3) {
                            throw new PixyExceptions("OOPS!!! The description of a event is not in correct format.");
                        }
                        Task event = new Event(parts[0], parts[1], parts[2]);
                        addToList(event);
                    }
                }
            } catch (PixyExceptions e) {
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________\n");
            } catch (NumberFormatException e) {
                System.out.println("OOPS!!! Please provide a valid number for the task.");
                System.out.println("____________________________________________________________\n");
            }
        }

    }
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Pixy.\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        Pixy pixy = new Pixy();
        pixy.run();
    }
}
