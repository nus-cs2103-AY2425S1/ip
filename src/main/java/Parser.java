public class Parser {

    public static Command parse(String input) throws IllegalArgumentException, OntosException{
        if (input.equalsIgnoreCase("bye")) {
            return new Command.ByeCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new Command.ListCommand();
        } else if (input.startsWith("mark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]);
                return new Command.MarkCommand(true, index);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new OntosException("The correct usage of 'mark' is: mark n, where n is a natural number (ℕ).");
            }
        } else if (input.startsWith("unmark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]);
                return new Command.MarkCommand(false, index);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new OntosException("The correct usage of 'unmark' is:"
                        + " unmark n, where n is a natural number (ℕ).");
            }
        } else if (input.startsWith("delete")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]);
                return new Command.DeleteCommand(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new OntosException("The correct usage of 'delete' is:"
                        + " delete n, where n is a natural number (ℕ).");
            }
        } else if (input.startsWith("todo")) {
            Task toDo = null;
            try {
                toDo = Task.toDo(input.split(" ", 2)[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new OntosException(" OOPS!!! The description of a todo cannot be empty.");
            }
            return new Command.AddTaskCommand(toDo);
        } else if (input.startsWith("deadline")) {
            int startOfDesc = input.indexOf(" ");
            int endOfDesc = input.indexOf(" /by");

            if (endOfDesc == -1) {
                throw new OntosException(" OOPS!!! A deadline task requires a deadline.");
            }

            try {
                String description = input.substring(startOfDesc, endOfDesc).trim();
                String dueBy = input.substring(endOfDesc + 4).trim();

                Task deadline = Task.deadline(description, dueBy);
                return new Command.AddTaskCommand(deadline);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new OntosException(" OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (input.startsWith("event")) {
            int startOfDesc = input.indexOf(" ");
            int endOfDesc = input.indexOf(" /from");
            int endOfFrom = input.indexOf(" /to");

            if (endOfDesc == -1 || endOfFrom == -1) {
                throw new OntosException(" OOPS!!! An event task requires a start and end time.");
            }

            try {
                String description = input.substring(startOfDesc, endOfDesc).trim();
                String start = input.substring(endOfDesc + 6, endOfFrom).trim();
                String end = input.substring(endOfFrom + 4).trim();

                Task event = Task.event(description, start, end);
                return new Command.AddTaskCommand(event);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new OntosException(" OOPS!!! The description of an event cannot be empty.");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
