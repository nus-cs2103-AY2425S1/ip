import java.util.Scanner;
public class Strand {
    private static boolean running = false;
    private static final String horizontalLine = "----------------------------------------><>";
    private static void print(String s) {
        System.out.println(s);
    }
    private static void output(String s) {
        print(s);
        System.out.println(horizontalLine);
    }
    private static void chatStart() {
        running = true;
        String nameLogo = """
                 ____ _____ ____      _    _   _ ____ \s
                / ___|_   _|  _ \\    / \\  | \\ | |  _ \\ \s
                \\___ \\ | | | |_) |  / _ \\ |  \\| | | | |\s
                 ___) || | |  _ <  / ___ \\| |\\  | |_| |\s
                |____/ |_| |_| \\_\\/_/   \\_\\_| \\_|____/\s
                """;
        output("Hello from \n" + nameLogo + "\nWhat can I do for you?");
    }
    private static void inputs(String input) {
        switch (input.toLowerCase()) {

            default : {
                output("Bye. Hope to see you again soon!");
                running = false;
                break;
            }
        }
    }
    public static void main(String[] args) {
        chatStart();
        while(running) {
            inputs("");
        }
    }
}
