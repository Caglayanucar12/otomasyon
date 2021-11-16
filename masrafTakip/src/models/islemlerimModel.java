package models;

public class islemlerimModel
{
    private String aciklama;
    private String fiyat;

    public islemlerimModel(String aciklama, String fiyat) {
        this.aciklama = aciklama;
        this.fiyat = fiyat;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String urun_adi) {
        this.aciklama = aciklama;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

}
