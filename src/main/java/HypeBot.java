import java.util.ArrayList;
import java.util.Scanner;

public class HypeBot {
    private static final String bufferLine = "______________________________________________________________________\n";
    public static void greet() {
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

    public static void exit() {
        System.out.println(bufferLine
                + "Alright homie, it's been a BLAST hanging out with you. "
                + "Have a wonderful\nday, and catch you soon again you ABSOLUTE BALLER!\n"
                + bufferLine);
    }

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        boolean canExit = false;
        ArrayList<String> commandList = new ArrayList<>();

        while (!canExit) {
            String command = sc.nextLine();
            String commandLowerCase = command.toLowerCase();
            if (commandLowerCase.equals("bye")) {
                canExit = true;
                continue;
            }

            if (commandLowerCase.equals("list")) {
                System.out.println(bufferLine + "ALRIGHT, Here's that list!\n");
                for (int i = 0; i < commandList.size(); i++) {
                    System.out.println(i + 1 + ". " + commandList.get(i));
                }
                System.out.println(bufferLine);
            } else {
                commandList.add(command);
                System.out.println(bufferLine
                        + "HECK YEAH, ADDED: "
                        + command
                        + "!\n"
                        + bufferLine);
            }
        }

        exit();
    }
}
