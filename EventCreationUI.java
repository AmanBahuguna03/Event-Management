import javax.swing.*;

import university.management.system.EventSchedulerUI;
import university.management.system.Project;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EventCreationUI extends JFrame {
    private JTextField eventNameField;
    private JTextField eventDateField;
    private JTextField eventTimeField;
    private JTextField venueField;
    private JButton createButton;

    public EventCreationUI() {
        // Initialize components
        eventNameField = new JTextField(20);
        eventDateField = new JTextField(10);
        eventTimeField = new JTextField(10);
        venueField = new JTextField(20);
        createButton = new JButton("Create Event");

        // Layout
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Event Name:"));
        panel.add(eventNameField);
        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(eventDateField);
        panel.add(new JLabel("Time (HH:MM):"));
        panel.add(eventTimeField);
        panel.add(new JLabel("Venue:"));
        panel.add(venueField);
        panel.add(new JLabel()); // Placeholder
        panel.add(createButton);

        // Event listener for create button
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					createEvent();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        // Frame setup
        setTitle("Event Creation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void createEvent() throws ClassNotFoundException {
        String eventName = eventNameField.getText();
        String eventDate = eventDateField.getText();
        String eventTime = eventTimeField.getText();
        String venue = venueField.getText();

        // Perform database insertion using JDBC
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_management", "root", "12345");
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO events (event_name, event_date, event_time, venue) VALUES ('" + eventName + "', '" + eventDate + "', '" + eventTime + "', '" + venue + "')";
            stmt.executeUpdate(sql);
				
            JOptionPane.showMessageDialog(this, "Event created successfully!");
            setVisible(false);
			new EventSchedulerUI();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error creating event: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Run the UI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventCreationUI();
            }
        });
    }
}

