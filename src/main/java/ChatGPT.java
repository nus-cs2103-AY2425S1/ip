import java.util.Scanner;

public class ChatGPT {
    private static String NAME = "ChatGPT";
    private static String LINE = "________________________________________________";
    private static Task[] list = new Task[100];
    private static int listSize = 0;
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        sendGreeting();
        do {
            String command = userInput.next();
            if (command.equals("bye")) {
                break;
            }
            if (command.equals("list")) {
                printList();
            } else if (command.equals("todo")) {
                String input = userInput.nextLine();

                Task newTask = new ToDos(input);

                list[listSize] = newTask;
                listSize++;
                sendAddTaskMsg(newTask);
            } else if (command.equals("deadline")) {
                String input = userInput.nextLine();

                String task = input.split("/by")[0];
                String deadline = input.split("/by")[1];
                Task newTask = new Deadlines(task, deadline);

                list[listSize] = newTask;
                listSize++;
                sendAddTaskMsg(newTask);
            } else if (command.equals("event")) {
                String input = userInput.nextLine();
                String task = input.split("/from")[0];

                String date = input.split("/from")[1];
                String startDate = date.split("/to")[0];
                String endDate = date.split("/to")[1];
                Task newTask = new Events(task, startDate, endDate);

                list[listSize] = newTask;
                listSize++;
                sendAddTaskMsg(newTask);
            } else if (command.equals("mark")) {
                int index = userInput.nextInt();
                System.out.println(list[index-1].setCompleted(true));
                System.out.println("\t"+LINE);
            } else if (command.equals("unmark")) {
                int index = userInput.nextInt();
                System.out.println(list[index-1].setCompleted(false));
                System.out.println("\t"+LINE);
            } else {
                userInput.nextLine();
                System.out.println("\t"+LINE);
                System.out.println("\t!! Invalid command entered !!");
                System.out.println("\t"+LINE);
            }
        } while (true);
        sendExit();
    }

    private static void sendGreeting() {
        System.out.println("\t"+LINE);
        System.out.println("\tHello! I'm " + NAME);
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t"+LINE);
    }

    private static void sendExit() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t"+LINE);
    }

    private static void sendAddTaskMsg(Task task) {
        System.out.println("\t"+LINE);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  "+ task.toString());
        System.out.println("\tNow you have " + listSize + " tasks in your list.");
        System.out.println("\t"+LINE);
    }

    private static void printList() {
        if (listSize == 0) {
            System.out.println("\tNothing has been added");
        } else {
            System.out.println("\tHere are the tasks in your list:");
        }
        for (int i = 0; i < listSize; i++){
            System.out.println("\t" + (i+1) + ". " + list[i]);
        }
        System.out.println("\t"+LINE);
    }
}
