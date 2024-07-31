import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectHotelController {
    private final SelectHotelView view;
    private final SelectHotelModel model;
    private final MainMenuView mainMenuView;

    public SelectHotelController(SelectHotelView view, SelectHotelModel model, MainMenuView mainMenuView) {
        this.view = view;
        this.model = model;
        this.mainMenuView = mainMenuView;

        // Initialize the view with hotel list
        populateHotelList();

        // Add action listeners to buttons
        this.view.getProceedButton().addActionListener(e -> proceed());

        this.view.getBackButton().addActionListener(e -> goBackToMainMenu());
    }

    private void populateHotelList() {
        view.getHotelComboBox().removeAllItems();
        if (model.hasHotels()) {
            for (Hotel hotel : model.getHotels()) {
                view.getHotelComboBox().addItem(hotel.getName());
            }
        } else {
            view.setMessage("No hotels available.");
            view.getProceedButton().setEnabled(false);
        }
    }

    public void refreshHotelList() {
        populateHotelList();
    }

    private void proceed() {
        String selectedHotelName = (String) view.getHotelComboBox().getSelectedItem();
        if (selectedHotelName == null) {
            view.setMessage("Please select a hotel.");
            return;
        }

        if (!view.getUpdateAttributesOption().isSelected() && !view.getViewInformationOption().isSelected()) {
            view.setMessage("Please select a hotel operation.");
            return;
        }

        if (view.getViewInformationOption().isSelected()) {
            // Open the HotelInfoView with HotelInfoController
            Hotel selectedHotel = model.getHotels().stream()
                    .filter(hotel -> hotel.getName().equals(selectedHotelName))
                    .findFirst()
                    .orElse(null);

            if (selectedHotel != null) {
                HotelInfoView hotelInfoView = new HotelInfoView(selectedHotel.getName());
                HotelInfoModel hotelInfoModel = new HotelInfoModel(selectedHotel);
                new HotelInfoController(hotelInfoView, hotelInfoModel, this, view);
                hotelInfoView.setVisible(true);
                this.view.setVisible(false); // Hide the current view
            }
        } else if (view.getUpdateAttributesOption().isSelected()) {
            // Open the UpdateHotelAttributesView with UpdateHotelAttributesController
            Hotel selectedHotel = model.getHotels().stream()
                    .filter(hotel -> hotel.getName().equals(selectedHotelName))
                    .findFirst()
                    .orElse(null);

            if (selectedHotel != null) {
                UpdateHotelAttributesView updateView = new UpdateHotelAttributesView(selectedHotel.getName());
                UpdateHotelAttributesModel updateModel = new UpdateHotelAttributesModel(selectedHotel, model.getHrs());
                new UpdateHotelAttributesController(updateView, updateModel, this, view);
                updateView.setVisible(true);
                this.view.setVisible(false); // Hide the current view
            }
        }
    }

    public void goBackToMainMenu() {
        view.dispose();
        mainMenuView.setVisible(true);
        mainMenuView.setHotelCount(model.getNumberOfOperatingHotels());
    }
}
