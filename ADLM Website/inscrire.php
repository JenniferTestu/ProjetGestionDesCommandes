<?php 
session_start();
// Script pour les erreurs
error_reporting(E_ALL);
ini_set('display_errors', '1');
// Connexion à la base de données   
include "scripts/connect_to_mysql.php"; 
mysqli_query($con,"SET NAMES 'utf8'");
?>
<?php
// Si l'utilisateur clique sur le bouton valider
if(isset($_POST['valider'])){

// On recupere les 2 mot de passe entré
$mdp = mysqli_real_escape_string($con,$_POST['mdp']);
$mdp2 = mysqli_real_escape_string($con,$_POST['mdp2']);

	if($mdp == $mdp2) // Si les 2 sont identiques
	{
		$nom = mysqli_real_escape_string($con,$_POST['nom']);
		$prenom = mysqli_real_escape_string($con,$_POST['prenom']);
		$mail = mysqli_real_escape_string($con,$_POST['mail']);
		$adresse = mysqli_real_escape_string($con,$_POST['adresse']);
		$cp = mysqli_real_escape_string($con,$_POST['cp']);
		$ville = mysqli_real_escape_string($con,$_POST['ville']);
		$pays = mysqli_real_escape_string($con,$_POST['pays']);
		$tel = mysqli_real_escape_string($con,$_POST['tel']);

		// On crypte le mot de passe
		$mdp = sha1($mdp);
		//On enregistre le nouveau compte client dans la base de données
		mysqli_query($con,'INSERT INTO clients (nom,prenom,mail,mdp,adresse,pays,tel) VALUES("'.$nom.'", "'.$prenom.'", "'.$mail.'", "'.$mdp.'", "'.$adresse.' '.$cp.' '.$ville.'", "'.$pays.'", "'.$tel.'")');

		
	}else
	{	// On retourne un message d'erreur si probleme avec les mots de passes
		$erreur = 'Les deux mots de passe que vous avez rentrés ne correspondent pas';
	}

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
	
	<div id="contenu">
		<div>	
			<h3>Veuillez remplir les champs ci dessous</h3>
			<p>* champs obligatoires</p>
			<?php
				if (isset($erreur)) echo '<br />',$erreur;
			?>
			<br />
			<br />
			
			<form id="form_inscrip" name="form" method="post" action="inscrire.php">
				<div>
					Nom *:<br />
					<input name="nom" type="text" id="nom" size="40" />
					<br /><br />
					Prenom *:<br />
					<input name="prenom" type="text" id="prenom" size="40" />
					<br /><br />
					Adresse mail *:<br />
					<input name="mail" type="text" id="mail" size="40" />
					<br /><br />
					Mot de passe *:<br />
					<input name="mdp" type="password" id="mdp" size="40" />
					<br /><br />
					Confirmation du mot de passe *:<br />
					<input name="mdp2" type="password" id="mdp2" size="40" />
					<br /><br />
				</div>
				<div>
					Adresse *:<br />
					<input name="adresse" type="text" id="adresse" size="40" />
					<br /><br />
					Code postal *:<br />
					<input name="cp" type="text" id="cp" size="40" />
					<br /><br />
					Ville *:<br />
					<input name="ville" type="text" id="ville" size="40" />
					<br /><br />
					Pays *:<br />
					<input name="pays" type="text" id="pays" size="40" />
					<br /><br />
					Numero de téléphone :<br />
					<input name="tel" type="text" id="tel" size="40" />
					<br />
					<br />
					<br />
				   
				<input type="submit" name="valider" id="button" value="Valider" />
				</div>
		  </form>

		</div>
		
	</div>	
		
	<?php include_once("footer.php");?>
	
</body>

</html>