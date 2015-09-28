<?php 

//compRegister into table 

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "project";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$id=$_REQUEST['id'];
$js_username=$_REQUEST['js_username'];
$sql="insert into id_js_username values('$id','$js_username')";
$conn->query($sql);


$conn->close();


?>