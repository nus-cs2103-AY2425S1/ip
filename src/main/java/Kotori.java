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
            if (input.equals("bye ")) {
                printExit();
                break;
            } else if (input.equals("list")) {
                printList(list);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                if (list.size() < index || index <= 0 ) {
                   printMessage("Sorry~ can not mark that task because it does not exist");
                } else {
                    Task task = list.get(index - 1);
                    try {
                        task.mark();
                        printMessage(String.format("Nice Job, Job %s has been marked as done!\n    %s",index,task));
                    } catch (IncorrectStateException e) {
                        printMessage(e.getMessage());
                    }
                }

            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                if (list.size() < index || index <= 0 ) {
                    printMessage("Sorry~ can not unmark that task because it does not exist");
                } else {
                    Task task = list.get(index - 1);
                    try {
                        task.unmark();
                        printMessage(String.format("Alright, Job %s has been marked as not done!\n    %s",index,task));
                    } catch (IncorrectStateException e) {
                        printMessage(e.getMessage());
                    }
                }
            } else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                if (list.size() < index || index <= 0) {
                    printMessage("Sorry~ Can not delete this task as such task does not exist.");
                } else {
                    Task task = list.remove(index - 1);
                    printMessages(new String[]{"OK~. I've deleted this task:",task.toString(),
                            String.format("Now you have %s tasks in the list",list.size())});
                }

            } else {
                try {
                    Task task = Task.of(input);
                    list.add(task);
                    printMessages(new String[]{"Got it. I've added this task:",task.toString(),
                    String.format("Now you have %s tasks in the list",list.size())});
                } catch (MissingInformationException e) {
                    printMessage(e.getMessage());
                } catch (InvalidInputException e) {
                    printMessage(e.getMessage());
                }
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

    public static void printMessages(String[] inputs) {
        printLine();
        for (String s : inputs) {
            System.out.println("    " + s);
        }
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


