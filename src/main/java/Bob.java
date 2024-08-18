import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        System.out.println(bot.greet());

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            switch (input) {
                case "bye":
                    System.out.print(bot.exit());
                    System.exit(0);
                case "list":
                    System.out.print(bot.list());
                    break;
                default:
                    System.out.print(bot.add(input));
                    break;
            }
        }
    }
}
