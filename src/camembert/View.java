package camembert;

import java.util.Arrays;
import java.util.List;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Created by amhachi on 18/03/15.
 */

public class View extends Application {




    Data myList;

    private TableView<Record> tableView = new TableView<Record>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TP Camembert");


        //prepare myList
        myList = new Data();
        myList.add(new Record("Rennes", 100));
        myList.add(new Record("Cesson", 200));
        myList.add(new Record("St Grégoire", 50));
        myList.add(new Record("Chantepie", 75));
        myList.add(new Record("Maurepas", 110));
        myList.add(new Record("Beaulieu", 300));
        myList.add(new Record("St-Jaques", 111));
        myList.add(new Record("Le Rheu", 75));

        Group root = new Group();

        tableView.setEditable(true);

        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {

                    @Override
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };

        TableColumn columnDay = new TableColumn("Quartier");
        columnDay.setCellValueFactory(
                new PropertyValueFactory<>("fieldDay"));
        columnDay.setMinWidth(60);

        TableColumn columnValue1 = new TableColumn("Budget");
        columnValue1.setCellValueFactory(
                new PropertyValueFactory<Record, Double>("fieldValue1"));
        columnValue1.setMinWidth(180);



        //--- Add for Editable Cell of Value field, in Double
        columnValue1.setCellFactory(cellFactory);
        columnValue1.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Record, Double>>() {

                    @Override public void handle(TableColumn.CellEditEvent<Record, Double> t) {
                        Record record = ((Record)t.getTableView().getItems().get(
                                t.getTablePosition().getRow()));

                        record.setFieldValue1(t.getNewValue());
                        int pos = t.getTablePosition().getRow();
                        myList.update1(pos, t.getNewValue());
                    }
                });



        //--- Prepare StackedBarChart

        List<String> quartierLabels = Arrays.asList(
                "Rennes",
                "Cesson",
                "St Grégoire",
                "Chantepie",
                "Maurepas",
                "Beaulieu",
                "St-Jaques",
                "Le Rheu");

        final PieChart pieChart1 = new PieChart(myList.pieChartData1);
        pieChart1.setPrefWidth(200);
        pieChart1.setTitle("Camembert");
        pieChart1.setLayoutX(200);
        pieChart1.setLayoutX(300);

        double pieCentreX = 401;
        double pieCentreY = 125;

        Circle circleWhite = new Circle();
        circleWhite.setFill(Color.WHITE);
        circleWhite.setRadius(60);
        circleWhite.setLayoutX(401);
        circleWhite.setLayoutY(125);


        double radius = 45;
        Circle circleBudget = new Circle();
        circleBudget.setFill(Color.LIGHTBLUE);
        circleBudget.setRadius(radius);

        Text text = new Text();
        text.setBoundsType(TextBoundsType.VISUAL);
        StackPane stack = new StackPane();
        text.textProperty().bind(myList.textBudget);
        stack.getChildren().addAll(circleBudget, text);
        stack.setTranslateX(pieCentreX-radius);
        stack.setTranslateY(pieCentreY-radius);

        System.out.println("Centre x = " + pieChart1.getLayoutX() + " , y = " + pieChart1.getLayoutY());

        //---
        tableView.setItems(myList.dataList);
        tableView.getColumns().addAll(columnDay, columnValue1);
        tableView.setPrefWidth(240);

        HBox hBox = new HBox();
        hBox.setSpacing(100);
        hBox.getChildren().addAll(tableView, pieChart1);

        root.getChildren().add(tableView);
        root.getChildren().add(pieChart1);
        root.getChildren().add(circleWhite);
        root.getChildren().add(stack);


        root.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
            System.out.println("Window clicked at  x = " + e.getX() + " , y = " + e.getY());
        });
        primaryStage.setScene(new Scene(root, 750, 400));
        primaryStage.show();
    }





    class EditingCell extends TableCell<Record, Double> {
        private TextField textField;

        public EditingCell() {}

        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }

            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }

        @Override
        public void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }

                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(Double.parseDouble(textField.getText()));
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

}
