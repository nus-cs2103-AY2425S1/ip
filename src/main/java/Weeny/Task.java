package weeny;
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

        public void setAsDone() {
            this.isDone = true;
        }

        public void setAsNotDone() {
            this.isDone = false;
        }

        public String toOutput() {
            return " ";
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() +"] " + getDescription();
        }
    }
