<?php
//connection

$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "belanja";  //nama di database
$conn = mysqli_connect($servername, $username, $password,$dbname) or die("Koneksi gagal.");
?>