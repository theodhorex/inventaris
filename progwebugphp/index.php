<?php
    // kode anda 

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Stok Bakery</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- Jangan sentuh kodingan ini!! -->
    <style>

        body {
            background-image: url("images/dumin.jpg");
            background-position: center;
            background-size: cover;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        table {
            border: 2px black solid;
            border-collapse: collapse;
        }

        table * {
            padding: 4px;
        }

    </style>

</head>
<body>
    <h1 class="text-center m-3 text-white font-weight-bold">Data Stok Dummin Coffee</h1>

    <div class="row justify-content-center m-3">
        <a class="btn btn-primary" href="formBarang.php">Tambah Barang</a>
    </div>

    <!-- Bikin tabel -->
    <?php
    // kode anda

        ?>
        <div class="row justify-content-center m-3">
            <table border="1" class="table table-striped table-light">
                <thead class="thead-dark">
                    <th>Nama Barang</th>
                    <th>Jumlah Barang</th>
                    <th>Harga</th>
                    <th>Potongan</th>
                    <th colspan="2">Pengaturan</th>
                </thead>
                
            <?php
                // kode anda
               include "koneksi.php";

               $sql = "SELECT * FROM dummincoffee";
               $res = mysqli_query($conn, $sql);

               if(mysqli_num_rows($res)>0){
                   ?>
                <tbody>
                    <?php

                    while($row = mysqli_fetch_assoc($res)){
                        echo "<tr>";
                        echo "<td>".$row['namaBarang']."</td>";
                        echo "<td>".$row['stok']."</td>";
                        echo "<td>".$row['harga']."</td>";
                        echo "<td>".$row['promo']."</td>";
                        echo "<td>";
                        echo "<a class='btn btn-info' href='edit.php?id=".$row['idBarang']."'>Edit</a>";
                        echo "  ";
                        echo "<a class='btn btn-danger' href='delete.php?id=".$row['idBarang']."'>Hapus</a>";
                        echo "</td>";
                        echo "</tr>";
                    }

                    ?>
                </tbody>
                <?php
               }else {
                echo "Data kosong!";
               }
                ?>
        </div>
        
        <!-- UNTUK MENAMPILKAN PESAN, JANGAN DIHAPUS  -->
        <?php if (isset($pesan) && $pesan !== ""): ?>
                <h3 class='text-white text-center mt-3'><?= $pesan ?></h3>
            <?php endif; ?>
            
        <?php
        // kode anda
        
    ?>

</body>
</html>