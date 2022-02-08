package classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 * class that has count of vaccinations to further statistics
 * @author Zofia Dobrowolska
 * @version 1.0
 * @since 5.02.2022
 */
public class LekarzWykres {

    private IntegerProperty ilosc = new SimpleIntegerProperty();

    public LekarzWykres() {
    }

    public int getIlosc() {
        return ilosc.get();
    }

    public IntegerProperty iloscProperty() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc.set(ilosc);
    }
}
