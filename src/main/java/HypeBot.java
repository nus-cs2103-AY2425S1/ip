import java.util.ArrayList;
import java.util.Scanner;

public class HypeBot {
    private static final String BUFFER_LINE = "______________________________________________________________________\n";
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * Returns message formatted with buffer lines.
     *
     * @param message String to be reformatted.
     * @return Reformatted string with buffer lines.
     */
    private static String addBufferLine(String message) {
        return BUFFER_LINE + message + BUFFER_LINE;
    }

    /**
     * Returns error message formatted with buffer lines.
     *
     * @param message Error message to be reformatted.
     * @return Reformatted string with buffer lines.
     */
    private static String addBufferLineError(String message) {
        return BUFFER_LINE + "I might be tripping bro, my bad, my bad - \n" + message + BUFFER_LINE;
    }

    /**
     * Greets the user upon init and prompts the user for a command.
     */
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

    /**
     * Greets the user goodbye upon the command 'bye'.
     */
    private static void exit() {
        System.out.println(addBufferLine("""
                Alright homie, it's been a BLAST hanging out with you. \
                Have a wonderful
                day, and catch you soon again you ABSOLUTE BALLER!
                """));
    }

    /**
     * Prints out all the tasks user has saved in TASKS.
     */
    private static void list() {
        StringBuilder list = new StringBuilder("ALRIGHT, Here's that list!\n");
        for (int i = 0; i < TASKS.size(); i++) {
            list.append(i + 1).append(". ").append(TASKS.get(i)).append("\n");
        }
        System.out.println(addBufferLine(list.toString()));
    }

    /**
     * Adds task requested by user to TASKS.
     * Informs user how many total tasks saved.
     *
     * @param task Task to add to TASKS.
     */
    private static void add(Task task) {
        TASKS.add(task);
        System.out.println(addBufferLine("HECK YEAH, ADDED: "
                + task
                + "!\nYOU'VE NOW GOT "
                + TASKS.size()
                + " TASKS TO GO!\n"));
    }

    /**
     * Takes in task number and marks corresponding task as incomplete.
     * If task number not found in list, throws IndexOutOfBoundsException.
     *
     * @param idx Task number to mark incomplete.
     * @throws IndexOutOfBoundsException Thrown if task number invalid (too low / too high).
     */
    private static void unmark(int idx) throws IndexOutOfBoundsException {
        TASKS.get(idx).unmark();
        System.out.println(addBufferLine("AIGHT, LET'S GET READY TO CONQUER THIS TASK:\n  "
                + TASKS.get(idx)
                + "\n"));
    }

    /**
     * Takes in task number and marks corresponding task as complete.
     * If task number not found in list, throws IndexOutOfBoundsException.
     *
     * @param idx Task number to mark complete.
     * @throws IndexOutOfBoundsException Thrown if task number invalid (too low / too high).
     */
    private static void mark(int idx) throws IndexOutOfBoundsException {
        TASKS.get(idx).mark();
        System.out.println(addBufferLine("AIGHT, ABSOLUTELY CONQUERED THIS TASK:\n  "
                + TASKS.get(idx)
                + "\n"));
    }

    /**
     * Takes in task number and deletes corresponding task from TASKS.
     * If task number not found in list, throws IndexOutOfBoundsException.
     *
     * @param idx Task number to delete.
     * @throws IndexOutOfBoundsException Thrown if task number invalid (too low / too high).
     */
    private static void delete(int idx) throws IndexOutOfBoundsException {
        Task removed = TASKS.remove(idx);
        System.out.println(addBufferLine("Say no more, BABY BYE BYE BYE to this task:\n "
            + removed
            + "!\nYOU'VE NOW GOT "
            + TASKS.size()
            + " TASKS TO GO!\n"));
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
                case "delete":
                    try {
                        int idx = Integer.parseInt(taskName.strip()) - 1;
                        delete(idx);
                    } catch (NumberFormatException e) {
                        System.out.println(addBufferLineError("try indicating the index of the task you wanna delete "
                                + "as a number!\n"));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(addBufferLineError("try indicating the index of an existing task you wanna "
                                + "delete!\n"));
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
