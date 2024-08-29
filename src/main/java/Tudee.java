import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tudee {
    enum Command {
        LIST, BYE, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DATE, UNKNOWN
    }
    public static void main(String[] args) {
        String logo = "____________________________________________________________ \n"
                + "Hello! I'm Tudee, your chatbot bestie! \n"
                + "How can I help you today? :) \n"
                + "____________________________________________________________ \n";
        System.out.println(logo);
        String path = "./data/tudee.txt";
        Storage storage = new Storage(path);
        List<Task> list = storage.load();
        int count = list.size();
        String input;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String output = "";
                input = sc.nextLine();
                Task currentTask;
                String[] inputArray = input.split(" ", 2);
                Command cmd = getCmd(inputArray[0]);
                if (cmd == Command.LIST) {
                    System.out.println("____________________________________________________________ \n");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                    System.out.println("____________________________________________________________ \n");
                } else if (cmd == Command.BYE) {
                    output = "____________________________________________________________ \n"
                            + "Bye. Hope to see you again soon! Don't forget about me :( \n"
                            + "____________________________________________________________";
                    System.out.println(output);
                    break;
                } else if (cmd == Command.MARK) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    Task target = list.get(index);
                    target.markAsDone();
                    System.out.println("____________________________________________________________ \n");
                    System.out.println("Nice! I've marked this task as done: \n");
                    System.out.println(target);
                    System.out.println("____________________________________________________________ \n");
                } else if (cmd == Command.UNMARK) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    Task target = list.get(index);
                    target.markAsNotDone();
                    System.out.println("____________________________________________________________ \n");
                    System.out.println("Ok, I've marked this task as not done yet: \n");
                    System.out.println(target);
                    System.out.println("____________________________________________________________ \n");
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
                        System.out.println("____________________________________________________________ \n");
                        System.out.println("Got it. I've added this task: \n");
                        System.out.println("  " + currentTask);
                        System.out.println("Now you have " + count + " tasks in the list. \n");
                        System.out.println("____________________________________________________________ \n");
                    }
                } else if (cmd == Command.DELETE) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    Task temp = list.remove(index);
                    count--;
                    System.out.println("____________________________________________________________ \n");
                    System.out.println("Noted. I've removed this task: \n");
                    System.out.println(temp);
                    System.out.println("Now you have " + count + " tasks in the list. \n");
                    System.out.println("____________________________________________________________ \n");
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
                                System.out.println("You have no tasks on this date, " + inputDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ".");
                            }
                        }
                        catch (DateTimeParseException e) {
                            System.out.println("Invalid date format, Please use yyyy-MM-dd.");
                        }
                    }
                }
                else {
                    throw new TudeeException("Everything has limits, and this is my limit.");
                }
                storage.save(list);
            } catch (TudeeException exception) {
                System.out.println("____________________________________________________________ \n");
                System.out.println(exception.getMessage());
                System.out.println("____________________________________________________________ \n");
            }
        }
        sc.close();
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
