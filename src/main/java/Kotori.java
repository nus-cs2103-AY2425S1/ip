import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Kotori {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        printGreeting();
        Scanner s = new Scanner(System.in);
        while (s.hasNext()){
            String input = s.nextLine();
            if (input.equals("bye")) {
                printExit();
                break;
            } else if (input.equals("list")) {
                printList(list);
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                Task task = list.get(index - 1);
                task.mark();
                printMessage(String.format("Nice Job, Job %s has been marked as done!\n    %s",index,task));
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                Task task = list.get(index - 1);
                task.unmark();
                printMessage(String.format("Alright, Job %s has been marked as not done!\n    %s",index,task));
            } else {
                list.add(new Task(input));
                printMessage("added:　" + input);
            }
        }

        s.close();

    }

    public static void printLine() {
        System.out.println("    ___________________________________________");
    }

    public static void printMessage(String input) {
        printLine();
        System.out.println("    " + input);
        printLine();
    }

    public static void printList(List<? extends Object> list) {
        printLine();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("    %s. %s",i + 1, list.get(i).toString()));
        }
        printLine();
    }

    public static void printGreeting() {
        printMessage("Hello! I'm Kotori.\n    What can I do for you?");
    }

    public static void printExit() {
        printMessage("Bye! Hope to see you again soon.");
    }
}


