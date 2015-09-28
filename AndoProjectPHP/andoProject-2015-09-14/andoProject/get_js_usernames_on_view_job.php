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

$sql="select * from id_js_username where id='$id'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       // echo "user: " . $row["username"]. " - pass: " . $row["password"].;
        $output[]=$row;
        print(json_encode($output));
    }

}else{
	print ("No results found");
} 


$conn->close();
//$a=$_REQUEST['user'];
?>


