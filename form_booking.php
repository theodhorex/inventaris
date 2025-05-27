<!DOCTYPE html>
<html lang="id">

<head>
  <meta charset="UTF-8">
  <title>Form Pemesanan Hotel</title>
  <style>
    body {
      font-family: sans-serif;
      background-color: #f0f8ff;
      padding: 20px;
    }

    main {
      max-width: 700px;
      margin: auto;
      background: white;
      padding: 20px;
      border-radius: 10px;
      transition: background-color 0.3s ease;
    }

    table {
      width: 100%;
    }

    td {
      padding: 10px;
    }

    input,
    select {
      width: 100%;
      padding: 8px;
    }

    input[type="submit"] {
      background-color: #28a745;
      color: white;
      border: none;
      padding: 10px;
    }

    input[type="submit"]:hover {
      background-color: #218838;
    }
  </style>
</head>

<body>
  <main id="formContainer">
    <h2>Form Pemesanan Kamar Hotel</h2>
    <form id="bookingForm" method="POST" action="summary_booking.php">
      <table>
        <tr>
          <td><label for="nama">Nama Pemesan:</label></td>
          <td><input type="text" id="nama" name="nama"></td>
        </tr>
        <tr>
          <td><label for="email">Email:</label></td>
          <td><input type="email" id="email" name="email"></td>
        </tr>
        <tr>
          <td><label for="booking-code">Kode Booking:</label></td>
          <td><input type="password" id="booking-code" name="booking-code" placeholder="ABCD-1234"></td>
        </tr>
        <tr>
          <td><label for="confirm-booking-code">Konfirmasi Kode Booking:</label></td>
          <td><input type="password" id="confirm-booking-code" name="confirm-booking-code"></td>
        </tr>
        <tr>
          <td><label for="jumlahMalam">Jumlah Malam:</label></td>
          <td><input type="number" id="jumlahMalam" name="jumlahMalam" min="1" max="30"></td>
        </tr>
        <tr>
          <td><label for="roomType">Jenis Kamar:</label></td>
          <td>
            <select id="roomType" name="roomType">
              <option value="">-- Pilih --</option>
              <option value="Standard">Standard</option>
              <option value="Deluxe">Deluxe</option>
              <option value="Suite">Suite</option>
            </select>
          </td>
        </tr>
        <tr id="roomFloorRow" style="display: none;">
          <td><label for="roomFloor">Pilih Lantai Kamar:</label></td>
          <td>
            <select id="roomFloor" name="roomFloor">
              <option value="">-- Pilih Lantai --</option>
            </select>
          </td>
        </tr>
      </table>
      <center><input type="submit" value="Pesan Sekarang"></center>
    </form>
  </main>

  <script>
    const form = document.getElementById("bookingForm");
    const roomType = document.getElementById("roomType");
    const formContainer = document.getElementById("formContainer");
    const roomFloorRow = document.getElementById("roomFloorRow");
    const roomFloor = document.getElementById("roomFloor");

    // Isikan Code pengganti background dan list bonus berdasarkan tiket (1,5 poin)
    roomType.addEventListener("change", function () {
      const roomTypeVal = roomType.value;
      roomFloor.innerHTML = '<option value="">-- Pilih Lantai --</option>'; // Reset opsi

      if (roomTypeVal === "Standard") {
        ["Lantai A", "Lantai B"].forEach(function (floor) {
          const option = document.createElement("option");
          option.value = floor;
          option.textContent = floor;
          roomFloor.appendChild(option);
        });
        roomFloorRow.style.display = "";
        formContainer.style.backgroundColor = "#d0e7f9";
      } else if (roomTypeVal === "Deluxe") {
        ["Lantai C", "Lantai D"].forEach(function (floor) {
          const option = document.createElement("option");
          option.value = floor;
          option.textContent = floor;
          roomFloor.appendChild(option);
        });
        roomFloorRow.style.display = "";
        formContainer.style.backgroundColor = "#d0f9e1";
      } else if (roomTypeVal === "Suite") {
        ["Lantai E", "Lantai F"].forEach(function (floor) {
          const option = document.createElement("option");
          option.value = floor;
          option.textContent = floor;
          roomFloor.appendChild(option);
        });
        roomFloorRow.style.display = "";
        formContainer.style.backgroundColor = "#f9d0e7";
      } else {
        roomFloorRow.style.display = "none";
        formContainer.style.backgroundColor = "white";
      }
    });


    form.addEventListener("submit", function (e) {
      const nama = document.getElementById("nama").value.trim();
      const email = document.getElementById("email").value.trim();
      const code = document.getElementById("booking-code").value.trim();
      const confirm = document.getElementById("confirm-booking-code").value.trim();
      const nights = document.getElementById("jumlahMalam").value.trim();
      const roomTypeVal = roomType.value;
      const selectedRoom = roomFloor.value;

      // Validasi form tidak boleh kosong
      if (!nama || !email || !code || !confirm || !nights || !roomTypeVal ||
        ((roomTypeVal === "Standard" || roomTypeVal === "Deluxe") && !selectedRoom)) {
        alert("Harap lengkapi semua kolom.");
        e.preventDefault();
        return;
      }

      // Validasi email harus @gmail.com
      if (!email.endsWith("@gmail.com")) {
        alert("Email harus menggunakan format @gmail.com");
        e.preventDefault();
        return;
      }

      // Validasi format kode booking (ABCD-1234)
      const codeRegex = /^[A-Z]{4}-\d{4}$/;
      if (!codeRegex.test(code)) {
        alert("Kode booking harus berformat ABCD-1234");
        e.preventDefault();
        return;
      }

      // Validasi kesamaan kode booking dan konfirmasi
      if (code !== confirm) {
        alert("Konfirmasi kode booking tidak sesuai");
        e.preventDefault();
        return;
      }
    });
  </script>
</body>

</html>