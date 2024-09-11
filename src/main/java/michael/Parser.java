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
        if (input.length() >= 4 && input.substring(0, 4).equals("mark")) { // mark a task as done
            if (input.length() < 6) { // no number given to mark
                throw new MichaelException("Enter integer position of task on list to mark. "
                        + "Use command list to check the position of the required task.");
            }

            int index = Integer.valueOf(input.substring(5));
            Task target = tasks.get(index - 1);
            target.doTask();
            return "Nice! I've marked this task as done:\n" + "  " + target;
        } else if (input.equals("list")) { // list user inputs thus far
            String list = "Here are the tasks in your list:\n";

            for (int i = 0; i < tasks.size(); i++) {
                String elem = String.valueOf(i + 1) + ". " + tasks.get(i) + "\n";
                list = list.concat(elem);
            }

            return list.substring(0, list.length() - 1); // substring to remove last line break
        } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) { // unmark a task
            if (input.length() < 8) { // no number given to unmark
                throw new MichaelException("Enter integer position of task on list to unmark. "
                        + "Use command list to check the position of the required task.");
            }

            int index = Integer.valueOf(input.substring(7));
            Task target = tasks.get(index - 1);
            target.undoTask();
            return "OK, I've marked this task as not done yet:\n" + "  " + target;
        } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) { // task of type todo to be added
            if (input.length() < 6) { // no task given
                throw new MichaelException("Enter a task to be done.");
            }

            ToDo curr = new ToDo(input.substring(5));
            tasks.add(curr);
            String message = "Got it. I've added this task:\n" + "  " + curr.toString() + "\n"
                    + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
            return message;
        } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) { // task of type deadline
            if (input.length() < 10) { // no deadline task given
                throw new MichaelException("Enter a valid task with a deadline.");
            }

            String task = input.substring(9);
            String[] parts = task.split("/");
            for (int i = 0; i < parts.length - 1; i++) {
                String curr = parts[i];
                parts[i] = curr.substring(0, curr.length() - 1);
            }

            String deadline = parts[1].substring(3);
            if (deadline.split("-").length < 3) { // invalid format of date
                throw new MichaelException("Deadline should be in format YYYY-MM-DD");
            }

            Deadline curr = new Deadline(parts[0], deadline);
            tasks.add(curr);
            String message = "Got it. I've added this task:\n" + "  " + curr.toString() + "\n"
                    + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
            return message;
        } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
            if (input.length() < 7) { // no event given
                throw new MichaelException("Enter a valid event.");
            }

            String task = input.substring(6);
            String[] parts = task.split("/");
            for (int i = 0; i < parts.length - 1; i++) {
                String curr = parts[i];
                parts[i] = curr.substring(0, curr.length() - 1);
            }

            Event curr = new Event(parts[0], parts[1].substring(5), parts[2].substring(3));
            tasks.add(curr);
            String message = "Got it. I've added this task:\n" + "  " + curr.toString() + "\n"
                    + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
            return message;
        } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
            if (input.length() < 8) { // no number given to delete
                throw new MichaelException("Enter integer position of task on list to delete. "
                        + "Use command list to check the position of the required task.");
            }

            int index = Integer.valueOf(input.substring(7));
            Task target = tasks.get(index - 1);
            tasks.delete(index - 1);
            return "Noted. I've removed this task:\n" + "  " + target + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
        } else if (input.startsWith("find")) {
            if (input.length() < 6) { // no string given to find
                throw new MichaelException("Enter valid string to find matching tasks.");
            }

            String keyword = input.substring(5);
            TaskList matchingTasks = new TaskList();

            // Iterate through tasks to check which tasks contain the keyword
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t.getTaskName().contains(keyword)) {
                    matchingTasks.add(t);
                }
            }

            String list = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                String elem = String.valueOf(i + 1) + ". " + matchingTasks.get(i) + "\n";
                list = list.concat(elem);
            }
            return list.substring(0, list.length() - 1); // substring to remove last line break
        } else { // invalid command
            throw new MichaelException("Invalid command entered. Please enter one of the following valid commands: "
                    + "todo, deadline, event, mark, unmark, list, delete, bye, find");
        }
    }
}
