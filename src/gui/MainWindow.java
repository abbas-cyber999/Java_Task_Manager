package gui;

import model.Task;
import util.TaskStorage;
import util.UIConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Main window of the ToDo application.
 *
 * @author Abbas
 */
public class MainWindow {

    private JFrame frame;

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel inputPanel;
    private JPanel todoPanel;
    private JPanel donePanel;

    private JLabel counterLabel;
    private TaskTextField taskField;

    private AddButton addButton;
    private DeleteButton deleteButton;
    private AppButton doneButton;

    private JProgressBar progressBar;

    private ArrayList<Task> tasks;
    private ArrayList<JCheckBox> todoBoxes;
    private ArrayList<JCheckBox> doneBoxes;

    private TaskStorage storage;

    /**
     * Creates the main window.
     */
    public MainWindow() {
        storage = new TaskStorage();
        tasks = storage.loadTasks();
        todoBoxes = new ArrayList<>();
        doneBoxes = new ArrayList<>();

        createDefaultTasks();
        createFrame();
        createComponents();
        setupFrame();
        refreshTasks();
    }

    /**
     * Creates example tasks when the program starts for the first time.
     */
    private void createDefaultTasks() {
        if (!storage.saveFileExists()) {
            tasks.add(new Task("Java lernen"));
            tasks.add(new Task("Mockup abgeben"));
            storage.saveTasks(tasks);
        }
    }

    /**
     * Creates the frame.
     */
    private void createFrame() {
        frame = new JFrame();
        frame.setTitle("Professional ToDo App");
        frame.setSize(620, 580);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Creates all components.
     */
    private void createComponents() {
        createMainPanel();
        createHeaderPanel();
        createInputPanel();
        createTaskPanels();

        mainPanel.add(headerPanel);
        mainPanel.add(inputPanel);
        mainPanel.add(todoPanel);
        mainPanel.add(donePanel);
    }

    /**
     * Creates the main panel.
     */
    private void createMainPanel() {
        mainPanel = new JPanel(null);
        mainPanel.setBackground(UIConstants.BACKGROUND);
    }

    /**
     * Creates the header panel.
     */
    private void createHeaderPanel() {
        headerPanel = createCard(30, 20, 540, 90);
        headerPanel.setBackground(UIConstants.HEADER);

        HeaderLabel titleLabel = new HeaderLabel();
        titleLabel.setBounds(25, 15, 300, 35);

        JLabel subtitleLabel = new JLabel("Aufgaben verwalten wie ein Profi");
        subtitleLabel.setBounds(27, 50, 300, 25);
        subtitleLabel.setForeground(new Color(189, 195, 199));
        subtitleLabel.setFont(UIConstants.NORMAL_FONT);

        counterLabel = new JLabel("Offen: 0 | Erledigt: 0");
        counterLabel.setBounds(360, 25, 170, 30);
        counterLabel.setForeground(Color.WHITE);
        counterLabel.setFont(UIConstants.BOLD_FONT);

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(360, 58, 150, 12);

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);
        headerPanel.add(counterLabel);
        headerPanel.add(progressBar);
    }

    /**
     * Creates the input panel.
     */
    private void createInputPanel() {
        inputPanel = createCard(30, 130, 540, 100);

        JLabel inputLabel = new JLabel("Neue Aufgabe");
        inputLabel.setBounds(25, 15, 130, 25);
        inputLabel.setFont(UIConstants.BOLD_FONT);

        taskField = new TaskTextField();
        taskField.setBounds(25, 50, 210, 35);

        addButton = new AddButton();
        addButton.setBounds(245, 50, 100, 35);

        doneButton = new AppButton("Erledigt", UIConstants.BLUE);
        doneButton.setBounds(355, 50, 80, 35);

        deleteButton = new DeleteButton();
        deleteButton.setBounds(445, 50, 80, 35);

        addButton.addActionListener(e -> addTask());
        doneButton.addActionListener(e -> markSelectedAsDone());
        deleteButton.addActionListener(e -> deleteSelectedTasks());

        inputPanel.add(inputLabel);
        inputPanel.add(taskField);
        inputPanel.add(addButton);
        inputPanel.add(doneButton);
        inputPanel.add(deleteButton);
    }

    /**
     * Creates task panels.
     */
    private void createTaskPanels() {
        todoPanel = createCard(30, 250, 255, 260);
        donePanel = createCard(315, 250, 255, 260);
    }

