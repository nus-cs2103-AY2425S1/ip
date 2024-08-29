package sora.Tasks;

import sora.Parser;
import sora.SoraException;
import sora.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void displayList() {
        if (this.taskList.isEmpty()) {
            System.out.println("\tSeems like there are no tasks!");
            return;
        }
        for (Task t : this.taskList) {
            System.out.println("\t" + t.toString());
        }
    }

    public void markTask(String value) throws SoraException {
        try {
            Task task = this.taskList.get(Integer.parseInt(value) - 1);
            if (task.isDone) {
                System.out.println("\tSora has discovered this task is already done!");
                return;
            }
            task.markAsDone();
            System.out.println("\tNice! Sora has marked this task as done:");
            System.out.println("\t" + task);
        } catch (NumberFormatException e) {
            throw new SoraException("\tPlease Enter - Mark (int)\n" + Ui.HORIZONTAL_LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("\tPlease Enter Integer Value within List Size\n" + Ui.HORIZONTAL_LINE);
        }
    }

    public void unmarkTask(String value) throws SoraException {
        try {
            Task task = this.taskList.get(Integer.parseInt(value) - 1);
            if (!task.isDone) {
                System.out.println("\tSora has discovered this task is already not done!");
                return;
            }
            task.markAsNotDone();
            System.out.println("\tOk, Sora has unmarked this task as not done:");
            System.out.println("\t" + task);
        } catch (NumberFormatException e) {
            throw new SoraException("\tPlease Enter - Mark (int)\n" + Ui.HORIZONTAL_LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("\tPlease Enter Integer Value within List Size\n" + Ui.HORIZONTAL_LINE);
        }
    }

    public void addTask(String mainCommand, ArrayList<String> parsedCommand) throws SoraException {
        switch (mainCommand) {
            case "todo":
                try {
                    this.taskList.add(new ToDo(parsedCommand.get(1)));
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new SoraException("\tPlease Enter - Todo (Description)\n" + Ui.HORIZONTAL_LINE);
                }
            case "deadline":
                String by = parsedCommand.get(2).substring(3);
                LocalDateTime byDate = Parser.parseDate(by);
                try {
                    if (byDate == null) {
                        this.taskList.add(new Deadline(parsedCommand.get(1), by));
                    } else {
                        this.taskList.add(new Deadline(parsedCommand.get(1), byDate));
                    }
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new SoraException("\tPlease Enter - Sora.Deadline (Description) /by (dd/MM/yy HHmm)\n" + Ui.HORIZONTAL_LINE);
                }
            case "event":
                String from = parsedCommand.get(2).substring(5);
                LocalDateTime fromDate = Parser.parseDate(from);
                String to = parsedCommand.get(3).substring(3);
                LocalDateTime toDate = Parser.parseDate(to);
                try {
                    if (fromDate != null && toDate != null) {
                        this.taskList.add(new Event(parsedCommand.get(1), fromDate, toDate));
                    } else if (fromDate != null && toDate == null) {
                        this.taskList.add(new Event(parsedCommand.get(1), fromDate, to));
                    } else if (fromDate == null && toDate != null) {
                        this.taskList.add(new Event(parsedCommand.get(1), from, toDate));
                    } else {
                        this.taskList.add(new Event(parsedCommand.get(1), from, to));
                    }
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new SoraException("\tPlease Enter - Event (Description) /from (dd/MM/yy HHmm) /to (dd/MM/yy HHmm)\n" + Ui.HORIZONTAL_LINE);
                }
        }
        System.out.println("\tGot it. Sora has added this task:");
        System.out.println("\t" + taskList.get(taskList.size() - 1));
        System.out.println("\tNow, you have " + taskList.size() + " tasks in your list");
    }

    public void deleteTask(String value) throws SoraException {
        try {
            int index = Integer.parseInt(value) - 1;
            Task deletedTask = this.taskList.remove(index);
            System.out.println("\tNoted. Sora has removed this task:");
            System.out.println("\t" + deletedTask);
        } catch (NumberFormatException e) {
            throw new SoraException("\tPlease Enter - Delete (int)\n" + Ui.HORIZONTAL_LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("\tPlease Enter Integer Value within List Size\n" + Ui.HORIZONTAL_LINE);
        }
    }

}