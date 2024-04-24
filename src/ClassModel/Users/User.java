package ClassModel.Users;

public class User {
    public String idUser;
    public String nama;
    public String alamat;
    public String noHp;
    public String role;
    public String Password;


    public String getRole() {
        return role;
    }
    public  User(String idUser,String nama,String alamat,String noHp,String role){
        this.idUser = idUser;
        this.nama = nama;
        this.alamat = alamat;
        this.noHp = noHp;
        this.role = role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
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
