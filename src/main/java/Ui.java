import java.util.Scanner;

class Ui {
    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public void printWelcomeMessage() {
        String hi = "Hello! I'm Foo\n" +
                "What can I do for you?";
        System.out.println(Parser.addHorizontalLinesAndIndentation(hi));
    }

    public void printGoodbyeMessage() {
        System.out.println(Parser
            .addHorizontalLinesAndIndentation(
                "Bye. Hope to see you again soon!"));
    }

    public void displayList() {
        String lString = "";
        int index = 1;
        for (Task task : taskList) {
            lString += String.valueOf(index) + "."  + task.toString();
            if (index == taskList.size()) {
                break;
            }
            lString += "\n";
            index++;
        }
        lString = Parser.addHorizontalLinesAndIndentation(lString);
        System.out.println(lString);
    }

    public void showLoadingError() {
        System.out.println(
            Parser.addHorizontalLinesAndIndentation(
                "Error loading data from file."));
    }

    public Command interactWithUser(String dialog) {
        Parser parser = new Parser();
        Command command = parser.parseUserInput(dialog, taskList);
        try {
            command.execute();
        } catch (FormatException e) {
            System.out.println(e.getMessage());
        } catch (NoInputException e) {
            System.out.println(e.getMessage());
        }
        return command;
    }

    public void startInteraction() {
        Scanner sc = new Scanner(System.in);
        printWelcomeMessage();

        while (true) {
            String dialog = sc.nextLine();
            Command command = interactWithUser(dialog);
            if (command instanceof ExitCommand) {
                break;
            }
        }
        sc.close();
    }

}
