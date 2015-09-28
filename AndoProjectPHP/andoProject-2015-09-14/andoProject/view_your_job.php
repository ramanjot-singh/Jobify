
<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "project";



$co_username=$_REQUEST['username'];

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}



$sql = "SELECT * FROM jobdetails where username ='$co_username' ";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       // echo "user: " . $row["username"]. " - pass: " . $row["password"].;
        $output[]=$row;
    }

} 
print(json_encode($output));

$conn->close();
//$a=$_REQUEST['user'];
?>


