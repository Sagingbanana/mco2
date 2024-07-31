import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SelectHotelView extends JFrame {
    private final JComboBox<String> hotelComboBox;
    private final JButton proceedButton;
    private final JButton backButton;
    private final JLabel messageLabel;
    private final JRadioButton updateAttributesOption;
    private final JRadioButton viewInformationOption;

    public SelectHotelView() {
        setTitle("Select Hotel To Manage");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close operation manually
        setLocationRelativeTo(null); // Center the frame

        // Create components
        JLabel instructionLabel = new JLabel("Select a Hotel:", SwingConstants.CENTER);
        hotelComboBox = new JComboBox<>();
        proceedButton = new JButton("Proceed");
        backButton = new JButton("Back");
        messageLabel = new JLabel("", SwingConstants.CENTER);

        updateAttributesOption = new JRadioButton("Update Hotel Attributes");
        viewInformationOption = new JRadioButton("View Hotel Information");
        ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(updateAttributesOption);
        optionGroup.add(viewInformationOption);

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

        // Add hotelComboBox
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        centerPanel.add(hotelComboBox, gbc);

        // Add option buttons
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centerPanel.add(updateAttributesOption, gbc);

        gbc.gridy = 3;
        centerPanel.add(viewInformationOption, gbc);

        // Add proceedButton
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        centerPanel.add(proceedButton, gbc);

        // Add backButton
        gbc.gridx = 1;
        gbc.gridy = 4;
        centerPanel.add(backButton, gbc);

        // Add messageLabel
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        centerPanel.add(messageLabel, gbc);

        add(centerPanel, BorderLayout.CENTER);

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
                    System.exit(0); // Terminate the program
                }
            }
        });
    }

    public JComboBox<String> getHotelComboBox() {
        return hotelComboBox;
    }

    public JButton getProceedButton() {
        return proceedButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JRadioButton getUpdateAttributesOption() {
        return updateAttributesOption;
    }

    public JRadioButton getViewInformationOption() {
        return viewInformationOption;
    }

    public void setMessage(String message) {
        messageLabel.setText("<html><div style='text-align: center;'>" + message + "</div></html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SelectHotelView view = new SelectHotelView();
            view.setVisible(true);
        });
    }
}
