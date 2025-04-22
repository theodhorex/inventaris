<?php
//connection

$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "progweb";  //nama di database
$conn = mysqli_connect($servername, $username, $password,$dbname) or die("Koneksi gagal.");
?>