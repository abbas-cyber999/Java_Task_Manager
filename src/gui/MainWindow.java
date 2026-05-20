package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow {

    private JFrame frame;

    private HeaderLabel titleLabel;

    private TaskLabel taskLabel;

    private JLabel listLabel;

    private JLabel exampleTask1;

    private JLabel exampleTask2;

    private TaskTextField taskField;

    private AddButton addButton;

    private DeleteButton deleteButton;

    public MainWindow() {

        frame = new JFrame();

        createComponents();

        addComponents();

        setupFrame();
    }

    private void createComponents() {
    	
    	
        titleLabel = new HeaderLabel();

        taskLabel = new TaskLabel();

        listLabel = new JLabel("Aufgabenliste:");

        exampleTask1 = new JLabel("- Java lernen");

        exampleTask2 = new JLabel("- Mockup abgeben");
        
        listLabel.setBounds(180, 240, 150, 30);

        exampleTask1.setBounds(190, 280, 200, 30);

        exampleTask2.setBounds(190, 310, 200, 30);
        
        taskField = new TaskTextField();

        addButton = new AddButton();

        deleteButton = new DeleteButton();
    }

    private void addComponents() {

        frame.add(titleLabel);

        frame.add(taskLabel);

        frame.add(taskField);

        frame.add(addButton);

        frame.add(deleteButton);

        frame.add(listLabel);

        frame.add(exampleTask1);

        frame.add(exampleTask2);
    }

    private void setupFrame() {

        frame.setTitle("ToDo App");

        frame.setSize(500, 400);

        frame.setLayout(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}