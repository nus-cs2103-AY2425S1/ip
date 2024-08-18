import java.util.Scanner;

public class Terminator {
    public static void main(String[] args) {
        String hline = "\t____________________________________________________________\n";
        String logo = """
                                     <((((((\\\\\\
                                     /      . }\\
                                     ;--..--._|}
                  (\\                 '--/\\--'  )
                   \\\\                | '-'  :'|
                    \\\\               . -==- .-|
                     \\\\               \\.__.'   \\--._
                     [\\\\          __.--|       //  _/'--.
                     \\ \\\\       .'-._ ('-----'/ __/      \\
                      \\ \\\\     /   __>|      | '--.       |
                       \\ \\\\   |   \\   |     /    /       /
                        \\ '\\ /     \\  |     |  _/       /
                         \\  \\       \\ |     | /        /
                          \\  \\      \\        /
                """;
        String greeting = hline +
                logo +
                "\tBooting...\n" +
                "\tDevice booted successfully.\n" +
                "\tState your request.\n" +
                hline;
        System.out.println(greeting);

        String exit = hline + "\tMission complete. Deactivating...\n" + hline;

        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        while (!userInput.equals("bye")) {
            System.out.println(hline + "\t" + userInput + "\n" + hline);
            userInput = sc.next();
        }
        System.out.println(exit);
    }
}
