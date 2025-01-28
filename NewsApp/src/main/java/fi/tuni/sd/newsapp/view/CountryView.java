/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.view;

import fi.tuni.sd.newsapp.datamodels.Country;
import java.util.List;
import java.util.Locale;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * The `CountryView` class is responsible for creating the user interface
 * components that display information about a country, including its name,
 * capital, region, languages, population, area, and flag. It also provides
 * functionality to embed a map view showing the country's geographical
 * coordinates and an option to view the location on Google Maps.
 *
 * @author Savidya
 */
public class CountryView {

    private final Country country;

    /**
     * Constructs a `CountryView` for the given country.
     *
     * @param country the country whose details will be displayed.
     */
    public CountryView(Country country) {
        this.country = country;
    }

    /**
     * Creates the complete country component view.
     *
     * @return A VBox containing the country details.
     */
    public VBox createCountryComponent() {
        VBox countryInfoCard = new VBox(20);
        countryInfoCard.setPadding(new Insets(20));
        countryInfoCard.setStyle("-fx-background-color: #FFFFFF; "
                + "-fx-background-radius: 15; "
                + "-fx-border-radius: 15; "
                + "-fx-border-color: #DDDDDD; "
                + "-fx-border-width: 1; "
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.5, 0, 2);");

        countryInfoCard.setEffect(new DropShadow(5, Color.GRAY));

        VBox contentBox = buildCountryDetails();

        countryInfoCard.getChildren().add(contentBox);

        VBox outerContainer = new VBox();
        outerContainer.setPadding(new Insets(20));
        outerContainer.getChildren().add(countryInfoCard);

        return outerContainer;
    }

    /**
     * Builds the main content containing country details and flag.
     *
     * @return A VBox with all country details.
     */
    private VBox buildCountryDetails() {
        VBox contentBox = new VBox(20);
        contentBox.setStyle("-fx-alignment: center;");

        ImageView flagView = createFlagView();
        contentBox.getChildren().add(flagView);

        contentBox.getChildren().addAll(
                createLabeledDetail("Country", country.getName().getOfficial()),
                createLabeledDetail("Capital", String.join(", ", country.getCapital())),
                createLabeledDetail("Region", country.getRegion()),
                createLabeledDetail("Languages", String.join(", ", country.getLanguages().values())),
                createLabeledDetail("Population", formatNumber(country.getPopulation())),
                createLabeledDetail("Area", formatNumber(country.getArea()) + " km²")
        );

        return contentBox;
    }

    /**
     * Creates the flag image view for the country.
     *
     * @return An ImageView with the flag image.
     */
    private ImageView createFlagView() {
        Image flagImage = new Image(country.getFlags().getPng());
        ImageView flagView = new ImageView(flagImage);
        flagView.setFitHeight(100);
        flagView.setPreserveRatio(true);
        return flagView;
    }

    /**
     * Creates a formatted detail section with a label and value.
     *
     * @param labelText The label text.
     * @param valueText The value text.
     * @return A TextFlow containing the label and value.
     */
    private HBox createLabeledDetail(String labelText, String valueText) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.GRAY);
        label.setMinWidth(100);

        Text value = new Text(valueText);
        value.setFont(Font.font("Arial", 15));
        value.setFill(Color.BLACK);
        value.setWrappingWidth(250);

        HBox detailContainer = new HBox(10);
        detailContainer.setStyle("-fx-alignment: center-left;");
        detailContainer.getChildren().addAll(label, value);

        return detailContainer;

    }

    /**
     * Formats a number with commas for better readability.
     *
     * @param number The number to format.
     * @return A formatted string with commas.
     */
    private String formatNumber(double number) {
        return String.format(Locale.FRANCE, "%,.0f", number);
    }

    /**
     * Updates the map view by embedding a Leaflet.js map in the provided
     * WebView. The map displays the country's geographical coordinates, if
     * available.
     *
     * If the country has valid coordinates, a marker is placed on the map, and
     * a link to Google Maps is provided. If the coordinates are missing, a "No
     * Data Available" message is displayed instead.
     *
     * @param mapView The WebView displaying the map.
     */
    public void updateMap(WebView mapView) {
        var mapEngine = mapView.getEngine();
        var webViewWidth = mapView.getWidth();
        var webViewHeight = mapView.getHeight();

        List<Double> latlng = country.getLatlng();
        if (latlng != null && latlng.size() == 2) {
            var latitude = latlng.get(0);
            var longitude = latlng.get(1);
            String googleMapsLink = "";
            /*
                    *
                    * (country.getMaps() != null && country.getMaps().containsKey("googleMaps"))
                    ? country.getMaps().get("googleMaps")
                    : null;
                    *
             */
            String content = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="utf-8" />
            <title>Leaflet Map</title>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />
            <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
        </head>
        <body>
            <div id="map" style="width: %spx; height: %spx;"></div>
            <script>
                var map = L.map('map').setView([%s, %s], 5);

                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                    maxZoom: 50,
                }).addTo(map);

                // Function to add a marker and link to Google Maps
                 function addMarker(lat, lon, googleLink) {
                     var marker = L.marker([lat, lon]).addTo(map);
         
                     var popupContent = '<b>Coordinates:</b><br>Latitude: ' + lat + '<br>Longitude: ' + lon + 
                                        '<br>';
                    // <b><a href="#" onclick="window.openGoogleMaps()">View on Google Maps</a></b>
                     
                     marker.bindPopup(popupContent);
         
                     marker.openPopup();

                     marker.on('click', function() {
                         if (!marker.isPopupOpen()) {
                             marker.openPopup();
                         }
                     });
                             
                     window.openGoogleMaps = function() {
                         javaBridge.openGoogleMaps();
                     };
                 }

                addMarker(%s, %s, '%s');
            </script>
        </body>
        </html>
        """.formatted(webViewWidth, webViewHeight, latitude, longitude, latitude, longitude, googleMapsLink != null ? googleMapsLink : "");

            mapEngine.loadContent(content);

            mapEngine.setJavaScriptEnabled(true);
            mapEngine.setOnAlert(event -> {
                if ("javafx:openGoogleMaps".equals(event.getData())) {
                    openInNewWebView(googleMapsLink != null ? googleMapsLink : "");
                }
            });

            // Provide the bridge for JavaScript to call Java
            mapEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                    mapEngine.executeScript("""
                    window.javaBridge = {
                        openGoogleMaps: function() {
                            alert('javafx:openGoogleMaps');
                        }
                    };
                """);
                }
            });
        } else {
            // Show "No Data Available" content if coordinates are missing
            String noDataContent = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="utf-8" />
            <title>No Data Available</title>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
        </head>
        <body style="display: flex; justify-content: center; align-items: center; width: %spx; height: %spx; background-color: #f0f0f0;">
            <h2>No data available</h2>
        </body>
        </html>
        """.formatted(webViewWidth, webViewHeight);

            mapEngine.loadContent(noDataContent);
        }
    }

    /**
     * Opens the provided Google Maps URL in a new WebView window.
     *
     * @param url the Google Maps URL to open.
     */
    private void openInNewWebView(String url) {
        Stage newStage = new Stage();
        WebView newWebView = new WebView();
        newWebView.getEngine().load(url);

        Scene scene = new Scene(newWebView, 800, 600);
        newStage.setScene(scene);
        newStage.setTitle("Google Maps");
        newStage.show();
    }

}
