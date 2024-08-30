import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private Parser parser;
    private TaskList tasks;

    public UI() {
        scanner = new Scanner(System.in);
        System.out.println("""
                Hello I'm YapMeister
                YAPYAPYAPYAP
                """);
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void process() {
        String input = "";
        boolean isRunning = true;
        while (isRunning) {
            input = scanner.nextLine();
            isRunning = parser.processInput(input);
        }
        System.out.println("Fine. Bye. Leave and never return");
    }
}
