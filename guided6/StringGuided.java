package com.rplbo.guided6;

public class StringGuided {
    private String s = "";

    public StringGuided(String ss){
        this.s = ss;
    }

    public boolean cekPalindrom(){
        //1. lower
        //2. replace semua non alfanumerik
        //3. reverse
        //4. cek sama atau tidak
        String temp = s.trim().toLowerCase();
        temp = temp.replace("[^a-zA-Z0-9]", "");
        String temp2 = new StringBuilder(temp).reverse().toString();
        if (temp.equals(temp2)) {
            return true;
        }
        return false;
    }

    public String ubahHurufBesarAwal() {
        //1. lower
        //2. split
        //3. ubah huruf besar char pertama
        //4. masukan ke SB
        String temp = s.trim().toLowerCase();
        String[] words = temp.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            // Capitalize the first letter of each word
            StringBuilder sb = new StringBuilder(word);
            sb.replace(0, 1, String.valueOf(word.charAt(0)).toUpperCase());
            result.append(sb).append(" ");
        }

        // Remove the trailing space and return the result
        return result.toString().trim();
    }
}
