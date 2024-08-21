import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TheBotFather {

    public static void main(String[] args) {

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        printLine();
        printGreeting();
        printHorse();
        printLine();

        while (true) {
            String input = sc.nextLine();
            printLine();
            if (Objects.equals(input, "bye")) break;
            String[] taskDec = input.split(" ", 2);
            Task task;
            int index;

            switch (taskDec[0]) {
                case "list":
                    print("You never even called me Don once.");
                    printList(taskList);
                    printLine();
                    break;

                case "todo":
                    task = new Todo(taskDec[1]);
                    taskList.add(task);
                    print("Leave the gun, take the cannoli.");
                    print("    " + task.toString());
                    print(Task.getCOUNT());
                    printLine();
                    break;

                case "deadline":
                    String[] deadlineInfo = taskDec[1].split(" /by ");
                    task = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                    taskList.add(task);
                    print("Look how they massacred my boy.");
                    print("    " + task.toString());
                    print(Task.getCOUNT());
                    printLine();
                    break;

                case "event":
                    String[] eventInfo = taskDec[1].split(" /from | /to ");
                    if (eventInfo.length != 3) break;
                    task = new Event(eventInfo[0], eventInfo[1], eventInfo[2]);
                    taskList.add(task);
                    print("That's my family, Kay, that's not me.");
                    print("    " + task.toString());
                    print(Task.getCOUNT());
                    printLine();
                    break;

                case "mark":
                    index = Integer.parseInt(String.valueOf(taskDec[1])) - 1;
                    task = taskList.get(index);
                    task.markAsDone();
                    print("It will be done");
                    print("    " + task.toString());
                    printLine();
                    break;

                case "unmark":
                    index = Integer.parseInt(String.valueOf(taskDec[1])) - 1;
                    task = taskList.get(index);
                    task.unmark();
                    print("A man who doesn't spend time with his family can never be a real man.");
                    print("    " + task.toString());
                    printLine();
                    break;

                default:
                    print("I don't know this command: " + taskDec[0]);
                    break;
            }
        }

        printGoodBye();
        sc.close();
        printLine();

    }


    public static void printList(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            print(i + 1 + ". " + taskList.get(i));
        }
    }

    public static void print(String str) {
        System.out.println("    " + str);
    }

    public static void printGreeting() {
        System.out.println("    Hello! I'm The BotFather");
        System.out.println("    I’m gonna make you an offer you can’t refuse.");
    }

    public static void printGoodBye() {
        System.out.println("    What are you worried about, if I wanted to kill you, you'd be dead already.");
    }

    public static void printLine() {
        System.out.println("    —————————————————————————————————————————————————————————————————");
    }

    public static void printHorse() {
        System.out.println("\t                                 |\\    /|");
        System.out.println("\t                              ___| \\,,/_/");
        System.out.println("\t                           ---__/ \\/    \\");
        System.out.println("\t                          __--/     (D)  \\");
        System.out.println("\t                          _ -/    (_      \\");
        System.out.println("\t                         // /       \\_ /  -\\");
        System.out.println("\t   __-------_____--___--/           / \\_ O o)");
        System.out.println("\t  /                                 /   \\__/");
        System.out.println("\t /                                 /");
        System.out.println("\t||          )                   \\_/\\");
        System.out.println("\t||         /              _      /  |");
        System.out.println("\t| |      /--______      ___\\    /\\  :");
        System.out.println("\t| /   __-  - _/   ------    |  |   \\ \\");
        System.out.println("\t |   -  -   /                | |     \\ )");
        System.out.println("\t |  |   -  |                 | )     | |");
        System.out.println("\t  | |    | |                 | |    | |");
        System.out.println("\t  | |    < |                 | |   |_/");
        System.out.println("\t  < |    /__\\                <  \\");
        System.out.println("\t  /__\\                       /___\\");
    }
}
