package models;

public class urunModel
{
    private String urun_adi;
    private String fiyat;

    public urunModel(String urun_adi, String fiyat) {
        this.urun_adi = urun_adi;
        this.fiyat = fiyat;
    }

    public String getUrun_adi() {
        return urun_adi;
    }

    public void setUrun_adi(String urun_adi) {
        this.urun_adi = urun_adi;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }
}
