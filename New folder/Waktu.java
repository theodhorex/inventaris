public class Waktu {
    private int jam;
    private int menit;
    private int detik;

    public int getJam() {
        return jam;
    }

    public int getMenit() {
        return menit;
    }

    public int getDetik() {
        return detik;
    }

    public void setJam(int jam) {
        this.jam = jam;
    }

    public void setMenit(int menit) {
        this.menit = menit;
    }

    public void setDetik(int detik) {
        this.detik = detik;
    }

    public Waktu(int jam, int menit, int detik) {
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    public Waktu(Waktu waktu) {
        this.jam = waktu.jam;
        this.menit = waktu.menit;
        this.detik = waktu.detik;
    }
    public Waktu(int jam) {
        this.jam = jam;
        this.menit = 0;
        this.detik = 0;
    }

    public int selisih(int jam, int menit, int detik) {
        int waktuIni = this.detik + (this.menit * 60) + (this.jam * 3600);
        int waktuParameter = detik + (menit * 60) + (jam * 3600);
        int hasil = Math.abs(waktuIni - waktuParameter);
        return hasil;
    }

    public int selisih(Waktu waktu){
        int jamParameter = waktu.getJam();
        int menitParameter = waktu.getMenit();
        int detikParameter = waktu.getDetik();
        return selisih(jamParameter, menitParameter, detikParameter);
    }
}
