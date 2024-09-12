package terminator.task;

/**
 * Enum to represent the possible types of tasks.
 * @see #TODO
 * @see #EVENT
 * @see #DEADLINE
 * @see #UNKNOWN
 */
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

    /**
     * Returns a string representation of the task type as a single character.
     * @return
     */
    public abstract String toString();
}
