import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateReservationView extends JDialog {
    public CreateReservationView(JFrame parentFrame) {
        super(parentFrame, "Create a Reservation", true); // Make it modal

        // Set up the dialog
        setSize(300, 150);
        setLocationRelativeTo(parentFrame); // Center relative to parent frame

        // Create components
        JLabel messageLabel = new JLabel("A reservation cannot be made as of the moment as there are no hotels or rooms currently available.", SwingConstants.CENTER);
        JButton okButton = new JButton("OK");

        // Set layout and add components
        setLayout(new BorderLayout());
        add(messageLabel, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);

        // Add action listener to the OK button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });
    }
}
