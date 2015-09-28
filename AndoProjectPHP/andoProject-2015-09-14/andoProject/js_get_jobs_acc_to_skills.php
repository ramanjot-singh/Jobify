
<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "project";

$js_username=$_REQUEST['js_username'];


include("connect.php");

$sql = "SELECT * FROM js_profile_details where username ='$js_username'";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       // echo "user: " . $row["username"]. " - pass: " . $row["password"].;
        $output[]=$row;
    }
print(json_encode($output));
} else {
	print("No result found");
}


$conn->close();
//$a=$_REQUEST['user'];
?>


