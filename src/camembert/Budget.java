package camembert;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by amhachi on 19/03/15.
 */
public class Budget {

    private SimpleDoubleProperty budget;

    public Budget(SimpleDoubleProperty budget) {
        this.budget = budget;
    }

    public Budget() {
        budget = new SimpleDoubleProperty(0.0);
    }

    public void setBudget(double budget) {
        this.budget.set(budget);
    }

    public double getBudget() {
        return budget.get();
    }

    public SimpleDoubleProperty budgetProperty() {
        return budget;
    }
}
