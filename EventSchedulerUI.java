import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EventSchedulerUI extends JFrame {
    private JTextField scheduleDateField;
    private JTextField scheduleTimeField;
    private JTextArea descriptionArea;
    private JButton createButton;

    public EventSchedulerUI() {
        // Initialize components
        scheduleDateField = new JTextField(10);
        scheduleTimeField = new JTextField(10);
        descriptionArea = new JTextArea(5, 20);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        createButton = new JButton("Create Event");

        // Layout
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Schedule Date (YYYY-MM-DD):"));
        panel.add(scheduleDateField);
        panel.add(new JLabel("Schedule Time (HH:MM):"));
        panel.add(scheduleTimeField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionScrollPane);
        panel.add(new JLabel()); // Placeholder
        panel.add(createButton);

        // Event listener for create button
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createEventAndSchedule();
            }
        });

        // Frame setup
        setTitle("Event Creation and Schedule Planning");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void createEventAndSchedule() {
        String scheduleDate = scheduleDateField.getText();
        String scheduleTime = scheduleTimeField.getText();
        String description = descriptionArea.getText();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_management", "username", "password");
           

            String scheduleSql = "INSERT INTO schedules (schedule_date, schedule_time, description) VALUES (?, ?, ?)";
            PreparedStatement scheduleStmt = conn.prepareStatement(scheduleSql);
            scheduleStmt.setString(2, scheduleDate);
            scheduleStmt.setString(3, scheduleTime);
            scheduleStmt.setString(4, description);
            scheduleStmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Event and Schedule created successfully!");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error creating event and schedule: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventSchedulerUI();
            }
        });
    }
}
