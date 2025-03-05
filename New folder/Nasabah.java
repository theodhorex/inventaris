public class Nasabah {
    private String noId;
    private String nama;
    private String alamat;

    public Nasabah(String noId, String nama, String alamat) {
        this.noId = noId;
        this.nama = nama;
        this.alamat = alamat;
    }

    public Nasabah() {

    }

    public String getNoId() {
        return noId;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setNoId(String noId) {
        this.noId = noId;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
