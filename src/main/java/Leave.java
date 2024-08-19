public class Leave {
    public void speak() {
        System.out.println(new FormatText(this.toString()).wrapLines());
    }

    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}
