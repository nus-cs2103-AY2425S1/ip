import java.util.Scanner;

public class Optimus {
    public static String linebreak = "____________________________";
    Storage storage;

    public Optimus() {
        this.greet();
        this.storage = new Storage();
    }

    private void greet() {
        System.out.println("Hello! I'm Optimus\nWhat can I do for you?");
        System.out.println(Optimus.linebreak);
    }

    public static void main(String[] args) {
        Optimus optimus = new Optimus();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean runChat = true;
            while (runChat) {
                String input = scanner.nextLine();
                try {
                    Command c = Parser.parse(input);
                    c.execute(optimus.storage);
                    runChat = c.shouldContinue();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}