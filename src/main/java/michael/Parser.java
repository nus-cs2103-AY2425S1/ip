package michael;

import java.io.IOException;

/**
 * Handles user input and allows chatbot to execute accordingly
 */
public class Parser {
    private TaskList tasks;
    private Storage storage;
    private String invalidCommand = "Invalid command entered. Please enter one of the following valid commands:\n"
            + "todo [task]\n" + "deadline [task] /by [YYYY-MM-DD]\n"
            + "event [task] /from [start day & time] /to [end time]\n" + "mark [number]\n" + "unmark [number]\n"
            + "list\n" + "delete [number]\n" +  "bye\n" +  "find [keyword]\n" + "sort\n";
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Parses user's input and carries out actions corresponding to their command.
     * If the command is invalid or used incorrectly, a  MichaelException is thrown.
     *
     * @param input User input to chatbot that needs to be processed.
     * @return Feedback to user on operation carried out.
     * @throws MichaelException If an invalid command is entered or an existing command is used incorrectly.
     */

    // Solution of abstracting commands out inspired from reviewing peer PRs (@kahsuann, @justsparsh)
    public String parse(String input) throws MichaelException {
        if (input.startsWith("bye")) {
            ByeCommand bc = new ByeCommand();
            return bc.feedback();
        } else if (input.startsWith("mark")) { // mark a task as done
            MarkCommand mc = new MarkCommand(tasks);
            mc.check(input);
            saveToFile();
            return mc.feedback();
        } else if (input.equals("list")) { // list user inputs thus far
            ListCommand lc = new ListCommand(tasks);
            return lc.feedback();
        } else if (input.startsWith("unmark")) { // unmark a task
            UnmarkCommand uc = new UnmarkCommand(tasks);
            uc.check(input);
            saveToFile();
            return uc.feedback();
        } else if (input.startsWith("todo")) { // task of type todo to be added
            ToDoCommand tc = new ToDoCommand(tasks);
            tc.check(input);
            saveToFile();
            return tc.feedback();
        } else if (input.startsWith("deadline")) { // task of type deadline
            DeadlineCommand dc = new DeadlineCommand(tasks);
            dc.check(input);
            saveToFile();
            return dc.feedback();
        } else if (input.startsWith("event")) {
            EventCommand ec = new EventCommand(tasks);
            ec.check(input);
            saveToFile();
            return ec.feedback();
        } else if (input.startsWith("delete")) {
            DeleteCommand dc = new DeleteCommand(tasks);
            dc.check(input);
            saveToFile();
            return dc.feedback();
        } else if (input.startsWith("find")) {
            FindCommand fc = new FindCommand(tasks);
            fc.check(input);
            return fc.feedback();
        } else if (input.startsWith("sort")) {
            SortCommand sc = new SortCommand(tasks);
            sc.sort();
            return sc.feedback();
        } else { // invalid command
            throw new MichaelException(invalidCommand);
        }
    }

    private void saveToFile() throws MichaelException {
        try {
            this.storage.save(tasks);
        } catch (IOException e) {
            throw new MichaelException("Can't save tasks!");
        }
    }
}
