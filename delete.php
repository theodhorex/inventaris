<?php
include 'koneksi.php';

$id = $_GET['id'] ?? '';

if (!empty($id)) {
    // Siapkan query DELETE
    $sql = "DELETE FROM belanja WHERE id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("s", $id);

    if ($stmt->execute()) {
        $pesan = "Berhasil menghapus";
        header("Location: index.php?pesan=$pesan");
    } else {
        header("Location: index.php");
        echo "Gagal menghapus data: " . $conn->error;
    }

    $stmt->close();
} else {
    echo "id tidak ditemukan di URL.";
}

?>