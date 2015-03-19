package sample;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller implements Serializable{

    @FXML
    private PieChart piechart;

    @FXML
    private Circle circle;

    @FXML
    private void handleButton1Action(ActionEvent event) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Cesson", 100),
                        new PieChart.Data("Rennes", 450),
                        new PieChart.Data("St Grégoire", 50),
                        new PieChart.Data("Chantepie", 75),
                        new PieChart.Data("Acigné", 110),
                        new PieChart.Data("Le Rheu", 300),
                        new PieChart.Data("Pacé", 111),
                        new PieChart.Data("Montgermont", 30),
                        new PieChart.Data("Vezin-le-Coquet", 75),
                        new PieChart.Data("Mordelles", 55),
                        new PieChart.Data("Chavagne", 225),
                        new PieChart.Data("Domloup", 99));



        piechart.setTitle("Neighborhood Record");
        piechart.setLegendVisible(false);

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");





        piechart.setData(pieChartData);

        for (final PieChart.Data data : piechart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            System.out.println("EventHandler" + data.getPieValue());
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()) + "%");
                            caption.setVisible(true);
                        }
                    });
        }
        //Circle
        circle.setCenterX(piechart.getLayoutX());
        circle.setCenterY(piechart.getLayoutY());

    }


    //@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}

