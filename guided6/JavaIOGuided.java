package com.rplbo.guided6;

import java.util.Scanner;

public class JavaIOGuided {

    public void cekPythagoras(){
        //Siapkan Scanner
        //Baca baris n
        //Loop sebanyak n
        //Pecah berdasarkan spasi
        //Cek pythagoras, output ya atau tidak
        // Siapkan Scanner
        Scanner sc = new Scanner(System.in);

        // Baca jumlah data yang akan diperiksa
        int temp1 = Integer.valueOf(sc.nextLine());  // Read number of test cases

        // Loop sebanyak n
        for (int i = 0; i < temp1; i++) {  // Start loop from 0, to include the first case
            String data = sc.nextLine();
            String[] pisah = data.split(" ");

            // Cek apakah triplet membentuk Pythagoras
            int a = Integer.valueOf(pisah[0]);
            int b = Integer.valueOf(pisah[1]);
            int c = Integer.valueOf(pisah[2]);

            if ((a * a + b * b) == c * c) {
                System.out.println("ya");
            } else {
                System.out.println("tidak");
            }
        }
    }

    public void linearSearch(){
        //Siapkan Scanner
        //Baca jumlah studi kasus
        //Baca jumlah data
        //Pecah data
        //Cari apakah ada sesuai yang dicari, jika ada kembalikan posisi indeksnya, jika tidak kembalikan -1
        Scanner sc = new Scanner(System.in);

        // Read the number of test cases
        int T = Integer.parseInt(sc.nextLine());

        // Loop through each test case
        for (int t = 0; t < T; t++) {
            // Read the size of the array
            int N = Integer.parseInt(sc.nextLine());

            // Read the array elements
            String[] elements = sc.nextLine().split(" ");
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(elements[i]);
            }

            // Read the element to search for
            int x = Integer.parseInt(sc.nextLine());

            // Perform linear search to find the index of x
            int index = -1; // Initialize as -1 (if not found)
            for (int i = 0; i < N; i++) {
                if (arr[i] == x) {
                    index = i;  // Set index to the first occurrence of x
                    break;  // Stop further searching once found
                }
            }

            // Output the result
            System.out.println(index);
        }

        sc.close();
    }
}
