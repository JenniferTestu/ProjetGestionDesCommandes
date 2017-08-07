<?php

	// Deconnexion du compte client
	
	session_start();
	session_unset();
	session_destroy();
	header('Location: index.php');
	exit();

?>