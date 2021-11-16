package models;

public class personelModel
{
    private String username;
    private String aciklama;
    private int fiyat;

    public personelModel(String username, String aciklama, int fiyat) {
        this.username = username;
        this.aciklama = aciklama;
        this.fiyat = fiyat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }
}
