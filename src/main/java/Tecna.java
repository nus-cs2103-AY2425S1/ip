import java.util.Scanner;

public class Tecna {
    private Storage storage;
    private TaskList taskList;
    /**
     * A constructor of Tecna chatbot
     */
    public Tecna() {
        this.taskList = new TaskList();
    }

    public Tecna(String taskData) {
        this.storage = new Storage(taskData);
        this.taskList = new TaskList(storage.load());
    }

    /**
     * Exits the chatbot by printing the goodbye lines
     */
    public void exitChatBot() {
        storage.setFilePath("src/main/data/tecna1.json");
        storage.save(this.taskList);
        System.out.println("----------------------------------------------");
        System.out.println("Pleased to help you! See you again ^_^");
        System.out.println("----------------------------------------------");
    }

    /**
     * Repeats the input entered by the user
     */
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

    /**
     * Receives requests entered by the user.
     * Accepts string input and processes accordingly.
     */
    public void getRequest() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] input_words = input.split(" ");
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("----------------------------------------------");
            if (input_words[0].equalsIgnoreCase("list")) {
                this.taskList.listItems();
            } else if (input_words[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(input_words[1]);
                taskList.mark(index - 1);
                System.out.println("Nice job! I've mark this as done. You deserve a short break <3");
                System.out.println(taskList.getTask(index - 1));
            } else if (input_words[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(input_words[1]);
                taskList.unmark(index - 1);
                System.out.println("I've mark this as undone. Keep going, my friend!");
                System.out.println(taskList.getTask(index - 1));
            } else if (input_words[0].equalsIgnoreCase("delete")) {
                int index = Integer.parseInt(input_words[1]);
                this.taskList.deleteItem(index - 1);
            } else {
                try {
                    this.taskList.addItem(input);
                } catch (InvalidRequestException ive) {
                    System.out.println("Oops! Your request sounds strange for me. Please enter a valid request ^^");
                } catch (TodoWrongFormatException tde) {
                    System.out.println(tde.getMessage());
                }
            }

            System.out.println("----------------------------------------------");

            input = sc.nextLine();
            input_words = input.split(" ");
        }
        this.exitChatBot();
        sc.close();
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
        Tecna tecna = new Tecna("src/main/data/tecna.json");
        tecna.getRequest();
        // tecna.echo();
    }
}
