package model;

import java.io.Serializable;

/**
 * Represents one task.
 *
 * @author Abbas
 */
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    private String text;
    private boolean done;

    /**
     * Creates a new task.
     *
     * @param text task text
     */
    public Task(String text) {
        this.text = text;
        this.done = false;
    }

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return done;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        done = true;
    }
}