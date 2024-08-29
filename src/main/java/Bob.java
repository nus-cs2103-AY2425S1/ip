import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Bob {

    private final TaskList myTasks;
    private final String filePath;
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String GREETINGS = "Hello! I'm Bob\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";

    public Bob(String filePath) {
        this.myTasks = new TaskList();
        this.filePath = filePath;
    }

    private void initialize() {
        Storage.readData(this.myTasks, this.filePath);
    }

    public static void main(String[] args) {

        Bob myBot = new Bob("./userdata.txt");
        myBot.initialize();

        System.out.print(DIVIDER + GREETINGS + DIVIDER);

        myBot.startChatBot();

        System.out.print(EXIT + DIVIDER);
    }

    public void startChatBot() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();
            System.out.print(DIVIDER);
            try {
                switch (input) {
                case "bye":
                    return;
                case "list":
                    System.out.println(myTasks);
                    break;
                case "mark":
                    int index = scanner.nextInt() - 1;
                    if (!(index < myTasks.size()) || index < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    myTasks.markTaskAtIndex(index);
                    break;
                case "unmark":
                    int ind = scanner.nextInt() - 1;
                    if (!(ind < myTasks.size()) || ind < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    myTasks.unmarkTaskAtIndex(ind);
                    break;
                case "todo":
                    myTasks.addTask(printAddTask(Parser.newToDo(scanner.nextLine().trim().replace("  ", " "))));
                    System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                    break;
                case "deadline":
                    myTasks.addTask(printAddTask(Parser.newDeadline(scanner.nextLine().trim().replace("  ", " "))));
                    System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                    break;
                case "event":
                    myTasks.addTask(printAddTask(Parser.newEvent(scanner.nextLine().trim().replace("  ", " "))));
                    System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                    break;
                case "delete":
                    int inde = scanner.nextInt() - 1;
                    if (!(inde < myTasks.size()) || inde < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    Task delTask = myTasks.removeTaskAtIndex(inde);
                    System.out.println(" Noted. I've removed this task:\n " + delTask);
                    System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                    break;
                default:
                    scanner.nextLine();
                    throw new InvalidInputException();
                }
                Storage.writeData(myTasks, this.filePath);
            } catch (EmptyArgumentException | MissingArgumentException |
                     InvalidTaskNumberException | InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date/time has been entered. Please key in with a DD/MM/YYYY format or DD/MM/YYYY HHMM format");
            }

            System.out.print(DIVIDER);
        }
    }

    public static Task printAddTask(Task task) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + task);
        return task;
    }

}

