<?php
include "koneksi.php";
//select
$sql = "SELECT * FROM belanja"; //sesuaikan nama tabel
$result = mysqli_query($conn, $sql); //untuk run query

if(mysqli_num_rows($result)>0){
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Daftar Belanja</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="background.css">
</head>
<body>
<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold">Daftar Belanja</h2>
        <a href="formBelanja.php" class="btn btn-success">+ Tambah Barang</a>
    </div>
    <div class="card shadow-sm">
        <div class="card-body">
            <table class="table table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>No</th>
                        <th>Nama Barang</th>
                        <th>Jumlah</th>
                        <th>Harga</th>
                        <th>Tanggal Beli</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <?php
                        $no = 1;
                    while($row = mysqli_fetch_assoc($result)){
                        echo "<tr>";
                        echo "<td>".$no."</td>";
                        echo "<td>".$row['nama_barang']."</td>";
                        echo "<td>".$row['jumlah']."</td>";
                        echo "<td>".$row['harga']."</td>";
                        echo "<td>".$row['tanggal_beli']."</td>";
                        echo "<td>";
                echo "<a href='edit.php?id=".$row['id']."'class='btn btn-warning btn-sm'>Edit</a>";
                echo "  ";
                echo "<a href='delete.php?id=".$row['id']."'class='btn btn-danger btn-sm' onclick=\"return confirm('Yakin?')\">Hapus</a>";
                echo "</td>";
                        echo "</tr>";
                        $no += 1;
                    }}
                    ?>
                </tbody>
            </table>
            
            <!-- UNTUK MENAMPILKAN PESAN, JANGAN DIHAPUS ATAU DIUBAH  -->
            <?php if (isset($pesan) && $pesan !== ""): ?>
                <h3 class='text-success text-center mt-3'><?= $pesan ?></h3>
            <?php endif; ?>
        </div>
    </div>
</div>
</body>
</html>
