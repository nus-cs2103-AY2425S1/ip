import java.util.ArrayList;
import java.util.Scanner;

public class Joe {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("\t" + "Hello! I'm Joe");
        System.out.println("\t" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String EXIT_WORD = "bye";
        String LIST_TASKS = "list";
        String MARK_TASK = "mark";
        while (true) {
            System.out.println("\t____________________________________________________________");
            String userInput = scanner.nextLine();
            if (userInput.equals(EXIT_WORD)) {
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                return;
            }
            if (userInput.equals(LIST_TASKS)) {
                if (tasks.isEmpty()) System.out.println("\t\uD83C\uDF89 No tasks yet! Looks like you've got a clean slate. Time to add some tasks and conquer the day! \uD83D\uDE80");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    String msg = String.format("%d. %s", i + 1, task.formatTask());
                    System.out.println("\t" + msg);
                }
                continue;
            }
            String[] inputArr = userInput.split(" ");
            if (inputArr[0].contains(MARK_TASK)) {
                // code for marking and unmarking tasks
                int idx = Integer.parseInt(inputArr[1]); // gets the task index to mark or unmark
                if (idx > tasks.size() || idx < 1) { // check that task index is valid
                    System.out.println("\t" + "Please input a valid task index");
                    continue;
                }
                Task task = tasks.get(idx - 1);
                String msg;
                if (inputArr[0].startsWith(MARK_TASK)) { // is marking
                    task.setIsDone(true);
                    msg = "\tNice! I've marked this task as done: \n\t  " + task.formatTask();
                } else {
                    task.setIsDone(false);
                    msg = "\tOK, I've marked this task as not done yet: \n\t  " + task.formatTask();
                }
                System.out.println(msg);
            }
            else {
                tasks.add(new Task(userInput));
                System.out.println("\t" + "added: " + userInput);
            }
        }
    }
}
