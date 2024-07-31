import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateHotelView extends JFrame {
    private final JTextField hotelNameField;
    private final JButton createHotelButton;
    private final JButton backButton;
    private final JLabel messageLabel;

    public CreateHotelView() {
        // Set up the frame
        setTitle("Create a Hotel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Create components
        JLabel instructionLabel = new JLabel("Enter the name of the new hotel:", SwingConstants.CENTER);
        hotelNameField = new JTextField(20);
        hotelNameField.setPreferredSize(new Dimension(200, 30));
        createHotelButton = new JButton("Create");
        backButton = new JButton("Back");
        messageLabel = new JLabel("", SwingConstants.CENTER);

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

        add(centerPanel, BorderLayout.CENTER);

        // Add key listener to hotelNameField to handle Enter key
        hotelNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    createHotelButton.doClick(); // Simulate button click
                }
            }
        });
    }

    public JTextField getHotelNameField() {
        return hotelNameField;
    }

    public JButton getCreateHotelButton() {
        return createHotelButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setMessage(String message) {
        messageLabel.setText("<html><div style='text-align: center;'>" + message + "</div></html>");
    }
}
