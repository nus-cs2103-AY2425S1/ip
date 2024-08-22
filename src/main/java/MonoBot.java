import java.util.Scanner;
import java.util.ArrayList;

public class MonoBot {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        MonoBot.printGreeting();

        Scanner sc = new Scanner(System.in);
        boolean keepAlive = true;

        while (keepAlive) {
            try {
                String input = sc.nextLine();

                String[] task = input.split(" ", 2);
                Command command = getCommand(task[0]);

                switch (command) {
                    case LIST:
                        MonoBot.printTasks();
                        break;

                    case TODO:
                        if (task.length != 2 || task[1].trim().isEmpty()) {
                            throw new MonoBotException("Details of task is missing");
                        }
                        Task todo = new Todo(task[1]);
                        MonoBot.addTask(todo);
                        break;

                    case DEADLINE:
                        if (task.length != 2 || task[1].trim().isEmpty()) {
                            throw new MonoBotException("Details of deadline is missing");
                        }
                        String[] deadlineDetails = task[1].split("/by", 2);
                        if (deadlineDetails.length != 2 || deadlineDetails[1].trim().isEmpty()) {
                            throw new MonoBotException("Due date/time of task is missing. " +
                                    "Note that format for adding a DEADLINE task is \n" +
                                    "deadline <task description> /by <due date/time>");
                        }
                        Task deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                        MonoBot.addTask(deadline);
                        break;

                    case EVENT:
                        if (task.length != 2 || task[1].trim().isEmpty()) {
                            throw new MonoBotException("Details of event is missing");
                        }
                        String[] eventDetails = task[1].split("/from|/to ", 3);
                        if (eventDetails.length != 3 || eventDetails[1].trim().isEmpty()
                                || eventDetails[2].trim().isEmpty()) {
                            throw new MonoBotException("Start and/or end time of event is missing. " +
                                    "Note that format for adding an event is \n" +
                                    "event <task description> /from <start date/time> /to <end date/time>");
                        }
                        Task event = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                        MonoBot.addTask(event);
                        break;

                    case MARK:
                        int indexToMark = Integer.parseInt(task[1]) - 1;
                        if (MonoBot.taskList.get(indexToMark).getIsDone()) {
                            throw new MonoBotException("Task has already been marked as completed");
                        }
                        MonoBot.markTask(indexToMark);
                        break;

                    case UNMARK:
                        int indexToUnmark = Integer.parseInt(task[1]) - 1;
                        if (!MonoBot.taskList.get(indexToUnmark).getIsDone()) {
                            throw new MonoBotException("Task was not marked as completed");
                        }
                        MonoBot.unmarkTask(indexToUnmark);
                        break;

                    case INVALID:
                        MonoBot.hLine();
                        System.out.println("Invalid Command. Valid commands are: \n" +
                                "list, todo, deadline, event, mark, unmark, bye");
                        MonoBot.hLine();
                        break;

                    case BYE:
                        sc.close();
                        keepAlive = false;
                        break;
                }
            } catch (MonoBotException e) {
                MonoBot.hLine();
                System.out.println(e);
                MonoBot.hLine();
            } catch (IndexOutOfBoundsException e) {
                MonoBot.hLine();
                int len = MonoBot.taskList.size();
                if (len > 0) {
                    System.out.println("Please provide an integer between 1 and " + (MonoBot.taskList.size()));
                } else {
                    System.out.println("No tasks added yet");
                }
                MonoBot.hLine();
            }
        }
        MonoBot.printFarewell();
    }

    private static void hLine() {
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    }

    private static void printGreeting() {
        MonoBot.hLine();
        System.out.println(" Hello! I'm MonoBot");
        System.out.println(" What can I do for you?");
        MonoBot.hLine();
    }

    private static void printFarewell() {
        MonoBot.hLine();
        System.out.println(" Bye. Hope to see you again soon!");
        MonoBot.hLine();
    }

    public static Command getCommand(String str) {
        String command = str.toLowerCase();
        return switch (command) {
            case "list" -> Command.LIST;
            case "mark" -> Command.MARK;
            case "unmark" -> Command.UNMARK;
            case "todo" -> Command.TODO;
            case "deadline" -> Command.DEADLINE;
            case "event" -> Command.EVENT;
            case "bye" -> Command.BYE;
            default -> Command.INVALID;
        };
    }

    private static void addTask(Task task) {
        MonoBot.taskList.add(task);
        MonoBot.hLine();
        System.out.println("Added: " + task);
        System.out.println("Now you have " + MonoBot.taskList.size() + " task(s) in the list");
        MonoBot.hLine();
    }

    private static void printTasks() {
        MonoBot.hLine();
        if (MonoBot.taskList.isEmpty()) {
            System.out.println("No tasks added yet");
        } else {
            System.out.println("Here are the tasks in your list");
            for (int i = 0; i < MonoBot.taskList.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, MonoBot.taskList.get(i));
            }
        }
        MonoBot.hLine();
    }

    private static void markTask(int i) {
        Task curr = MonoBot.taskList.get(i);
        curr.markTask();
        MonoBot.hLine();
        System.out.println("Nice! I have marked this task as completed:\n" + curr);
        MonoBot.hLine();
    }

    private static void unmarkTask(int i) {
        Task curr = MonoBot.taskList.get(i);
        curr.unmarkTask();
        MonoBot.hLine();
        System.out.println("Ok! I have marked this task as incomplete:\n" + curr);
        MonoBot.hLine();
    }
}
