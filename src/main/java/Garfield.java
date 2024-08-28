import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Garfield {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private Ui ui;

    public Garfield(String saveFilePath){
        this.ui = new Ui();

        // Load harddisk file
        File savedFile = new File(saveFilePath);
        try {
            Scanner fileScanner = new Scanner(savedFile);
            String savedTask;
            while (fileScanner.hasNext()) {
                savedTask = fileScanner.nextLine();
                String[] taskDetails = savedTask.split(" \\| ");
                String taskType = taskDetails[0];

                switch (taskType) {
                    case "T":
                        Todo newTodo = new Todo(taskDetails[2]);
                        taskList.add(newTodo);
                        if (taskDetails[1].equals("1")) {
                            newTodo.markAsDone();
                        } else {
                            newTodo.markAsUndone();
                        }
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(taskDetails[2], Garfield.parseDateTime(taskDetails[3]));
                        taskList.add(newDeadline);
                        if (taskDetails[1].equals("1")) {
                            newDeadline.markAsDone();
                        } else {
                            newDeadline.markAsUndone();
                        }
                        break;
                    case "E":
                        Event newEvent = new Event(taskDetails[2], Garfield.parseDateTime(taskDetails[3]), Garfield.parseDateTime(taskDetails[4]));
                        taskList.add(newEvent);
                        if (taskDetails[1].equals("1")) {
                            newEvent.markAsDone();
                        } else {
                            newEvent.markAsUndone();
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            // You don't have a save file
        }
    }

    public void run() {
        this.ui.showGreeting();


        String userInput;
        while (true) {
            this.saveToHardDisk();
            userInput = this.ui.readCommand();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                if (taskList.isEmpty()) {
                    this.ui.showMessage("Your list is empty. Just like my lasagna pan. "
                            + "Are we done here, or are you going to add something?");
                } else {
                    StringBuilder listSummary = new StringBuilder("Ugh, here's what you've got so far:\n\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        listSummary.append((i + 1)).append(". ")
                                .append(taskList.get(i).toString()).append("\n");

                    }
                    listSummary.append("\nCan we be done now?");
                    this.ui.showMessage(listSummary.toString());
                }
                continue;
            }

            if (userInput.toLowerCase().startsWith("mark")) {
                String[] output = userInput.trim().split("\\s+");
                if (output.length == 2) {
                    int taskId = Integer.parseInt(output[1]);
                    if (taskId <= taskList.size()) {
                        Task task = taskList.get(taskId - 1);
                        task.markAsDone();
                        this.ui.showMessage("Nice. You actually did something. I've marked that task as done:\n\n\t"
                                + task);
                        continue;
                    }
                }
            }

            if (userInput.toLowerCase().startsWith("unmark")) {
                String[] output = userInput.trim().split("\\s+");
                if (output.length == 2) {
                    int taskId = Integer.parseInt(output[1]);
                    if (taskId <= taskList.size()) {
                        Task task = taskList.get(taskId - 1);
                        task.markAsUndone();
                        this.ui.showMessage("Oh, having second thoughts? OK, I've marked that task as not done yet:\n\n\t"
                                + task + "\n\nClearly, you're still undecided.");
                        continue;
                    }
                }
            }

            if (userInput.toLowerCase().startsWith("delete")) {
                String[] output = userInput.trim().split("\\s+");
                if (output.length == 2) {
                    int taskId = Integer.parseInt(output[1]);
                    if (taskId <= taskList.size()) {
                        Task toRemove = taskList.get(taskId - 1);
                        taskList.remove(taskId - 1);
                        this.ui.showMessage("Alright you've got 1 less task.\n\n\t"
                                + toRemove + "\n\nEnjoy the extra ‘fun’ —or whatever you call it.");
                        continue;
                    }
                }
            }

            if (userInput.toLowerCase().startsWith("todo")) {
                if (userInput.length() <= 5) {
                    this.ui.showMessage("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }
                String todoDescription = userInput.substring(5);
                if (!todoDescription.isBlank()) {
                    Todo newTodo = new Todo(todoDescription);
                    taskList.add(newTodo);
                    this.ui.showMessage("Fine. I'll add '" + todoDescription + "' to the list.\n\n\t"
                            + newTodo + "\n\nJust what you needed to boost your list to a grand total of "
                            + taskList.size() + " task" + ((taskList.size() == 1)? "" : "s") + ". Lucky you.");
                    continue;
                } else {
                    this.ui.showMessage("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }
            }

            if (userInput.toLowerCase().startsWith("deadline")) {
                if (userInput.length() <= 9) {
                    this.ui.showMessage("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }
                String deadlineDescription = userInput.substring(9);
                String[] deadlineArgs = deadlineDescription.split("/by");
                if (deadlineArgs.length != 2) {
                    this.ui.showMessage("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }
                if (!deadlineArgs[0].isBlank() && !deadlineArgs[1].isBlank()) {

                    LocalDateTime byDateTime;
                    try {
                        byDateTime = Garfield.parseDateTime(deadlineArgs[1].strip());
                    } catch (DateTimeParseException e) {
                        this.ui.showMessage("Ugh. Get your date and time in the right format. It's YYYY-MM-DD HH:MM");
                        continue;
                    }

                    Deadline newDeadline= new Deadline(deadlineArgs[0].strip(), byDateTime);
                    taskList.add(newDeadline);
                    this.ui.showMessage("Fine. I'll add '" + deadlineArgs[0].strip() + "' to the list.\n\n\t"
                            + newDeadline + "\n\nNow your list is up to " + taskList.size() + " task"
                            + ((taskList.size() == 1)? "" : "s") + ". Because who doesn't love more deadlines.");
                    continue;
                } else {
                    this.ui.showMessage("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }

            }

            if (userInput.toLowerCase().startsWith("event")) {
                if (userInput.length() <= 6) {
                    this.ui.showMessage("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }
                String eventDescription = userInput.substring(6);
                String[] eventArgs = eventDescription.split("/from");
                eventDescription = eventArgs[0];
                if (eventArgs.length == 2) {
                    eventArgs = eventArgs[1].split("/to");
                } else {
                    this.ui.showMessage("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }

                if (!eventDescription.isBlank() && !eventArgs[0].isBlank() && !eventArgs[1].isBlank()) {

                    LocalDateTime fromDateTime;
                    LocalDateTime toDateTime;
                    try {
                        fromDateTime = Garfield.parseDateTime(eventArgs[0].strip());
                        toDateTime = Garfield.parseDateTime(eventArgs[1].strip());
                    } catch (DateTimeParseException e) {
                        this.ui.showMessage("Ugh. Get your date and time in the right format. It's YYYY-MM-DD HH:MM");
                        continue;
                    }

                    Event newEvent = new Event(eventDescription.strip(),
                            fromDateTime, toDateTime);
                    taskList.add(newEvent);
                    this.ui.showMessage("Fine. I'll add '" + eventDescription + "' to the list.\n\n\t"
                            + newEvent + "\n\nYour list is now at " + taskList.size() + " task"
                            + ((taskList.size() == 1)? "" : "s") + ". Maybe you'll get around to actually doing them.");
                    continue;
                } else {
                    this.ui.showMessage("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }
            }

            this.ui.showMessage(userInput + "? I’m not sure what that means. Can you give me a bit more to work with?");
        }

        this.ui.showMessage("Finally. Try not to come back too soon.");
    }
    public static void main(String[] args) {
        new Garfield("./data/save.txt").run();
    }

    private void saveToHardDisk() {
        try (FileWriter fw = new FileWriter("./data/save.txt")) {
            String prefix = "";
            StringBuilder textToWrite = new StringBuilder();
            for (Task t : taskList) {
                textToWrite.append(prefix);
                prefix = System.lineSeparator();
                textToWrite.append(t.toSaveRepresentation());
            }
            fw.write(textToWrite.toString());
        } catch (IOException e) {
            this.ui.showMessage("Something went wrong when saving your task list.");
        }
    }

    private static LocalDateTime parseDateTime(String dateInput) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateInput, formatter);
    }
}