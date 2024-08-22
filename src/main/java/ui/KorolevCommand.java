package ui;

import exception.DukeException;
import task.KorolevList;

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

    public boolean executeCommand() {

        if (input.equals("bye")) {
            repo.saveEvent();
            return false;
        } else if (input.equals("list")) {
            System.out.println(repo.displayList());
        } else if (target[0].equals("unmark")) {
            try {
                repo.unmarkEvent(Integer.parseInt(target[1]) - 1);
            } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Error:" + e.getMessage());
            }
        } else if (target[0].equals("mark")) {
            try {
                repo.markEvent(Integer.parseInt(target[1]) - 1);
            } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Error:" + e.getMessage());
            }
        } else if (target[0].equals("delete")) {
            try {
                repo.removeEvent(Integer.parseInt(target[1]) - 1);
            } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Error:" + e.getMessage());
            }
        } else {
            try {
                repo.addEvent(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(divider);
        repo.saveEvent();
        return true;
    }
}
