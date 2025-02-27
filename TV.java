package org.example;

public class TV {
    public int maxResolusi;
     public Colokan colokan;

    public TV(int maxResolusi) {
        this.maxResolusi = maxResolusi;
    }

    public void connect(Colokan colokan) {
        this.colokan = colokan;
        System.out.println("Berhasil Connect ke - " + colokan.getMerk());
    }

    public int getResolution() {
        this.colokan = colokan;
        if (colokan.getRealBandwith() >= 10 && colokan.getRealBandwith() < 35) {
            if (maxResolusi < 480) {
                return maxResolusi;
            }
            return 480;
        } else if (colokan.getRealBandwith() >= 35 && colokan.getRealBandwith() <= 100) {
            if (maxResolusi < 720) {
                return maxResolusi;
            }
            return 720;
        } else if (colokan.getRealBandwith() > 100) {
            if (maxResolusi < 1080) {
                return maxResolusi;
            }
            return 1080;
        } else {
            System.out.println("gak bisa nyala mas");
        }
        return 0;
    }
}
