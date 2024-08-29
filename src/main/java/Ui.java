import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    protected String readCommand() throws TheBotFatherException {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            throw new TheBotFatherException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n" +
                    "\tUse \"bye\" if you want to exit the program");
        }
    }

    private void print(String str) {
        System.out.println("    " + str);
    }

    protected void printGreeting() {
        this.printLine();
        print("Hello! I'm The BotFather");
        print("I’m gonna make you an offer you can’t refuse.");
        this.printHorse();
        this.printLine();
    }

    protected void printLine() {
        print("—————————————————————————————————————————————————————————————————");
    }

    protected void printAddedTodo(Task task) {
        this.print("Leave the gun, take the cannoli.");
        this.print(task.toString());
    }

    protected void printAddedDeadline(Task task) {
        this.print("Look how they massacred my boy.");
        this.print(task.toString());
    }

    protected void printAddedEvent(Task task) {
        this.print("That's my family, Kay, that's not me.");
        this.print(task.toString());
    }

    protected void printCount() {
        this.print("You have " + TaskList.COUNT + " tasks left to do... think it's doable??");
    }

    protected void printTaskList(String taskListDesc) {
        String[] lines = taskListDesc.split("\n");
        for (String line : lines) {
            this.print(line);
        }
        this.printCount();
    }

    protected void printMarked(String taskDesc) {
        this.print("It will be done");
        this.print(taskDesc);
    }

    protected void printUnmarked(String taskDesc) {
        this.print("A man who doesn't spend time with his family can never be a real man.");
        this.print(taskDesc);
    }

    protected void printDeleted(Task task) {
        this.print("You are sure you wanted to do that right? Anyways... too late");
        this.print(task.toString());
    }

    protected void printGoodBye() {
        print("What are you worried about, if I wanted to kill you, you'd be dead already.");
        sc.close();
    }

    protected void showLoadingError() {
        print("There seems to have been some error loading the files, check properly");
    }

    protected void showError(String message) {
        print(message);
    }

    private void printHorse() {
        System.out.println("\t                                 |\\    /|");
        System.out.println("\t                              ___| \\,,/_/");
        System.out.println("\t                           ---__/ \\/    \\");
        System.out.println("\t                          __--/     (D)  \\");
        System.out.println("\t                          _ -/    (_      \\");
        System.out.println("\t                         // /       \\_ /  -\\");
        System.out.println("\t   __-------_____--___--/           / \\_ O o)");
        System.out.println("\t  /                                 /   \\__/");
        System.out.println("\t /                                 /");
        System.out.println("\t||          )                   \\_/\\");
        System.out.println("\t||         /              _      /  |");
        System.out.println("\t| |      /--______      ___\\    /\\  :");
        System.out.println("\t| /   __-  - _/   ------    |  |   \\ \\");
        System.out.println("\t |   -  -   /                | |     \\ )");
        System.out.println("\t |  |   -  |                 | )     | |");
        System.out.println("\t  | |    | |                 | |    | |");
        System.out.println("\t  | |    < |                 | |   |_/");
        System.out.println("\t  < |    /__\\                <  \\");
        System.out.println("\t  /__\\                       /___\\");
    }

}
