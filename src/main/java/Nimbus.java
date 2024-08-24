public class Nimbus {
    public static String welcomeMessage = "";
    public static String horizontalLine = "\n-------------------------------------------------";
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

        welcomeMessage += "Hello from \n" + logo;
        System.out.println(welcomeMessage + horizontalLine);
        System.out.println("How can I help you today~ UwU" + horizontalLine);
        System.out.println("BAIBAI! NIMBUS WEEEEEEEEE" + horizontalLine);
    }
}
