import java.util.Scanner;

public class Donna {
    private static Task[] tasks = new Task[100];
    private static int taskNum = 0;

    static private void printDashedLine() {
        System.out.println("____________________________________________________________");
    }

    private static int addTask(String desc) {
        tasks[taskNum] = new Task(desc);
        taskNum++;
        printDashedLine();
        System.out.println("added: " + desc);
        printDashedLine();
        return taskNum;
    }

    static private void printDonnaLogo() {
        System.out.println(" ____      ");
        System.out.println("|  _ \\  ___  _ __  _ __   __ _ ");
        System.out.println("| | | |/ _ \\| '_ \\| '_ \\ / _` |");
        System.out.println("| |_| | (_) | | | | | | | (_| |");
        System.out.println("|____/ \\___/|_| |_|_| |_|\\__,_|");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printDonnaLogo();

        //greeting
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Donna");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        //constant inputs from the user
        while (true) {
            String input = sc.nextLine();
            String[] inputWords = input.split(" ");

            if (input.equals("bye")) { //exit
                printDashedLine();
                System.out.println("Bye. Hope to see you again soon!");
                printDashedLine();
                break;
            } else if (input.equals("list")) { //display list
                printDashedLine();
                for (int i = 0; i < taskNum; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                printDashedLine();
            } else if (inputWords[0].equals("mark") && inputWords.length == 2) { //request to mark a task as done
                try {
                    int taskToMark = Integer.parseInt(inputWords[1]); //not index
                    if ((taskToMark - 1) <= taskNum) { //if task to mark is a valid task
                        tasks[taskToMark - 1].markDone();
                        printDashedLine();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("    " + tasks[taskToMark - 1]);
                        printDashedLine();
                    } else {
                        printDashedLine();
                        System.out.println("Invalid task number :(");
                        printDashedLine();
                    }
                } catch (NumberFormatException e) { //if the phrase isnt "mark INTEGER" it's a task instead of a request
                    taskNum = addTask(input);
                }
            } else if (inputWords[0].equals("unmark") && inputWords.length == 2) { //request to unmark
                try {
                    int taskToMark = Integer.parseInt(inputWords[1]);
                    if ((taskToMark - 1) <= taskNum) { //if task to mark is a valid task
                        tasks[taskToMark - 1].markNotDone();
                        printDashedLine();
                        System.out.println("OK, I have marked this task as not done yet: ");
                        System.out.println("    " + tasks[taskToMark - 1]);
                        printDashedLine();
                    } else {
                        printDashedLine();
                        System.out.println("Invalid task number :(");
                        printDashedLine();
                    }
                } catch (NumberFormatException e) { //if the phrase isnt "unmark INTEGER" it's a task instead of a request
                    taskNum = addTask(input);
                }
            } else { //not a request
                taskNum = addTask(input);
            }
        }

        sc.close();
    }
}
