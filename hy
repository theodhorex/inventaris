<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Pendaftaran Volunteer UKDW</title>
</head>

<body>
    <header>
        <table>
            <tr>
                <td>
                    <h3 align="right">Universitas Kristen Duta Wacana</h3>
                </td>
                <td><a href="https://ukdw.ac.id/"><img src="images\ukdw.png" width="50px"></a>
                </td>
            </tr>
            <table>

    </header>
    <hr>
    <main>
        <center>
            <h1>Formulir Pendaftaran Volunteer UKDW</h1>
            <a href="https://ukdw.ac.id/"><img src="images\ukdw.png" width="250px"></a>
            <form action="hasil.html" method="POST">
                <table>
                    <tr>
                        <td>
                            <fieldset>
                                <legend>
                                    <h3>Informasi Pribadi</h3>
                                </legend>
                                <table>
                                    <tr>
                                        <td><label for="nama">Nama Lengkap :</label></td>
                                        <td><input type="text" id="namaID" name="nama" maxlength="50"></td>
                                    </tr>
                                    <tr>
                                        <td><label>NIK :</label></td>
                                        <td><input type="text" id="nikID" name="nik" maxlength="16"></td>
                                    </tr>
                                    <tr>
                                        <td><label>Alamat :</label></td>
                                        <td><textarea id="alamatID" name="alamat" cols="30" rows="2"
                                                required></textarea></td>
                                    </tr>
                                    <tr>
                                        <td><label>Tanggal Lahir :</label></td>
                                        <td><input type="date" name="tgl_lahir" id="tgl_lahirID" required></td>
                                    </tr>
                                    <tr>
                                        <td><label>Jenis Kelamin:</label></td>
                                        <td>
                                            <select name="kelamin">
                                                <option value="0">--Jenis Kelamin--</option>
                                                <option value="1">Laki-laki</option>
                                                <option value="2">Perempuan</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>Upload Foto Diri</label></td>
                                        <td><input type="file" name="foto_diri"></td>
                                    </tr>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>
                            <fieldset>
                                <legend>
                                    <h3>Pengalaman & Minat</h3>
                                </legend>
                                <table>
                                    <tr>
                                        <td><label>Pendidikan Terakhir :</label></td>
                                        <td>
                                            <input type="radio" name="pendidikan" value="1"> SMA
                                            <input type="radio" name="pendidikan" value="2"> D3
                                            <input type="radio" name="pendidikan" value="3"> D1
                                            <input type="radio" name="pendidikan" value="4"> Lainnya
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>Keahlian :</label></td>
                                        <td><textarea id="keahlianID" name="keahlian" cols="30" rows="2"
                                                required></textarea></td>
                                    </tr>
                                    <tr>
                                        <td><label>Bidang Minat :</label></td>
                                        <td>
                                            <input type="checkbox" name="minat" value="1"> IT
                                            <input type="checkbox" name="minat" value="2"> Event
                                            <input type="checkbox" name="minat" value="3"> Humas
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>Link Portofolio :</label></td>
                                        <td><input type="text" name="link" required></td>
                                    </tr>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>
                            <fieldset>
                                <legend>
                                    <h3>Kontak</h3>
                                </legend>
                                <table>
                                    <tr>
                                        <td><label>Email :</label></td>
                                        <td><input type="text" name="email" required></td>
                                    </tr>
                                    <tr>
                                        <td><label>Nomor HP (Whatsapp) :</label></td>
                                        <td><input type="text" name="No_wa" required></td>
                                    </tr>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td colspan="2">
                            <input type="reset" value="Reset">
                            <input type="submit" value="Daftar">
                        </td>
                    </tr>
                </table>
            </form>
        </center>
    </main>
    <hr>
    <footer>
        <h1><u>Jonathan Satriani Gracio Andrianto/71230978</u></h1>
    </footer>

</body>

</html>
