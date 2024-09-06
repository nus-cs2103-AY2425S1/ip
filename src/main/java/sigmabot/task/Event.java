package sigmabot.task;

import java.util.Scanner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;


/**
 * The {@code Event} class represents an event with a name, description, start time, end time, and location.
 * It extends the {@link Task} class to include specific details pertinent to events.
 * This class provides constructors for creating events and methods for user input creation and string representation.
 */
public class Event extends Task {
    private String location;
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructs a new {@code Event} object with the specified name, description, start time, end time, and location.
     *
     * @param name The name of the event.
     * @param description The description of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     * @param location The location of the event.
     */
    public Event(String name, String description, LocalDate startTime, LocalDate endTime, String location) {
        super(name, description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    /**
     * Constructs a new {@code Event} object with the specified name, description, start time, end time, location, and completion status.
     *
     * @param name The name of the event.
     * @param description The description of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     * @param location The location of the event.
     * @param isDone The completion status of the event. If {@code true}, the event is marked as done; otherwise, it is not done.
     */
    public Event(String name, String description, LocalDate startTime, LocalDate endTime, String location, boolean isDone) {
        super(name, description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.isDone = isDone;
    }
    /**
     * Prompts the user to enter the details for a new {@code Event} and creates a new {@code Event} object.
     * (This method was for the CLI version of the method).
     *
     * @param sc The {@link Scanner} object used to read user input.
     * @return A new {@code Event} object created based on user input.
     */
    public static Event createEvent(Scanner sc) {
        System.out.println("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.println("Enter description: ");
        String description = sc.nextLine().trim();
        LocalDate startTime = null;
        LocalDate endTime = null;
        String location;
        // Loop to prompt user for a valid start time
        while (true) {
            try {
                System.out.println("Enter start time (yyyy-MM-dd): ");
                startTime = LocalDate.parse(sc.nextLine().trim());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in the format yyyy-MM-dd.");
            }
        }
        // Loop to prompt user for a valid end time
        while (true) {
            try {
                System.out.println("Enter end time (yyyy-MM-dd): ");
                endTime = LocalDate.parse(sc.nextLine().trim());
                if (endTime.isBefore(startTime)) {
                    System.out.println("End time cannot be before start time. Please enter a valid end time.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in the format yyyy-MM-dd.");
            }
        }

        System.out.println("Enter location: ");
        location = sc.nextLine().trim();
        return new Event(name, description, startTime, endTime, location);
    }

    /**
     * Creates a new {@code Event} object using GUI input components and stores it in the provided map.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     * @param taskMap     The map to store the created {@code Event} object.
     */
    /**
     * Creates a new {@code Event} object using GUI input components and stores it in the provided map.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     * @param taskMap     The map to store the created {@code Event} object.
     */
    public static void createEventGUI(TextArea displayArea, TextField inputField, Map<String, Task> taskMap) {
        displayArea.appendText("Enter name for Event or '/exit' to cancel:\n");

        // Handle name input
        inputField.setOnAction(event -> {
            String name = inputField.getText().trim();
            inputField.clear();

            if (name.equalsIgnoreCase("/exit")) {
                displayArea.appendText("Event creation canceled.\n");
                resetInputHandler(displayArea, inputField, taskMap);  // Reset input handler
                return;
            }

            displayArea.appendText("Enter description for Event or '/exit' to cancel:\n");

            // Handle description input
            inputField.setOnAction(eventDesc -> {
                String description = inputField.getText().trim();
                inputField.clear();

                if (description.equalsIgnoreCase("/exit")) {
                    displayArea.appendText("Event creation canceled.\n");
                    resetInputHandler(displayArea, inputField, taskMap);  // Reset input handler
                    return;
                }

                displayArea.appendText("Enter start time (yyyy-MM-dd) for Event or '/exit' to cancel:\n");

                // Handle start time input
                inputField.setOnAction(eventStart -> {
                    String startStr = inputField.getText().trim();
                    inputField.clear();

                    if (startStr.equalsIgnoreCase("/exit")) {
                        displayArea.appendText("Event creation canceled.\n");
                        resetInputHandler(displayArea, inputField, taskMap);  // Reset input handler
                        return;
                    }

                    try {
                        LocalDate startTime = LocalDate.parse(startStr);
                        displayArea.appendText("Enter end time (yyyy-MM-dd) for Event or '/exit' to cancel:\n");

                        // Handle end time input
                        inputField.setOnAction(eventEnd -> {
                            String endStr = inputField.getText().trim();
                            inputField.clear();

                            if (endStr.equalsIgnoreCase("/exit")) {
                                displayArea.appendText("Event creation canceled.\n");
                                resetInputHandler(displayArea, inputField, taskMap);  // Reset input handler
                                return;
                            }

                            try {
                                LocalDate endTime = LocalDate.parse(endStr);
                                if (endTime.isBefore(startTime)) {
                                    displayArea.appendText("End time cannot be before start time. Please enter a valid end time or '/exit' to cancel:\n");
                                } else {
                                    displayArea.appendText("Enter location for Event or '/exit' to cancel:\n");

                                    // Handle location input
                                    inputField.setOnAction(eventLocation -> {
                                        String location = inputField.getText().trim();
                                        inputField.clear();

                                        if (location.equalsIgnoreCase("/exit")) {
                                            displayArea.appendText("Event creation canceled.\n");
                                            resetInputHandler(displayArea, inputField, taskMap);  // Reset input handler
                                            return;
                                        }

                                        Event newEvent = new Event(name, description, startTime, endTime, location);
                                        taskMap.put(name, newEvent);  // Store the new Event in the map
                                        displayArea.appendText("Event created: " + newEvent.toString() + "\n");
                                        resetInputHandler(displayArea, inputField, taskMap);  // Reset input handler
                                    });
                                }
                            } catch (DateTimeParseException e) {
                                displayArea.appendText("Invalid date format. Please enter in the format yyyy-MM-dd.\n");
                            }
                        });
                    } catch (DateTimeParseException e) {
                        displayArea.appendText("Invalid date format. Please enter in the format yyyy-MM-dd.\n");
                    }
                });
            });
        });
    }

    // Utility method to reset input handler
    private static void resetInputHandler(TextArea displayArea, TextField inputField, Map<String, Task> taskMap) {
        // Re-enable the main input handler
        inputField.setOnAction(event -> {
            String input = inputField.getText().trim();
            inputField.clear();

            if (input.equalsIgnoreCase("/exit")) {
                displayArea.appendText("Finished adding tasks.\n");
                return;
            }

            switch (input.toLowerCase()) {
            case "todo":
                Todo.createTodoGUI(displayArea, inputField, taskMap);
                break;
            case "deadline":
                Deadline.createDeadlineGUI(displayArea, inputField, taskMap);
                break;
            case "event":
                createEventGUI(displayArea, inputField, taskMap);
                break;
            default:
                displayArea.appendText("Invalid task type. Please enter 'todo', 'deadline', or 'event'.\n");
            }
        });
    }


    public String getLocation() {
        return location;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDate getEndTime() {
        return endTime;
    }

    /**
     * Returns a string representation of the {@code Event} task.
     * The format includes the type of task (Event) and the information about its completion status, name, description, start time, end time, and location.
     *
     * @return A string representation of the {@code Event} task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() +
                "\n\tStart Time: " + startTime +
                "\n\tEnd Time: " + endTime +
                "\n\tLocation: " + location;
    }
}
