<!DOCTYPE html>
<html lang="id">
<head>
  <meta charset="UTF-8">
  <title>Ringkasan Pemesanan</title>
  <style>
    body {
      background-color: #fffaf0;
      font-family: sans-serif;
      padding: 20px;
    }
    .container {
      background: white;
      max-width: 700px;
      margin: auto;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 2px 6px rgba(0,0,0,0.1);
    }
    table {
      width: 100%;
      border-collapse: collapse;
    }
    td {
      padding: 10px;
      border-bottom: 1px solid #ddd;
    }
    td:first-child {
      font-weight: bold;
      width: 40%;
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>Ringkasan Pemesanan Kamar</h2>

    <?php
      $nama = $_REQUEST['nama'];
      $email = $_REQUEST['email'];
      $kode = $_REQUEST['booking-code'];
      $malam = $_REQUEST['jumlahMalam'];
      $tipe = $_REQUEST['roomType'];
      $lantai = $_REQUEST['roomFloor'];
      $hargaPerMalam = ['Standard' => 300000, 'Deluxe' => 600000, 'Suite' => 900000];
      $biaya = $malam * $hargaPerMalam[$tipe];
    ?>


    <table>
      <tr><td>Nama Pemesan</td><td><?= $nama ?></td></tr>
      <tr><td>Email</td><td><?= $email ?></td></tr>
      <tr><td>Kode Booking</td><td><?= $kode ?></td></tr>
      <tr><td>Jumlah Malam</td><td><?= $malam ?> malam</td></tr>
      <tr><td>Jenis Kamar</td><td><?= $tipe ?></td></tr>
      <tr><td>Lantai Kamar</td><td><?= $lantai ?></td></tr>
  </table>


    <h3>Total Pembayaran</h3>
    <table>
      <tr>
        <td>Harga per Malam (Rp<?= number_format($hargaPerMalam[$tipe], 0, ',', '.') ?>)</td>
        <td>Rp<?= number_format($hargaPerMalam[$tipe], 0, ',', '.') ?></td>
      </tr>
      <tr>
        <td>Total (<?= $malam ?> malam)</td>
        <td><strong>Rp<?= number_format($biaya, 0, ',', '.') ?></strong></td>
      </tr>
    </table>

    <p><a href="form_booking.php">← Kembali ke Form</a></p>
  </div>
</body>
</html>
