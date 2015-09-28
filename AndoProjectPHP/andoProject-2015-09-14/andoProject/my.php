<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "project";

$myarray1 = array("1aaa","2","3bbb","4","-1","-2");
$converted_string = implode(",",$myarray1);
//echo $converted_string;
//$converted_values = explode(",",$converted_string);
//echo print_r($converted_values);
$query="select ";
//print("        {}}}{{{");
$length = count($myarray1);
for ($i = 0; $i < $length; $i++) {
  //print $myarray1[$i];
}
$query=$query."* from ";
$query=$query."jobdetails";
//$query=$query.$myarray1[2];
//print ($query);

include("connect.php");

$sql = "SELECT * FROM jobdetails ";
$result = $conn->query($query);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       // echo "user: " . $row["username"]. " - pass: " . $row["password"].;
        $output[]=$row;
    }
//print(json_encode($output));
} else {
	print("No result found");
}

$arr =$_REQUEST['words'];
//$arr = json_decode($arr);
//$length = count($myarray1);
//for ($i = 0; $i < $length; $i++) {
 // print $arr[$i];
//}
//print ($arr);

$length=count($arr);
for ($i = 0; $i < $length; $i++) {
  print ($arr);
  print ("_");
  print($i);
}
$sss= implode(",", $arr);
print ($sss);
$conn->close();
//$a=$_REQUEST['user'];
?>


