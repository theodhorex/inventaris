<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Input Data Makanan</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; max-width: 400px; margin: auto; }
        label { display: block; margin: 10px 0 5px; }
        input, button { width: 100%; padding: 8px; }
        button { margin-top: 15px; background-color: #007bff; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #0056b3; }
    </style>
</head>
<body>
    <h2>Input Data Makanan</h2>
    <form action="upload.php" method="post" enctype="multipart/form-data">
        <label for="namaMakanan">Nama Makanan:</label>
        <input type="text" id="namaMakanan" name="namaMakanan" required />

        <label for="asalMakanan">Asal Negara Makanan:</label>
        <input type="text" id="asalMakanan" name="asalMakanan" required />

        <label for="gambarMakanan">Upload File PDF (gambar makanan):</label>
        <input type="file" id="gambarMakanan" name="gambarMakanan" accept="application/pdf" required />

        <button type="submit">Simpan</button>
    </form>
</body>
</html>
