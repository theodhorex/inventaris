<?php
include "koneksi.php";

$nama_barang = $jumlah = $harga = $tanggal_beli = "";

if ($_POST) {
    $nama_barang = $_POST['nama_barang'];
    $jumlah = $_POST['jumlah'];
    $harga = $_POST['harga'];
    $tanggal_beli = $_POST['tanggal_beli'];

    $query = "INSERT INTO belanja (nama_barang, jumlah, harga, tanggal_beli) VALUES ('$nama_barang', '$jumlah', '$harga', '$tanggal_beli')";
    if (!mysqli_query($conn, $query)) {
        echo '<h3 style = "color:red;text-align:center;">Gagal Menambah Data</h3>';
    } else {
        $pesan = "Berhasil menambahkan data : $nama_barang";
        header("Location:index.php?pesan=.'$pesan.'");
        exit();
    }
}

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tambah Barang</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="background.css">
</head>
<body>
<div class="container py-5">
    <h2 class="mb-4 fw-bold">Tambah Barang</h2>
    <form method="post" class="card shadow-sm p-4 bg-white">
        <div class="mb-3">
            <label class="form-label">Nama Barang</label>
            <input type="text" name="nama_barang" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Jumlah</label>
            <input type="number" name="jumlah" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Harga</label>
            <input type="number" name="harga" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Tanggal Beli</label>
            <input type="date" name="tanggal_beli" class="form-control" required>
        </div>
        <div class="d-flex justify-content-between">
            <a href="index.php" class="btn btn-secondary">Kembali</a>
            <button type="submit" class="btn btn-success">Simpan</button>
        </div>
    </form>
</div>
</body>
</html>
