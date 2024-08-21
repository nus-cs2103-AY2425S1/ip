import java.util.ArrayList;
import java.util.Scanner;

public class MrTracker {

    public static void printLine() {
        int length = 75;
        for (int i = 0; i < length; i++) {
            System.out.print('-');
            if (i == length - 1) {
                System.out.println();
            }
        }
    }

    public static void printTaskList(ArrayList<String> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + ". " + taskList.get(i));
        }
    }

    public static void addTask (ArrayList<String> taskList, String task) {
        taskList.add(task);
        System.out.println("added: " + task);
    }

    public static void main(String[] args) {
        String name = "Mr Tracker";
        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<String>();
        MrTracker.printLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you? \n");
        MrTracker.printLine();
        while (true) {
            String input = sc.nextLine();
            MrTracker.printLine();
            String lowerInput = input.toLowerCase();
            if (lowerInput.equals("bye")) {
                sc.close();
                break;
            } else if (lowerInput.equals("list")) {
                MrTracker.printTaskList(taskList);
            } else {
                MrTracker.addTask(taskList, input);
            }
            MrTracker.printLine();
        }
        System.out.println("Bye. Hope to see you again soon! \n");
        MrTracker.printLine();
    }
}
