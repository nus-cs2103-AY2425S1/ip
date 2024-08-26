package rob;

import java.util.Objects;

public class Parser {
    private String string;

    public Parser(String string) {
        this.string = string;

    }

    public void checkString() {
        if (string.isEmpty()) {
            System.out.println("Invalid input! Please enter a task.");
        }
    }

    public String getCommand() {
        return string.split(" ", 2)[0];
    }

    public String getDesc() throws DukeException {
        if (string.split(" ", 2).length < 2) {
            throw new DukeException("Invalid format... What task would you like to add?");
        } else {
            String rem = string.split(" ", 2)[1].trim(); // ignore first keyword of input
            if (Objects.equals(this.getCommand(), "deadline")) {
                return rem.split(" /by")[0].trim();
            } else if (Objects.equals(this.getCommand(), "event")) {
                return rem.split(" /from")[0].trim();
            } else if (Objects.equals(this.getCommand(), "todo")) {
                return rem;
            }
        }
        return "";
    }

    public String getDay() throws DukeException {
        if (!string.contains(" /by")) {
            throw new DukeException("Missing '/by' in deadline command.");
        } else {
            String rem = string.split(" ", 2)[1].trim(); // ignore first keyword of input
            return rem.split(" /by")[1].trim();
        }
    }

    public String getFrom() throws DukeException {
        if (!string.contains(" /from")) {
            throw new DukeException("Missing '/from' or '/to' in event command.");
        } else {
            String rem = string.split(" ", 2)[1].trim(); // ignore first keyword of input
            return rem.split(" /from")[1].split(" /to")[0].trim();

        }
    }

    public String getTo() throws DukeException {
        if (!string.contains(" /to")) {
            throw new DukeException("Missing '/from' or '/to' in event command.");
        } else {
            String rem = string.split(" ", 2)[1].trim(); // ignore first keyword of input
            return rem.split(" /from")[1].split(" /to")[1].trim();
        }
    }

    public String getFind() throws DukeException {
        if (string.split(" ", 2).length < 2) {
            throw new DukeException("Invalid format... What task would you like to find?");
        } else {
            System.out.println(string.split(" ", 2)[0].trim());
            return string.split(" ", 2)[1].trim();
        }
    }
}
