<?php
$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "progweb";  //nama di database
$conn = mysqli_connect($servername, $username, $password,$dbname) or die("Koneksi gagal.");

$id = null;
$oldNim = $oldNama = $oldKelamin = ""; 

if(isset($_GET['id'])){ 
    $id = $_GET['id'];
    $sql="SELECT * FROM mahasiswa WHERE id='".$id."' ";
    $result=mysqli_query($conn,$sql);
    if(mysqli_num_rows($result)>0){
        $row = mysqli_fetch_assoc($result);
        $oldNim = $row['nim'];
        $oldNama = $row['nama'];
        $oldKelamin = $row['jenis_kelamin'];
    }else{
        echo "Data yang hendak diedit raono";
    }
}

if(isset($_POST['submit'])){
    if(isset($_POST['id'])){ 
        //update
        $id = $_POST["id"];
        $nim = isset($_POST["nim"]) ? $_POST["nim"] : "";
        $nama = isset($_POST["nama"]) ? $_POST["nama"] : "";
        $kelamin = isset($_POST["kelamin"]) ? $_POST["kelamin"] : ""; 
        $sql = "UPDATE mahasiswa set nim='".$nim."', jenis_kelamin='".$kelamin."' WHERE id='".$id."'";
        if (mysqli_query($conn, $sql)){
            echo "berhasil update";
        }else{
            echo "gagal update";
        }
    }else{
        //insert
        $nim = isset($_POST["nim"]) ? $_POST["nim"] : "";
        $nama = isset($_POST["nama"]) ? $_POST["nama"] : "";
        $kelamin = isset($_POST["kelamin"]) ? $_POST["kelamin"] : "";
        $sql = "INSERT into mahasiswa (nim, nama, jenis_kelamin) VALUES ('".$nim."','".$nama."','".$kelamin."')";
        if(mysqli_query($conn, $sql)){
            echo "berhasil mengisi";
            echo "gagal mengisi";
        }
    }
}

mysqli_close($conn);
?>

<form action="create.php" method="POST">
    <?php
        if($id != null){
            ?>
            <input type="hidden" name="id" value="<?= $id; ?>"/>
            <?php
        }
    ?>
    nim: <input type="number" name="nim" maxlength= "8" minlength="8" required value="<?php echo $oldNim;?>"> <br>
    nama: <input type="text" name="nama" maxlength="50" required value="<?php echo $oldNama;?>"> <br>
    kelamin: <input type="radio" name="kelamin" value="L" <?php echo ($oldKelamin=="L")?'checked':''?>> Laki-laki
    <input type="radio" name="kelamin" value="P" <?php echo ($oldKelamin=="P")?'checked':''?>>perempuan <br>
    <input type="submit" name="submit">
</form>
