import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {
    private MainMenuView view;
    private MainMenuModel model;

    public MainMenuController(MainMenuView view, MainMenuModel model) {
        this.view = view;
        this.model = model;

        // Initialize the view with the number of hotels
        updateHotelCount();

        // Add action listeners to buttons
        this.view.getManageHotelsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageHotels();
            }
        });

        this.view.getCreateReservationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createReservation();
            }
        });
    }

    private void manageHotels() {
        // Placeholder for manage hotels logic
        System.out.println("Manage Hotels button clicked");
    }

    private void createReservation() {
        if (model.areThereNoHotels()) {
            // Display an alert that no hotels are available
            JOptionPane.showMessageDialog(view, "No hotels available.", "Reservation Error", JOptionPane.ERROR_MESSAGE);
        } else if (model.areThereNoAvailableRooms()) {
            // Display an alert that no rooms are available
            JOptionPane.showMessageDialog(view, "No rooms available.", "Reservation Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Create and show the reservation window
            CreateReservationView reservationView = new CreateReservationView(view);
            reservationView.setVisible(true);
        }
    }

    private void updateHotelCount() {
        view.setHotelCount(model.getNumberOfOperatingHotels());
    }
}
