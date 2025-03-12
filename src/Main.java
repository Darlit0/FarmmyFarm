import Databases.*;
    import Models.*;
    import javafx.application.Application;
    import javafx.beans.binding.Bindings;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.GridPane;
    import javafx.stage.Modality;
    import javafx.stage.Stage;
    import javafx.scene.Node;

    import java.io.IOException;
    import java.util.Objects;

    public class Main extends Application {
        private String currentPlantMode = null;
        private Piece piece = new Piece(500); // Initialize with 500 pieces

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/fxml/Interface.fxml")));
            Parent root = loader.load();
            primaryStage.setTitle("Farm my Farm");
            primaryStage.setScene(new Scene(root, 1240, 765));
            primaryStage.show();

            // Add event handler for the "Market" button
            Button marketButton = (Button) loader.getNamespace().get("marketButton");
            if (marketButton != null) {
                marketButton.setOnAction(event -> openMarket());
            } else {
                System.err.println("marketButton est null. Vérifiez le fichier FXML.");
            }

            // Add event handler for the "coin" button
            Button coinButton = (Button) loader.getNamespace().get("coinButton");
            if (coinButton != null) {
                coinButton.textProperty().bind(Bindings.concat("Pieces: ", piece.pieceProperty().asString()));
            } else {
                System.err.println("coinButton est null. Vérifiez le fichier FXML.");
            }

            // Add event handlers for other buttons...
            Button saladeButton = (Button) loader.getNamespace().get("saladeButton");
            if (saladeButton != null) {
                saladeButton.setOnAction(event -> setPlantMode("Salade"));
            } else {
                System.err.println("saladeButton est null. Vérifiez le fichier FXML.");
            }

            Button canneSucreButton = (Button) loader.getNamespace().get("canneSucreButton");
            if (canneSucreButton != null) {
                canneSucreButton.setOnAction(event -> setPlantMode("CanneSucre"));
            } else {
                System.err.println("canneSucreButton est null. Vérifiez le fichier FXML.");
            }

            Button carrotteButton = (Button) loader.getNamespace().get("carrotteButton");
            if (carrotteButton != null) {
                carrotteButton.setOnAction(event -> setPlantMode("Carrotte"));
            } else {
                System.err.println("carrotteButton est null. Vérifiez le fichier FXML.");
            }

            Button bleButton = (Button) loader.getNamespace().get("bleButton");
            if (bleButton != null) {
                bleButton.setOnAction(event -> setPlantMode("Blé"));
            } else {
                System.err.println("bleButton est null. Vérifiez le fichier FXML.");
            }

            Button maisButton = (Button) loader.getNamespace().get("maïsButton");
            if (maisButton != null) {
                maisButton.setOnAction(event -> setPlantMode("Maïs"));
            } else {
                System.err.println("maïsButton est null. Vérifiez le fichier FXML.");
            }

            Button bettraveButton = (Button) loader.getNamespace().get("bettraveButton");
            if (bettraveButton != null) {
                bettraveButton.setOnAction(event -> setPlantMode("Bettrave"));
            } else {
                System.err.println("bettraveButton est null. Vérifiez le fichier FXML.");
            }

            // Bind the label text to the piece count property
            Label pieceLabel = (Label) loader.getNamespace().get("pieceLabel");
            if (pieceLabel != null) {
                pieceLabel.textProperty().bind(Bindings.concat("Pieces: ", piece.pieceProperty().asString()));
            } else {
                System.err.println("pieceLabel est null. Vérifiez le fichier FXML.");
            }

            // Set up event handlers for the grid cells
            GridPane gridPane = (GridPane) root.lookup("#gridPane");
            if (gridPane != null) {
                setupGridEventHandlers(gridPane);
            } else {
                System.err.println("Impossible de charger Gridpane. Vérifiez le fichier FXML.");
            }
        }

        private void openMarket() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Market.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Market");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);

                Button closeButton = (Button) loader.getNamespace().get("closeButton");
                if (closeButton != null) {
                    closeButton.setOnAction(event -> stage.close());
                } else {
                    System.err.println("closeButton is null. Check the FXML file.");
                }

                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void setPlantMode(String plantMode) {
            currentPlantMode = plantMode;
        }

        private void setupGridEventHandlers(GridPane gridPane) {
            for (Node node : gridPane.getChildren()) {
                if (node instanceof ImageView) {
                    ImageView imageView = (ImageView) node;
                    imageView.setOnMouseClicked(event -> {
                        if (currentPlantMode != null) {
                            switch (currentPlantMode) {
                                case "Carrotte":
                                    Carrotte carrotte = new Carrotte(1);
                                    carrotte.plantCarrot(imageView);
                                    break;
                                case "Blé":
                                    Blé ble = new Blé(1);
                                    ble.plantBlé(imageView);
                                    break;
                                case "Maïs":
                                    Maïs maïs = new Maïs(1);
                                    maïs.plantMaïs(imageView);
                                    break;
                                case "Bettrave":
                                    Bettrave bettrave = new Bettrave(1);
                                    bettrave.plantBettrave(imageView);
                                    break;
                                case "Salade":
                                    Salade salade = new Salade(1);
                                    salade.plantSalade(imageView);
                                    break;
                                case "CanneSucre":
                                    CanneSucre canneSucre = new CanneSucre(1);
                                    canneSucre.plantCanneSucre(imageView);
                                    break;
                            }
                        } else if (imageView.getImage() != null && imageView.getImage().getUrl().endsWith("end.png")) {
                            piece.setPiece(piece.getPiece() + 50);
                            imageView.setImage(null); // Clear the image after collecting
                        }
                    });
                }
            }
        }
    }