public class Task {
    public String name;
    //public Integer number;
    public Boolean completed;
    public Integer number;
    public Task (String name, int number) {
        this.name = name;
        this.completed = false;
        this.number = number;
    }
    public int getNumber(){
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public void markAsDone() {
        this.completed = true;
    }

    public void markAsNotDone() {
        this.completed = false;
    }
    public String toString() {
        if (this.completed) {
            return "[X] " + this.name;
        }
        else {
            return "[ ] " + this.name;
        }
    }
}
