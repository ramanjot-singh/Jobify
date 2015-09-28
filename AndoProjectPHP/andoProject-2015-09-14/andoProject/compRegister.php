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
$coName=$_REQUEST['coName'];
$mobile=$_REQUEST['mobile'];
$email=$_REQUEST['email'];
$password=$_REQUEST['password'];

$sql2="select * from compregister where email='$email'";
$result = $conn->query($sql2);

if ($result->num_rows > 0) {
   
  print ("Already a member");
    }


else{

	$sql="insert into compregister values('$name','$coName','$mobile','$email','$password')";
	$conn->query($sql);
}
$conn->close();


?>