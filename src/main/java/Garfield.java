import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Garfield {

    private static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = """          
                   _____             __ _      _     _
                  / ____|           / _(_)    | |   | |
                 | |  __  __ _ _ __| |_ _  ___| | __| |
                 | | |_ |/ _` | '__|  _| |/ _ \\ |/ _` |
                 | |__| | (_| | |  | | | |  __/ | (_| |
                  \\_____|\\__,_|_|  |_| |_|\\___|_|\\__,_|
                """;

        String initialGreeting = "Hey. I'm\n\n" + logo
                + "\nLet's get this over with. What do you want?";
        Garfield.speak(initialGreeting);

        // Load harddisk file
        String filePath = "./data/save.txt";
        File savedFile = new File(filePath);
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
                        Deadline newDeadline = new Deadline(taskDetails[2], taskDetails[3]);
                        taskList.add(newDeadline);
                        if (taskDetails[1].equals("1")) {
                            newDeadline.markAsDone();
                        } else {
                            newDeadline.markAsUndone();
                        }
                        break;
                    case "E":
                        Event newEvent = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
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

        // Loop to get user input
        Scanner inputScanner = new Scanner(System.in);
        String userInput;
        while (true) {
            Garfield.saveToHardDisk();
            userInput = inputScanner.nextLine().strip();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                if (taskList.isEmpty()) {
                    Garfield.speak("Your list is empty. Just like my lasagna pan. "
                            + "Are we done here, or are you going to add something?");
                } else {
                    StringBuilder listSummary = new StringBuilder("Ugh, here's what you've got so far:\n\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        listSummary.append((i + 1)).append(". ")
                                .append(taskList.get(i).toString()).append("\n");

                    }
                    listSummary.append("\nCan we be done now?");
                    Garfield.speak(listSummary.toString());
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
                        Garfield.speak("Nice. You actually did something. I've marked that task as done:\n\n\t"
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
                        Garfield.speak("Oh, having second thoughts? OK, I've marked that task as not done yet:\n\n\t"
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
                        Garfield.speak("Alright you've got 1 less task.\n\n\t"
                                + toRemove + "\n\nEnjoy the extra ‘fun’ —or whatever you call it.");
                        continue;
                    }
                }
            }

            if (userInput.toLowerCase().startsWith("todo")) {
                if (userInput.length() <= 5) {
                    Garfield.speak("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }
                String todoDescription = userInput.substring(5);
                if (!todoDescription.isBlank()) {
                    Todo newTodo = new Todo(todoDescription);
                    taskList.add(newTodo);
                    Garfield.speak("Fine. I'll add '" + todoDescription + "' to the list.\n\n\t"
                    + newTodo + "\n\nJust what you needed to boost your list to a grand total of "
                    + taskList.size() + " task" + ((taskList.size() == 1)? "" : "s") + ". Lucky you.");
                    continue;
                } else {
                    Garfield.speak("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }
            }

            if (userInput.toLowerCase().startsWith("deadline")) {
                if (userInput.length() <= 9) {
                    Garfield.speak("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }
                String deadlineDescription = userInput.substring(9);
                String[] deadlineArgs = deadlineDescription.split("/by");
                if (!deadlineArgs[0].isBlank() && !deadlineArgs[1].isBlank()) {
                    Deadline newDeadline= new Deadline(deadlineArgs[0].strip(), deadlineArgs[1].strip());
                    taskList.add(newDeadline);
                    Garfield.speak("Fine. I'll add '" + deadlineArgs[0].strip() + "' to the list.\n\n\t"
                            + newDeadline + "\n\nNow your list is up to " + taskList.size() + " task"
                            + ((taskList.size() == 1)? "" : "s") + ". Because who doesn't love more deadlines.");
                    continue;
                } else {
                    Garfield.speak("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }

            }

            if (userInput.toLowerCase().startsWith("event")) {
                if (userInput.length() <= 6) {
                    Garfield.speak("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }
                String eventDescription = userInput.substring(6);
                String[] eventArgs = eventDescription.split("/from");
                eventDescription = eventArgs[0];
                eventArgs = eventArgs[1].split("/to");
                if (!eventDescription.isBlank() && !eventArgs[0].isBlank() && !eventArgs[1].isBlank()) {
                    Event newEvent = new Event(eventDescription.strip(),
                            eventArgs[0].strip(), eventArgs[1].strip());
                    taskList.add(newEvent);
                    Garfield.speak("Fine. I'll add '" + eventDescription + "' to the list.\n\n\t"
                            + newEvent + "\n\nYour list is now at " + taskList.size() + " task"
                            + ((taskList.size() == 1)? "" : "s") + ". Maybe you'll get around to actually doing them.");
                    continue;
                } else {
                    Garfield.speak("Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
                    continue;
                }
            }

            Garfield.speak(userInput + "? I’m not sure what that means. Can you give me a bit more to work with?");
        }

        Garfield.speak("Finally. Try not to come back too soon.");
    }

    private static void speak(String message) {
        Garfield.line(70);
        System.out.println(message);
        Garfield.line(70);
    }

    private static void line(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    private static void saveToHardDisk() {
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
            Garfield.speak("Something went wrong when saving your task list.");
        }
    }
}