package essentials;

public class User
{
    public User(String username)
    {
        this.username = username;
    }

    public User(String username, int maas) {
        this.username = username;
        this.maas = maas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String username;
    public int maas;

    @Override
    public String toString() {
        return username;
    }

    public void setMaas(int maas)
    {
        this.maas = maas;
    }

    public int getMaas()
    {
        return maas;
    }
}
