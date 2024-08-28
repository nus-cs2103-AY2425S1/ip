public class Parser {
    public static Command parse(String command, TaskList tasks) {
        String firstWord;
        String restOfString = "";

        if (command.contains(" ")) {
            firstWord = command.substring(0, command.indexOf(" "));
            restOfString = command.split(" ", 2)[1];
        } else {
            firstWord = command;
        }

        try {
            if (command.equals("bye")) {
                return new ExitCommand(tasks);
            } else if (command.equals("list")) {
                return new ListCommand(tasks);
            } else if (firstWord.equals("mark")) {
                return new MarkCommand(tasks, restOfString);
            } else if (firstWord.equals("unmark")) {
                return new UnmarkCommand(tasks, restOfString);
            } else if (firstWord.equals("delete")) {
                return new DeleteCommand(tasks, restOfString);
            } else if (firstWord.equals("todo")) {
                return new AddCommand(tasks, restOfString, Bruno.TaskType.TODO);
            } else if (firstWord.equals("deadline")) {
                return new AddCommand(tasks, restOfString, Bruno.TaskType.DEADLINE);
            } else if (firstWord.equals("event")) {
                return new AddCommand(tasks, restOfString, Bruno.TaskType.EVENT);
            } else {
                throw new UnknownCommandException();
            }
        } catch (BrunoException e) {
            Ui.printErrorMessage(e);
            return null;
        }
    }
}
