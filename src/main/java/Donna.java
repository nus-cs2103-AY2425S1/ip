import java.util.Objects;
import java.util.Scanner;

public class Donna {
    private static Task[] tasks = new Task[100];
    private static int taskNum = 0;

    static private void printDashedLine() {
        System.out.println("____________________________________________________________");
    }

    static private void printDonnaLogo() {
        System.out.println(" ____      ");
        System.out.println("|  _ \\  ___  _ __  _ __   __ _ ");
        System.out.println("| | | |/ _ \\| '_ \\| '_ \\ / _` |");
        System.out.println("| |_| | (_) | | | | | | | (_| |");
        System.out.println("|____/ \\___/|_| |_|_| |_|\\__,_|");
        System.out.println();
    }

    private static void addTask(String desc) throws DonnaException {
        String[] descWords = desc.split(" ", 2); // first word specifies type, the rest= description
        String taskType = descWords[0];
        String taskDescription;

        Task newTask;
        if (taskType.equals("todo")) {
            if (descWords.length != 2) {
                throw DonnaException.emptyDescription(taskType);
            }
            taskDescription = descWords[1];

            newTask = new ToDo(taskDescription);

        } else if (taskType.equals("deadline")) {
            if (descWords.length != 2) {
                throw DonnaException.emptyDescription(taskType);
            }
            taskDescription = descWords[1];

            if (taskDescription.trim().equals("/by") || taskDescription.isBlank()) {
                throw DonnaException.emptyDescription(taskType);
            }

            String[] taskDescriptionWords = taskDescription.split(" /by ", 2);
            if (taskDescriptionWords.length != 2) {
                throw DonnaException.emptyDeadline();
            }
            if (taskDescriptionWords[0].isBlank()) {
                throw DonnaException.emptyDescription(taskType);
            }
            String deadlineTime = taskDescriptionWords[1];
            newTask = new Deadline(taskDescriptionWords[0], deadlineTime);

        } else if (taskType.equals("event")) {
            if (descWords.length != 2) {
                throw DonnaException.emptyDescription(taskType);
            }
            taskDescription = descWords[1];

            String[] taskDescriptionWords = taskDescription.split(" /from ", 2); //task description but split into words
            String[] taskTimes;
            String toTime;
            String fromTime;
            String eventDesc;

            if (taskDescriptionWords.length != 2) { // no /from found, so look for /to
                fromTime = "no information!";

                taskTimes = taskDescriptionWords[0].split(" /to ", 2); // if /to exists, divided.
                if (taskTimes.length != 2) { // /to not found either;
                    throw DonnaException.emptyEventTime(); // only thrown when both /from and /to are not given by the user. The user however is still permitted to provide either (or both)
                } else {
                    toTime = taskTimes[1];
                    eventDesc = taskTimes[0];
                };
            } else { // /from exists
                eventDesc = taskDescriptionWords[0];

                taskTimes = taskDescriptionWords[1].split(" /to ", 2); // /from exists so check for /to
                if (taskTimes.length != 2) {
                    fromTime = taskTimes[0];
                    toTime = "no information!";
                } else {
                    fromTime = taskTimes[0];
                    toTime = taskTimes[1];
                }

            }

            if (eventDesc.isBlank() || eventDesc.startsWith("/from") || eventDesc.startsWith("/to")) {
                throw DonnaException.emptyDescription(taskType);
            }

            newTask = new Event(eventDesc, fromTime, toTime);
        } else {
            throw DonnaException.invalidTaskType(taskType);
        }

        tasks[taskNum] =  newTask;
        taskNum++;
        printDashedLine();
        System.out.println("Got it. I've added this task: " );
        System.out.println("    " + newTask);
        if (taskNum == 1) {
            System.out.println("This is the first task in the list. ");
        } else {
            System.out.println("You now have " + taskNum + " tasks in the list. ");
        }
        printDashedLine();
    }

    private static void updateTaskStatus(int taskToMark, boolean toBeMarked) {
        if (taskToMark > 0 && (taskToMark - 1) < taskNum) { //if task to mark is a valid task
            if (toBeMarked) {
                tasks[taskToMark - 1].markDone();
                printDashedLine();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("    " + tasks[taskToMark - 1]);
                printDashedLine();
            } else {
                tasks[taskToMark - 1].markNotDone();
                printDashedLine();
                System.out.println("OK, I have marked this task as not done yet: ");
                System.out.println("    " + tasks[taskToMark - 1]);
                printDashedLine();
            }
        } else {
            printDashedLine();
            System.out.println("Invalid task number :(");
            System.out.println("No task assigned to this number yet. Retry with a valid task number!");
            printDashedLine();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //greeting
        printDashedLine();
        printDonnaLogo();
        System.out.println("Hello! I'm Donna");
        System.out.println("What can I do for you?");
        printDashedLine();

        //constant inputs from the user
        while (true) {
            String input = sc.nextLine();
            String[] inputWords = input.split(" ");

            try {
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
                        updateTaskStatus(taskToMark, true);
                    } catch (NumberFormatException e) { //if the phrase isnt "mark INTEGER" it's a task instead of a request
                        addTask(input);
                    }
                } else if (inputWords[0].equals("unmark") && inputWords.length == 2) { //request to unmark
                    try {
                        int taskToMark = Integer.parseInt(inputWords[1]);
                        updateTaskStatus(taskToMark, false);
                    } catch (NumberFormatException e) { //if the phrase isnt "unmark INTEGER" it's a task instead of a request
                        addTask(input);
                    }
                } else { //not a request
                    addTask(input);
                }
            } catch (DonnaException e) {
                printDashedLine();
                System.out.println(e.getMessage());
                printDashedLine();
            }
        }

        sc.close();
    }
}
