import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Justbot {
    public static void main(String[] args) throws JustbotException {
        final String chatbotName = "JustBot";
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        final String filePath = "/Users/justinyeo/Desktop/CS2103T-ip/data/justbottask.txt";

        TaskFileHandler taskFileHandler = new TaskFileHandler(filePath);
        taskFileHandler.createFileIfDoesNotExist();
        taskFileHandler.loadTasks(tasks);
        Commands.botIntro(chatbotName);

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            String commandString = input.split(" ")[0];
            CommandType command = CommandType.fromString(commandString);
            switch(command) {
                case BYE:
                    System.out.println("------------------------------------------");
                    Commands.bye();
                    scanner.close();
                    return;
                case LIST:
                    try {
                        if (tasks.isEmpty()) {
                            throw new JustbotException("Hey man you have no tasks in your list!");
                        }
                        Commands.returnTaskList(tasks);
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    }
                    break;
                case MARK:
                    try {
                        String[] splitInputMark = input.split(" ");

                        if (splitInputMark.length < 2) {
                            throw new JustbotException("Hey man you have provided me an invalid format for delete.\n" +
                                    "Use the format: mark [task number]");
                        }
                        int markNumber = Integer.parseInt(splitInputMark[1]);
                        if (markNumber < 1 || markNumber > tasks.size()) {
                            throw new IndexOutOfBoundsException("Hey man there is no such task");
                        }

                        if (tasks.get(markNumber -1).getIsDone()) {
                            throw new JustbotException("Hey man this task is already marked!");
                        }
                        Commands.markTask(tasks, markNumber);
                        taskFileHandler.saveTasks(tasks);
                    } catch (NumberFormatException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man please input a number for the task number!");
                        System.out.println("------------------------------------------");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man there is no such task!");
                        System.out.println("------------------------------------------");
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    }
                    break;
                case UNMARK:
                    try {
                        String[] splitInputUnmark = input.split(" ");

                        if (splitInputUnmark.length < 2) {
                            throw new JustbotException("Hey man you have provided me an invalid format for delete.\n" +
                                    "Use the format: unmark [task number]");
                        }
                        int unmarkNumber = Integer.parseInt(splitInputUnmark[1]);
                        if (unmarkNumber < 1 || unmarkNumber > tasks.size()) {
                            throw new IndexOutOfBoundsException("Hey man there is no such task");
                        }
                        if (!tasks.get(unmarkNumber -1).getIsDone()) {
                            throw new JustbotException("Hey man this task is already unmarked!");
                        }
                        Commands.unmarkTask(tasks, unmarkNumber);
                        taskFileHandler.saveTasks(tasks);
                    } catch (NumberFormatException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man please input a number for the task number!");
                        System.out.println("------------------------------------------");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man there is no such task!");
                        System.out.println("------------------------------------------");
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    }
                    break;
                case DEADLINE:
                    try {
                        String[] splitPartsDeadline = input.split("/by");

                        if (splitPartsDeadline.length < 2) {
                            throw new JustbotException("Hey man please enter the deadline in the following format:\n" +
                                    "  deadline [description] /by DD/MM/YYYY HHmm\n\n" +
                                    "For example:\n" +
                                    "  deadline run /by 26/09/2024 1800");
                        }

                        String commandAndDescriptionDeadline = splitPartsDeadline[0].trim();
                        String deadlineDateTimeString = splitPartsDeadline[1].trim();
                        String deadlineDescription = commandAndDescriptionDeadline.substring(8).trim();

                        if (deadlineDescription.isBlank() && deadlineDateTimeString.isBlank()) {
                            throw new JustbotException("Hey man the description and deadline date cannot be blank!");
                        } else if (deadlineDescription.isBlank()) {
                            throw new JustbotException("Hey man the description cannot be blank!");
                        } else if (deadlineDateTimeString.isBlank()) {
                            throw new JustbotException("Hey man the deadline date cannot be blank!");
                        }

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        if (!deadlineDateTimeString.matches("\\d{2}/\\d{2}/\\d{4} \\d{4}")) {
                            throw new JustbotException("Deadline date and time must be in the format: dd/MM/yyyy HHmm");
                        }
                        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineDateTimeString, formatter);

                        Commands.addTask(tasks, new Deadline(deadlineDescription, deadlineDateTime));
                        taskFileHandler.saveTasks(tasks);
                    } catch (DateTimeParseException e) {
                        System.out.println("Hey man please enter the deadline in the following format:\n" +
                                "  deadline [description] /by DD/MM/YYYY HHmm\n\n" +
                                "For example:\n" +
                                "  deadline run /by 26/09/2024 1800");
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man please enter the deadline in the following format:\n" +
                                "  deadline [description] /by DD/MM/YYYY HHmm\n\n" +
                                "For example:\n" +
                                "  deadline run /by 26/09/2024 1800");
                        System.out.println("------------------------------------------");
                    }
                    break;
                case EVENT:
                    try {
                        String[] splitPartsEvent = input.split("/from");

                        if (splitPartsEvent.length < 2) {
                            throw new JustbotException("Hey man, you have provided me an invalid format for an event.\n" +
                                    "Please enter the event in the following format:\n" +
                                    "  event [description] /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm\n\n" +
                                    "For example:\n" +
                                    "  event meeting /from 26/09/2024 1400 /to 26/09/2024 1600");
                        }
                        String commandAndDescriptionEvent = splitPartsEvent[0].trim();
                        String startAndEnd = splitPartsEvent[1].trim();
                        String eventDescription = commandAndDescriptionEvent.substring(5).trim();

                        String[] splitStartEnd = startAndEnd.split("/to");
                        if (splitStartEnd.length < 2) {
                            throw new JustbotException("Hey man, you have provided me an invalid format for event timing.\n" +
                                    "Please enter the event in the following format:\n" +
                                    "  event [description] /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm\n\n" +
                                    "For example:\n" +
                                    "  event meeting /from 26/09/2024 1400 /to 26/09/2024 1600");
                        }
                        String eventStart = splitStartEnd[0].trim();
                        String eventEnd = splitStartEnd[1].trim();

                        if (eventDescription.isBlank() && eventStart.isBlank() && eventEnd.isBlank()) {
                            throw new JustbotException("Hey man the event and start/end cannot be blank!");
                        } else if (eventDescription.isBlank()) {
                            throw new JustbotException("Hey man the description cannot be blank!");
                        } else if (eventStart.isBlank()) {
                            throw new JustbotException("Hey man the start of the event cannot be blank!");
                        } else if (eventEnd.isBlank()) {
                            throw new JustbotException("Hey man the end of the event cannot be blank!");
                        }

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

                        if (!eventStart.matches("\\d{2}/\\d{2}/\\d{4} \\d{4}") || !eventEnd.matches("\\d{2}/\\d{2}/\\d{4} \\d{4}")) {
                            throw new JustbotException("Event date and time must be in the format: dd/MM/yyyy HHmm");
                        }
                        LocalDateTime startDateTime = LocalDateTime.parse(eventStart, formatter);
                        LocalDateTime endDateTime = LocalDateTime.parse(eventEnd, formatter);

                        if (startDateTime.isAfter(endDateTime)) {
                            throw new JustbotException("Hey man, why is the event start time after the event end time?");
                        }

                        Commands.addTask(tasks, new Event(eventDescription, startDateTime, endDateTime));
                        taskFileHandler.saveTasks(tasks);
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man, you have provided me an invalid format for event timing.\n" +
                                "Please enter the event in the following format:\n" +
                                "  event [description] /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm\n\n" +
                                "For example:\n" +
                                "  event meeting /from 26/09/2024 1400 /to 26/09/2024 1600");
                        System.out.println("------------------------------------------");
                    }
                    break;
                case TODO:
                    try {
                        String[] splitPartsTodo = input.split(" ", 2);

                        if (splitPartsTodo.length < 2) {
                            throw new JustbotException("Hey man you have provided me an invalid format for todo.\n" +
                                    "Use the format: todo [description]");
                        }

                        String description = splitPartsTodo[1];
                        if (description.isBlank()) {
                            throw new JustbotException("Hey man the description cannot be blank!");
                        }
                        Commands.addTask(tasks, new Todo(description));
                        taskFileHandler.saveTasks(tasks);
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man the format is wrong. Make sure to follow the format:\n"
                                + "todo [description]");
                        System.out.println("------------------------------------------");
                    }
                    break;
                case DELETE:
                    try {
                        String[] splitInputDelete = input.split(" ");

                        if (splitInputDelete.length < 2) {
                            throw new JustbotException("Hey man you have provided me an invalid format for delete.\n" +
                                    "Use the format: delete [task number]");
                        }
                        int deleteNumber = Integer.parseInt(splitInputDelete[1]);
                        if (deleteNumber < 1 || deleteNumber > tasks.size()) {
                            throw new IndexOutOfBoundsException("Hey man there is no such task");
                        }
                        Commands.deleteTask(tasks, deleteNumber);
                        taskFileHandler.saveTasks(tasks);
                    } catch (NumberFormatException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man please input a number for the task number!");
                        System.out.println("------------------------------------------");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man there is no such task!");
                        System.out.println("------------------------------------------");
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    }
                    break;
                default:
                    System.out.println("------------------------------------------");
                    System.out.println("Hey man please use one of these valid commands\n" + "list\n" + "delete [task number]\n" + "mark [task number]\n" + "unmark [task number]\n" + "todo [description]\n" + "deadline [description] /by DD/MM/YYYY HHmm\n" + "event [description] /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm\n");
                    System.out.println("------------------------------------------");
            }
        }
    }
}
