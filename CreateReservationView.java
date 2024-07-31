import javax.swing.*;
import java.awt.*;

public class CreateReservationView extends JFrame {
    private final JTextField guestNameField;
    private final JTextField checkInDateField;
    private final JTextField checkOutDateField;
    private final JComboBox<String> hotelComboBox;
    private final JComboBox<String> roomComboBox;
    private final JTextField discountCodeField;
    private final JButton confirmButton;
    private final JButton cancelButton;

    public CreateReservationView() {
        setTitle("Create Reservation");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Adding padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Guest Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Guest Name:"), gbc);
        guestNameField = new JTextField(15);
        gbc.gridx = 1;
        add(guestNameField, gbc);

        // Check-In Date
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Check-In Date (1-30):"), gbc);
        checkInDateField = new JTextField(15);
        gbc.gridx = 1;
        add(checkInDateField, gbc);

        // Check-Out Date
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Check-Out Date (2-31):"), gbc);
        checkOutDateField = new JTextField(15);
        gbc.gridx = 1;
        add(checkOutDateField, gbc);

        // Hotel
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Hotel:"), gbc);
        hotelComboBox = new JComboBox<>();
        gbc.gridx = 1;
        add(hotelComboBox, gbc);

        // Room
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Room:"), gbc);
        roomComboBox = new JComboBox<>();
        gbc.gridx = 1;
        add(roomComboBox, gbc);

        // Discount Code
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Discount Code:"), gbc);
        discountCodeField = new JTextField(15);
        gbc.gridx = 1;
        add(discountCodeField, gbc);

        // OK and Cancel buttons
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(confirmButton, gbc);

        gbc.gridx = 1;
        add(cancelButton, gbc);

        setLocationRelativeTo(null);  // Center the window on the screen
    }

    public JTextField getGuestNameField() {
        return guestNameField;
    }

    public JTextField getCheckInDateField() {
        return checkInDateField;
    }

    public JTextField getCheckOutDateField() {
        return checkOutDateField;
    }

    public JComboBox<String> getHotelComboBox() {
        return hotelComboBox;
    }

    public JComboBox<String> getRoomComboBox() {
        return roomComboBox;
    }

    public JTextField getDiscountCodeField() {
        return discountCodeField;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreateReservationView view = new CreateReservationView();
            view.setVisible(true);
        });
    }

}
