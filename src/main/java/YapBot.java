import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class YapBot {
    private static final String PREFIXLINE = "\n-------------------------------------------";
    private static final String POSTFIXLINE = "-------------------------------------------\n";
    private static ArrayList<Task> storedTasks = new ArrayList<>();

    public static void list() {
        Iterator<Task> iterateStored = storedTasks.iterator();
        System.out.println(PREFIXLINE);
        int index = 1;
        while (iterateStored.hasNext()) {
            System.out.println(index + "." + iterateStored.next());
            index++;
        }
        System.out.println(POSTFIXLINE);
    }

    public static void markOrUnmark(String input) {
        if (input.contains("unmark")) {
            int index = Integer.parseInt(input.substring(7)) - 1;

            if (index == -1 || index >= storedTasks.size()) {
                System.out.println(PREFIXLINE + "\nNope, that task does not exist, continue yapping\n" + POSTFIXLINE);
                return;
            }

            Task task = storedTasks.get(index);
            task.changeDone(false);
            System.out.println(PREFIXLINE + "\nAlright, you have not done this task then: \n" + task + "\n" + POSTFIXLINE);
        } else {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index == -1 || index >= storedTasks.size()) {
                System.out.println(PREFIXLINE + "\nNope, that task does not exist, continue yapping\n" + POSTFIXLINE);
                return;
            }
            Task task = storedTasks.get(index);
            task.changeDone(true);
            System.out.println(PREFIXLINE + "\nGood Job, this task is done: \n" + task + "\n" + POSTFIXLINE);
        }
    }

    public static void main(String[] args) {

        System.out.println(PREFIXLINE + "\nHello, I am YapBot. \nHow can I help?\n" + POSTFIXLINE);
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list();
                input = in.nextLine();
            } else if (input.contains("mark")) {
                markOrUnmark(input);
                input = in.nextLine();
            }  else {
                Task taskToAdd = new Task(input);
                storedTasks.add(taskToAdd);
                System.out.println(PREFIXLINE + "\nAdded: " + input + "\n" + POSTFIXLINE);
                input = in.nextLine();
            }
        }

        in.close();
        System.out.println(PREFIXLINE + "\nAlright, enough yapping for one day.\n" + POSTFIXLINE);
    }
}
