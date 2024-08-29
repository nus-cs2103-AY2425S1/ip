import java.util.Scanner;

public class Tecna {
    private Storage storage;
    private TaskList taskList;
    private CommandScanner commandScanner;
    /**
     * A constructor of Tecna chatbot
     */
    public Tecna() {
        this.taskList = new TaskList();
        this.commandScanner = new CommandScanner();
    }

    public Tecna(String taskData) {
        this.storage = new Storage(taskData);
        this.taskList = new TaskList(storage.load());
        this.commandScanner = new CommandScanner();
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
        CommandType command = this.commandScanner.getRequest();

        while (!command.equals(CommandType.BYE)) {
            System.out.println("----------------------------------------------");
            switch (command) {
            case LIST:
                this.taskList.listItems();
                break;
            case MARK:
                int index = commandScanner.markIndex();
                taskList.mark(index);
                System.out.println("Nice job! I've mark this as done. You deserve a short break <3");
                System.out.println(taskList.getTask(index));
                break;
            case UNMARK:
                index = commandScanner.markIndex();
                taskList.unmark(index);
                System.out.println("I've mark this as undone. Keep going, my friend!");
                System.out.println(taskList.getTask(index));
                break;
            case DELETE:
                index = commandScanner.markIndex();
                this.taskList.deleteItem(index);
                break;
            case TODO:
            case EVENT:
            case DEADLINE:
            case TODO_WRONG_FORMAT:
                try {
                    this.taskList.addItem(commandScanner.getInput());
                } catch (InvalidRequestException ive) {
                    System.out.println("Oops! Your request sounds strange for me. Please enter a valid request ^^");
                } catch (TodoWrongFormatException tde) {
                    System.out.println(tde.getMessage());
                }
                break;
            }
            System.out.println("----------------------------------------------");
            command = this.commandScanner.getRequest();
        }
        this.exitChatBot();
        commandScanner.close();

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
