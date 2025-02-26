package com.rplbo.projectrekeninginheritance;

public class RekeningUtama extends RekeningKeluarga{
    public RekeningUtama(Nasabah nasabah) {
        super(nasabah);
    }

    public RekeningUtama(Nasabah nasabah, int saldo) {
        super(nasabah, saldo);
    }

    @Override
    public void penarikan(int jumlah) {
        if (jumlah <= 3000000) {
            jumlah += 5000;
            super.penarikan(jumlah);
        } else {
            System.out.println("melebihi batas");
        }
    }
}
