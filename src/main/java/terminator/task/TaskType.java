package terminator.task;

public enum TaskType {
    TODO {
        @Override
        public String toString() {
            return "T";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "E";
        }
    },
    DEADLINE {
        @Override
        public String toString() {
            return "D";
        }
    },
    UNKNOWN {
        @Override
        public String toString() {
            return "ERROR";
        }
    };

    public abstract String toString();
}
