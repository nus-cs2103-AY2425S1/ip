import java.util.Scanner;
public class Ui {
    private Scanner scanner;
    private static String baoHappy =
            "     ___\n"
                    + "   /     \\\n"
                    + "  /       \\\n"
                    + " |  ^   ^  |\n"
                    + " |    3    |\n"
                    + " \\________/\n";

    private static String baoSad =
            "     ___\n"
                    + "   /     \\\n"
                    + "  /       \\\n"
                    + " |  T   T  |\n"
                    + " |    ^    |\n"
                    + " \\________/\n";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String command() {
        return scanner.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(baoHappy);
        System.out.println("Bao says hello! Bao's name is Bao but you can call me Bao");
        System.out.println("Bao is ready for instructions");
        System.out.println("____________________________________________________________");
    }
}
