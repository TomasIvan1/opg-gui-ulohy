package sk.spse.uloha5.declarative;

public class Jedlo {
    private Integer id;
    private String nazov;
    private Integer kalorie;
    private Double cena;

    public Jedlo(Integer id, String nazov, Integer kalorie, Double cena) {
        this.id = id;
        this.nazov = nazov;
        this.kalorie = kalorie;
        this.cena = cena;
    }

    public Integer getId() {
        return id;
    }

    public String getNazov() {
        return nazov;
    }

    public Integer getKalorie() {
        return kalorie;
    }

    public Double getCena() {
        return cena;
    }
}
