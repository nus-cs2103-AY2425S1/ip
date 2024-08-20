import java.util.ArrayList;
import java.util.Scanner;

public class Justbot {
    public static void main(String[] args) {
        final String chatbotName = "JustBot";
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = "";

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
                        Commands.markTask(tasks, markNumber);
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
                        Commands.unmarkTask(tasks, unmarkNumber);
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
                            throw new JustbotException("Hey man you have provided me an invalid format for deadline.\n" +
                                    "Use the format: deadline [description] /by [deadline]");
                        }

                        String commandAndDescriptionDeadline = splitPartsDeadline[0].trim();
                        String by = splitPartsDeadline[1];
                        String deadlineDescription = commandAndDescriptionDeadline.substring(8).trim();

                        if (deadlineDescription.isBlank() && by.isBlank()) {
                            throw new JustbotException("Hey man the description and deadline date cannot be blank!");
                        } else if (deadlineDescription.isBlank()) {
                            throw new JustbotException("Hey man the description cannot be blank!");
                        } else if (by.isBlank()) {
                            throw new JustbotException("Hey man the deadline date cannot be blank!");
                        }
                        Commands.addTask(tasks, new Deadline(deadlineDescription, by));
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man the format is wrong. Make sure to follow the format:\n"
                                + "deadline [description] /by [deadline]");
                        System.out.println("------------------------------------------");
                    }
                    break;
                case EVENT:
                    try {
                        String[] splitPartsEvent = input.split("/from");

                        if (splitPartsEvent.length < 2) {
                            throw new JustbotException("Hey man you have provided me an invalid format for event.\n" +
                                    "Use the format: event [description] /from [start] /to [end]");
                        }
                        String commandAndDescriptionEvent = splitPartsEvent[0].trim();
                        String startAndEnd = splitPartsEvent[1].trim();
                        String eventDescription = commandAndDescriptionEvent.substring(5).trim();
                        String eventStart = startAndEnd.split("/to")[0].trim();
                        String eventEnd = startAndEnd.split("/to")[1].trim();

                        if (eventDescription.isBlank() && eventStart.isBlank() && eventEnd.isBlank()) {
                            throw new JustbotException("Hey man the event and start/end cannot be blank!");
                        } else if (eventDescription.isBlank()) {
                            throw new JustbotException("Hey man the description cannot be blank!");
                        } else if (eventStart.isBlank()) {
                            throw new JustbotException("Hey man the start of the event cannot be blank!");
                        } else if (eventEnd.isBlank()) {
                            throw new JustbotException("Hey man the end of the event cannot be blank!");
                        }
                        Commands.addTask(tasks, new Event(eventDescription, eventStart, eventEnd));
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man the format is wrong. Make sure to follow the format:\n"
                                + "event [description] /from [start] /to [end]");
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
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println("Hey man the format is wrong. Make sure to follow the format:\n"
                                + "event [description] /from [start] /to [end]");
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
                    System.out.println("Hey man please provide me with a valid command!");
                    System.out.println("------------------------------------------");
            }
        }
    }
}
