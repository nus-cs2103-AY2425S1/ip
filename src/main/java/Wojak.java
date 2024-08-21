import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Wojak {

    public static void printList(ArrayList<Task> ls) {
        if (!ls.isEmpty()) {
            System.out.println("____________________________________________________________");
            for (int i = 0; i < ls.size(); i++) {
                System.out.println(ls.get(i).getTaskString(i+1));
            }
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("List is empty");
            System.out.println("____________________________________________________________");
        }

    }

    public static void main(String[] args) {

        ArrayList<Task> list = new ArrayList<>();

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Wojak\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();

        String markRegex = "mark (100|[1-9]|[1-9][0-9])";
        String unmarkRegex = "unmark (100|[1-9]|[1-9][0-9])";
        Pattern markPattern = Pattern.compile(markRegex);
        Pattern unmarkPattern = Pattern.compile(unmarkRegex);

        while (!(nextLine.equals("bye"))) {
            Matcher markMatcher = markPattern.matcher(nextLine);
            Matcher unmarkMatcher = unmarkPattern.matcher(nextLine);
            if (nextLine.equals("list")) {
                printList(list);
            }
            else if (markMatcher.matches()) {
                String numberString = markMatcher.group(1);
                int number = Integer.parseInt(numberString);
                try {
                    Task retrievedTask = list.get(number-1);
                    retrievedTask.mark();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(retrievedTask.getTaskString(number));
                    System.out.println("____________________________________________________________");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task doesn't exist");
                }
            }else if (unmarkMatcher.matches()) {
                String numberString = unmarkMatcher.group(1);
                int number = Integer.parseInt(numberString);
                try {
                    Task retrievedTask = list.get(number-1);
                    retrievedTask.unmark();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(retrievedTask.getTaskString(number));
                    System.out.println("____________________________________________________________");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task doesn't exist");
                }
            } else {
                Task newTask = new Task(nextLine);
                list.add(newTask);
                System.out.println("____________________________________________________________\n" +
                        "added: " + newTask.getDescription() + "\n" +
                        "____________________________________________________________\n");
            }

            nextLine = sc.nextLine();
        }


        System.out.println(
                " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
    }
}