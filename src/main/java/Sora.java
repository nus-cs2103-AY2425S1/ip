import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sora {
    private static final String HORIZONTALLINE = "---------------------------------------------------";
    private final List<Task> taskList;
    private boolean isLive;

    public Sora() {
        this.taskList = new ArrayList<>();
        this.isLive = true;
    }

    public static void main(String[] args) {
        Sora sora = new Sora();
        Scanner commandScanner = new Scanner(System.in);

        System.out.println(greeting());

        while (sora.isLive) {
            ArrayList<String> parsedCommand = parse(commandScanner.nextLine().trim());

            String mainCommand = parsedCommand.get(0).toLowerCase();
            try {
                switch (mainCommand) {
                    case "bye":
                        System.out.println(farewell());
                        sora.isLive = false;
                        break;
                    case "list":
                        sora.displayList();
                        break;
                    case "mark":
                        sora.markTask(parsedCommand.get(1));
                        break;
                    case "unmark":
                        sora.unmarkTask(parsedCommand.get(1));
                        break;
                    case "todo":
                        sora.addTask("todo", parsedCommand);
                        break;
                    case "deadline":
                        sora.addTask("deadline", parsedCommand);
                        break;
                    case "event":
                        sora.addTask("event", parsedCommand);
                        break;
                    case "delete":
                        sora.deleteTask(parsedCommand.get(1));
                        break;
                    case "":
                        throw new SoraException("\tPlease Enter a Command\n" + HORIZONTALLINE);
                    default:
                        System.out.println("\tSora doesn't understand! Please Try Again!");

                }
                System.out.println(HORIZONTALLINE);
            } catch (SoraException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String greeting() {
        return HORIZONTALLINE + "\n" + "\tHello! I'm Sora!\n\tWhat can I do for you?\n" + HORIZONTALLINE;
    }

    private static String farewell() {
        return "\tBye. Hope to see you again soon!";
    }

    private static ArrayList<String> parse(String command) {
        ArrayList<String> parsedResult = new ArrayList<>();
        String[] parse_1 = command.split(" ", 2);
        parsedResult.add(parse_1[0]);
        if (parse_1.length > 1) {
            String[] parse_2 = parse_1[1].split(" /", 3);
            for (String s: parse_2) {
                parsedResult.add(s);
            }
        }
        return parsedResult;
    }

    private void displayList() {
        if (this.taskList.isEmpty()) {
            System.out.println("\tSeems like there are no tasks!");
            return;
        }
        for (Task t : this.taskList) {
            System.out.println("\t" + t.toString());
        }
    }

    private void markTask(String value) throws SoraException {
        try {
            Task task = this.taskList.get(Integer.parseInt(value) - 1);
            if (task.isDone) {
                System.out.println("\tSora has discovered this task is already done!");
                return;
            }
            task.markAsDone();
            System.out.println("\tNice! Sora has marked this task as done:");
            System.out.println("\t" + task);
        } catch (NumberFormatException e) {
            throw new SoraException("\tPlease Enter - Mark (int)\n" + HORIZONTALLINE);
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("\tPlease Enter Integer Value within List Size\n" + HORIZONTALLINE);
        }
    }

    private void unmarkTask(String value) throws SoraException {
        try {
            Task task = this.taskList.get(Integer.parseInt(value) - 1);
            if (!task.isDone) {
                System.out.println("\tSora has discovered this task is already not done!");
                return;
            }
            task.markAsNotDone();
            System.out.println("\tOk, Sora has unmarked this task as not done:");
            System.out.println("\t" + task);
        } catch (NumberFormatException e) {
            throw new SoraException("\tPlease Enter - Mark (int)\n" + HORIZONTALLINE);
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("\tPlease Enter Integer Value within List Size\n" + HORIZONTALLINE);
        }
    }

    private void addTask(String mainCommand, ArrayList<String> parsedCommand) throws SoraException {
        switch (mainCommand) {
            case "todo":
                try {
                    this.taskList.add(new ToDo(parsedCommand.get(1)));
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new SoraException("\tPlease Enter - Todo (Description)");
                }
            case "deadline":
                try {
                    this.taskList.add(new Deadline(parsedCommand.get(1), parsedCommand.get(2).substring(3)));
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new SoraException("\tPlease Enter - Deadline (Description) /by (by)");
                }
            case "event":
                try {
                    this.taskList.add(new Event(parsedCommand.get(1), parsedCommand.get(2).substring(5), parsedCommand.get(3).substring(3)));
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new SoraException("\tPlease Enter - Event (Description) /from (from) /to (to)");
                }
        }
        System.out.println("\tGot it. Sora has added this task:");
        System.out.println("\t" + taskList.get(taskList.size() - 1));
        System.out.println("\tNow, you have " + taskList.size() + " tasks in your list");
    }

    private void deleteTask(String value) throws SoraException {
        try {
            int index = Integer.parseInt(value) - 1;
            Task deletedTask = this.taskList.remove(index);
            System.out.println("\tNoted. Sora has removed this task:");
            System.out.println("\t" + deletedTask);
        } catch (NumberFormatException e) {
            throw new SoraException("\tPlease Enter - Delete (int)\n" + HORIZONTALLINE);
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("\tPlease Enter Integer Value within List Size\n" + HORIZONTALLINE);
        }
    }
}
