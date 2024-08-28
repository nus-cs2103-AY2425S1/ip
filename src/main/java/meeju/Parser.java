package meeju;

/**
 * The Parser class is responsible for interpreting and processing user instructions.
 * It interacts with the TaskList object to perform various operations like creating, updating, finding and deleting tasks.
 */
public class Parser {

    /**
     * Parses the given user instruction and performs the corresponding action on the <code>TaskList</code>.
     *
     * @param taskList    The task list on which the operations are performed.
     * @param instruction The user instruction to be parsed and executed.
     * @return An integer representing the result of the parsing:
     *         <ul>
     *             <li>{@code -1} if the instruction is "bye", signaling the program to exit.</li>
     *             <li>{@code 0} if the instruction is successfully executed.</li>
     *             <li>{@code 5} if the instruction is not recognized.</li>
     *         </ul>
     */
    public int parse(TaskList taskList, String instruction) {
        if (instruction.equals("bye")) {
            return -1;
        } else if (instruction.equals("list")) {
            taskList.printList();
        } else if (instruction.startsWith("find ")) {
            try {
                taskList.findTask(instruction.substring(5));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        } else if (instruction.startsWith("mark ")) {
            try {
                taskList.markTask(instruction.substring(5));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        } else if (instruction.startsWith("unmark ")) {
            try {
                taskList.unmarkTask(instruction.substring(7));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        } else if (instruction.startsWith("delete ")) {
            try {
                taskList.deleteTask(instruction.substring(7));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        } else if (instruction.startsWith("todo ")) {
            try {
                taskList.addTodoTask(instruction.substring(5));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        }  else if (instruction.startsWith("deadline ")) {
            try {
                taskList.addDeadlineTask(instruction.substring(9));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        }  else if (instruction.startsWith("event ")) {
            try {
                taskList.addEventTask(instruction.substring(6));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("I'm sorry, I did not understand that =^..^=");
            return 5;
        }
        return 0;
    }
}
