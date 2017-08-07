<?php 
// Script pour les erreurs
error_reporting(E_ALL);
ini_set('display_errors', '1');
// Connexion à la base de données   
include "scripts/connect_to_mysql.php"; 
mysqli_query($con,"SET NAMES 'utf8'");
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
<?php
// preremplir les champs du formulaire

$id = $_SESSION['id'];

$sql = mysqli_query($con,"SELECT * FROM clients WHERE id='$id' LIMIT 1");
$clientCount = mysqli_num_rows($sql); // On compte le nombre de resultat
    if ($clientCount > 0) {
		while($row = mysqli_fetch_array($sql)){ 
			 $nom = $row["nom"];
			 $prenom = $row["prenom"];
			 $mail = $row["mail"];
			 $adresse = $row["adresse"];
			 $pays = $row["pays"];
			 $tel = $row["tel"];
        }
	}

?>
<?php
// Si l'utilisateur clique sur le bouton valider
if(isset($_POST['valider'])){

$id = $_SESSION['id'];

// On recupere les 2 mot de passe entré
$mdp = mysqli_real_escape_string($con,$_POST['mdp']);
$mdp2 = mysqli_real_escape_string($con,$_POST['mdp2']);

	if($mdp == $mdp2) // Si les 2 sont identiques
	{
		$nom = mysqli_real_escape_string($con,$_POST['nom']);
		$prenom = mysqli_real_escape_string($con,$_POST['prenom']);
		$mail = mysqli_real_escape_string($con,$_POST['mail']);
		$adresse = mysqli_real_escape_string($con,$_POST['adresse']);
		$pays = mysqli_real_escape_string($con,$_POST['pays']);
		$tel = mysqli_real_escape_string($con,$_POST['tel']);

		// On crypte le mot de passe
		$mdp = sha1($mdp);
		//On enregistre le nouveau compte client dans la base de données
		mysqli_query($con,'UPDATE clients SET nom = "'.$nom.'", prenom = "'.$prenom.'", mail = "'.$mail.'", mdp = "'.$mdp.'", adresse = "'.$adresse.'", pays = "'.$pays.'", tel = "'.$tel.'" WHERE id = '.$id.'');

		$message = 'Vos modifications ont été prise en compte';
	}else
	{	// On retourne un message d'erreur si probleme avec les mots de passes
		$message = 'Les deux mots de passe que vous avez rentrés ne correspondent pas';
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
	
	<h3>VOS INFORMATIONS</h3>
	
	<div id="contenu">
		<div>	
			<p>* champs obligatoires</p>
			<?php
				if (isset($message)) echo '<br />',$message;
			?>
			<br />
			<br />
			
			<form id="form_inscrip" name="form" method="post" action="informations.php">
				<div>
					Nom *:<br />
					<input name="nom" type="text" id="nom" size="40" value='<?php echo $nom; ?>'/>
					<br /><br />
					Prenom *:<br />
					<input name="prenom" type="text" id="prenom" size="40" value='<?php echo $prenom; ?>'/>
					<br /><br />
					Adresse mail *:<br />
					<input name="mail" type="text" id="mail" size="40" value='<?php echo $mail; ?>'/>
					<br /><br />
					Mot de passe *:<br />
					<input name="mdp" type="password" id="mdp" size="40" />
					<br /><br />
				</div>
				<div>
					Confirmation du mot de passe *:<br />
					<input name="mdp2" type="password" id="mdp2" size="40" />
					<br /><br />
					Adresse *:<br />
					<input name="adresse" type="text" id="adresse" size="40" value='<?php echo $adresse; $cp; $ville;?>'/>
					<br /><br />
					Pays *:<br />
					<input name="pays" type="text" id="pays" size="40" value='<?php echo $pays; ?>'/>
					<br /><br />
					Numero de téléphone :<br />
					<input name="tel" type="text" id="tel" size="40" value='<?php echo $tel; ?>'/>
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