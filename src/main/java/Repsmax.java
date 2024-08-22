import java.util.Scanner;

public class Repsmax {
    public static void main(String[] args) {
        //initialise scanner
        Scanner scanner = new Scanner(System.in);
        //initialise string array
        Task[] tasks = new Task[100];
        int taskcount = 0;
        //greet the user
        String greeting = "  ____________________________________________________________\n" +
                "   Hello! I'm Repsmax\n" +
                "   What can I do for you?\n" +
                "  ____________________________________________________________\n";
        String goodbye = "  ____________________________________________________________\n" +
                "   Bye. Hope to see you again soon!\n" +
                "  ____________________________________________________________\n";
        System.out.println(greeting);
        while(true) {
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" " ,2);
            String command = splitInput[0];
            if (userInput.equals("bye")) {
                System.out.println(goodbye);
                break;
            }
            switch (command) {
                case "list":
                    System.out.println("  ____________________________________________________________\n");
                    System.out.println("  here are the task in your list:\n");
                    for (int i = 0; i < taskcount; i++) {
                        System.out.println("   " + (i + 1) + "." + tasks[i]);
                    }
                    System.out.println("  ____________________________________________________________\n");
                    break;

                case "mark":
                    int markIndex = Integer.parseInt(splitInput[1]) - 1;
                    System.out.println("  ____________________________________________________________\n");
                    System.out.println("  Nice! I've marked this task as done:\n");
                    tasks[markIndex].setDone();
                    System.out.println("  " + tasks[markIndex]);
                    System.out.println("  ____________________________________________________________\n");
                    break;

                case "unmark":
                    int unmarkIndex = Integer.parseInt(splitInput[1]) - 1;
                    System.out.println("  ____________________________________________________________\n");
                    System.out.println("  OK, I've marked this task as not done yet:\n");
                    tasks[unmarkIndex].setUndone();
                    System.out.println("  " + tasks[unmarkIndex]);
                    System.out.println("  ____________________________________________________________\n");
                    break;

                default:
                    tasks[taskcount] = new Task(userInput);
                    taskcount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" added: " + userInput);
                    System.out.println("____________________________________________________________");
                    break;
            }
        }
    }
}