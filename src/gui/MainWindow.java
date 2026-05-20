package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow {

    private JFrame frame;

    private JPanel mainPanel, headerPanel, inputPanel, todoPanel, donePanel;

    private JLabel counterLabel;
    private JTextField taskField;

    private JButton addButton, deleteButton, doneButton;
    private JProgressBar progressBar;

    private ArrayList<JCheckBox> todoTasks = new ArrayList<>();
    private ArrayList<JCheckBox> doneTasks = new ArrayList<>();

    public MainWindow() {
        frame = new JFrame();
        createComponents();
        setupFrame();
    }

    private void createComponents() {

        mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(236, 240, 245));

        headerPanel = createCard(30, 20, 540, 90);
        headerPanel.setBackground(new Color(44, 62, 80));

        JLabel titleLabel = new JLabel("ToDo Dashboard");
        titleLabel.setBounds(25, 15, 300, 35);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JLabel subtitleLabel = new JLabel("Aufgaben verwalten wie ein Profi");
        subtitleLabel.setBounds(27, 50, 300, 25);
        subtitleLabel.setForeground(new Color(189, 195, 199));
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        counterLabel = new JLabel("Offen: 0 | Erledigt: 0");
        counterLabel.setBounds(360, 25, 170, 30);
        counterLabel.setForeground(Color.WHITE);
        counterLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(360, 58, 150, 12);

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);
        headerPanel.add(counterLabel);
        headerPanel.add(progressBar);

        inputPanel = createCard(30, 130, 540, 100);

        JLabel inputLabel = new JLabel("Neue Aufgabe");
        inputLabel.setBounds(25, 15, 130, 25);
        inputLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        taskField = new JTextField();
        taskField.setBounds(25, 50, 210, 35);
        taskField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        addButton = createButton("Hinzufügen", new Color(46, 204, 113));
        addButton.setBounds(245, 50, 100, 35);

        doneButton = createButton("Erledigt", new Color(52, 152, 219));
        doneButton.setBounds(355, 50, 80, 35);

        deleteButton = createButton("Löschen", new Color(231, 76, 60));
        deleteButton.setBounds(445, 50, 80, 35);

        inputPanel.add(inputLabel);
        inputPanel.add(taskField);
        inputPanel.add(addButton);
        inputPanel.add(doneButton);
        inputPanel.add(deleteButton);

        todoPanel = createCard(30, 250, 255, 260);
        donePanel = createCard(315, 250, 255, 260);

        addButton.addActionListener(e -> addTask());
        doneButton.addActionListener(e -> markSelectedAsDone());
        deleteButton.addActionListener(e -> deleteSelectedTasks());

        createTask("Java lernen");
        createTask("Mockup abgeben");

        mainPanel.add(headerPanel);
        mainPanel.add(inputPanel);
        mainPanel.add(todoPanel);
        mainPanel.add(donePanel);
    }

    private JPanel createCard(int x, int y, int width, int height) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, width, height);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return panel;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });

        return button;
    }

    private void addTask() {
        String text = taskField.getText().trim();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Bitte geben Sie eine Aufgabe ein!");
            return;
        }

        createTask(text);
        taskField.setText("");
    }

    private void createTask(String text) {
        JCheckBox task = new JCheckBox(text);
        task.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        task.setForeground(new Color(52, 73, 94));
        task.setBackground(Color.WHITE);

        todoTasks.add(task);
        refreshTasks();
    }

    private void markSelectedAsDone() {
        boolean moved = false;

        for (int i = todoTasks.size() - 1; i >= 0; i--) {
            JCheckBox task = todoTasks.get(i);

            if (task.isSelected()) {
                todoTasks.remove(i);

                task.setText("<html><s>✔ " + task.getText() + "</s></html>");
                task.setForeground(new Color(39, 174, 96));
                task.setSelected(false);

                doneTasks.add(task);
                moved = true;
            }
        }

        if (!moved) {
            JOptionPane.showMessageDialog(frame, "Bitte wählen Sie zuerst eine offene Aufgabe aus!");
        }

        refreshTasks();
    }

    private void deleteSelectedTasks() {
        boolean deleted = false;

        for (int i = todoTasks.size() - 1; i >= 0; i--) {
            if (todoTasks.get(i).isSelected()) {
                todoTasks.remove(i);
                deleted = true;
            }
        }

        for (int i = doneTasks.size() - 1; i >= 0; i--) {
            if (doneTasks.get(i).isSelected()) {
                doneTasks.remove(i);
                deleted = true;
            }
        }

        if (!deleted) {
            JOptionPane.showMessageDialog(frame, "Bitte wählen Sie zuerst eine Aufgabe zum Löschen aus!");
        }

        refreshTasks();
    }

    private void refreshTasks() {

        todoPanel.removeAll();
        donePanel.removeAll();

        JLabel todoTitle = new JLabel("📌 Offene Aufgaben");
        todoTitle.setBounds(20, 15, 210, 30);
        todoTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
        todoTitle.setForeground(new Color(41, 128, 185));

        JLabel doneTitle = new JLabel("✅ Erledigte Aufgaben");
        doneTitle.setBounds(20, 15, 220, 30);
        doneTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
        doneTitle.setForeground(new Color(39, 174, 96));

        todoPanel.add(todoTitle);
        donePanel.add(doneTitle);

        int y = 55;

        for (JCheckBox task : todoTasks) {
            task.setBounds(20, y, 210, 30);
            todoPanel.add(task);
            y += 35;
        }

        y = 55;

        for (JCheckBox task : doneTasks) {
            task.setBounds(20, y, 210, 30);
            donePanel.add(task);
            y += 35;
        }

        updateCounter();

        todoPanel.revalidate();
        todoPanel.repaint();
        donePanel.revalidate();
        donePanel.repaint();
    }

    private void updateCounter() {
        int todo = todoTasks.size();
        int done = doneTasks.size();
        int total = todo + done;

        counterLabel.setText("Offen: " + todo + " | Erledigt: " + done);

        if (total == 0) {
            progressBar.setValue(0);
        } else {
            progressBar.setValue((done * 100) / total);
        }
    }

    private void setupFrame() {
        frame.setTitle("Professional ToDo App");
        frame.setSize(620, 580);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}