package camembert;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by amhachi on 19/03/15.
 */
public class Record {

    private SimpleStringProperty fieldDay;
    private SimpleDoubleProperty fieldValue1;

    Record(String fDay, double fValue1){
        this.fieldDay = new SimpleStringProperty(fDay);
        this.fieldValue1 = new SimpleDoubleProperty(fValue1);
    }

    public String getFieldDay() {
        return fieldDay.get();
    }

    public double getFieldValue1() {
        return fieldValue1.get();
    }

    public void setFieldDay(String fDay) {
        fieldDay.set(fDay);
    }

    public void setFieldValue1(Double fValue1) {
        fieldValue1.set(fValue1);
    }

}
