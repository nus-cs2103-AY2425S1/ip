import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DGPT {

    // List to store texts
    private List<String> list;

    public DGPT() {
        list = new ArrayList<>();
    }

    private void addToList(String text) {
        this.list.add(text);
        System.out.println("-----------------------");
        System.out.println("DGPT> added :" + text);
        System.out.println("-----------------------");
    }

    private void showList() {
        int numOfItems = this.list.size();
        System.out.println("-----------------------");
        System.out.println("DGPT>");
        for (int i = 1; i <= numOfItems; i++) {
            System.out.println(i + ". " + this.list.get(i - 1));
        }
        System.out.println("-----------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DGPT dgpt = new DGPT();

        // Starting Message
        System.out.println("-----------------------");
        System.out.println("DGPT> Hello! I'm DGPT");
        System.out.println("DGPT> What can I do for you?");
        System.out.println("-----------------------");

        String input;
        do {
            System.out.print("User> ");
            input = scanner.nextLine();

            if (input.equals("list")) {
                dgpt.showList();
            } else if (!input.equals("bye")) {
                dgpt.addToList(input);
            }
        } while (!input.equals("bye"));

        scanner.close();

        // Closing Message
        System.out.println("-----------------------");
        System.out.println("DGPT> Bye. Hope to see you again soon!");
        System.out.println("-----------------------");
    }
}
