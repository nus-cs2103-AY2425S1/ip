package michael;
public class Parser {
    private TaskList tasks;
    private String invalidCommand = "Invalid command entered. Please enter one of the following valid commands: "
            + "todo, deadline, event, mark, unmark, list, delete, bye, find";
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses user's input and carries out actions corresponding to their command.
     * If the command is invalid or used incorrectly, a  MichaelException is thrown.
     *
     * @param input User input to chatbot that needs to be processed.
     * @return Feedback to user on operation carried out.
     * @throws MichaelException If an invalid command is entered or an existing command is used incorrectly.
     */
    public String parse(String input) throws MichaelException {
        if (input.startsWith("bye")) {
            ByeCommand bc = new ByeCommand();
            return bc.feedback();
        }
        if (input.startsWith("mark")) { // mark a task as done
            MarkCommand mc = new MarkCommand(tasks);
            mc.check(input);
            return mc.feedback();
        } else if (input.equals("list")) { // list user inputs thus far
            ListCommand lc = new ListCommand(tasks);
            return lc.feedback();
        } else if (input.startsWith("unmark")) { // unmark a task
            UnmarkCommand uc = new UnmarkCommand(tasks);
            uc.check(input);
            return uc.feedback();
        } else if (input.startsWith("todo")) { // task of type todo to be added
            ToDoCommand tc = new ToDoCommand(tasks);
            tc.check(input);
            return tc.feedback();
        } else if (input.startsWith("deadline")) { // task of type deadline
            DeadlineCommand dc = new DeadlineCommand(tasks);
            dc.check(input);
            return dc.feedback();
        } else if (input.startsWith("event")) {
            EventCommand ec = new EventCommand(tasks);
            ec.check(input);
            return ec.feedback();
        } else if (input.startsWith("delete")) {
            DeleteCommand dc = new DeleteCommand(tasks);
            dc.check(input);
            return dc.feedback();
        } else if (input.startsWith("find")) {
            FindCommand fc = new FindCommand(tasks);
            fc.check(input);
            return fc.feedback();
        } else { // invalid command
            throw new MichaelException(invalidCommand);
        }
    }
}
