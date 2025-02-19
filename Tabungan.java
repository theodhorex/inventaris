public class Tabungan {
    private Nasabah nasabah;
    private double saldo;

    public Tabungan() {
    }

    public Tabungan(Nasabah nasabah, double saldo) {
        this.nasabah = nasabah;
        this.saldo = saldo;
    }

//    public Tabungan(String noId, String nama, String alamat, double saldo) {
//        this.nasabah = new Nasabah(String noId, String nama, String alamat);
//        this.saldo = saldo;
//    }

    public Tabungan(Nasabah nasabah) {
        this.nasabah = nasabah;
        this.saldo = 0;
    }

    public Nasabah getNasabah() {
        return nasabah;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setNasabah(Nasabah nasabah) {
        this.nasabah = nasabah;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void penyetoran(double uang) {
        if (uang > 0) {
            this.saldo = saldo + uang;
        }
    }

    public void penarikan(double uang) {
        if (uang > 0 && uang <= saldo) {
            this.saldo = saldo - uang;
        }
    }
}
