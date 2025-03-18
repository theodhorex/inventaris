<?php
$warna = [
    "judul" => "color: rgb(255, 235, 87)",
    "footer" => "color: rgb(36, 255, 145)"
];

$parkir = [
    ["jenis" => "Motor", "lama_parkir" => 4, "hari" => "Selasa"],
    ["jenis" => "Mobil", "lama_parkir" => 12, "hari" => "Rabu"],
    ["jenis" => "Motor", "lama_parkir" => 11, "hari" => "Kamis"],
    ["jenis" => "Mobil", "lama_parkir" => 2, "hari" => "Sabtu"],
    ["jenis" => "Motor", "lama_parkir" => 14, "hari" => "Minggu"],
    ["jenis" => "Sepatu", "lama_parkir" => 5, "hari" => "Jumat"]
];

?>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Laporan Biaya Parkir</title>
    <style>
        h2, h3 {
            color: rgb(237, 237, 237);
        }
        body {
            text-align: center;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            flex-direction: column;
            background: linear-gradient(100deg, rgb(110, 116, 127), rgb(54, 61, 74)); 
        }
        table {
            width: 70%;
            border-collapse: collapse;
            margin: 20px auto;
            text-align: center;
            color: rgb(227, 227, 227);
        }
        th, td {
            border: 3px solid rgb(193, 193, 193);
            padding: 8px;
            text-align: center;
            vertical-align: middle;
            background-color: rgb(108, 105, 105);
        }
        th {
            background-color:rgb(53, 53, 53);
        }
    </style>
</head>
<body>

<img src="image2.png" alt="" height="20%">

<?php
    // Kode untuk menampilkan judul (style warna harus diakses dengan pengaksesan array dari array php)
?>

<table>
    <tr>
        <th>No</th>
        <th>Jenis Kendaraan</th>
        <th>Lama Parkir (Jam)</th>
        <th>Hari</th>
        <th>Biaya Asli</th>
        <th>Diskon</th>
        <th>Total Biaya</th>
    </tr>

    <?php
        // Kode anda di sini
    ?>
</table>

<?php
    // Kode untuk menampilkan footer (Total semua biaya parkir ditampilkan, warnanya harus diakses dengan pengaksesan array dari array php)
?>

</body>
</html>