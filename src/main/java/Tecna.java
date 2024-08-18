import java.util.Scanner;

public class Tecna {
    private String[] todo;
    private int todoSize;

    public Tecna() {
        this.todo = new String[100];
        this.todoSize = 0;
    }

    public void exitChatBot() {
        System.out.println("----------------------------------------------");
        System.out.println("Pleased to help you! See you again ^_^");
        System.out.println("----------------------------------------------");
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        if (input.equalsIgnoreCase("bye")) {
            this.exitChatBot();
        } else {
            System.out.println("----------------------------------------------");
            System.out.println(input);
            System.out.println("----------------------------------------------");
        }
        sc.close();
    }

    public void getRequest() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("----------------------------------------------");
            if (input.equalsIgnoreCase("list")) {
                this.listItems();
            } else {
                this.addItem(input);
            }

            System.out.println("----------------------------------------------");

            input = sc.nextLine();
        }
        this.exitChatBot();
        sc.close();
    }

    public void addItem(String item) {
        this.todo[this.todoSize] = item;
        ++this.todoSize;
        System.out.println("added: " + item);
    }

    public void listItems() {
        for (int i = 0; i < this.todoSize; ++i) {
            System.out.println(i + 1 + ". " + this.todo[i]);
        }
    }

    public static void main(String[] args) {
        String logo = " **          **\n" +
                      "*  *        *  *\n" +
                      "*   *      *   *\n" +
                      "*    *    *    *\n" +
                      "*     *  *     *\n" +
                      " *     **     *\n" +
                      "  *    **    *\n" +
                      "   *   **   *\n" +
                      "    *  **  *\n" +
                      "     ******\n" +
                      "      ****\n" +
                      "     * ** *\n" +
                      "    *  **  *\n" +
                      "    ***  ***\n";
        System.out.println(logo);
        System.out.println("I'm Tecna!\nHow can I help you?");
        System.out.println("----------------------------------------------");
        Tecna tecna = new Tecna();
        tecna.getRequest();
    }
}
