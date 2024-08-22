    public class Task {
        protected String description;
        protected boolean isDone;
        protected TaskType type;
        public Task(String description, TaskType type) {
            this.description = description;
            this.isDone = false;
            this.type = type;
        }

        public String getStatusIcon() {
            return (this.isDone ? "X" : " "); // mark task as done
        }

        public String getDescription() {
            return this.description;
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() +"] " + getDescription();
        }
    }
