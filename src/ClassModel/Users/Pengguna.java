package ClassModel.Users;

public class Pengguna extends User{
    private int idPengguna;
    public Pengguna(int idPengguna,String idUser, String nama, String alamat, String noHp, String role) {
        super(idUser, nama, alamat, noHp, role );
        this.idPengguna = idPengguna;
    }
}
