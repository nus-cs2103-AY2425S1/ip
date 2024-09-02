package chatBot.bot;

/** Bword is a chatbot that helps with planning tasks
 *
 */

public class Bword {
    private static final String FILELOCATION = "./data/bword.txt";
    //public static final String HLINE = "____________________________________________________________\n";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    Bword(String s) {
        this.ui = new Ui();
        this.storage = new Storage(s);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (Exception e) {
                this.ui.showError(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Bword bot = new Bword(FILELOCATION);
        bot.run();

        /*
        TaskHandler th = new TaskHandler();
        File file;
        try {
            file = new File(FILELOCATION);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                // System.out.println(s);
                th.addPastTask(s);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return;
        }
        */
        // Scanner sc = new Scanner(System.in);

        /*
        enum States {to_loop, to_exit, to_list}
        States currentState = States.to_loop;

        while (currentState != States.to_exit) {
            String command = sc.next();
            String s = sc.nextLine();
            System.out.print(HLINE);
            String tmp = s.strip();
            if (command.equals("bye")) {
                break;
            }
            th.handleCommand(command, s);
            System.out.print(HLINE);
        }

        file.delete();
        th.writeToFile(FILELOCATION);

        System.out.println(
                " Bye. Hope to see you again soon!\n" +
                HLINE);
         */
    }
}
