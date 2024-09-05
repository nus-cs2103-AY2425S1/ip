//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//
//public class Parser {
//
//    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
//
//    public boolean processCommand(String userInput, TaskList tasks, Ui ui, Storage storage) throws BigmouthException {
//        if (userInput.equals("bye")) {
//            ui.showGoodbyeMessage();
//            return true;
//        } else if (userInput.equals("list")) {
//            ui.showTaskList(tasks);
//        } else if (userInput.startsWith("mark ")) {
//            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
//            Task task = tasks.getTask(taskIndex);
//            task.markAsDone();
//            ui.showTaskMarked(task);
//            storage.saveToFile(tasks);
//        } else if (userInput.startsWith("unmark ")) {
//            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
//            Task task = tasks.getTask(taskIndex);
//            task.markAsNotDone();
//            ui.showTaskUnmarked(task);
//            storage.saveToFile(tasks);
//        } else if (userInput.startsWith("todo ")) {
//            String description = userInput.substring(5).trim();
//            if (description.isEmpty()) {
//                throw new BigmouthException("The description of a todo cannot be empty.");
//            }
//            Task task = new Todo(description);
//            tasks.addTask(task);
//            ui.showTaskAdded(task, tasks.size());
//            storage.saveToFile(tasks);
//        } else if (userInput.startsWith("deadline ")) {
//            String[] parts = userInput.split(" /by ");
//            if (parts.length < 2 || parts[1].trim().isEmpty()) {
//                throw new BigmouthException("The deadline command is missing a date/time.");
//            }
//            String description = parts[0].substring(9).trim();
//            LocalDateTime by = parseDateTime(parts[1].trim());
//            Task task = new Deadline(description, by);
//            tasks.addTask(task);
//            ui.showTaskAdded(task, tasks.size());
//            storage.saveToFile(tasks);
//        } else if (userInput.startsWith("event ")) {
//            String[] parts = userInput.split(" /from | /to ");
//            if (parts.length < 3) {
//                throw new BigmouthException("The event command is missing start or end times.");
//            }
//            String description = parts[0].substring(6).trim();
//            LocalDateTime from = parseDateTime(parts[1].trim());
//            LocalDateTime to = parseDateTime(parts[2].trim());
//            Task task = new Event(description, from, to);
//            tasks.addTask(task);
//            ui.showTaskAdded(task, tasks.size());
//            storage.saveToFile(tasks);
//        } else if (userInput.startsWith("delete ")) {
//            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
//            Task task = tasks.getTask(taskIndex);
//            tasks.deleteTask(taskIndex);
//            ui.showTaskRemoved(task, tasks.size());
//            storage.saveToFile(tasks);
//        } else {
//            throw new BigmouthException("OOPS! I don't know what that means.");
//        }
//
//        return false;
//    }
//
//    private LocalDateTime parseDateTime(String input) throws BigmouthException {
//        try {
//            return LocalDateTime.parse(input, DATE_FORMATTER);
//        } catch (DateTimeParseException e) {
//            throw new BigmouthException("Please enter the date/time in the format 'd/M/yyyy HHmm'.");
//        }
//    }
//}
//
