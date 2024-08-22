import java.util.Scanner;

public class Fred {
    static String line = "____________________________________________________________";
    static String name = "Fred";
    static String[] taskList = new String[100];
    public static void main(String[] args) {
        greet();
        //echo();
        addToTaskList();
        sayFarewell();
        exit();
    }

    private static void greet() {
        String greeting = "Hello! I'm " + name + "\n" +
                "What can I do for you?";
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
    }

    private static void sayFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(farewell);
        System.out.println(line);
    }

    private static void exit(){
        System.exit(0);
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
        sayFarewell();
        exit();
    }

    private static void addToTaskList() {
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")){
                break;
            } else if (input.equals("list")){
                printTaskList();
                continue;
            }
            if (index < taskList.length) {
                taskList[index] = input;
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
                index++;
            } else {
                System.out.println(line);
                System.out.println("Unable to add to task list");
                System.out.println(line);
            }
        }
        sayFarewell();
        exit();
    }

    private static void printTaskList() {
        int index = 1;
        System.out.println(line);
        while (taskList[index - 1] != null) {
            System.out.println(index + ". " + taskList[index - 1]);
            index++;
            if (index > taskList.length) {
                break;
            }
        }
        System.out.println(line);
    }
}
