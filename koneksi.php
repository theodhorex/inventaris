<?php
// koneksi.php: koneksi ke database MySQL
$host = "localhost";
$user = "root";       // sesuaikan dengan user db Anda
$password = "";       // sesuaikan dengan password db Anda
$database = "php4";  // sesuaikan dengan nama database Anda

$conn = new mysqli($host, $user, $password, $database);

if ($conn->connect_error) {
    die("Koneksi gagal: " . $conn->connect_error);
}
?>
