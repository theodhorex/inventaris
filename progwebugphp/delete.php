<?php
   include "koneksi.php";


    $id = $_GET['id'];
    $query = "DELETE FROM dummincoffee WHERE idBarang = $id";
    if (mysqli_query($conn, $query)) {
        header("Location: index.php?pesan=Berhasil Menghapus Data");
    }
?>