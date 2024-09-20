package dawn;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reminder {
    private ArrayList<Task> tasks;
    public Reminder(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Displays the upcoming deadlines within the week and any events happening today
     */
    public String showReminder() {
        StringBuilder reminder = upcomingDeadlines().append(eventsHappeningToday());
        return String.valueOf(reminder);
    }

    private StringBuilder upcomingDeadlines() {
        StringBuilder deadlines = new StringBuilder();
        deadlines.append("Reminder for upcoming deadlines: \n");
        boolean haveTasks = false;
        int counter = 1;
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i) instanceof Deadline) {
                Deadline d = (Deadline) this.tasks.get(i);
                if (d.getDate().isBefore(LocalDate.now().plusDays(7))) {
                    deadlines.append(counter + ": " + d + "\n");
                    haveTasks = true;
                    counter++;
                }
            }
        }
        if (!haveTasks) {
            deadlines.append("Yipee! There are no deadlines due this week!\n\n");
        }
        return deadlines;
    }

    private StringBuilder eventsHappeningToday() {
        StringBuilder events = new StringBuilder();
        events.append("Events happening today: \n");
        boolean haveEvents = false;
        int counter = 1;
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i) instanceof Event) {
                Event e = (Event) this.tasks.get(i);
                if (e.getDate().equals(LocalDate.now())) {
                    events.append(counter + ": " + e + "\n");
                    haveEvents = true;
                    counter++;
                }
            }
        }
        if (!haveEvents) {
            events.append("There are no events happening today!\n");
        }
        return events;
    }
}
