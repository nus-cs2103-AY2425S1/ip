import java.util.Scanner;

public class Terminator {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________\n";
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
        String greeting = horizontalLine +
                logo +
                "Booting...\n" +
                "Device booted successfully.\n" +
                "State your request.\n" +
                horizontalLine;
        System.out.println(greeting);

        String exit = "Mission complete. Shutting down.\n" + horizontalLine;
        System.out.println(exit);
    }
}
