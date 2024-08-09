import java.util.Scanner;

public class SkibidiSigma {
    public static void main(String[] args) {
        String logo = " _____ _    _ _     _     _ _   _____ _                       \n"
                + "/  ___| |  (_) |   (_)   | (_) /  ___(_)                      \n"
                + "\\ `--.| | ___| |__  _  __| |_  \\ `--. _  __ _ _ __ ___   __ _ \n"
                + " `--. \\ |/ / | '_ \\| |/ _` | |  `--. \\ |/ _` | '_ ` _ \\ / _` |\n"
                + "/\\__/ /   <| | |_) | | (_| | | /\\__/ / | (_| | | | | | | (_| |\n"
                + "\\____/|_|\\_\\_|_.__/|_|\\__,_|_| \\____/|_|\\__, |_| |_| |_|\\__,_|\n"
                + "                                         __/ |                \n"
                + "                                        |___/                 \n";

        String horizontalLine = "_____________________________________________________________________________________";
        Scanner scanner = new Scanner(System.in);

        System.out.println(horizontalLine + "\nHello! I'm SkibidiSigma\n" + logo + "\nWhat can I do for you?\n" + horizontalLine);

        while (true) {
            String userInput = scanner.nextLine().trim();

            if ("bye".equalsIgnoreCase(userInput)) {
                System.out.println(
                        horizontalLine +
                        "\nCatch ya on the flip side, my dude! \uD83D\uDE80\uD83E\uDD2F See ya soon!\n" +
                        horizontalLine);
                break;
            }

            System.out.println(horizontalLine + "\n" + userInput + "\n" + horizontalLine);
        }

        scanner.close();
    }
}
