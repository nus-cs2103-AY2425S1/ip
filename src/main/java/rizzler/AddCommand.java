package rizzler;

import java.time.DateTimeException;
import java.time.LocalDate;

public class AddCommand implements Command {
    private final String taskType;
    private final String[] fullCommand;

    AddCommand(String[] fullCommand) {
        this.taskType = fullCommand[0];
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks,  Ui ui, FileStorage fileStorage) throws RizzlerException {
        switch (this.taskType) {
        case "todo":
            if (this.fullCommand.length == 1) {
                throw new RizzlerException("Please add in the task description\n"
                        + "Format:\n"
                        + "todo [task name]");
            } else {
                String taskName = this.fullCommand[1];
                for (int i = 2; i < this.fullCommand.length; i++) {
                    taskName += " " + fullCommand[i];
                }
                tasks.add(new Todo(taskName));
                fileStorage.save(tasks.getListToSave());
            }
            break;
        case "deadline":
            int indexOfBy = 0;
            boolean byInInput = false;
            for (int i = 1; i < this.fullCommand.length; i++) {
                if (this.fullCommand[i].equals("/by")) {
                    indexOfBy = i;
                    byInInput = true;
                    break;
                }
            }
            if (indexOfBy == 1 || !byInInput ||
                    (indexOfBy == this.fullCommand.length - 1)) {
                throw new RizzlerException("This command was entered incorrectly\n"
                        + "Format:\n"
                        + "deadline [task name] /by [yyyy-mm-dd]");
            }
            try {
                String deadline = this.fullCommand[1];
                for (int i = 2; i < indexOfBy; i++) {
                    deadline += " " + this.fullCommand[i];
                }
                String byTime = this.fullCommand[indexOfBy + 1];
                tasks.add(new Deadline(deadline, LocalDate.parse(byTime)));
                fileStorage.save(tasks.getListToSave());
                break;
            } catch (DateTimeException e) {
                throw new RizzlerException("Please put a valid date-time format\n"
                        + "Format:\n"
                        + "deadline [task name] /by [yyyy-mm-dd]");
            }
        case "event":
            int indexOfFrom = 0;
            int indexOfTo = 0;
            boolean hasFrom = false;
            boolean hasTo = false;
            for (int i = 1; i < this.fullCommand.length; i++) {
                if (!hasFrom && this.fullCommand[i].equals("/from")) {
                    indexOfFrom = i;
                    hasFrom = true;
                }
                if (!hasTo && this.fullCommand[i].equals("/to")) {
                    indexOfTo = i;
                    hasTo = true;
                }
            }
            if (!hasFrom || !hasTo ||
                    (indexOfFrom == 1) ||
                    (indexOfTo - indexOfFrom == 1) ||
                    (indexOfTo == this.fullCommand.length - 1)) {
                throw new RizzlerException("This command was entered incorrectly\n"
                        + "Format:\n"
                        + "event [task name] /from [yyyy-mm-dd] /to [yyyy-mm-dd]");
            }
            String event = this.fullCommand[1];
            for (int i = 2; i < indexOfFrom; i++) {
                event += " " + this.fullCommand[i];
            }
            String fromTime = this.fullCommand[indexOfFrom + 1];
            String toTime = this.fullCommand[indexOfTo + 1];
            try {
                tasks.add(new Event(event,
                        LocalDate.parse(fromTime),
                        LocalDate.parse(toTime)));
                fileStorage.save(tasks.getListToSave());
                break;
            } catch (DateTimeException e) {
                throw new RizzlerException("Please put a valid date-time format\n"
                        + "Format:\n"
                        + "event [task name] /from [yyyy-mm-dd] /to [yyyy-mm-dd]");
            }
        }
    }

    public boolean isExit() {
        return false;
    }
}
