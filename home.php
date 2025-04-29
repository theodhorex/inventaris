<?php
session_start();
require_once "koneksi.php";
$nama = $_SESSION['nama'] ?? "";
$nim = $_COOKIE['nim'] ?? "";
$email = $_COOKIE['email'] ?? "";
$id = $_SESSION['id_users'] ?? "";
?>

<!DOCTYPE html>
<html lang="id">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Home - Jadwal Kuliah</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: linear-gradient(135deg, #e0f7e6, #d4f1f9, #e6f9e0);
      background-size: 400% 400%;
      animation: gradientAnim 10s ease infinite;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      position: relative;
      overflow: hidden;
    }

    @keyframes gradientAnim {
      0% {
        background-position: 0% 50%;
      }

      50% {
        background-position: 100% 50%;
      }

      100% {
        background-position: 0% 50%;
      }
    }

    .decor {
      position: absolute;
      top: 0;
      left: 0;
      z-index: 0;
      width: 100%;
      height: 100%;
      pointer-events: none;
      opacity: 0.15;
    }

    .container {
      position: relative;
      z-index: 1;
      background-color: #ffffffdd;
      padding: 30px;
      border-radius: 20px;
      box-shadow: 0 12px 24px rgba(0, 0, 0, 0.1);
      width: 90%;
      max-width: 800px;
    }

    h2 {
      text-align: center;
      color: #4a8f7c;
      margin-bottom: 20px;
    }

    p {
      font-size: 1rem;
      color: #5a8b7b;
      text-align: center;
      margin-bottom: 20px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      background-color: #f0fff7;
      border-radius: 10px;
      overflow: hidden;
      margin: 20px 0;
    }

    th,
    td {
      padding: 12px 15px;
      text-align: left;
      border-bottom: 1px solid #c0e3d3;
    }

    th {
      background-color: #88c9a1;
      color: white;
    }

    tr:hover {
      background-color: #d4f1e9;
    }

    .logout-btn {
      margin-top: 20px;
      display: inline-block;
      background-color: #6bbd8b;
      color: white;
      padding: 10px 20px;
      border-radius: 8px;
      text-decoration: none;
      transition: background-color 0.3s ease;
      font-weight: bold;
    }

    .logout-btn:hover {
      background-color: #5aac7a;
    }
  </style>
</head>

<body>

  <div class="container">
    <h2>Selamat Datang, <?php echo htmlspecialchars($nama); ?>!</h2>
    <p>
      Email: <?php echo htmlspecialchars($email); ?> |
      NIM: <?php echo htmlspecialchars($nim); ?>
    </p>

    <h2>Jadwal Kuliah Mahasiswa</h2>
    <table>
      <thead>
        <tr>
          <th>Hari</th>
          <th>Mata Kuliah</th>
          <th>Sesi</th>
        </tr>
      </thead>
      <tbody>
        <?php
        $sql = "SELECT * FROM jadwal_kuliah WHERE id_users = '$id'";
        $result = mysqli_query($conn, $sql);
        if (mysqli_num_rows($result) > 0) {
          while ($row = mysqli_fetch_assoc($result)) {
            echo "<tr>";
            echo "<td>" . $row['hari'] . "</td>";
            echo "<td>" . $row['mata_kuliah'] . "</td>";
            echo "<td>" . $row['sesi'] . "</td>";
            echo "</tr>";
          }
        } else {
          echo "Data tidak ditemukan.";
        }
        ?>
      </tbody>
    </table>

    <center><a href="logout.php" class="logout-btn">Logout</a></center>
  </div>

</body>

</html>