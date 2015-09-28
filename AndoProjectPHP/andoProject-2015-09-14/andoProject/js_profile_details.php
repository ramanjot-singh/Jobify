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
$name=$_REQUEST['name'];
$username=$_REQUEST['username'];
$exp_salary=$_REQUEST['exp_salary'];
$xp=$_REQUEST['xp'];
$skills=$_REQUEST['skills'];
$sql="insert into js_profile_details values('$name','$username','$exp_salary','$xp','$skills')";
$conn->query($sql);


$conn->close();


?>