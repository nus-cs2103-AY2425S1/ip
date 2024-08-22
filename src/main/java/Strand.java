import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Strand {
    private static boolean running = false;
    private static final String horizontalLine = "----------------------------------------><>";
    private static final List<String> strandList = new ArrayList<>();
    private static void print(String s) {
        System.out.println(s.indent(4));
    }
    private static void output(String s) {
        System.out.println(horizontalLine.indent(4));
        print(s);
        System.out.println(horizontalLine.indent(4));
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

    private static void listAll() {
        output(
                strandList.stream()
                        .map((x) -> (strandList.indexOf(x)+1) + ". " + x + "\n")
                        .reduce((a, b) -> a + b).orElse("")
        );
    }

    private static void inputs(String input) {
        switch (input.toLowerCase()) {
            case "bye": {
                output("Bye. Hope to see you again soon!");
                running = false;
                break;
            }

            case "list": {
                listAll();
                break;
            }

            default : {
                output("added: " + input);
                strandList.add(input);
            }
        }
    }
    public static void main(String[] args) {
        chatStart();
        while(running) {
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            inputs(userInput);
        }
    }
}