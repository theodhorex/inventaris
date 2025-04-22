<?php
include "koneksi.php";
//select

$sql = "SELECT * FROM mahasiswa"; //sesuaikan nama tabel
$result = mysqli_query($conn, $sql); //untuk run query

if(mysqli_num_rows($result)>0){
    ?>
    <table border='1'>
        <thead>
            <th>NIM</th>
            <th>Nama</th>
            <th>Jenis Kelamin</th>
            <th>Action</th>
        </thead>
        <?php
            while($row = mysqli_fetch_assoc($result)){
                echo "<tr>";
                echo "<td>".$row['nim']."</td>";
                echo "<td>".$row['nama']."</td>";
                echo "<td>".$row['jenis_kelamin']."</td>";
                echo "<td>";
                echo "<a href='create.php?id=".$row['id']."'>Edit</a>";
                echo "  ";
                echo "<a href='delete.php?id=".$row['id']."'>Hapus</a>";
                echo "</td>";
                echo "</tr>";
            }
            ?>
    </table>
    <?php
} else {
    echo "Data tidak ditemukan.";
}
?>
