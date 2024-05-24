package Model.Users;

public abstract class User {
    private int idUser;
    private String nama;
    private String alamat;
    private String noHp;

    private String role;

    public String getRole() {
        return role;
    }
    public  User(int idUser,String nama,String alamat,String noHp,String role){
        this.idUser = idUser;
        this.nama = nama;
        this.alamat = alamat;
        this.noHp = noHp;
        this.role = role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
       this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
