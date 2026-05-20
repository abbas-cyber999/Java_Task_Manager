package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

public class MainWindow {

    private JFrame frame;

    private HeaderLabel titleLabel;
    private TaskLabel taskLabel;

    private JLabel listLabel;
    private JLabel exampleTask1;
    private JLabel exampleTask2;
    private JLabel counterLabel;

    private TaskTextField taskField;

    private AddButton addButton;
    private DeleteButton deleteButton;

    private int taskYPosition = 340;

    private ArrayList<JLabel> tasks = new ArrayList<>();

    public MainWindow() {

        frame = new JFrame();

        createComponents();
        addComponents();
        setupFrame();
    }

    private void createComponents() {

        frame.getContentPane().setBackground(new Color(245, 247, 250));

        titleLabel = new HeaderLabel();
        titleLabel.setForeground(new Color(52, 73, 94));

        taskLabel = new TaskLabel();
        taskLabel.setForeground(new Color(44, 62, 80));

        counterLabel = new JLabel("Aufgaben: 0");
        counterLabel.setBounds(20, 20, 150, 30);
        counterLabel.setForeground(new Color(231, 76, 60));
        counterLabel.setFont(new Font("Arial", Font.BOLD, 14));

        listLabel = new JLabel("Aufgabenliste:");
        listLabel.setBounds(180, 240, 150, 30);
        listLabel.setForeground(new Color(41, 128, 185));
        listLabel.setFont(new Font("Arial", Font.BOLD, 16));

        exampleTask1 = new JLabel("- Java lernen");
        exampleTask1.setBounds(190, 280, 200, 30);
        exampleTask1.setForeground(new Color(39, 174, 96));
        exampleTask1.setFont(new Font("Arial", Font.PLAIN, 14));

        exampleTask2 = new JLabel("- Mockup abgeben");
        exampleTask2.setBounds(190, 310, 200, 30);
        exampleTask2.setForeground(new Color(39, 174, 96));
        exampleTask2.setFont(new Font("Arial", Font.PLAIN, 14));

        taskField = new TaskTextField();

        addButton = new AddButton();
        deleteButton = new DeleteButton();

        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 13));

        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 13));

        addButton.addActionListener(e -> {

            String taskText = taskField.getText();

            if (taskText.isEmpty()) {

                JOptionPane.showMessageDialog(
                    frame,
                    "Bitte geben Sie eine Aufgabe ein!"
                );

            } else {

                JLabel newTask = new JLabel("- " + taskText);
                newTask.setBounds(190, taskYPosition, 250, 30);
                newTask.setForeground(new Color(142, 68, 173));
                newTask.setFont(new Font("Arial", Font.PLAIN, 14));

                frame.add(newTask);
                tasks.add(newTask);

                taskYPosition += 30;

                taskField.setText("");

                counterLabel.setText("Aufgaben: " + tasks.size());

                frame.repaint();
            }
        });

        deleteButton.addActionListener(e -> {

            if (!tasks.isEmpty()) {

                JLabel lastTask = tasks.get(tasks.size() - 1);

                frame.remove(lastTask);
                tasks.remove(lastTask);

                taskYPosition -= 30;

                counterLabel.setText("Aufgaben: " + tasks.size());

                frame.repaint();

            } else {

                JOptionPane.showMessageDialog(
                    frame,
                    "Es gibt keine Aufgabe zum Löschen!"
                );
            }
        });
    }

    private void addComponents() {

        frame.add(titleLabel);
        frame.add(taskLabel);
        frame.add(counterLabel);
        frame.add(taskField);
        frame.add(addButton);
        frame.add(deleteButton);
        frame.add(listLabel);
        frame.add(exampleTask1);
        frame.add(exampleTask2);
    }

    private void setupFrame() {

        frame.setTitle("ToDo App");
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}