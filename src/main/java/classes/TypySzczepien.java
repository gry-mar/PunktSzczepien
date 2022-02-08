package classes;
/**
 * class to map data from typy_szczepien table
 * @author martyna Grygiel, Zofia Dobrowolska
 * @version 1.0
 * @since 03.02.2022
 */
public class TypySzczepien {


    private String nazwa;
    private String choroba;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getChoroba() {
        return choroba;
    }

    public void setChoroba(String choroba) {
        this.choroba = choroba;
    }
}
