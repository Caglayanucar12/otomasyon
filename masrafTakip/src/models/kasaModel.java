package models;

public class kasaModel
{
    private String tur;
    private String user;
    private String urun_adi;
    private int fiyat;
    private int adet;
    private int toplam_fiyat;

    public kasaModel(String tur, String user, String urun_adi, int fiyat, int adet, int toplam_fiyat) {
        this.tur = tur;
        this.user = user;
        this.urun_adi = urun_adi;
        this.fiyat = fiyat;
        this.adet = adet;
        this.toplam_fiyat = toplam_fiyat;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrun_adi() {
        return urun_adi;
    }

    public void setUrun_adi(String urun_adi) {
        this.urun_adi = urun_adi;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public int getToplam_fiyat() {
        return toplam_fiyat;
    }

    public void setToplam_fiyat(int toplam_fiyat) {
        this.toplam_fiyat = toplam_fiyat;
    }
}
