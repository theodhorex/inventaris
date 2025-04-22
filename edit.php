<?php
// kode anda

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Barang</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="background.css">
</head>
<body>
<div class="container py-5">
    <h2 class="mb-4 fw-bold">Edit Barang</h2>
    <form method="post" class="card shadow-sm p-4 bg-white">
        <input type="hidden" name="id" value="<?php //kode anda ?>">
        <div class="mb-3">
            <label class="form-label">Nama Barang</label>
            <input type="text" name="nama_barang" value="<?php //kode anda ?>" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Jumlah</label>
            <input type="number" name="jumlah" value="<?php //kode anda ?>" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Harga</label>
            <input type="number" name="harga" value="<?php //kode anda ?>" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Tanggal Beli</label>
            <input type="date" name="tanggal_beli" value="<?php //kode anda ?>" class="form-control" required>
        </div>
        <div class="d-flex justify-content-between">
        <a href="index.php" class="btn btn-secondary">Kembali</a>
        <button type="submit" class="btn btn-warning">Update</button>
        </div>
    </form>
</div>
</body>
</html>
