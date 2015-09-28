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
//$name=$_REQUEST['name'];
//$mobile=$_REQUEST['mobile'];
//$email=$_REQUEST['email'];
//$password=$_REQUEST['password'];


$name="ea";
$mobile="2";
$email="raman.20the94@yahoo.in";
$password="a";

$sql2="select * from tryemp where email='$email'";
$result = $conn->query($sql2);

if ($result->num_rows > 0) {
   
  print ("Already a member");
    }


else{

	$sql="insert into tryemp values('$name','$email','$mobile','$password')";
	$conn->query($sql);
}
$conn->close();


?>