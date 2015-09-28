
<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "project";

$skill=$_REQUEST['skill'];
//$location=$_REQUEST['location'];
//$xp=$_REQUEST['xp'];

include("connect.php");

$sql = "SELECT * FROM jobdetails where title LIKE '%$skill%' ";
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


