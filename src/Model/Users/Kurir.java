package Model.Users;

public class Kurir extends User{
    private int idKurir;

    public Kurir(int idKurir,int idUser, String nama, String alamat, String noHp, String role) {
        super(idUser, nama, alamat, noHp, role);
        this.idKurir = idKurir;
    }

    public int getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(int idKurir) {
        this.idKurir = idKurir;
    }
}
