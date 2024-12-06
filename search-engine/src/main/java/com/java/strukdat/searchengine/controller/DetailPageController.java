    package com.java.strukdat.searchengine.controller;

    import com.java.strukdat.searchengine.model.GimmickNode;
    import com.java.strukdat.searchengine.model.Node;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.control.Button;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.layout.Pane;
    import javafx.scene.text.Text;
    import javafx.scene.Scene;
    import javafx.stage.Stage;

    import java.io.IOException;

    public class DetailPageController {
        @FXML
        private Text nodeTitle; // For the `key`
        @FXML
        private Text nodeDescription; // For the `value`
        @FXML
        private ImageView nodeImage; // For the `imgPath`
        @FXML
        private Button backButton; // For navigation
        @FXML
        private Pane componentField;

        private Scene previousScene; // Store the previous scene


        // Method to pass Node data to this controller
        public void setNodeData(Node nodeData) {
            if (nodeData != null) {
                nodeTitle.setText(nodeData.getKey());
                nodeDescription.setText(nodeData.getValue());

                if (nodeData instanceof GimmickNode) {
                    // Clear existing children from componentField
                    componentField.getChildren().clear();

                    // Ambil FXML path dari GimmickNode
                    String gimmickFXMLPath = ((GimmickNode) nodeData).getGimmickFXMLPath();

                    if (gimmickFXMLPath != null) {
                        try {
                            // Memuat FXML sesuai dengan path yang diberikan
                            FXMLLoader loader = new FXMLLoader(getClass().getResource(gimmickFXMLPath));
                            Parent gimmickComponent = loader.load();  // Memuat komponen dari FXML

                            // Menambahkan komponen ke componentField
                            componentField.getChildren().add(gimmickComponent);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Failed to load FXML from path: " + gimmickFXMLPath);
                        }
                    } else {
                        System.out.println("No FXML path available in GimmickNode");
                    }
                }
            }
        }


        // Set the previous scene to allow going back
        public void setPreviousScene(Scene scene) {
            this.previousScene = scene;
        }

        @FXML
        public void handleBackButton() {
            if (previousScene != null) {
                // Get the stage from the current scene and set the previous scene
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(previousScene);
                stage.show();
            }
        }




    }
