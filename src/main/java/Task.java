public class Task {
     private boolean isCompleted;
     private String name;
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
     @Override
     public String toString() {
          String checkBox = isCompleted ? "[X]" : "[ ]";
          return checkBox + " " + name;
     }
}