    /**
     * Creates a card panel.
     *
     * @param x x position
     * @param y y position
     * @param width panel width
     * @param height panel height
     * @return new panel
     */
    private JPanel createCard(int x, int y, int width, int height) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, width, height);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return panel;
    }

    /**
     * Adds a new task.
     */
    private void addTask() {
        String text = taskField.getText().trim();

        if (text.isEmpty()) {
            showMessage("Bitte geben Sie eine Aufgabe ein!");
            return;
        }

        tasks.add(new Task(text));
        taskField.setText("");
        saveAndRefresh();
    }

    /**
     * Marks selected tasks as done.
     */
    private void markSelectedAsDone() {
        boolean changed = false;

        for (JCheckBox box : todoBoxes) {
            if (box.isSelected()) {
                Task task = (Task) box.getClientProperty("task");
                task.markAsDone();
                changed = true;
            }
        }

        if (!changed) {
            showMessage("Bitte wählen Sie zuerst eine offene Aufgabe aus!");
            return;
        }

        saveAndRefresh();
    }

    /**
     * Deletes selected tasks.
     */
    private void deleteSelectedTasks() {
        boolean deleted = false;

        deleted = deleteSelectedFromList(todoBoxes) || deleted;
        deleted = deleteSelectedFromList(doneBoxes) || deleted;

        if (!deleted) {
            showMessage("Bitte wählen Sie zuerst eine Aufgabe zum Löschen aus!");
            return;
        }

        saveAndRefresh();
    }

    /**
     * Deletes selected tasks from a checkbox list.
     *
     * @param boxes checkbox list
     * @return true if at least one task was deleted
     */
    private boolean deleteSelectedFromList(ArrayList<JCheckBox> boxes) {
        boolean deleted = false;

        for (JCheckBox box : boxes) {
            if (box.isSelected()) {
                Task task = (Task) box.getClientProperty("task");
                tasks.remove(task);
                deleted = true;
            }
        }

        return deleted;
    }

    /**
     * Saves and refreshes the program.
     */
    private void saveAndRefresh() {
        storage.saveTasks(tasks);
        refreshTasks();
    }

    /**
     * Refreshes the task lists.
     */
    private void refreshTasks() {
        clearPanels();
        addPanelTitles();
        fillPanels();
        updateCounter();
        repaintPanels();
    }

    /**
     * Clears panels and checkbox lists.
     */
    private void clearPanels() {
        todoPanel.removeAll();
        donePanel.removeAll();
        todoBoxes.clear();
        doneBoxes.clear();
    }

    /**
     * Adds titles to the task panels.
     */
    private void addPanelTitles() {
        TaskLabel todoTitle = new TaskLabel("Offene Aufgaben", UIConstants.BLUE);
        todoTitle.setBounds(20, 15, 220, 30);

        TaskLabel doneTitle = new TaskLabel("Erledigte Aufgaben", new Color(39, 174, 96));
        doneTitle.setBounds(20, 15, 220, 30);

        todoPanel.add(todoTitle);
        donePanel.add(doneTitle);
    }

    /**
     * Fills both task panels.
     */
    private void fillPanels() {
        int todoY = 55;
        int doneY = 55;

        for (Task task : tasks) {
            if (task.isDone()) {
                doneY = addTaskBox(donePanel, doneBoxes, task, doneY);
            } else {
                todoY = addTaskBox(todoPanel, todoBoxes, task, todoY);
            }
        }
    }

    /**
     * Adds a task checkbox to a panel.
     *
     * @param panel target panel
     * @param boxes checkbox list
     * @param task task object
     * @param y y position
     * @return next y position
     */
    private int addTaskBox(JPanel panel, ArrayList<JCheckBox> boxes, Task task, int y) {
        JCheckBox box = new JCheckBox(createTaskText(task));
        box.setBounds(20, y, 210, 30);
        box.setFont(UIConstants.NORMAL_FONT);
        box.setBackground(Color.WHITE);
        box.setForeground(task.isDone() ? new Color(39, 174, 96) : UIConstants.TEXT_DARK);
        box.putClientProperty("task", task);

        boxes.add(box);
        panel.add(box);

        return y + 35;
    }

    /**
     * Creates the visible task text.
     *
     * @param task task object
     * @return task text
     */
    private String createTaskText(Task task) {
        if (task.isDone()) {
            return "<html><s>✔ " + task.getText() + "</s></html>";
        }

        return task.getText();
    }

    /**
     * Updates counter and progress bar.
     */
    private void updateCounter() {
        int done = countDoneTasks();
        int total = tasks.size();
        int open = total - done;

        counterLabel.setText("Offen: " + open + " | Erledigt: " + done);

        if (total == 0) {
            progressBar.setValue(0);
        } else {
            progressBar.setValue((done * 100) / total);
        }
    }

    /**
     * Counts done tasks.
     *
     * @return number of done tasks
     */
    private int countDoneTasks() {
        int counter = 0;

        for (Task task : tasks) {
            if (task.isDone()) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Repaints panels.
     */
    private void repaintPanels() {
        todoPanel.revalidate();
        todoPanel.repaint();

        donePanel.revalidate();
        donePanel.repaint();
    }

    /**
     * Shows a message.
     *
     * @param message message text
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    /**
     * Shows the frame.
     */
    private void setupFrame() {
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}