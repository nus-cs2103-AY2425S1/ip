public class HypeBot {
    private static final String bufferLine = "______________________________________________________________________\n";
    public static void greet() {
        String logo = " ('-. .-.               _ (`-.    ('-. .-. .-')                .-') _   \n" +
                "( OO )  /              ( (OO  ) _(  OO)\\  ( OO )              (  OO) )  \n" +
                ",--. ,--.  ,--.   ,--._.`     \\(,------.;-----.\\  .-'),-----. /     '._ \n" +
                "|  | |  |   \\  `.'  /(__...--'' |  .---'| .-.  | ( OO'  .-.  '|'--...__)\n" +
                "|   .|  | .-')     /  |  /  | | |  |    | '-' /_)/   |  | |  |'--.  .--'\n" +
                "|       |(OO  \\   /   |  |_.' |(|  '--. | .-. `. \\_) |  |\\|  |   |  |   \n" +
                "|  .-.  | |   /  /\\_  |  .___.' |  .--' | |  \\  |  \\ |  | |  |   |  |   \n" +
                "|  | |  | `-./  /.__) |  |      |  `---.| '--'  /   `'  '-'  '   |  |   \n" +
                "`--' `--'   `--'      `--'      `------'`------'      `-----'    `--'   \n";
        System.out.println(bufferLine
                + "AYO WHAT'S UP IT'S ME YOUR\n\n"
                + logo
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
