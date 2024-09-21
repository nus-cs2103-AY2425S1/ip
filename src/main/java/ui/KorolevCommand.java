package ui;

import exception.DukeException;
import task.KorolevList;

/**
 * Represents a command entity input by users.
 */
public class KorolevCommand {
    private String[] target;
    private String input;
    private KorolevList repo;

    public KorolevCommand(String command, KorolevList repo) {
        this.input = command;
        this.repo = repo;
        this.target = command.split("\\s");
    }

    private String handleDisplayList() {
        System.out.println(repo.displayList());
        return repo.displayList();
    }

    private String handleUnmark() {
        try {
            return repo.unmarkEvent(Integer.parseInt(target[1]) - 1);
        } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error:" + e.getMessage());
            return "Error:" + e.getMessage();
        }
    }

    private String handleMark() {
        try {
            return repo.markEvent(Integer.parseInt(target[1]) - 1);
        } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error:" + e.getMessage());
            return "Error:" + e.getMessage();
        }
    }

    private String handleDelete() {
        try {
            return repo.removeEvent(Integer.parseInt(target[1]) - 1);
        } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error:" + e.getMessage());
            return "Error:" + e.getMessage();
        }
    }

    private String handleTag() {
        try {
            int index = Integer.parseInt(target[1]) - 1;
            StringBuilder tag = new StringBuilder();
            for (int i = 2; i < target.length; i++) {
                tag.append(target[i]).append(" ");
            }
            return repo.tagEvent(index, tag.toString());
        } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error:" + e.getMessage());
            return "Error:" + e.getMessage();
        }
    }

    private String handleUntag() {
        try {
            int index = Integer.parseInt(target[1]) - 1;
            return repo.untagEvent(index);
        } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error:" + e.getMessage());
            return "Error:" + e.getMessage();
        }
    }

    private String handleFind() {
        String key = "";
        for (String s : this.target) {
            key = (s + " ");
        }
        return KorolevList.displayFilteredList(repo.findItem(key.strip()));
    }

    private String handleAddEvent() {
        try {
            return repo.addEvent(input);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Executes the respective command based on input string.
     *
     * @return whether the execution is successful or not.
     */
    public String executeCommand() {
        if (input.equals("bye")) {
            repo.saveEvent();
            System.exit(0);
        } else if (input.equals("list")) {
            return this.handleDisplayList();
        } else if (target[0].equals("unmark")) {
            return this.handleUnmark();
        } else if (target[0].equals("mark")) {
            return this.handleMark();
        } else if (target[0].equals("delete")) {
            return this.handleDelete();
        } else if (target[0].equals("tag")) {
            return this.handleTag();
        } else if (target[0].equals("untag")) {
            return this.handleUntag();
        } else if (target[0].equals("find")) {
            return this.handleFind();
        } else if (target[0].equals("stats")) {
            return repo.showStats();
        } else {
            return this.handleAddEvent();
        }
        return "";
    }
}
