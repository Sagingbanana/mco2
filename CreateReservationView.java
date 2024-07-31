import javax.swing.*;
import java.awt.*;

public class CreateReservationView extends JFrame {
    private JTextField guestNameField;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;
    private JComboBox<String> hotelComboBox;
    private JComboBox<String> roomComboBox;
    private JTextField discountCodeField;
    private JButton okButton;
    private JButton cancelButton;

    public CreateReservationView() {
        setTitle("Create Reservation");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Guest Name:"));
        guestNameField = new JTextField();
        add(guestNameField);

        add(new JLabel("Check-In Date (1-30):"));
        checkInDateField = new JTextField();
        add(checkInDateField);

        add(new JLabel("Check-Out Date (2-31):"));
        checkOutDateField = new JTextField();
        add(checkOutDateField);

        add(new JLabel("Hotel:"));
        hotelComboBox = new JComboBox<>();
        add(hotelComboBox);

        add(new JLabel("Room:"));
        roomComboBox = new JComboBox<>();
        add(roomComboBox);

        add(new JLabel("Discount Code:"));
        discountCodeField = new JTextField();
        add(discountCodeField);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        add(okButton);
        add(cancelButton);

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

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
