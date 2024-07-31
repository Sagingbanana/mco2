import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * View for selecting a hotel to manage within the hotel reservation system.
 */
public class SelectHotelView extends JFrame {
    private final JComboBox<String> hotelComboBox; // Dropdown for selecting a hotel
    private final JButton proceedButton; // Button to proceed with the selected hotel
    private final JButton backButton; // Button to go back to the previous menu
    private final JLabel messageLabel; // Label for displaying messages to the user
    private final JRadioButton updateAttributesOption; // Option for updating hotel attributes
    private final JRadioButton viewInformationOption; // Option for viewing hotel information

    /**
     * Constructs the SelectHotelView and initializes its components.
     */
    public SelectHotelView() {
        setTitle("Select Hotel To Manage"); // Set the title of the frame
        setSize(400, 300); // Set the size of the frame
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close operation manually
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create components
        JLabel instructionLabel = new JLabel("Select a Hotel:", SwingConstants.CENTER);
        hotelComboBox = new JComboBox<>(); // Dropdown for hotel selection
        proceedButton = new JButton("Proceed"); // Button to proceed with selected hotel
        backButton = new JButton("Back"); // Button to return to the main menu
        messageLabel = new JLabel("", SwingConstants.CENTER); // Label for displaying messages

        updateAttributesOption = new JRadioButton("Update Hotel Attributes"); // Radio button for updating attributes
        viewInformationOption = new JRadioButton("View Hotel Information"); // Radio button for viewing information
        ButtonGroup optionGroup = new ButtonGroup(); // Group for radio buttons
        optionGroup.add(updateAttributesOption); // Add update option to the group
        optionGroup.add(viewInformationOption); // Add view option to the group

        // Set layout and add components to the frame
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(); // Panel for holding center components
        centerPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexible positioning
        GridBagConstraints gbc = new GridBagConstraints(); // Constraints for layout
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        gbc.insets = new Insets(10, 10, 10, 10); // Margin for each component

        // Add instruction label to the center panel
        gbc.gridx = 0; // Column position
        gbc.gridy = 0; // Row position
        gbc.gridwidth = 2; // Span across two columns
        centerPanel.add(instructionLabel, gbc);

        // Add hotel combo box to the center panel
        gbc.gridx = 0; // Column position
        gbc.gridy = 1; // Row position
        gbc.gridwidth = 2; // Span across two columns
        centerPanel.add(hotelComboBox, gbc);

        // Add update attributes option to the center panel
        gbc.gridx = 0; // Column position
        gbc.gridy = 2; // Row position
        gbc.gridwidth = 2; // Span across two columns
        centerPanel.add(updateAttributesOption, gbc);

        // Add view information option to the center panel
        gbc.gridy = 3; // Move to next row
        centerPanel.add(viewInformationOption, gbc);

        // Add proceed button to the center panel
        gbc.gridx = 0; // Column position
        gbc.gridy = 4; // Row position
        gbc.gridwidth = 1; // Single column
        centerPanel.add(proceedButton, gbc);

        // Add back button to the center panel
        gbc.gridx = 1; // Column position
        gbc.gridy = 4; // Same row as proceed button
        centerPanel.add(backButton, gbc);

        // Add message label to the center panel
        gbc.gridx = 0; // Column position
        gbc.gridy = 5; // Row position
        gbc.gridwidth = 2; // Span across two columns
        centerPanel.add(messageLabel, gbc);

        add(centerPanel, BorderLayout.CENTER); // Add center panel to the frame

        // Add window listener to handle close operation
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        SelectHotelView.this,
                        "Are you sure you want to quit? All data will be lost.",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0); // Terminate the program if user confirms
                }
            }
        });
    }

    /**
     * Gets the hotel combo box for selecting hotels.
     *
     * @return The hotel combo box.
     */
    public JComboBox<String> getHotelComboBox() {
        return hotelComboBox; // Return the hotel combo box
    }

    /**
     * Gets the proceed button.
     *
     * @return The proceed button.
     */
    public JButton getProceedButton() {
        return proceedButton; // Return the proceed button
    }

    /**
     * Gets the back button.
     *
     * @return The back button.
     */
    public JButton getBackButton() {
        return backButton; // Return the back button
    }

    /**
     * Gets the radio button for updating hotel attributes.
     *
     * @return The update attributes option.
     */
    public JRadioButton getUpdateAttributesOption() {
        return updateAttributesOption; // Return the update attributes option
    }

    /**
     * Gets the radio button for viewing hotel information.
     *
     * @return The view information option.
     */
    public JRadioButton getViewInformationOption() {
        return viewInformationOption; // Return the view information option
    }

    /**
     * Sets a message to be displayed in the message label.
     *
     * @param message The message to display.
     */
    public void setMessage(String message) {
        messageLabel.setText("<html><div style='text-align: center;'>" + message + "</div></html>");
        // Set the message in HTML format to center it
    }

    /**
     * Main method to launch the SelectHotelView.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SelectHotelView view = new SelectHotelView(); // Create instance of SelectHotelView
            view.setVisible(true); // Make the view visible
        });
    }
}
