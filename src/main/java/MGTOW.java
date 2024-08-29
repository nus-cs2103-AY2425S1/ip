import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MGTOW {
    private static TaskStorage tasks;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void printMsg(String str){
        System.out.println("\t" + str);
    }
    public static void printTask(int ind, Task task) {
        System.out.println("\t" + ind + ". " + task);
    }

    public static void addTodo(String desc) throws InvalidTaskException {
        if (desc.isBlank()) {
            throw new InvalidTaskException("What you want me to add?");
        }

        tasks.addTask(new ToDo(desc));
    }

    public static void addEvent(String desc, String start, String end) throws InvalidTaskException{
        if (desc.isBlank()) {
            throw new InvalidTaskException("What you want me to add?");
        } else if (start.isBlank()){
            throw new InvalidTaskException("Start when? Try again");
        } else if (end.isBlank()){
            throw new InvalidTaskException("Till when? Try again");
        }

        tasks.addTask(new Event(desc,start,end));
    }

    public static void addDeadline(String desc, String end) throws InvalidTaskException {
        if (desc.isBlank()) {
            throw new InvalidTaskException("What you want me to add?");
        } else if (end.isBlank()){
            throw new InvalidTaskException("By when? Try again");
        }

        tasks.addTask(new Deadline(desc,end));
    }

    public static void findTasksOnDate(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DATE_FORMAT);
            ArrayList<Task> tasksOnDate = tasks.getTasksOnDate(date);
            if (tasksOnDate.isEmpty()) {
                printMsg("No tasks found on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            } else {
                printMsg("Tasks on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
                for (int i = 0; i < tasksOnDate.size(); i++) {
                    printTask(i + 1, tasksOnDate.get(i));
                }
            }
        } catch (DateTimeParseException e) {
            printMsg("Invalid date format. Please use yyyy-MM-dd");
        }
    }

    public static void main(String[] args) {
        String logo = "";
        String line = "____________________________________________";
        Scanner sc = new Scanner(System.in);

        printMsg(line);
        printMsg("Hello! I'm MGTOW");
        printMsg("What can I do for you?");
        printMsg(line);

        tasks = new TaskStorage();

        Boolean isFinished = false;

        while (!isFinished){
            String input = sc.nextLine();
            printMsg(line);

            if (input.equals("bye")){
                isFinished = true;
                sc.close();
            } else {
                String[] parts = input.split(" ", 2);
                String cmd = parts[0];
                String desc = parts.length > 1 ? parts[1] : "";

                try {
                    switch (cmd) {
                        case "list":
                            tasks.listAllTasks();
                            break;
                        case "mark":
                            int markIndex = Integer.parseInt(desc) - 1;
                            tasks.markTask(markIndex);
                            break;
                        case "unmark":
                            int unmarkIndex = Integer.parseInt(desc) - 1;
                            tasks.unmarkTask(unmarkIndex);
                            break;
                        case "delete":
                            int deleteIndex = Integer.parseInt(desc) - 1;
                            tasks.deleteTask(deleteIndex);
                            break;
                        case "todo":
                            addTodo(desc);
                            break;
                        case "deadline":
                            String[] deadlineArgs = desc.split(" /by ");
                            if (deadlineArgs.length > 1) {
                                addDeadline(deadlineArgs[0], deadlineArgs[1]);
                            } else {
                                throw new InvalidTaskException("Invalid deadline format. Use: deadline [description] /by [yyyy-MM-dd HHmm]");
                            }
                            break;
                        case "event":
                            String[] eventArgs = desc.split(" /from ");
                            if (eventArgs.length > 1) {
                                String[] timeArgs = eventArgs[1].split(" /to ");
                                if (timeArgs.length > 1) {
                                    addEvent(eventArgs[0], timeArgs[0], timeArgs[1]);
                                } else {
                                    throw new InvalidTaskException("Invalid event format. Use: event [description] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm]");
                                }
                            } else {
                                throw new InvalidTaskException("Invalid event format. Use: event [description] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm]");
                            }
                            break;
                        case "find":
                            findTasksOnDate(desc);
                            break;
                        default:
                            throw new InvalidTaskException("What you talking?");
                    }
                } catch (InvalidTaskException e) {
                    printMsg(e.getMessage());
                } catch (NumberFormatException e) {
                    printMsg("Invalid number format. Please enter a valid number.");
                }
                printMsg(line);
            }
        }

        printMsg("OK bye time to MGTOW");
        printMsg(line);
    }
}
