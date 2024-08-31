package wansbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

import java.time.LocalDate;

import wansbot.tasks.Deadlined;
import wansbot.tasks.Events;
import wansbot.tasks.InputEmptyException;
import wansbot.tasks.NotANumMarkingException;
import wansbot.tasks.TaskList;
import wansbot.tasks.Todos;
import wansbot.ui.UI;

public class WansBot {
    private final String HR = "----------------------------------------------------------------------";
    TaskList userTaskList = new TaskList();
    UI ui = new UI();

    // Method that deals with empty inputs by throwing wansbot.tasks.InputEmptyException
    private  void emptyInput(String userInput) throws InputEmptyException {
        if (userInput.strip().equalsIgnoreCase("todos") ||
            userInput.strip().equalsIgnoreCase("deadline") ||
            userInput.strip().equalsIgnoreCase("event") ||
            userInput.strip().equalsIgnoreCase("mark") ||
            userInput.strip().equalsIgnoreCase("unmark") ||
            userInput.strip().equalsIgnoreCase("remove")) {
            throw new InputEmptyException(userInput);
        }
    }

    // Method that throws NumberFormatException and custom wansbot.tasks.NotANumMarkingException
    private void notNumInput(String userInput, int taskListSize) throws NumberFormatException,
            NotANumMarkingException, NullPointerException {
        if (userInput.startsWith("unmark")) {
            int posTask = Integer.parseInt(userInput.substring(7));
            if (posTask > taskListSize || posTask < 1) {
                throw new NotANumMarkingException(posTask);
            }
        } else if (userInput.startsWith("mark")) {
            int posTask = Integer.parseInt(userInput.substring(5));
            if (posTask > taskListSize || posTask < 1) {
                throw new NotANumMarkingException(posTask);
            }
        } else if (userInput.startsWith("remove")) {
            int posTask = Integer.parseInt(userInput.substring(7));
            if (posTask > taskListSize || posTask < 1) {
                throw new NotANumMarkingException(posTask);
            }
        }
    }

    // Method that throws custom wansbot.tasks.InputEmptyException for deadlineds
    private void missingInputDeadline(String userInput) {
        String[] splitUser = userInput.split( " /by ", 2);
        if (splitUser.length < 2) {
            throw new InputEmptyException(userInput, "/by");
        }
    }

    // Method that throws custom wansbot.tasks.InputEmptyException for events
    private void missingInputEvent(String userInput) {
        String[] splitUserStartDate = userInput.split(" /from ", 3);
        if (splitUserStartDate.length < 2) {
            throw new InputEmptyException(userInput, "/from");
        }
        String[] splitUserEndDate = splitUserStartDate[1].split( " /to ", 2);
        if (splitUserEndDate.length < 2) {
            throw new InputEmptyException(userInput, "/to");
        }
    }

    // Handles marking tasks function
    private void markTasks(String userInput) {
        try {
            notNumInput(userInput, userTaskList.numOfTasks());
            int posTask = Integer.parseInt(userInput.substring(5)) - 1;
            userTaskList.number(posTask).finish();
            System.out.println(HR + "\nWans:"
                    + "\nNice! I've marked\n"
                    + userTaskList.number(posTask).toString()
                    + " as completed\n" + HR);
        } catch (NullPointerException e) {
            System.out.println(HR + "\nWans:\n"
                    + "You need to put something in your Task List!"
                    + "!\n" + HR);
        } catch (NumberFormatException e) {
            System.out.println(HR + "\nWans:\n"
                    + "You need to input a single space, followed by a number after mark"
                    + "!\n" + HR);
        } catch (NotANumMarkingException e) {
            System.out.println(HR + "\nWans:\n"
                    + "You need to input a valid number that exists in your TaskList!"
                    + "\n" + HR);
        }
    }

    // Handles unmarking tasks function
    private void unmarkTasks(String userInput) {
        try {
            notNumInput(userInput, userTaskList.numOfTasks());
            int posTask = Integer.parseInt(userInput.substring(7)) - 1;
            userTaskList.number(posTask).unfinish();
            System.out.println(HR + "\nWans:"
                    + "\nOkay, so you lied! I've marked\n"
                    + userTaskList.number(posTask).toString()
                    + " as uncompleted\n" + HR);
        } catch (NumberFormatException e) {
            System.out.println(HR + "\nWans:\n"
                    + "You need to input a single space, followed by a number after mark"
                    + "!\n" + HR);
        } catch (NotANumMarkingException e) {
            System.out.println(HR + "\nWans:\n"
                    + "You need to input a valid number that exists in your TaskList!"
                    + "\n" + HR);
                }
    }

