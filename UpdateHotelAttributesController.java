import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateHotelAttributesController {
    private UpdateHotelAttributesView view;
    private UpdateHotelAttributesModel model;
    private SelectHotelController selectHotelController;
    private SelectHotelView selectHotelView;

    public UpdateHotelAttributesController(UpdateHotelAttributesView view, UpdateHotelAttributesModel model, SelectHotelController selectHotelController, SelectHotelView selectHotelView) {
        this.view = view;
        this.model = model;
        this.selectHotelController = selectHotelController;
        this.selectHotelView = selectHotelView;

        // Add action listeners to buttons
        this.view.getChangeHotelNameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeHotelName(); // Call the method to change hotel name
            }
        });

        this.view.getAddRoomsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define action for adding rooms
            }
        });

        this.view.getRemoveRoomButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define action for removing a room
            }
        });

        this.view.getUpdateBasePriceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define action for updating base price
            }
        });

        this.view.getRemoveReservationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define action for removing a reservation
            }
        });

        this.view.getRemoveHotelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define action for removing a hotel
            }
        });

        this.view.getSetDatePriceModifierButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define action for setting date price modifier
            }
        });

        // Add action listeners for bottom buttons
        this.view.getBackToSelectionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToSelectionPage();
            }
        });

        this.view.getBackToMainMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToMainMenu();
            }
        });
    }

    private void changeHotelName() {
        String currentHotelName = model.getSelectedHotelName();
        String newHotelName = JOptionPane.showInputDialog(view, "Enter a new name for the hotel:", currentHotelName);

        if (newHotelName != null && model.isHotelNameValid(newHotelName)) {
            // Update the hotel name in the model
            if (model.getHrs().updateHotelName(newHotelName, model.getHotel())) { // Use the passed hrs
                JOptionPane.showMessageDialog(view, "Hotel name updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update hotel name. It may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Invalid hotel name. Please ensure it starts with a letter and is not empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void backToSelectionPage() {
        view.dispose(); // Close the current view
        selectHotelView.setVisible(true); // Show the selection view again
    }

    private void backToMainMenu() {
        view.dispose(); // Close the current view
        selectHotelController.goBackToMainMenu(); // Show the main menu
    }
}
