
<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "project";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$email=$_REQUEST['email'];
//$email="mail.com";
$password=$_REQUEST['password'];
//$password="t";

$sql2="select * from compregister where email='$email'";
$result = $conn->query($sql2);
if ($result->num_rows == 0) {
   
  print ("Please Register");

    }
else{
	print("member");
	$row = $result->fetch_assoc();
	
	if($password==$row["password"]){
		print(" Matched");
	}else{
		print("Wrong Password");
	}
}



$conn->close();




?>