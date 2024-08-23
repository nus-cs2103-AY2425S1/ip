import java.util.ArrayList;
import java.util.Scanner;

public class HypeBot {
    private static final String bufferLine = "______________________________________________________________________\n";
    private static final ArrayList<Task> commandList = new ArrayList<>();

    private static String addBufferLine(String message) {
        return bufferLine + message + bufferLine;
    }

    private static String addBufferLineError(String message) {
        return bufferLine + "I might be tripping bro, my bad, my bad - \n" + message + bufferLine;
    }

    private static void greet() {
        String logo = """
                 ('-. .-.               _ (`-.    ('-. .-. .-')                .-') _  \s
                ( OO )  /              ( (OO  ) _(  OO)\\  ( OO )              (  OO) ) \s
                ,--. ,--.  ,--.   ,--._.`     \\(,------.;-----.\\  .-'),-----. /     '._\s
                |  | |  |   \\  `.'  /(__...--'' |  .---'| .-.  | ( OO'  .-.  '|'--...__)
                |   .|  | .-')     /  |  /  | | |  |    | '-' /_)/   |  | |  |'--.  .--'
                |       |(OO  \\   /   |  |_.' |(|  '--. | .-. `. \\_) |  |\\|  |   |  |  \s
                |  .-.  | |   /  /\\_  |  .___.' |  .--' | |  \\  |  \\ |  | |  |   |  |  \s
                |  | |  | `-./  /.__) |  |      |  `---.| '--'  /   `'  '-'  '   |  |  \s
                `--' `--'   `--'      `--'      `------'`------'      `-----'    `--'  \s
                """;
        System.out.println(addBufferLine("AYO WHAT'S UP IT'S ME YOUR\n\n"
                + logo
                + "\nWhat can I do for you, my wonderful homie?\n"));
    }

    private static void exit() {
        System.out.println(addBufferLine("""
                Alright homie, it's been a BLAST hanging out with you. \
                Have a wonderful
                day, and catch you soon again you ABSOLUTE BALLER!
                """));
    }

    private static void list() {
        StringBuilder list = new StringBuilder("ALRIGHT, Here's that list!\n");
        for (int i = 0; i < commandList.size(); i++) {
            list.append(i + 1).append(". ").append(commandList.get(i)).append("\n");
        }
        System.out.println(addBufferLine(list.toString()));
    }

    private static void add(Task task) {
        commandList.add(task);
        System.out.println(addBufferLine("HECK YEAH, ADDED: "
                + task
                + "!\nYOU'VE NOW GOT "
                + commandList.size()
                + " TASKS TO GO!\n"));
    }

    private static void unmark(int idx) throws IndexOutOfBoundsException {
        commandList.get(idx).unmark();
        System.out.println(addBufferLine("AIGHT, LET'S GET READY TO CONQUER THIS TASK:\n  "
                + commandList.get(idx)
                + "\n"));
    }

    private static void mark(int idx) throws IndexOutOfBoundsException {
        commandList.get(idx).mark();
        System.out.println(addBufferLine("AIGHT, ABSOLUTELY CONQUERED THIS TASK:\n  "
                + commandList.get(idx)
                + "\n"));
    }

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        boolean canExit = false;

        while (!canExit) {
            String line = sc.nextLine();

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] splitLineForDates = line.split("/");
            String[] commandAndTaskName = splitLineForDates[0].split(" ");
            String command = commandAndTaskName[0];
            StringBuilder taskNameBuilder = new StringBuilder();
            for (int i = 1; i < commandAndTaskName.length; i++) {
                taskNameBuilder.append(commandAndTaskName[i]).append(" ");
            }
            String taskName = taskNameBuilder.toString();

            switch (command) {
                case "bye":
                    canExit = true;
                    break;
                case "list":
                    list();
                    break;
                case "todo":
                    if (taskName.isEmpty()) {
                        System.out.println(addBufferLineError("drop the name of the task, bro I gotta know!\n"));
                        break;
                    }
                    ToDo newTodo = new ToDo(taskName);
                    add(newTodo);
                    break;
                case "deadline":
                    if (splitLineForDates.length < 2) {
                        System.out.println(addBufferLineError("make sure you got the due date for that SWAGGIN' "
                                + "deadline you got!\n"));
                        break;
                    }
                    Deadline newDeadline = new Deadline(taskName, splitLineForDates[1]);
                    add(newDeadline);
                    break;
                case "event":
                    if (splitLineForDates.length < 3) {
                        System.out.println(addBufferLineError("make sure you got that start time AND end time for "
                                + "that AWESOME event you got!\n"));
                        break;
                    }
                    Event newEvent = new Event(taskName, splitLineForDates[1], splitLineForDates[2]);
                    add(newEvent);
                    break;
                case "mark":
                    try {
                        int idx = Integer.parseInt(taskName.strip()) - 1;
                        mark(idx);
                    } catch (NumberFormatException e) {
                        System.out.println(addBufferLineError("try indicating the index of the task you wanna mark "
                                + "CONQUERED as a number!\n"));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(addBufferLineError("try indicating the index of an existing task you wanna "
                                + "mark CONQUERED!\n"));
                    }
                    break;
                case "unmark":
                    try {
                        int idx = Integer.parseInt(taskName.strip()) - 1;
                        unmark(idx);
                    } catch (NumberFormatException e) {
                        System.out.println(addBufferLineError("try indicating the index of the task you wanna TAKE "
                                + "ON AGAIN "
                                + "as a number!\n"));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(addBufferLineError("try indicating the index of an existing task you wanna "
                                + "TAKE ON AGAIN!\n"));
                    }
                    break;
                default:
                    System.out.println(addBufferLineError("but I don't think we're vibing when you say '"
                            + command
                            + "'.\nMind if I ask you for anything else, homie?\n"));
            }
        }

        exit();
    }
}
