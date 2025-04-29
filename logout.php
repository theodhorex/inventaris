<?php
session_start();

// Hapus semua session
session_destroy();

// Hapus cookie kalau ada
if (isset($_COOKIE['email'])) {
    setcookie('email', '', time() - 60, "/");
}
if (isset($_COOKIE['nim'])) {
    setcookie('nim', '', time() - 60, "/");
}

header("Location: login.php");
exit();
?>
