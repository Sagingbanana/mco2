import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The CreateHotelView class represents the user interface for creating a new hotel.
 * It provides fields for user input and buttons for action, along with a message label
 * to display feedback to the user.
 */
public class CreateHotelView extends JFrame {
    private final JTextField hotelNameField; // Text field for entering the hotel name
    private final JButton createHotelButton; // Button to create the hotel
    private final JButton backButton; // Button to navigate back to the main menu
    private final JLabel messageLabel; // Label to display messages to the user

    /**
     * Constructs a CreateHotelView instance and sets up the UI components.
     */
    public CreateHotelView() {
        // Set up the frame
        setTitle("Create a Hotel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close operation manually
        setLocationRelativeTo(null); // Center the frame

        // Create components
        JLabel instructionLabel = new JLabel("Enter the name of the new hotel:", SwingConstants.CENTER);
        hotelNameField = new JTextField(20);
        hotelNameField.setPreferredSize(new Dimension(200, 30)); // Set preferred size for the text field
        createHotelButton = new JButton("Create");
        backButton = new JButton("Back");
        messageLabel = new JLabel("", SwingConstants.CENTER); // Initialize message label

        // Set the preferred size for buttons to be equal
        Dimension buttonSize = new Dimension(100, 30);
        createHotelButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);

        // Set layout and add components
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margin for each component

        // Add instructionLabel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(instructionLabel, gbc);

        // Add hotelNameField
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        centerPanel.add(hotelNameField, gbc);

        // Add createHotelButton
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        centerPanel.add(createHotelButton, gbc);

        // Add backButton
        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(backButton, gbc);

        // Add messageLabel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        centerPanel.add(messageLabel, gbc);

        add(centerPanel, BorderLayout.CENTER); // Add the center panel to the frame

        // Add key listener to hotelNameField to handle Enter key
        hotelNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    createHotelButton.doClick(); // Simulate button click when Enter is pressed
                }
            }
        });

        // Add window listener to handle close operation
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Confirm exit dialog
                int response = JOptionPane.showConfirmDialog(
                        CreateHotelView.this,
                        "Are you sure you want to quit? All data will be lost.",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0); // Terminate the program if confirmed
                }
            }
        });
    }

    /**
     * Returns the JTextField used for entering the hotel name.
     *
     * @return the hotel name text field
     */
    public JTextField getHotelNameField() {
        return hotelNameField;
    }

    /**
     * Returns the JButton for creating the hotel.
     *
     * @return the create hotel button
     */
    public JButton getCreateHotelButton() {
        return createHotelButton;
    }

    /**
     * Returns the JButton for navigating back to the main menu.
     *
     * @return the back button
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * Sets a message to be displayed in the message label.
     *
     * @param message The message to display
     */
    public void setMessage(String message) {
        messageLabel.setText("<html><div style='text-align: center;'>" + message + "</div></html>");
    }

    /**
     * The main method to launch the CreateHotelView.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreateHotelView createHotelView = new CreateHotelView();
            createHotelView.setVisible(true); // Make the frame visible
        });
    }
}
