<?php
include 'koneksi.php';
$targetUpload = "uploads/".$_POST["namaMakanan"] . "." . strtolower(pathinfo($_FILES["gambarMakanan"]["name"], PATHINFO_EXTENSION));
if ($_FILES["gambarMakanan"]["type"] == "application/pdf" || $_FILES["gambarMakanan"]["type"] == "image/jpg") {
    if (move_uploaded_file($_FILES["gambarMakanan"]["tmp_name"], $targetUpload)) {
        $namaMakanan = $_POST["namaMakanan"];
        $asalMakanan = $_POST["asalMakanan"];
        $query = "INSERT into makanan (namaMakanan, asalMakanan, gambarMakanan) VALUES ('$namaMakanan', '$asalMakanan', '$targetUpload')";
        if(mysqli_query($conn, $query)) {
            echo "<h3>File PDF berhasil diupload dan data tersimpan</h3>";
        echo "<a href='input.php'><p>Kembali ke form input</p></a>";
        echo "<a href='index.php'><p>Lihat data makanan</p></a>";
        }
    } else
        echo "File harus berupa PDF atau JPG";
} else {
    echo "Gagal Upload: Failed";
}
?>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }

        h3 {
            color: #333;
        }

        p {
            margin: 10px 0;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

</body>

</html>