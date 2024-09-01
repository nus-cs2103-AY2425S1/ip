package ui;

import exception.DukeException;
import task.KorolevList;

/**
 * Represents a command entity input by users
 */
public class KorolevCommand {
    private String[] target;
    private String input;
    private KorolevList repo;
    private final String divider = "--------------------";
    public KorolevCommand(String command, KorolevList repo) {
        this.input = command;
        this.repo = repo;
        this.target = command.split("\\s");
    }

    /**
     * Executes the respective command based on input string.
     *
     * @return whether the execution is successful or not
     */
    public String executeCommand() {

        if (input.equals("bye")) {
            repo.saveEvent();
            System.exit(0);
        } else if (input.equals("list")) {
            System.out.println(repo.displayList());
            return repo.displayList();
        } else if (target[0].equals("unmark")) {
            try {
                return repo.unmarkEvent(Integer.parseInt(target[1]) - 1);
            } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Error:" + e.getMessage());
                return "Error:" + e.getMessage();
            }
        } else if (target[0].equals("mark")) {
            try {
                return repo.markEvent(Integer.parseInt(target[1]) - 1);
            } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Error:" + e.getMessage());
                return "Error:" + e.getMessage();
            }
        } else if (target[0].equals("delete")) {
            try {
                return repo.removeEvent(Integer.parseInt(target[1]) - 1);
            } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Error:" + e.getMessage());
                return "Error:" + e.getMessage();
            }
        } else if (target[0].equals("find")) {
            String key = "";
            for (String s : this.target) {
                key = (s + " ");
            }
            return KorolevList.displayFilteredList(repo.findItem(key.strip()));
        } else {
            try {
                return repo.addEvent(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
        }
        return "";
    }
}