    // Handles adding Todos to the task list
    private void addTodos(String userInput) {
        Todos newTodo = new Todos(userInput.substring(5));
        userTaskList.add(newTodo);
        System.out.println(HR + "\nWans:\n"
                + "Ok! I've added " + newTodo.toString()
                + "\n" + HR);
    }

    // Handles adding Deadlineds to the task list
    private void addDeadlined(String userInput) {
        try {
            missingInputDeadline(userInput);
            String[] splitUser = userInput.split( " /by ", 2);
            Deadlined newDeadlined = new Deadlined(splitUser[0].substring(8)
                    , LocalDate.parse(splitUser[1].trim()));
            userTaskList.add(newDeadlined);
            System.out.println(HR + "\nWans:\n"
                    + "Ok! I've added " + newDeadlined.toString()
                    + "\n" + HR);
        } catch(InputEmptyException e) {
            ui.handleDeadlineFormat();
        } catch (DateTimeParseException e) {
            System.out.println(HR + "\nWans:\n"
                    + "Your date needs to be in the form YYYY-MM-DD! It needs to be a \n valid date too please!"
                    + "\n" + HR);
        }
    }

    // Handles adding Events to the task list
    private void addEvent(String userInput) {
        try {
            missingInputEvent(userInput);
            String[] splitUserStartDate = userInput.split(" /from ", 3);
            String[] splitUserEndDate = splitUserStartDate[1].split( " /to ", 2);
            Events newEvent = new Events(splitUserStartDate[0].substring(5),
                    LocalDate.parse(splitUserEndDate[0].trim()),
                    LocalDate.parse(splitUserEndDate[1].trim()));
            userTaskList.add(newEvent);
            System.out.println(HR + "\nWans:\n"
                    + "Ok! I've added " + newEvent.toString()
                    + "\n" + HR);
        } catch (InputEmptyException e) {
            ui.handleEventFormat();
        } catch (DateTimeParseException e) {
            ui.handleDateTimeException();
        }
    }

    // Handles remove function of bot
    private void removeTask(String userInput) {
        try {
            notNumInput(userInput, userTaskList.numOfTasks());
            int posTask = Integer.parseInt(userInput.substring(7)) - 1;
            System.out.println(HR + "\nWans:\n"
                    + "Ok! I've removed " + userTaskList.number(posTask)
                    + "\n" + HR);
            userTaskList.removeTask(posTask);
        } catch (NullPointerException e) {
            System.out.println(HR + "\nWans:\n"
                    + "You need to put something in your Task List!"
                    + "!\n" + HR);
        }catch (NumberFormatException e) {
            System.out.println(HR + "\nWans:\n"
                    + "You need to input a single space, followed by a number after remove"
                    + "!\n" + HR);
        } catch (NotANumMarkingException e) {
            System.out.println(HR + "\nWans:\n"
                    + "You need to input a valid number that exists in your TaskList!"
                    + "\n" + HR);
        }
    }

