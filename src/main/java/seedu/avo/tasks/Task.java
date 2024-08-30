package seedu.avo.tasks;

import java.time.LocalDate;

public abstract class Task {
     private boolean isCompleted;
     private final String name;
     public Task(String name) {
          this.name = name;
          isCompleted = false;
     }
     public void complete() {
          isCompleted  = true;
     }
     public void unComplete() {
          isCompleted = false;
     }
     public boolean isOccurringOnDate(LocalDate date) { return false; }
     public String formatData() {
          return String.format("%d : %s", isCompleted ? 1 : 0, name);
     }
     public boolean matchName(String name) {
          return this.name.contains(name);
     }
     @Override
     public String toString() {
          String checkBox = isCompleted ? "[X]" : "[ ]";
          return checkBox + " " + name;
     }
     @Override
     public boolean equals(Object o) {
          if (o instanceof Task) {
               return name.equals(((Task) o).name);
          }
          return false;
     }
}
