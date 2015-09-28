<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "project";

$q=$_REQUEST['q'];

//$skill="";

include("connect.php");


print($q);

$conn->close();
//$a=$_REQUEST['user'];
?>
