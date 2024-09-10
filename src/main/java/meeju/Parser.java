package meeju;

/**
 * The Parser class is responsible for interpreting and processing user instructions.
 * It interacts with the TaskList object to perform various operations like creating, updating,
 * finding and deleting tasks.
 */
public class Parser {

    /**
     * Parses the given user instruction and performs the corresponding action on the <code>TaskList</code>.
     *
     * @param taskList    The task list on which the operations are performed.
     * @param instruction The user instruction to be parsed and executed.
     * @return A String representing the result of the parsing
     */
    public String parse(TaskList taskList, String instruction) {
        assert taskList != null : "TaskList object is null";
        assert instruction != null : "instruction is null";

        if (instruction.trim().isEmpty()) {
            return "Empty Command!";
        } else if (instruction.equals("bye")) {
            return " Bye. Hope to see you again soon!";
        } else if (instruction.equalsIgnoreCase("hi")) {
            return "Meow! Hello There! How can i help you?";
        } else if (instruction.equals("list")) {
            return taskList.printList();
        } else if (instruction.startsWith("find ")) {
            try {
                return taskList.findTask(instruction.substring(5));
            } catch (MeejuException e) {
                return e.getMessage();
            }
        } else if (instruction.startsWith("mark ")) {
            try {
                return taskList.markTask(instruction.substring(5));
            } catch (MeejuException e) {
                return e.getMessage();
            }
        } else if (instruction.startsWith("unmark ")) {
            try {
                return taskList.unmarkTask(instruction.substring(7));
            } catch (MeejuException e) {
                return e.getMessage();
            }
        } else if (instruction.startsWith("delete ")) {
            try {
                return taskList.deleteTask(instruction.substring(7));
            } catch (MeejuException e) {
                return e.getMessage();
            }
        } else if (instruction.startsWith("todo ")) {
            try {
                return taskList.addTodoTask(instruction.substring(5));
            } catch (MeejuException e) {
                return e.getMessage();
            }
        } else if (instruction.startsWith("deadline ")) {
            try {
                return taskList.addDeadlineTask(instruction.substring(9));
            } catch (MeejuException e) {
                return e.getMessage();
            }
        } else if (instruction.startsWith("event ")) {
            try {
                return taskList.addEventTask(instruction.substring(6));
            } catch (MeejuException e) {
                return e.getMessage();
            }
        } else {
            return "I'm sorry, I did not understand that =^..^=";
        }
    }
}
