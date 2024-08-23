import java.util.ArrayList;
import java.util.Scanner;

public class HypeBot {
    private static final String bufferLine = "______________________________________________________________________\n";
    private static final ArrayList<Task> commandList = new ArrayList<>();

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
        System.out.println(bufferLine
                + "AYO WHAT'S UP IT'S ME YOUR\n\n"
                + logo
                + "\nWhat can I do for you, my wonderful homie?\n"
                + bufferLine);
    }

    private static void exit() {
        System.out.println(bufferLine
                + "Alright homie, it's been a BLAST hanging out with you. "
                + "Have a wonderful\nday, and catch you soon again you ABSOLUTE BALLER!\n"
                + bufferLine);
    }

    private static void list() {
        System.out.println(bufferLine + "ALRIGHT, Here's that list!\n");
        for (int i = 0; i < commandList.size(); i++) {
            System.out.println(i + 1 + ". " + commandList.get(i));
        }
        System.out.println(bufferLine);
    }

    private static void add(String taskName) {
        commandList.add(new Task(taskName));
        System.out.println(bufferLine
                + "HECK YEAH, ADDED: "
                + taskName
                + "!\n"
                + "YOU'VE NOW GOT "
                + commandList.size()
                + "TASKS TO GO!\n"
                + bufferLine);
    }

    private static void unmark(int idx) {
        commandList.get(idx).unmark();
        System.out.println(bufferLine
                + "AIGHT, LET'S GET READY TO CONQUER THIS TASK:\n  "
                + commandList.get(idx)
                + bufferLine);
    }

    private static void mark(int idx) {
        commandList.get(idx).mark();
        System.out.println(bufferLine
                + "AIGHT, ABSOLUTELY CONQUERED THIS TASK:\n  "
                + commandList.get(idx)
                + bufferLine);
    }

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        boolean canExit = false;

        while (!canExit) {
            String command = sc.nextLine();

            if (command.trim().isEmpty()) {
                continue;
            }

            String commandLowerCase = command.toLowerCase();
            if (commandLowerCase.equals("bye")) {
                canExit = true;
            } else if (commandLowerCase.equals("list")) {
                list();
            } else if (commandLowerCase.contains("unmark")) {
                try {
                    Integer idx = Integer.parseInt(commandLowerCase.split(" ")[1]) - 1;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(bufferLine
                            + "I might be tripping bro, my bad, my bad - \n"
                            + "try indicating the index of the task you wanna TAKE ON AGAIN!\n"
                            + bufferLine);
                    continue;
                }
                int idx = Integer.parseInt(commandLowerCase.split(" ")[1]) - 1;
                unmark(idx);
            } else if (commandLowerCase.contains("mark")) {
                try {
                    Integer idx = Integer.parseInt(commandLowerCase.split(" ")[1]) - 1;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(bufferLine
                            + "I might be tripping bro, my bad, my bad - \n"
                            + "try indicating the index of the task you wanna mark CONQUERED!\n"
                            + bufferLine);
                    continue;
                }
                int idx = Integer.parseInt(commandLowerCase.split(" ")[1]) - 1;
                mark(idx);
            }  else {
                add(command);
            }
        }

        exit();
    }
}
