
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
//$email="raman.2094@yahoo.in";
$password=$_REQUEST['password'];
//$password="raman";

$sql2="select * from empregister where Email='$email'";
$result = $conn->query($sql2);
if ($result->num_rows == 0) {
   
  print ("Please Register");

    }
else{
	print("member");
	$row = $result->fetch_assoc();
	
	if($password==$row["Password"]){
		print(" Matched");
	}else{
		print("Wrong Password");
	}
}




$conn->close();




?>