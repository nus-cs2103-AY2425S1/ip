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
        System.out.println("Alright homie, it's been a BLAST hanging out with you. "
                + "Have a wonderful\nday, and catch you soon again you ABSOLUTE BALLER!\n"
                + bufferLine);
    }

    public static void main(String[] args) {
        greet();
        exit();
    }
}
