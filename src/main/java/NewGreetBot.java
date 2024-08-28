import java.io.IOException;

public class NewGreetBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isRunning;

    public NewGreetBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isRunning = true;
        try {
            tasks = new TaskList(storage.load());
            //tasks.printTasks();
            storage.saveData(tasks);
        } finally {
            /*catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }*/
        }
    }

    // run and getResponse method is adapted from https://github.com/david-eom/CS2103T-IP/blob/master/src/main/java/duke/Duke.java
    public void run() {
        System.out.println(this.ui.greetUser());


        while (this.isRunning) {
            String input = this.ui.readInput().strip();
            System.out.println(input);
            //Parser.parseCommand(input);
            System.out.println(this.getResponse(input));
        }

    }

    public String getResponse(String input) {
        String[] segment = Parser.parseCommand(input);
        String keyword = segment[0];
        if (keyword.equals("BYE")) {
            isRunning = false;
            ui.closeInput();
            storage.saveData(tasks);
            return ui.farewellUser();
        } else if (keyword.equals("LIST")) {
            return ui.showList(this.tasks);
        } else if (keyword.equals("MARK")) {
            return this.markAsDone(Parser.parseMarkUnmark(segment[1]) - 1);
        } else if (keyword.equals("UNMARK")) {
            return this.markAsNotDone(Parser.parseMarkUnmark(segment[1]) - 1);
        } /*else if (keyword.equals("TODO")) {
            return ui.addTodo()
        } else if (keyword.equals("EVENT")) {
            return ui.addEvent()
        } else if (keyword.equals("DEADLINE")) {
            return ui.addDeadline()
        } else if (keyword.equals("DELETE")) {
            return ui.delete()
        }*/ else {
            return "";
        }
    }

    private String markAsDone(int position) {
        this.tasks.get(position).mark();
        return ui.showMarked(tasks.get(position), tasks.getLength());
    }

    private String markAsNotDone(int position) {
        this.tasks.get(position).unmark();
        return ui.showUnmarked(tasks.get(position), tasks.getLength());
    }

    public static void main(String[] args) {
        new NewGreetBot("data/greetbot.txt").run();
    }
}
