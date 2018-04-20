<?php
	
	$con= mysqli_connect ("localhost", "root", "","attendance");
	
	//mysqli_select_db($con,"attendance") or die(mysqli_error());
	
	$t_name = $_POST['teacher_name'];
	$t_email = $_POST['teacher_email'];
	
	$insert1 = "insert into teacher (teacher_name) values ('$t_name')";
	$insert2 = "insert into user (email) values ('$t_email')";
	
	mysqli_query($con,$insert1) or die(mysqli_error($con));
	mysqli_query($con,$insert2) or die(mysqli_error($con));
	
	echo "Record sucessfully inserted";
?>