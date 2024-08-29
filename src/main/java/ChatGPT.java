import java.util.ArrayList;
import java.util.Scanner;

public class ChatGPT {
    private final static String NAME = "ChatGPT";
    private final static String LINE = "________________________________________________";
    private static ArrayList<Task> list = new ArrayList<Task>();
    private static int taskAmt = 0;
    private enum Commands {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE;

        public static Commands getCommand(String s) throws NoSuchMethodException {
            try {
                return Commands.valueOf(s);
            } catch (IllegalArgumentException e) {
                throw new NoSuchMethodException("\t Oops!! I don't understand what that means :((");
            }
        }
    }
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        sendGreeting();
        do {
            try {
                switch(Commands.getCommand(userInput.next().toUpperCase())) {
                case BYE:
                    sendExit();
                    return;

                case LIST:
                    printList();
                    break;

                case TODO:
                    String input = userInput.nextLine();

                    Task newTask = new ToDos(input);
                    list.add(newTask);
                    taskAmt++;
                    sendAddTaskMsg(newTask);
                    break;

                case DEADLINE:
                    input = userInput.nextLine();

                    String task = input.split("/by")[0];
                    String deadline = input.split("/by")[1];
                    newTask = new Deadlines(task, deadline);

                    list.add(newTask);
                    taskAmt++;
                    sendAddTaskMsg(newTask);
                    break;

                case EVENT:
                    input = userInput.nextLine();
                    task = input.split("/from")[0];

                    String date = input.split("/from")[1];
                    String startDate = date.split("/to")[0];
                    String endDate = date.split("/to")[1];
                    newTask = new Events(task, startDate, endDate);

                    list.add(newTask);
                    taskAmt++;
                    sendAddTaskMsg(newTask);
                    break;

                case MARK:
                    int index = userInput.nextInt();
                    System.out.println("\t" + LINE);
                    System.out.println(list.get(index-1).setCompleted(true));
                    System.out.println("\t" + LINE);
                    break;

                case UNMARK:
                    index = userInput.nextInt();
                    System.out.println("\t" + LINE);
                    System.out.println(list.get(index-1).setCompleted(false));
                    System.out.println("\t" + LINE);
                    break;

                case DELETE:
                    index = userInput.nextInt();
                    Task deletedTask = list.get(index-1);
                    list.remove(index-1);
                    taskAmt--;
                    sendDeleteMsg(deletedTask);
                    break;
                }
            } catch (IllegalArgumentException | NoSuchMethodException e) {
                System.out.println("\t" + LINE);
                System.out.println(e.getMessage());
                System.out.println("\t" + LINE);
            } catch (Exception e) {
                System.out.println("\t" + LINE);
                System.out.println("\t There seems to have been a problem with your inputs Q.Q" +
                        "\n\t Please try again with a valid command and inputs");
                System.out.println("\t" + LINE);
            }
        } while (true);
    }

    private static void sendGreeting() {
        System.out.println("\t"+LINE);
        System.out.println("\tHello! I'm " + NAME);
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t"+LINE);
    }

    private static void sendExit() {
        System.out.println("\t"+LINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t"+LINE);
    }

    private static void sendAddTaskMsg(Task task) {
        System.out.println("\t"+LINE);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  "+ task.toString());
        System.out.println("\tNow you have " + taskAmt + " tasks in your list.");
        System.out.println("\t"+LINE);
    }

    private static void sendDeleteMsg(Task task) {
        System.out.println("\t"+LINE);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  "+ task.toString());
        System.out.println("\tNow you have " + taskAmt + " tasks in your list.");
        System.out.println("\t"+LINE);
    }

    private static void printList() {
        System.out.println("\t"+LINE);
        if (taskAmt == 0) {
            System.out.println("\tNothing has been added");
        } else {
            System.out.println("\tHere are the tasks in your list:");
        }
        for (int i = 0; i < taskAmt; i++){
            System.out.println("\t" + (i+1) + ". " + list.get(i).toString());
        }
        System.out.println("\t"+LINE);
    }
}
