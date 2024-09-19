package michael;
public class Parser {
    private TaskList tasks;
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
        if (input.startsWith("mark")) { // mark a task as done
            if (input.length() < 6) { // no number given to mark
                throw new MichaelException("Enter integer position of task on list to mark. "
                        + "Use command list to check the position of the required task.");
            }

            int position = Integer.valueOf(input.substring(5)) - 1;
            MarkCommand mc = new MarkCommand(tasks, position);
            mc.mark();
            return mc.feedback();
        } else if (input.equals("list")) { // list user inputs thus far
            ListCommand lc = new ListCommand(tasks);
            return lc.feedback();
        } else if (input.startsWith("unmark")) { // unmark a task
            if (input.length() < 8) { // no number given to unmark
                throw new MichaelException("Enter integer position of task on list to unmark. "
                        + "Use command list to check the position of the required task.");
            }

            int position = Integer.valueOf(input.substring(7)) - 1;
            UnmarkCommand uc = new UnmarkCommand(tasks, position);
            uc.unmark();
            return uc.feedback();
        } else if (input.startsWith("todo")) { // task of type todo to be added
            if (input.length() < 6) { // no task given
                throw new MichaelException("Enter a task to be done.");
            }

            ToDoCommand tc = new ToDoCommand(tasks);
            String task = input.substring(5);
            tc.add(task);
            return tc.feedback();
        } else if (input.startsWith("deadline")) { // task of type deadline
            if (input.length() < 10) { // no deadline task given
                throw new MichaelException("Enter a valid task with a deadline.");
            }

            DeadlineCommand dc = new DeadlineCommand(tasks);
            String task = input.substring(9);
            dc.add(task);
            return dc.feedback();
        } else if (input.startsWith("event")) {
            if (input.length() < 7) { // no event given
                throw new MichaelException("Enter a valid event.");
            }

            EventCommand ec = new EventCommand(tasks);
            String task = input.substring(6);
            ec.add(task);
            return ec.feedback();
        } else if (input.startsWith("delete")) {
            if (input.length() < 8) { // no number given to delete
                throw new MichaelException("Enter integer position of task on list to delete. "
                        + "Use command list to check the position of the required task.");
            }

            int position = Integer.valueOf(input.substring(7)) - 1;
            DeleteCommand dc = new DeleteCommand(tasks, position);
            dc.delete();
            return dc.feedback();
        } else if (input.startsWith("find")) {
            if (input.length() < 6) { // no string given to find
                throw new MichaelException("Enter valid string to find matching tasks.");
            }

            String keyword = input.substring(5);
            FindCommand fc = new FindCommand(tasks, keyword);
            return fc.feedback();
        } else { // invalid command
            throw new MichaelException("Invalid command entered. Please enter one of the following valid commands: "
                    + "todo, deadline, event, mark, unmark, list, delete, bye, find");
        }
    }
}