    // Handles saving of tasks into user machine
    private void saveTasks() {
        try {
            if (!Files.exists(Paths.get("data"))) {
                File directory = new File("./data");
                directory.mkdirs();
            }
            File file = new File("data", "tasklist.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < userTaskList.numOfTasks(); i++) {
                writer.write(userTaskList.getTask(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(HR + "\nWans:\n"
                    + "I can't seem to save your tasks!"
                    + "\n" + HR);
        }
    }

    // Handles parsing of task list to load tasks
    private void returnTask(String fileInput) {
        String[] splitInput = fileInput.split(" ");
        String typeTask = splitInput[1];
        String nameTask = "";
        switch (typeTask) {
            case "T":
                if (fileInput.contains("[ X ]")) {
                    nameTask = fileInput.substring(11);
                    Todos next = new Todos(nameTask);
                    next.finish();
                    userTaskList.add(next);
                } else {
                    nameTask = fileInput.substring(11);
                    Todos next = new Todos(nameTask);
                    userTaskList.add(next);
                }
                break;
            case "D":
                String[] deadlineSplit = fileInput.split("by: ");
                LocalDate deadline = LocalDate.parse(deadlineSplit[1].substring(0,
                        deadlineSplit[1].length() - 1).trim(), DateTimeFormatter.ofPattern("MMM d yyyy"));
                nameTask = deadlineSplit[0].substring(11, deadlineSplit[0].length() - 1);
                if (fileInput.contains("[ X ]")) {
                    Deadlined next = new Deadlined(nameTask, deadline);
                    next.finish();
                    userTaskList.add(next);
                } else {
                    Deadlined next = new Deadlined(nameTask, deadline);
                    userTaskList.add(next);
                }
                break;
            case "E":
                String[] splitUserStartDate = fileInput.split("from: ", 3);
                String[] splitUserEndDate = splitUserStartDate[1].split( "to: ", 2);
                if (fileInput.contains("[ X ]")) {
                    Events next = new Events(splitUserStartDate[0].substring(11, splitUserStartDate[0].length() - 2),
                            LocalDate.parse(splitUserEndDate[0].substring(0, splitUserEndDate[0].length() -1).trim(),
                                    DateTimeFormatter.ofPattern("MMM d yyyy")),
                            LocalDate.parse(splitUserEndDate[1].substring(0, splitUserEndDate[1].length() - 2).trim(),
                                    DateTimeFormatter.ofPattern("MMM d yyyy")));
                    next.finish();
                    userTaskList.add(next);
                } else {
                    Events next = new Events(splitUserStartDate[0].substring(11, splitUserStartDate[0].length() - 2),
                            LocalDate.parse(splitUserEndDate[0].substring(0, splitUserEndDate[0].length() -1).trim(),
                                    DateTimeFormatter.ofPattern("MMM d yyyy")),
                            LocalDate.parse(splitUserEndDate[1].substring(0, splitUserEndDate[1].length() - 2).trim(),
                                    DateTimeFormatter.ofPattern("MMM d yyyy")));
                    userTaskList.add(next);
                }
                break;
        }
    }

    // Handles loading of task function from use machine
    private void loadTasks() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./data/tasklist.txt"));
            String line = reader.readLine();
            while (line != null) {
                returnTask(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nWans:\n"
                    + "You don't have files to load!"
                    + "\n" + HR);
        } catch (IOException e) {
            System.out.println(HR + "\nWans:\n"
                    + "I can't seem to load your tasks!"
                    + "\n" + HR);;
        }
    }

    // Handles finding tasks of a specific date
    private void findTaskDate(String userInput) {
        try {
            String[] splitDate = userInput.split(" ");
            TaskList filteredList = new TaskList();
            for (int i = 0; i < userTaskList.numOfTasks(); i++) {
                if (userTaskList.getTask(i) instanceof Events) {
                    Events event = (Events) userTaskList.getTask(i);
                    if (event.isBetweenDate(LocalDate.parse(splitDate[1]))) {
                        filteredList.add(event);
                    }
                } else if (userTaskList.getTask(i) instanceof Deadlined) {
                    Deadlined deadlined = (Deadlined) userTaskList.getTask(i);
                    if (deadlined.isOnDate(LocalDate.parse(splitDate[1]))) {
                        filteredList.add(deadlined);
                    }
                }
            }
            if (filteredList.numOfTasks() != 0) {
                System.out.println(HR + "\nWans:"
                        + "\nHere are your tasks on " + LocalDate.parse(splitDate[1]).
                        format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n"
                        + userTaskList.toString());
                System.out.println("You have " + userTaskList.numOfTasks() + " tasks " + "on "
                        + LocalDate.parse(splitDate[1]).
                        format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + "\n" + HR);
            } else {
                System.out.println(HR + "\nWans:"
                        + "\n You have no tasks on "
                        + LocalDate.parse(splitDate[1]).
                        format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + "!" + "\n" + HR);
            }
        } catch (DateTimeParseException e) {
            ui.handleDateTimeException();
        }
    }

    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ui.introduceToUser();
        loadTasks();

        while (true) {
            System.out.println("User: ");
            String userInput = sc.nextLine();
            try {
                emptyInput(userInput);
            } catch (InputEmptyException e) {
                System.out.println(HR + "\nWans:\n"
                        + "You need to input something after " + userInput
                        + "!\n" + HR);
                continue;
            }

            String command = userInput.split(" ")[0];

            switch (command) {
                case "list":
                    ui.handleListingTask(userTaskList);
                    break;
                case "mark":
                    markTasks(userInput);
                    break;
                case "unmark":
                    unmarkTasks(userInput);
                    break;
                case "todos":
                    addTodos(userInput);
                    break;
                case "deadline":
                    addDeadlined(userInput);
                    break;
                case "event":
                    addEvent(userInput);
                    break;
                case "remove":
                    removeTask(userInput);
                    break;
                case "save":
                    saveTasks();
                    break;
                case "load":
                    loadTasks();
                    break;
                case "find":
                    findTaskDate(userInput);
                    break;
                case "bye":
                    ui.handleGoodbye();
                    break;
                default:
                    ui.handleUnrecognisedInput(userInput);
            }
        }
    }
}
