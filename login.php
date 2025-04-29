<?php
session_start();
require "koneksi.php";
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $nim = mysqli_real_escape_string($conn, $_POST['nim']);
    $password = $_POST['password'];

    $query = "SELECT * FROM users WHERE nim='$nim' AND password='$password' LIMIT 1";
    $result = mysqli_query($conn, $query);

    if (mysqli_num_rows($result) == 1) {
        $data=mysqli_fetch_assoc($result);
        $_SESSION['nama'] = $data['nama'];
        $_SESSION['id_users'] = $data['id_users'];

        setcookie('email', $data['email'], time() + 60, "/");
        setcookie('nim', $data['nim'], time() + 60, "/");

        header("Location: home.php");
        exit();
    } else {
        $error = "Username atau Password salah!";
    }
}

?>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lihat Jadwal Kuliah Anda</title>
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
        }

        @keyframes gradientAnim {
            0% {background-position: 0% 50%;}
            50% {background-position: 100% 50%;}
            100% {background-position: 0% 50%;}
        }

        .login-container {
            background-color: #ffffffd9;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.15);
            width: 100%;
            max-width: 360px;
            text-align: center;
        }

        .login-container h2 {
            margin-bottom: 20px;
            color: #4a8f7c;
        }

        .login-container img {
            width: 80px;
            margin-bottom: 20px;
        }

        .form-group {
            text-align: left;
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #5a8b7b;
            font-weight: bold;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #c0e3d3;
            border-radius: 8px;
            background-color: #f0fff7;
        }

        .login-btn {
            margin-top: 20px;
            width: 100%;
            padding: 12px;
            background-color: #88c9a1;
            border: none;
            color: white;
            font-weight: bold;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .login-btn:hover {
            background-color: #6bbd8b;
        }

        .alert {
            padding: 10px;
            background-color: #ffcccc;
            color: #cc0000;
            border-radius: 5px;
            margin-bottom: 15px;
            border: 1px solid #ff9999;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login Jadwal Kuliah</h2>
        <img src="https://cdn-icons-png.flaticon.com/512/3135/3135789.png" alt="Mahasiswa">
        <?php if (isset($error)) { ?>
            <div class="alert"><?php echo $error; ?></div>
        <?php } ?>
        <form method="post" action="login.php">
            <div class="form-group">
                <label for="nim">NIM Mahasiswa</label>
                <input type="text" id="nim" name="nim" required>
            </div>
            <div class="form-group">
                <label for="password">Kata Sandi</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="login-btn">Login</button>
        </form>
    </div>
</body>
</html>