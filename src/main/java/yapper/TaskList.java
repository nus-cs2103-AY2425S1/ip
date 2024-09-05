package yapper;import java.time.LocalDateTime;import java.util.ArrayList;/** * Represents a TaskList, which contains the ArrayList of Tasks. It also has a reference to a Storage object to * read and write the history of Tasks whenever it is updated. */public class TaskList {    public final Storage storage;    private final ArrayList<Task> listOfTask;    /**     * Creates an instance of TaskList.     *     * @param listOfTask List of tasks to be accessed.     * @param storage Storage object to read and write tasks into.     */    public TaskList(ArrayList<Task> listOfTask, Storage storage) {        this.listOfTask = listOfTask;        this.storage = storage;    }    /**     * Finds the Task in the ArrayList of Tasks that matches the String.     *     * @param command "find" followed by the String to search in the ArrayList of Tasks.     * @throws YapperException If the String following "find" is empty.     */    public String findTask(String command) throws YapperException {        if (command.equals("find") || command.equals("find ")) {            throw new YapperException("String to find cannot be empty!");        } else {            String wordToFind = command.substring(5);            ArrayList<Task> listOfTaskWithWord = new ArrayList<>();            TaskList taskList = new TaskList(listOfTaskWithWord, null);            for (Task task : listOfTask) {                if (task.contains(wordToFind)) {                    listOfTaskWithWord.add(task);                }            }            return taskList.returnList();        }    }    /**     * Converts a String into a LocalDateTime object.     * If Time is unspecified, the default time for a starting time is 00:00,     * and the default time for an ending time is 23:59.     *     * @param dateTime Date and/or Time to be converted.     * @param type 0 or 1: 0 for starting time, 1 for ending time.     * @return LocalDateTime objected created with the given date and time.     */    public static LocalDateTime convertStringToDateTime(String dateTime, int type) {        try {            String[] splitDateTime = dateTime.split(" ");            String date = splitDateTime[0];            String[] splitYearMonthDay = date.split("/");            String year = splitYearMonthDay[0];            String month = splitYearMonthDay[1];            String day = splitYearMonthDay[2];            LocalDateTime toReturn;            if (splitDateTime.length == 2) {                String time = splitDateTime[1];                String hour = time.substring(0, 2);                String minute = time.substring(2);                String toParse = String.format("%s-%s-%sT%s:%s", year, month, day, hour, minute);                toReturn = LocalDateTime.parse(toParse);            } else if (type == 0) {                String toParse = String.format("%s-%s-%sT00:00", year, month, day);                toReturn = LocalDateTime.parse(toParse);            } else if (type == 1) {                String toParse = String.format("%s-%s-%sT23:59", year, month, day);                toReturn = LocalDateTime.parse(toParse);            } else {                throw new YapperException("Error in stringToDateTime: Wrong Type");            }            return toReturn;        } catch (YapperException e) {            System.out.println(e.getMessage());        }        return null;    }    /**     * Deletes a task off the list of tasks.     *     * @param command Word "delete" followed by a number corresponding to the task number to be deleted.     * @throws YapperException If the string following "delete" cannot be treated as a valid task number.     */    public String deleteTask(String command) throws YapperException {        if (command.length() == 6) {            throw new YapperException("Task Number cannot be empty!");        } else {            String input = command.substring(7);            int order = Integer.parseInt(input);            if (order <= 0) {                throw new YapperException("Task Number cannot be less than 1!");            } else if (order > this.listOfTask.size()) {                throw new YapperException("Task Number cannot be more than size of list!");            } else {                Task task = this.listOfTask.get(order - 1);                this.listOfTask.remove(order - 1);                String toReturn = "Noted. I've removed this task: \n"                        + task + "\n" + "Now you have " + this.listOfTask.size() + " tasks in the list";                System.out.println(toReturn);                this.storage.writeHistory(this.listOfTask);                return toReturn;            }        }    }    /**     * Add a ToDo object into the list of tasks.     *     * @param command Word "todo" followed by the name of the task.     * @throws YapperException If the string following "todo" is empty.     */    public String addToDo(String command) throws YapperException {        if (command.equals("todo") || command.equals("todo ")) {            throw new YapperException("Description for ToDo cannot be empty!");        } else {            String input = command.substring(5);            ToDo todo = new ToDo(input);            return this.addTask(todo);        }    }    /**     * Add a Deadline object into the list of tasks.     *     * @param command Word "deadline" followed by the name of the task, " /by ", followed by the ending time.     * @throws YapperException If the name of the task is empty.     * @throws YapperException If "/by" command does not exist     * @throws YapperException If the ending time is empty.     */    public String addDeadline(String command) throws YapperException {        if (command.startsWith("deadline /by ")) {            throw new YapperException("Name for Deadline cannot be empty!");        } else {            String input = command.substring(9);            String[] split = input.split(" /by ");            if (!input.contains(" /by ")) {                throw new YapperException("Deadline require /by command with Deadline Time");            } else if (split.length == 1) {                throw new YapperException("Deadline Time is empty!");            } else {                LocalDateTime byDateTime = convertStringToDateTime(split[1], 1);                Deadline deadline = new Deadline(split[0], byDateTime);                return this.addTask(deadline);            }        }    }    /**     * Add a Event object into the list of tasks.     *     * @param command Word "event" followed by the name of the task, " /from ", followed by the starting time,     *                " /to ", followed by the ending time.     * @throws YapperException If name of event is empty.     * @throws YapperException If "/from" command does not exist.     * @throws YapperException If "/to" command does not exist     * @throws YapperException If starting time is empty.     * @throws YapperException If ending time is empty.     */    public String addEvent(String command) throws YapperException {        if (command.equals("event /from ")) {            throw new YapperException("Name for Event cannot be empty!");        } else {            String input = command.substring(6);            String[] split = input.split(" /from ");            String[] split2 = split[1].split(" /to ");            if (!input.contains(" /from ")) {                throw new YapperException("Event require /from command with Start Time");            } else if (!input.contains(" /to ")) {                throw new YapperException("Event require /to command with End Time");            } else if (split2[0].isEmpty()) {                throw new YapperException("Event Start Time is empty!");            } else if (split2[1].isEmpty()) {                throw new YapperException("Event End Time is empty!");            } else {                LocalDateTime fromDateTime = convertStringToDateTime(split2[0], 0);                LocalDateTime toDateTime = convertStringToDateTime(split2[1], 1);                Event event = new Event(split[0], fromDateTime, toDateTime);                return this.addTask(event);            }        }    }    /**     * Add a Task object into the list of tasks.     *     * @param task Task to be added into the list.     */    public String addTask(Task task) {        this.listOfTask.add(task);        String toReturn = "Got it. I've added this task:\n" + task                + "\nNow you have " + this.listOfTask.size() + " tasks in the list";        System.out.println(toReturn);        this.storage.writeHistory(this.listOfTask);        return toReturn;    }    /**     * Marks a task in the list as done.     *     * @param command "mark" followed by the task number to be marked as done.     * @throws YapperException If the string following "mark" cannot be treated as a valid task number.     */    public String mark(String command) throws YapperException {        if (command.length() == 4) {            throw new YapperException("Task Number cannot be empty!");        } else {            String input = command.substring(5);            int order = Integer.parseInt(input);            if (order <= 0) {                throw new YapperException("Task Number cannot be less than 1!");            } else if (order > this.listOfTask.size()) {                throw new YapperException("Task Number cannot be more than size of list!");            } else {                Task taskToMark = this.listOfTask.get(order - 1);                taskToMark.setDone(true);                String toReturn = "Nice! I've marked this task as done: \n" + taskToMark;                System.out.println(toReturn);                this.storage.writeHistory(this.listOfTask);                return toReturn;            }        }    }    /**     * Marks a task in the list as undone.     *     * @param command "unmark" followed by the task number to be marked as undone.     * @throws YapperException If the string following "unmark" cannot be treated as a valid task number.     */    public String unmark(String command) throws YapperException {        if (command.length() == 6) {            throw new YapperException("Task Number cannot be empty!");        } else {            String input = command.substring(7);            int order = Integer.parseInt(input);            if (order <= 0) {                throw new YapperException("Task Number cannot be less than 1!");            } else if (order > this.listOfTask.size()) {                throw new YapperException("Task Number cannot be more than size of list!");            } else {                Task taskToUnmark = this.listOfTask.get(order - 1);                taskToUnmark.setDone(false);                String toReturn = "OK, I've marked this task as not done yet: \n" + taskToUnmark;                System.out.println(toReturn);                this.storage.writeHistory(this.listOfTask);                return toReturn;            }        }    }    /**     * Prints the list of tasks out in System out     */    public String returnList() {        String toReturn = "Here are the tasks in your list: ";        System.out.println(toReturn);        int order = 1;        for (Task task : listOfTask) {            String toAdd = order + "." + task;            System.out.println(toAdd);            order++;            toReturn += "\n" + toAdd;        }        return toReturn;    }    /**     * Exits the program.     */    public String exit() {        String toReturn = "Yapper shall yap next time!";        System.out.println(toReturn);        return toReturn;    }}