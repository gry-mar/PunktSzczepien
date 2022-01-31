package classes;

public class LekarzStatus {
    private String pesel;
    private String nazwa;
    private String choroba;
    private String data;
    private String godzina;

    public LekarzStatus(String pesel, String nazwa, String choroba, String data, String godzina) {
        this.pesel = pesel;
        this.nazwa = nazwa;
        this.choroba = choroba;
        this.data = data;
        this.godzina = godzina;
    }

    public String getPesel() {
        return pesel;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getChoroba() {
        return choroba;
    }

    public String getData() {
        return data;
    }

    public String getGodzina() {
        return godzina;
    }
}
