<?php
// kode anda 


?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tambah Barang</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- Jangan sentuh kodingan ini!!-->
    <style>
        body{
            background-image: url("images/dumin.jpg");
            background-position: center;
            background-size: cover;
        }
        
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        table {
            margin-bottom: 20px; 
        }

        .form-group {
            margin-bottom: 10px;
        }

        td {
            font-size: large;
            color: aliceblue;
        }
    </style>

</head>
<body>
    <h1 class="row justify-content-center m-3 text-white">Tambah Barang</h1>
    <form action="formBarang.php" method="post" class="row justify-content-center m-3">
    <table>
            <tr>
                <td>Nama Barang : </td>
                <td><input type="text" name="namaBarang" max="30"></td>
            </tr>
            <tr>
                <td>Jumlah Barang : </td>
                <td><input type="number" name="jumlahBarang"></td>
            </tr>
            <tr>
                <td>Harga Barang : </td>
                <td><input type="text" name="hargaBarang"></td>
            </tr>
            <tr>
                <td>Promo :</td>
                <td>
                    <input type="radio" name="promo" value="Y"> Y
                    <input type="radio" name="promo" value="N"> N
                </td>
            </tr> 
    </table>
        <div class="form-group">
            <button type="submit" class="btn btn-success">Submit</button>
            <a href="index.php" class="btn btn-secondary">Back</a>
        </div>
    </form>

    <?php
    // kode anda 
    require "koneksi.php";

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $namaBarang = $_POST['namaBarang'];
        $jumlahBarang = $_POST['jumlahBarang'];
        $hargaBarang = $_POST['hargaBarang'];
        $promo = $_POST['promo'];

        $sql = "INSERT INTO dummincoffee (namaBarang, stok, harga, promo)
                VALUES ('$namaBarang', '$jumlahBarang', '$hargaBarang', '$promo')";

        $query = mysqli_query($conn, $sql);

        if ($query) {
            header("Location: index.php?pesan=Berhasil Mengisi Data");
            exit;
        } else {
            echo "<div class='alert alert-danger text-center'>Gagal Mengisi Data</div>";
        }
    }
    ?>

</body>
</html>