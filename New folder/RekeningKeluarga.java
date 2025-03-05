package com.rplbo.projectrekeninginheritance;

public class RekeningKeluarga extends Rekening{
    public final double BUNGA_SETOR = 0.005;
    int totalSetoran = 0;

    public RekeningKeluarga(Nasabah nasabah) {
        super(nasabah);
    }

    public RekeningKeluarga(Nasabah nasabah, int saldo) {
        super(nasabah, saldo);
    }

    public void penyetoran(int jumlah) {
        if(jumlah > 0) {
            int totalSetoran = jumlah + bunga(jumlah);
            super.penyetoran(totalSetoran);
        }
    }

    private double bunga(int jumlah) {
        return (int) BUNGA_SETOR * jumlah;
    }

    @Override
    public void penarikan(int jumlah) {
        jumlah += 5000;
        super.penarikan(jumlah);
    }
}
