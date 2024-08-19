import java.util.Objects;
import java.util.Scanner;
public class Optimus {
    boolean isLive;
    String[] storage;
    Integer numOfTextsStored;
    static String linebreak = "____________________________";

    public Optimus() {
        this.isLive = true;
        this.storage = new String[100];
        this.numOfTextsStored = 0;
        this.greet();
    }

    public boolean getStatus() {
        return this.isLive;
    }

    public void addToStorage(String input) {
        this.storage[numOfTextsStored] = input;
        this.numOfTextsStored += 1;
    }

    public void printAllTexts() {
        for (int i = 1; i <= this.numOfTextsStored; i++){
            String out = String.format("%d. %s", i, this.storage[i-1]);
            System.out.println(out);
        }
    }

//    public void echo(String input) {
//        System.out.println(input);
//        System.out.println(Optimus.linebreak);
//    }

    private void greet() {
        System.out.println("Hello! I'm Optimus\nWhat can I do for you?");
        System.out.println(Optimus.linebreak);
    }

    private void leave() {
        this.isLive = false;
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Optimus.linebreak);
    }

    public static void main(String[] args) {
        Optimus optimus = new Optimus();
        Scanner scanner = new Scanner(System.in);
        
        while (optimus.getStatus()) {
            String input = scanner.nextLine();

            if (Objects.equals(input, "bye")) {
                optimus.leave();
            } else if ( Objects.equals(input, "list")) {
                optimus.printAllTexts();
            } else {
                optimus.addToStorage(input);
                System.out.println("added: " + input);
            }

        }
    }
}
