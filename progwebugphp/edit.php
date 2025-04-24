<?php
// kode anda

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Barang</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
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
    <h1 class="text-center m-3 text-white">Edit Barang</h1>
   
   <form action="edit.php" method="post" class="row justify-content-center m-3">

   <table>
        <input type="hidden" name="idBarang" value="<?php echo $data['idBarang'] ?>">
        <tr>
            <td>Nama Barang : </td>
            <td><input type="text" name="namaBarang" max="30" value="<?php echo $data['namaBarang'] ?>"></td>
        </tr>
        <tr>
            <td>Jumlah Barang : </td>
            <td><input type="number" name="jumlahBarang" value="<?php echo $data['stok'] ?>"></td>
        </tr>
        <tr>
            <td>Harga Barang : </td>
            <td><input type="text" name="hargaBarang" value="<?php echo $data['harga'] ?>"></td>
        </tr>
        <tr>
            <td>Promo : </td>
            <td>
                <input type="radio" name="promo" value="Y" <?php echo $data['promo'] == 'Y' ? 'N' : ''; ?>> Y
                <input type="radio" name="promo" value="N" <?php echo $data['promo'] == 'Y' ? 'N' : ''; ?>> N
            </td>
        </tr> 
   </table>
        <div class="form-group">
            <button type="submit" class="btn btn-success">Edit</button>
            <a href="index.php" class="btn btn-secondary">Back</a>
        </div>
   </form>

   <?php
        //kode anda
        require 'koneksi.php';

        if (isset($_GET['id'])) {
            $id = $_GET['id'];
            $sql = "SELECT * FROM dummincoffee WHERE idBarang = $id";
            $query = mysqli_query($conn, $sql);
            $data = mysqli_fetch_assoc($query);
        }


        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $id = $_POST['id'];
            $namaBarang = $_POST['namaBarang'];
            $jumlahBarang = $_POST['jumlahBarang'];
            $hargaBarang = $_POST['hargaBarang'];
            $promo = $_POST['promo'];

            $update = "UPDATE dummincoffee SET 
                        namaBarang = '$namaBarang', 
                        stok = '$jumlahBarang', 
                        harga = '$hargaBarang', 
                        promo = '$promo' 
                        WHERE idBarang = $id";

            $hasil = mysqli_query($conn, $update);

            if ($hasil) {
                header("Location: index.php?pesan=Berhasil Mengupdate Data");
                exit;
            }
        }
    ?>

</body>
</html>