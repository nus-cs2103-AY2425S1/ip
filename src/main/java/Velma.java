import java.util.Scanner;
import java.util.ArrayList;

public class Velma {

    public static void printLine() {
        for (int i = 0; i < 50; i ++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        int count = 1;
        ArrayList<Task> list = new ArrayList<>(100);
        boolean end = false;
        printLine();
        System.out.println("Hello! I am Velma!" );
        System.out.println("What can I do for you?");
        Scanner req = new Scanner(System.in);
        printLine();

        while(!end) {
            String request = req.nextLine();
            if (request.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (request.equals("list")) {
                printLine();
                System.out.println("Here are the tasks in your list:");
                for (Task task : list) {
                    System.out.println(count + ". " + "[" + task.getStatusIcon() + "] " + task.description);
                    count++;
                }
                printLine();
                count = 1;
            } else if (request.contains("mark")) {
                String[] parts = request.split(" ");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                list.get(taskNumber).changeIsDone();
                printLine();
                System.out.println("Nice! I have marked this task as done:");
                System.out.println("  " + "[" + list.get(taskNumber).getStatusIcon() + "] " + list.get(taskNumber).description);
            } else if (request.contains("unmark")) {
                String[] parts = request.split(" ");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                list.get(taskNumber).changeIsDone();
                printLine();
                System.out.println("OK! I have marked this task as not done yet:");
                System.out.println("  " + "[" + list.get(taskNumber).getStatusIcon() + "] " + list.get(taskNumber).description);
            }
            else {
                list.add(new Task(request));
                printLine();
                System.out.println("added: " + request);
                printLine();
            }
        }
    }


}
