package camembert;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;

/**
 * Created by amhachi on 19/03/15.
 */
public class Data {


    ObservableList<Record> dataList;
    Budget budget = new Budget();
    ObservableList<PieChart.Data> pieChartData1;
    StringProperty textBudget = new SimpleStringProperty();

    Data(){
        dataList = FXCollections.observableArrayList();
        pieChartData1 = FXCollections.observableArrayList();

        textBudget.setValue(budget.getBudget()+"€");
    }

    public void add(Record r){
        dataList.add(r);
        budget.setBudget(budget.getBudget()+r.getFieldValue1());
        textBudget.setValue(budget.getBudget()+"€");
        pieChartData1.add(new PieChart.Data(r.getFieldDay(), r.getFieldValue1()));
    }

    public void update1(int pos, Double val){
        budget.setBudget(budget.getBudget()-pieChartData1.get(pos).getPieValue()+val);
        textBudget.setValue(budget.getBudget()+"€");
        pieChartData1.set(pos, new PieChart.Data(pieChartData1.get(pos).getName(), val));
    }
}
