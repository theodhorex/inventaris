<?php
include "koneksi.php";
$oldnama_barang = $oldjumlah = $oldharga = $oldtanggal_beli = "";

if (isset($_GET['id'])) {
    $id = $_GET['id'];
    $sql = "SELECT * FROM belanja WHERE id='" . $id . "' ";
    $result = mysqli_query($conn, $sql);
    if (mysqli_num_rows($result) > 0) {
        $row = mysqli_fetch_assoc($result);
        $oldid = $id;
        $oldnama_barang = $row['nama_barang'];
        $oldjumlah = $row['jumlah'];
        $oldharga = $row['harga'];
        $oldtanggal_beli = $row['tanggal_beli'];
    } else {
        echo "Data yang hendak diedit raono";
    }
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $id = $_POST["id"];
    $nama_barang = $_POST["nama_barang"];
    $jumlah = $_POST["jumlah"];
    $harga = $_POST["harga"];
    $tanggal_beli = $_POST["tanggal_beli"];
    $sql2 = "UPDATE belanja SET nama_barang='$nama_barang', jumlah = '$jumlah', harga = '$harga', tanggal_beli='$tanggal_beli' WHERE id=$id";
    if (mysqli_query($conn, $sql2)) {
        $pesan = "Berhasil mengupdate data : $nama_barang";
        header("Location:index.php?pesan=$pesan");
        exit();
    } else {
        echo '<h3 style = "color:red;text-align:center;">Gagal Mengedit Data</h3>';
    }
}
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
            <input type="hidden" name="id" value="<?php echo $oldid; ?>">
            <div class="mb-3">
                <label class="form-label">Nama Barang</label>
                <input type="text" name="nama_barang" value="<?php echo $oldnama_barang; ?>" class="form-control"
                    required>
            </div>
            <div class="mb-3">
                <label class="form-label">Jumlah</label>
                <input type="number" name="jumlah" value="<?php echo $oldjumlah; ?>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Harga</label>
                <input type="number" name="harga" value="<?php echo $oldharga; ?>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Tanggal Beli</label>
                <input type="date" name="tanggal_beli" value="<?php echo $oldtanggal_beli; ?>" class="form-control"
                    required>
            </div>
            <div class="d-flex justify-content-between">
                <a href="index.php" class="btn btn-secondary">Kembali</a>
                <button type="submit" class="btn btn-warning">Update</button>
            </div>
        </form>
    </div>
</body>

</html>