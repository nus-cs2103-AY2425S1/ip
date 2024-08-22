import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pixy {

    private List<Task> list = new ArrayList<>();
    private int numOfItems;
    private String inputTask() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        return sc.next();
    }
    private void addToList(String task) {
        list.add(new Task(task));
        System.out.println("added: " + task +
                "\n____________________________________________________________\n");
    }
    private void printList() {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task == null) {
                break;
            }
            System.out.println((i + 1) + ". " + "[" + task.getStatusIcon() + "] " + task.getDescription());
        }
    }
    private boolean isValidTaskNumber(int index) {
        return index > 0 && index <= list.size();
    }
    private void markTask(int taskNumber, boolean done) {
        if (isValidTaskNumber(taskNumber)) {
            Task task = list.get(taskNumber - 1);
            task.markAsDone(done);
            System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription() +
                    "\n____________________________________________________________\n");
        } else {
            System.out.println("Invalid task number entered!Input again.");
        }
    }
    public void run() {
        while (true) {
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


            } else if (command.startsWith("unmark")){
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                System.out.println("OK, I have marked this task as not done yet:");
                markTask(taskNumber, false);
            } else {
                addToList(command);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Pixy. \n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        Pixy pixy = new Pixy();
        pixy.run();
    }
}
