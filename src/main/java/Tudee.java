import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tudee {
    enum Command {
        LIST, BYE, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DATE, UNKNOWN
    }
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();
        String path = "./data/tudee.txt";
        Storage storage = new Storage(path);
        List<Task> list = storage.load();
        int count = list.size();
        String input;
        while (true) {
            try {
                String output = "";
                input = ui.readCommand();
                Task currentTask;
                String[] inputArray = input.split(" ", 2);
                Command cmd = getCmd(inputArray[0]);
                if (cmd == Command.LIST) {
                    ui.showTaskList(list);
                } else if (cmd == Command.BYE) {
                    ui.showBye();
                    break;
                } else if (cmd == Command.MARK) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    Task target = list.get(index);
                    target.markAsDone();
                    ui.showMark(target);
                } else if (cmd == Command.UNMARK) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    Task target = list.get(index);
                    target.markAsNotDone();
                    ui.showUnMark(target);
                } else if (cmd == Command.TODO || cmd == Command.DEADLINE || cmd == Command.EVENT) {
                    if (inputArray.length <= 1) {
                        throw new TudeeException("Oopsie daisy, are you not aware of my capabilities?!?! I need to know what you want to add.");
                    } else {
                        if (cmd == Command.TODO) {
                            currentTask = new ToDo(inputArray[1]);
                        } else if (cmd == Command.DEADLINE) {
                            String[] deadline = inputArray[1].split(" /by ");
                            currentTask = new Deadline(deadline[0], deadline[1]);
                        } else {
                            String[] timeframe = inputArray[1].split(" /from | /to ");
                            currentTask = new Events(timeframe[0], timeframe[1], timeframe[2]);
                        }
                        list.add(currentTask);
                        count++;
                        ui.showAdd(currentTask, count);
                    }
                } else if (cmd == Command.DELETE) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    Task temp = list.remove(index);
                    count--;
                    ui.showDelete(temp, count);
                } else if (cmd == Command.DATE) {
                    if (inputArray.length <= 1) {
                        throw new TudeeException("Tell me the date you want to check for.");
                    } else {
                        try {
                            LocalDate inputDate = LocalDate.parse(inputArray[1]);
                            boolean haveTask = false;
                            for (Task task : list) {
                                if (task instanceof Deadline) {
                                    Deadline deadline = (Deadline) task;
                                    if (deadline.getDateTime().isEqual(inputDate)) {
                                        System.out.println(deadline);
                                        haveTask = true;
                                    }
                                }
                                else if (task instanceof Events) {
                                    Events events = (Events) task;
                                    if ((events.getStart().isBefore(inputDate) && events.getEnd().isAfter(inputDate)) || events.getStart().isEqual(inputDate) || events.getEnd().isEqual(inputDate)){
                                        System.out.println(events);
                                        haveTask = true;
                                    }
                                }
                            }
                            if (!haveTask) {
                                throw new TudeeException("You have no tasks on this date, " + inputDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ".");
                            }
                        }
                        catch (DateTimeParseException e) {
                            throw new TudeeException("Invalid date format, Please use yyyy-MM-dd.");
                        }
                    }
                }
                else {
                    throw new TudeeException("Everything has limits, and this is my limit.");
                }
                storage.save(list);
            } catch (TudeeException exception) {
                ui.showError(exception.getMessage());
            }
        }
        ui.close();
    }

    private static Command getCmd(String command) {
        if (command.equalsIgnoreCase("list")) {
            return Command.LIST;
        }
        else if (command.equalsIgnoreCase("bye")) {
            return Command.BYE;
        }
        else if (command.equalsIgnoreCase("todo")) {
            return Command.TODO;
        }
        else if (command.equalsIgnoreCase("deadline")) {
            return Command.DEADLINE;
        }
        else if (command.equalsIgnoreCase("event")) {
            return Command.EVENT;
        }
        else if (command.equalsIgnoreCase("mark")) {
            return Command.MARK;
        }
        else if (command.equalsIgnoreCase("unmark")) {
            return Command.UNMARK;
        }
        else if (command.equalsIgnoreCase("delete")) {
            return Command.DELETE;
        }
        else if (command.equalsIgnoreCase("date")) {
            return Command.DATE;
        }
        else {
            return Command.UNKNOWN;
        }
    }
}
