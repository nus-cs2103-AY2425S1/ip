public class Task {
    protected String description;
    
    public Task(String description) {
        this.description = description;
    }

    public void mark() {
        System.out.println("test");
    }

    public String getDescription() {
        return this.description;
    }
}
