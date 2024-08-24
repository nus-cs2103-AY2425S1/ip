import java.util.Scanner;
public class Nimbus {
    private static String welcomeMessage = "";
    private static String horizontalLine = "\n-------------------------------------------------";
    private static String endingMessage = "BAIBAI! NIMBUS WEEEEEEEEE" + horizontalLine;
    public static void main(String[] args) {

        // Came up with the name and used an online ASCII art generator
        // Generator: https://patorjk.com/software/taag/#p=display&h=0&v=0&f=Ghost&t=nimbus
        String logo = "\n" +
                "     .-') _             _   .-')    .-. .-')                  .-')    \n" +
                "    ( OO ) )           ( '.( OO )_  \\  ( OO )                ( OO ).  \n" +
                ",--./ ,--,'    ,-.-')   ,--.   ,--.) ;-----.\\   ,--. ,--.   (_)---\\_) \n" +
                "|   \\ |  |\\    |  |OO)  |   `.'   |  | .-.  |   |  | |  |   /    _ |  \n" +
                "|    \\|  | )   |  |  \\  |         |  | '-' /_)  |  | | .-') \\  :` `.  \n" +
                "|  .     |/    |  |(_/  |  |'.'|  |  | .-. `.   |  |_|( OO ) '..`''.) \n" +
                "|  |\\    |    ,|  |_.'  |  |   |  |  | |  \\  |  |  | | `-' /.-._)   \\ \n" +
                "|  | \\   |   (_|  |     |  |   |  |  | '--'  / ('  '-'(_.-' \\       / \n" +
                "`--'  `--'     `--'     `--'   `--'  `------'    `-----'     `-----'  \n";

        welcomeMessage += "Hello from \n" + logo + horizontalLine + " \nHow can I help you today~ UwU" + horizontalLine;
        System.out.println(welcomeMessage);


        String userInput ="";
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Nimbus: " + endingMessage);
            } else if (userInput.equals("list")) {
                taskList.toString();
            } else {
                taskList.addTask(userInput);
            }
        }
        scanner.close();
    }
}
