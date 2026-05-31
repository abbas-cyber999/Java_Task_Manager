package util;

import model.Task;

import java.io.*;
import java.util.ArrayList;

/**
 * Saves and loads tasks with Java serialization.
 *
 * @author Abbas
 */
public class TaskStorage {

    private static final String FILE_NAME = "tasks.ser";

    /**
     * Checks if the save file exists.
     *
     * @return true if the file exists
     */
    public boolean saveFileExists() {
        return new File(FILE_NAME).exists();
    }

    /**
     * Saves tasks to a file.
     *
     * @param tasks task list
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from a file.
     *
     * @return saved task list
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() {
        if (!saveFileExists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}