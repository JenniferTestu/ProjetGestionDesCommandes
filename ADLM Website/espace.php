<?php 
// Afficher les erreurs
error_reporting(E_ALL);
ini_set('display_errors', '1');
?>
<?php 

session_start();
// Si l'utilisateur ne s'est pas connecté
if(!isset($_SESSION['id'])){
	// On le renvoie a la page d'inscription
	header('Location: inscrire.php');
	exit();
}

?>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Autour de la moto</title>
<link href="style/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<?php include_once("header.php");?>
	
	<h3>TON ESPACE</h3>
	
	<div id="contenu">
		<div>
			<p id="liste">Bienvenue <?php echo htmlentities(trim($_SESSION['prenom'])); ?> !</p>
			<br/><form action="deconnexion.php"><input type="submit" value="Se déconnecter"></form>
			<br/><form action="informations.php"><input type="submit" value="Modifier mes informations"></form>
			<br/><form action="#"><input type="submit" value="Supprimer mon compte"></form>
		</div>

	</div>	
		
	<?php include_once("footer.php");?>
	
</body>

</html>