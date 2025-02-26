package com.rplbo.projectrekeninginheritance;

public class RekeningSyariah extends Rekening{

    public RekeningSyariah(Nasabah nasabah) {
        super(nasabah);
    }
    public RekeningSyariah(Nasabah nasabah, int saldo) {
        super(nasabah, saldo);
    }
    
    @Override
    public void penarikan(int jumlah) {
        if (jumlah > 100000) {
            jumlah += 1000;
            super.penarikan(jumlah);
        }
    }

    public void sedekah(int jumlah){

        }
    }
