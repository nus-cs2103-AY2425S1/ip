package Tasks;

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
     public boolean isOccuringOnDate(LocalDate date) { return false; }
     @Override
     public String toString() {
          String checkBox = isCompleted ? "[X]" : "[ ]";
          return checkBox + " " + name;
     }
}
