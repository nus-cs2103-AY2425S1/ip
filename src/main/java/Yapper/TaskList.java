package Yapper;import java.time.LocalDate;import java.time.format.DateTimeParseException;import java.util.ArrayList;public class TaskList {    private final ArrayList<Task> listOfTask;    public final Storage storage;    public TaskList(ArrayList<Task> listOfTask, Storage storage) {        this.listOfTask = listOfTask;        this.storage = storage;    }    public static LocalDate convertStringToDate(String date) throws YapperException {        long numberOfDash = date.chars().filter(c -> c == '/').count();        LocalDate returnDate;        if (numberOfDash != 2) { // If there is not 2 / in date format            throw new YapperException("Invalid date format\n" +                    "Date Format: Year/Month/Day or Year/Month/Day Time");        } else {            String[] timeSplit = date.split(" ");            String[] dayMonthYearSplit = timeSplit[0].split("/");            try {                String year = dayMonthYearSplit[0];                String month = dayMonthYearSplit[1];                String day = dayMonthYearSplit[2];                if (timeSplit.length == 2) { // Date includes a time                    String time = timeSplit[1];                    returnDate = LocalDate.parse(String.format("%s-%s-%sT%s", year, month, day, time));                } else { // Date does not include a time                    returnDate = LocalDate.parse(String.format("%s-%s-%s", year, month, day));                }            } catch (NumberFormatException | DateTimeParseException e) {                throw new YapperException("Invalid date\n" +                        "Date Format: Year/Month/Day or Year/Month/Day Time");            }        }        return returnDate;    }    public void deleteTask(String command) throws YapperException    {        if (command.length() == 6) { // If command is only "delete"            throw new YapperException("Task Number cannot be empty!");        }        String input = command.substring(7);        int order = Integer.parseInt(input);        if (order <= 0) { // If Task Number is less than 1            throw new YapperException("Task Number cannot be less than 1!");        } else if (order > listOfTask.size()) { // If Task Number if greater than the size of the list            throw new YapperException("Task Number cannot be more than size of list!");        } else {            Task task = listOfTask.get(order - 1);            listOfTask.remove(order - 1);            System.out.println("Noted. I've removed this task: \n" +                    task + "\nNow you have " + listOfTask.size() + " tasks in the list");        }        storage.writeHistory(listOfTask);    }    public void addToDo(String command) throws YapperException    {        if (command.length() == 4) { // If command is only "todo"            throw new YapperException("Description for ToDo cannot be empty!");        }        String input = command.substring(5);        ToDo todo = new ToDo(input);        addTask(todo);    }    public void addDeadline(String command) throws YapperException    {        if (command.length() == 8) { // If command is only "deadline"            throw new YapperException("Description for Deadline cannot be empty!");        }        String input = command.substring(9);        String[] split = input.split(" /by ");        if (split.length == 1) { // If command is deadline not followed /by            throw new YapperException("Deadline require /by command with Deadline Time");        } else if (split[0].isEmpty()) { // If command is "deadline /by something"            throw new YapperException("Deadline Task is empty!");        } else if (split[1].isEmpty()) { // If command is "deadline something /by"            throw new YapperException("Deadline Time is empty!");        } else {            LocalDate deadlineDate = convertStringToDate(split[1]);            Deadline deadline = new Deadline(split[0], deadlineDate);            addTask(deadline);        }    }    public void addEvent(String command) throws YapperException    {        if (command.length() == 5) { // If command is only "event"            throw new YapperException("Event Task cannot be empty!");        }        String input = command.substring(6);        String[] split = input.split(" /from ");        String[] split2 = split[1].split(" /to ");        if (split.length == 1) { // If command does not have /from            throw new YapperException("Event require /from command with Start Time");        } else if (split2.length == 1) { // If command does not have /to            throw new YapperException("Event require /to command with End Time");        } else if (split[0].isEmpty()) { // If command does not have a Task            throw new YapperException("Event Task is empty!");        } else if (split2[0].isEmpty()) { // If command does not have a Start Time            throw new YapperException("Event Start Time is empty!");        } else if (split2[1].isEmpty()) { // If command does not have an End Time            throw new YapperException("Event End Time is empty!");        } else {            LocalDate fromDate = convertStringToDate(split2[0]);            LocalDate toDate = convertStringToDate(split2[1]);            Event event = new Event(split[0], fromDate, toDate);            addTask(event);        }    }    // add a new task into the list    public void addTask(Task task)    {        listOfTask.add(task);        System.out.println("Got it. I've added this task:\n" +                task + "\nNow you have " + listOfTask.size() + " tasks in the list");        storage.writeHistory(listOfTask);    }    // mark a task as done    public void mark(String command) throws YapperException    {        if (command.length() == 4) { // If command is only "mark"            throw new YapperException("Task Number cannot be empty!");        }        String input = command.substring(5);        int order = Integer.parseInt(input);        if (order <= 0) { // If Task Number is less than 1            throw new YapperException("Task Number cannot be less than 1!");        } else if (order > listOfTask.size()) { // If Task Number is greater than size of the list            throw new YapperException("Task Number cannot be more than size of list!");        } else {            Task taskToMark = listOfTask.get(order - 1);            taskToMark.setDone(true);            System.out.println("Nice! I've marked this task as done: \n" +                    taskToMark);        }        storage.writeHistory(listOfTask);    }    // umark a task as undone    public void unmark(String command) throws YapperException    {        if (command.length() == 6) { // If command is only "unmark"            throw new YapperException("Task Number cannot be empty!");        }        String input = command.substring(7);        int order = Integer.parseInt(input);        if (order <= 0) { // If Task Number is less than 1            throw new YapperException("Task Number cannot be less than 1!");        } else if (order > listOfTask.size()) { // If Task Number is greater than size of the list            throw new YapperException("Task Number cannot be more than size of list!");        } else {            Task taskToUnmark = listOfTask.get(order - 1);            taskToUnmark.setDone(false);            System.out.println("OK, I've marked this task as not done yet: \n" +                    taskToUnmark);        }        storage.writeHistory(listOfTask);    }    // return list    public void returnList()    {        System.out.println("Here are the tasks in your list: ");        int order = 1;        for (Task task : listOfTask) {            System.out.println(order + "." + task);            order++;        }    }    // exits when the user types the command "bye"    public void exit()    {        System.out.println("Yapper shall yap next time!");    }}