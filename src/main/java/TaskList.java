import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class TaskList {

    private ArrayList<Task> tasklist;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public TaskList(Scanner fileReader) {
        this.tasklist = readFile(fileReader);
    }

    // public void executeCommand(String command) {
    //     if (command.equals("list")) {
    //         this.list();
    //     } else {
    //         String[] splitwords = command.split(" ", 2);
    //         String firstCommand = splitwords[0];
    //         if (firstCommand.equals("mark")) {
    //             try {
    //                 int index = Integer.valueOf(splitwords[1]);
    //                 this.markTask(index);
    //             } catch (Exception e) {
    //                 System.out.println("Invalid task number given. Type list to find out the task number");
    //             }
    //         } else if (firstCommand.equals("unmark")) {
    //             try {
    //                 int index = Integer.valueOf(splitwords[1]);
    //                 this.unmarkTask(index);
    //             } catch (Exception e) {
    //                 System.out.println("Invalid task number given. Type list to find out the task number");
    //             }
                
    //         } else if (firstCommand.equals("delete")) {
    //             try {
    //                 int index = Integer.valueOf(splitwords[1]);
    //                 this.deleteTask(index);
    //             } catch (Exception e) {
    //                 System.out.println("Invalid task number given. Type list to find out the task number");
    //             }
                
    //         } else if (firstCommand.equals("deadline")) {
    //             if (splitwords.length == 1) {
    //                 System.out.println("The description of a deadline cannot be empty");
    //                 return;
    //             }
    //             String[] taskparts = splitwords[1].split(" /by ", 2);
    //             String name = taskparts[0];
    //             if (taskparts.length == 1) {
    //                 System.out.println("The /by of a deadline cannot be empty");
    //                 return;
    //             }
    //             String deadline = taskparts[1];
    //             if (name.trim().equals("")) {
    //                 System.out.println("The description of a deadline cannot be empty");
    //                 return;
    //             }
    //             Task task = new Deadline(name, deadline);
    //             this.add(task);
    //         } else if (firstCommand.equals("event")) {
    //             if (splitwords.length == 1) {
    //                 System.out.println("The description of an event cannot be empty");
    //                 return;
    //             }
    //             String[] taskParts = splitwords[1].split(" /from ", 2);
    //             if (taskParts.length == 1) {
    //                 System.out.println("The from and to of an event cannot be empty");
    //                 return;
    //             }
    //             String[] deadlines = taskParts[1].split(" /to ", 2);
    //             String name = taskParts[0];
    //             String from = deadlines[0];
    //             if (deadlines.length == 1) {
    //                 System.out.println("The from or to of an event cannot be empty");
    //                 return;
    //             }
    //             String to = deadlines[1];
    //             if (name.trim().equals("")) {
    //                 System.out.println("The description of an event cannot be empty");
    //                 return;
    //             }
    //             Task task = new Event(name, from, to);
    //             this.add(task);
    //         } else if (firstCommand.equals("todo")) {
    //             if (splitwords.length == 1) {
    //                 System.out.println("The description of a todo cannot be empty");
    //                 return;
    //             }
    //             String name = splitwords[1];
    //             if (name.trim().equals("")) {
    //                 System.out.println("The description of a todo cannot be empty");
    //                 return;
    //             }
    //             Task task = new Todo(name);
    //             this.add(task);
    //         } else {
    //             System.out.println("Invalid command, please try again");
    //         }
    //     }
    // }

    private CommandType getCommandType(String input) {
        switch (input.toLowerCase()) {
            case "list":
                return CommandType.LIST;
            case "mark":
                return CommandType.MARK;
            case "unmark":
                return CommandType.UNMARK;
            case "delete":
                return CommandType.DELETE;
            case "todo":
                return CommandType.TODO;
            case "deadline":
                return CommandType.DEADLINE;
            case "event":
                return CommandType.EVENT;
            case "bye":
                return CommandType.BYE;
            default:
                return CommandType.INVALID;
        }
    }
    
    private ArrayList<Task> readFile(Scanner fileReader) {
        ArrayList<Task> tasks = new ArrayList<>();
        while (fileReader.hasNext()) {
            String task = fileReader.nextLine();
            String[] taskParts = task.split(",");
            String taskType = taskParts[0];
            boolean isDone = taskParts[1].equals("1");
            String taskDescription = taskParts[2];
            Task newTask = null;
            switch (taskType) {
                case "T":
                    newTask = new Todo(taskDescription, isDone);
                    break;
                case "D":
                    if (taskParts[3].length() == 10) {
                        LocalDate d = LocalDate.parse(taskParts[3]);
                        newTask = new Deadline(taskDescription, d, isDone);
                    } else {
                        String[] dateParts = taskParts[3].split(" ");
                        LocalDateTime d = LocalDateTime.parse(dateParts[0] + "T" + dateParts[1]);
                        newTask = new Deadline(taskDescription, d, isDone);
                    }
                    break;
                case "E":
                    if (taskParts[3].length() == 10 && taskParts[4].length() == 10) {
                        LocalDate from = LocalDate.parse(taskParts[3]);
                        LocalDate to = LocalDate.parse(taskParts[4]);
                        newTask = new Event(taskDescription, from, to, isDone);
                    } else if (taskParts[3].length() != 10 && taskParts[4].length() != 10) {
                        String[] dateParts1 = taskParts[3].split(" ");
                        String[] dateParts2 = taskParts[4].split(" ");
                        LocalDateTime from = LocalDateTime.parse(dateParts1[0] + "T" + dateParts1[1]);
                        LocalDateTime to = LocalDateTime.parse(dateParts2[0] + "T" + dateParts2[1]);
                        newTask = new Event(taskDescription, from, to, isDone);
                    } else if (taskParts[3].length() == 10 && taskParts[4].length() != 10) {
                        LocalDate from = LocalDate.parse(taskParts[3]);
                        String[] dateParts = taskParts[4].split(" ");
                        LocalDateTime to = LocalDateTime.parse(dateParts[0] + "T" + dateParts[1]);
                        newTask = new Event(taskDescription, from, to, isDone);
                    } else {
                        String[] dateParts = taskParts[3].split(" ");
                        LocalDateTime from = LocalDateTime.parse(dateParts[0] + "T" + dateParts[1]);
                        LocalDate to = LocalDate.parse(taskParts[4]);
                        newTask = new Event(taskDescription, from, to, isDone);
                    }
                    break;
            }
            tasks.add(newTask);
        }
        return tasks;
    }

    public void executeCommand(String command) {
        String[] splitWords = command.split(" ", 2);
        CommandType commandType = getCommandType(splitWords[0]);
    
        try {
            switch (commandType) {
                case LIST:
                    this.list();
                    break;
                case MARK:
                    handleMarkCommand(splitWords);
                    handleSaveTaskListToFile();
                    break;
                case UNMARK:
                    handleUnmarkCommand(splitWords);
                    handleSaveTaskListToFile();
                    break;
                case DELETE:
                    handleDeleteCommand(splitWords);
                    handleSaveTaskListToFile();
                    break;
                case TODO:
                    handleTodoCommand(splitWords);
                    handleSaveTaskListToFile();
                    break;
                case DEADLINE:
                    handleDeadlineCommand(splitWords);
                    handleSaveTaskListToFile();
                    break;
                case EVENT:
                    handleEventCommand(splitWords);
                    handleSaveTaskListToFile();
                    break;
                case BYE:
                    System.out.println("Bye. Hope to see you again soon!");
                    handleSaveTaskListToFile();
                    break;
                default:
                    throw new InvalidCommandException();
            }
        } catch (ScheduloException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleSaveTaskListToFile() {
        try {
            FileWriter fileWriter = new FileWriter("./data/data.txt");
            for (Task task : tasklist) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred while saving the task list to file.");
        }
    }
    
    private void handleMarkCommand(String[] splitWords) throws ScheduloException {
        if (splitWords.length < 2) {
            throw new InvalidTaskNumberException();
        }
        try {
            int index = Integer.parseInt(splitWords[1]);
    
            // Check if the index is within bounds
            if (index < 1 || index > tasklist.size()) {
                throw new InvalidTaskNumberException();
            }
    
            this.markTask(index);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }
    
    private void handleUnmarkCommand(String[] splitWords) throws ScheduloException {
        if (splitWords.length < 2) {
            throw new InvalidTaskNumberException();
        }
        try {
            int index = Integer.parseInt(splitWords[1]);
    
            // Check if the index is within bounds
            if (index < 1 || index > tasklist.size()) {
                throw new InvalidTaskNumberException();
            }
    
            this.unmarkTask(index);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }
    
    private void handleDeleteCommand(String[] splitWords) throws ScheduloException {
        if (splitWords.length < 2) {
            throw new InvalidTaskNumberException();
        }
        try {
            int index = Integer.parseInt(splitWords[1]);
    
            // Check if the index is within bounds
            if (index < 1 || index > tasklist.size()) {
                throw new InvalidTaskNumberException();
            }
    
            this.deleteTask(index);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }
    

    private void handleDeadlineCommand(String[] splitWords) throws ScheduloException {
        if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        String[] taskParts = splitWords[1].split(" /by ", 2);
        if (taskParts.length < 2 || taskParts[0].trim().isEmpty()) {
            throw new ScheduloException("The description of a deadline or the /by cannot be empty.");
        }
        String[] dateParts = taskParts[1].split(" ");
        if (dateParts.length == 1) {
            if (dateParts[0].split("-").length != 3) {
                throw new ScheduloException("The /by of a deadline should be in the format yyyy-mm-dd or yyyy-mm-dd HH:mm.");
            }
            LocalDate d = LocalDate.parse(dateParts[0]);
            if (d.isBefore(LocalDate.now())) {
                throw new ScheduloException("The deadline of a deadline should be in the future.");
            }
            Task task = new Deadline(taskParts[0], d);
            this.add(task);
        } else if (dateParts.length == 2) {
            if (dateParts[1].length() != 5) {
                throw new ScheduloException("The /by of a deadline should be in the format yyyy-mm-dd HH:mm.");
            }
            if (dateParts[0].split("-").length != 3) {
                throw new ScheduloException("The /by of a deadline should be in the format yyyy-mm-dd HH:mm.");
            }
            LocalDateTime d = LocalDateTime.parse(dateParts[0] + "T" + dateParts[1]);
            if (d.isBefore(LocalDateTime.now())) {
                throw new ScheduloException("The deadline of a deadline should be in the future.");
            }
            Task task = new Deadline(taskParts[0], d);
            this.add(task);
        } else {
            throw new ScheduloException("The /by of a deadline should be in the format yyyy-mm-dd or yyyy-mm-dd HH:mm.");
        }
    }

    // private void handleEventCommand(String[] splitWords) throws ScheduloException {
    //     if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
    //         throw new EmptyDescriptionException("event");
    //     }
    //     String[] taskParts = splitWords[1].split(" /from ", 2);
    //     if (taskParts.length < 2 || taskParts[0].trim().isEmpty()) {
    //         throw new ScheduloException("The description or /from of an event cannot be empty.");
    //     }
    //     String[] deadlines = taskParts[1].split(" /to ", 2);
    //     if (deadlines.length < 2) {
    //         throw new ScheduloException("The /from or /to of an event cannot be empty.");
    //     }
    //     String[] dateParts1 = deadlines[0].split("-");
    //     String[] dateParts2 = deadlines[1].split("-");
    //     if (dateParts1.length != 3 || dateParts2.length != 3) {
    //         throw new ScheduloException("The /from and /to of an event should be in the format yyyy-mm-dd.");
    //     }
    //     LocalDate from = LocalDate.parse(deadlines[0]);
    //     LocalDate to = LocalDate.parse(deadlines[1]);
    //     if (from.isAfter(to)) {
    //         throw new ScheduloException("The /from of an event should be before the /to.");
    //     }
    //     Task task = new Event(taskParts[0], from, to);
    //     this.add(task);
    // }

    private void handleEventCommand(String[] splitWords) throws ScheduloException {
        if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        String[] taskParts = splitWords[1].split(" /from ", 2);
        if (taskParts.length < 2 || taskParts[0].trim().isEmpty()) {
            throw new ScheduloException("The description or /from of an event cannot be empty.");
        }
        String[] deadlines = taskParts[1].split(" /to ", 2);
        if (deadlines.length < 2) {
            throw new ScheduloException("The /from or /to of an event cannot be empty.");
        }
        String[] dateParts1 = deadlines[0].split(" ");
        String[] dateParts2 = deadlines[1].split(" ");
        
        if (dateParts1.length == 1 && dateParts2.length == 1) {
            // Both are dates
            if (dateParts1[0].split("-").length != 3 || dateParts2[0].split("-").length != 3) {
                throw new ScheduloException("The /from and /to of an event should be in the format yyyy-mm-dd or yyyy-mm-dd HHmm.");
            }
            LocalDate from = LocalDate.parse(dateParts1[0]);
            LocalDate to = LocalDate.parse(dateParts2[0]);
            if (from.isAfter(to)) {
                throw new ScheduloException("The /from of an event should be before the /to.");
            }
            Task task = new Event(taskParts[0], from, to);
            this.add(task);
        } else if (dateParts1.length == 2 && dateParts2.length == 2) {
            // Both are date-times
            if (dateParts1[1].length() != 5 || dateParts2[1].length() != 5) {
                throw new ScheduloException("The /from and /to of an event should be in the format yyyy-mm-dd HH:mm.");
            }
            if (dateParts1[0].split("-").length != 3 || dateParts2[0].split("-").length != 3) {
                throw new ScheduloException("The /from and /to of an event should be in the format yyyy-mm-dd HH:mm.");
            }
            LocalDateTime from = LocalDateTime.parse(dateParts1[0] + "T" + dateParts1[1]);
            LocalDateTime to = LocalDateTime.parse(dateParts2[0] + "T" + dateParts2[1]);
            if (from.isAfter(to)) {
                throw new ScheduloException("The /from of an event should be before the /to.");
            }
            Task task = new Event(taskParts[0], from, to);
            this.add(task);
        } else if (dateParts1.length == 1 && dateParts2.length == 2) {
            // from is date, to is date-time
            if (dateParts1[0].split("-").length != 3 || dateParts2[0].split("-").length != 3 || dateParts2[1].length() != 5) {
                throw new ScheduloException("The /from and /to of an event should be in the format yyyy-mm-dd or yyyy-mm-dd HHmm.");
            }
            LocalDate from = LocalDate.parse(dateParts1[0]);
            LocalDateTime to = LocalDateTime.parse(dateParts2[0] + "T" + dateParts2[1]);
            if (from.atStartOfDay().isAfter(to)) {
                throw new ScheduloException("The /from of an event should be before the /to.");
            }
            Task task = new Event(taskParts[0], from, to);
            this.add(task);
        } else if (dateParts1.length == 2 && dateParts2.length == 1) {
            // from is date-time, to is date
            if (dateParts1[0].split("-").length != 3 || dateParts1[1].length() != 5 || dateParts2[0].split("-").length != 3) {
                throw new ScheduloException("The /from and /to of an event should be in the format yyyy-mm-dd or yyyy-mm-dd HHmm.");
            }
            LocalDateTime from = LocalDateTime.parse(dateParts1[0] + "T" + dateParts1[1]);
            LocalDate to = LocalDate.parse(dateParts2[0]);
            if (from.isAfter(to.atStartOfDay())) {
                throw new ScheduloException("The /from of an event should be before the /to.");
            }
            Task task = new Event(taskParts[0], from, to);
            this.add(task);
        } else {
            throw new ScheduloException("The /from and /to of an event should be in the format yyyy-mm-dd or yyyy-mm-dd HHmm.");
        }
    }

    private void handleTodoCommand(String[] splitWords) throws ScheduloException {
        if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        Task task = new Todo(splitWords[1]);
        this.add(task);
    }

    public void list() {
        System.out.println(this.toString());
    }

    private void add(Task task) {
        this.tasklist.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + this.tasklist.size() + " tasks in the list.");
    }

    private void markTask(int index) {
        Task task = this.tasklist.get(index-1);
        task.mark();
        System.out.println(" Nice! I've marked this task as done: \n" + task);
    }

    private void unmarkTask(int index) {
        Task task = this.tasklist.get(index-1);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet: \n" + task);
    }

    private void deleteTask(int index) {
        Task task = this.tasklist.get(index - 1);
        this.tasklist.remove(index - 1);
        System.out.println(" Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + this.tasklist.size() + " tasks in the list.");
    }

    @Override
    public String toString() {
        String tasks = "Here are the tasks in your list: \n";
        for (int i=0; i < tasklist.size(); i++) {
            tasks += (i+1) + ". " + tasklist.get(i) + "\n";
        }
        return tasks;
    }

}
