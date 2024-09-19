package babblebot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TentativeEvent extends Task {
    private ArrayList<LocalDate[]> timeSlots;
    private LocalDate[] confirmedSlot = null; // Store confirmed start and end as LocalDate array

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a TentativeEvent with the specified description and multiple time slots.
     *
     * @param description The description of the tentative event.
     * @param timeSlots   A list of possible time slots for the event.
     */
    public TentativeEvent(String description, ArrayList<LocalDate[]> timeSlots) {
        super(description); // Call Task constructor to set description
        this.timeSlots = timeSlots;
    }

    /**
     * Gets the list of time slots for the tentative event.
     *
     * @return A list of time slots, where each slot is represented by a LocalDate[] (start and end).
     */
    public ArrayList<LocalDate[]> getTimeSlots() {
        return timeSlots;
    }

    /**
     * Confirms a time slot by its index and sets the confirmed start and end date.
     *
     * @param index The index of the slot to confirm (0-based).
     * @return true if the slot is confirmed, false otherwise.
     */
    public boolean confirmSlotByIndex(int index) {
        if (index >= 0 && index < timeSlots.size()) {
            this.confirmedSlot = timeSlots.get(index);
            return true;
        }
        return false;
    }



    /**
     * Checks if the tentative event has been confirmed.
     *
     * @return true if a time slot has been confirmed, false otherwise.
     */
    public boolean isConfirmed() {
        return confirmedSlot != null;
    }

    /**
     * Converts the TentativeEvent to a regular Event if a slot is confirmed.
     *
     * @return A new Event object if a time slot is confirmed.
     * @throws IllegalStateException if no slot has been confirmed.
     */
    public Event toEvent() {
        if (isConfirmed()) {
            DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return new Event(getDescription(), confirmedSlot[0].format(fileFormatter), confirmedSlot[1].format(fileFormatter));
        } else {
            throw new IllegalStateException("Cannot convert to Event until a slot is confirmed.");
        }
    }

    /**
     * Returns a string representation of the TentativeEvent, including the list of time slots.
     * If confirmed, it shows the confirmed time slot.
     *
     * @return A string representation of the TentativeEvent.
     */
    @Override
    public String toString() {
        if (isConfirmed()) {
            return "[E]" + super.toString() + " (Confirmed: " + confirmedSlot[0].format(formatter) + " to " + confirmedSlot[1].format(formatter) + ")";
        } else {
            StringBuilder slotsString = new StringBuilder("[Pending] [E]" + super.toString() + "\n    Tentative slots:\n");
            for (int i = 0; i < timeSlots.size(); i++) {
                slotsString.append("        ").append(i + 1).append(": ")
                        .append(timeSlots.get(i)[0].format(formatter)).append(" to ").append(timeSlots.get(i)[1].format(formatter));
                if (i < timeSlots.size() - 1) {
                    slotsString.append(",\n ");
                }
            }
            return slotsString.toString();
        }
    }

    /**
     * Converts the TentativeEvent to a format suitable for file storage.
     *
     * @return A string representation of the TentativeEvent in file format.
     */
    @Override
    public String toFileFormat() {
        StringBuilder fileFormat = new StringBuilder("P | ");
        fileFormat.append(isDone ? "1" : "0").append(" | ").append(description).append(" | ");

        if (isConfirmed()) {
            fileFormat.append(confirmedSlot[0].format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(" to ")
                    .append(confirmedSlot[1].format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } else {
            for (int i = 0; i < timeSlots.size(); i++) {
                fileFormat.append(timeSlots.get(i)[0].format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .append(" to ")
                        .append(timeSlots.get(i)[1].format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                if (i < timeSlots.size() - 1) {
                    fileFormat.append(", ");
                }
            }
        }
        return fileFormat.toString();
    }
}
