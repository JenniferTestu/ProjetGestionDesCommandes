<?php 
session_start();
// Script pour les erreurs
error_reporting(E_ALL);
ini_set('display_errors', '1');
// Connexion à la base de données   
include "scripts/connect_to_mysql.php"; 
// Gestion des accents
mysqli_query($con,"SET NAMES 'utf8'");
?>
<?php

  $errorMessage = '';
 
  // Si le formulaire a été envoyé
  if(isset($_POST['Valider']))
  {
    // Si les identifiants sont transmit
    if(isset($_POST['mail']) && isset($_POST['mdp'])) {
		
		$mail = mysqli_real_escape_string($con,$_POST['mail']);
		$mdp = mysqli_real_escape_string($con,$_POST['mdp']);
		$mdp=sha1($mdp);
		
		$sql=mysqli_query($con,'SELECT * FROM clients WHERE mail="'.$mail.'" AND mdp="'.$mdp.'"');
	 
		while($row=mysqli_fetch_assoc($sql)) // On lit les resultats
		{
				 $id = $row["id"];
				 $nom = $row["nom"];
				 $prenom = $row["prenom"];	

				$_SESSION['id']=$id;
				$_SESSION['nom']=$nom;
				$_SESSION['prenom']=$prenom;
				
				// Si l'utilisateur été en train de commander
				if($_SESSION['redir']==true){
					// On le renvoie a la page du panier
					header('Location: panier.php');
				}else{
					// Sinon on le renvoie a l'espace du compte client
					header('Location: espace.php');	
				}	
				exit();
		}
    }
      else
    {
      $errorMessage = 'Veuillez inscrire vos identifiants svp !';
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
		<h3>Pour vous connectez</h3>
		<form id="form_connexion" name="form1" method="post" >
		Adresse mail :<br />
        <input name="mail" type="text" id="nom" size="40" />
        <br /><br />
        Mot de passe :<br />
		<input name="mdp" type="password" id="mdp" size="40" />
		<br />
		<br />
		<a href="./inscrire.php">Pas encore de compte ?</a>
		<br />
		<br />
		<br />
       
        <input type="submit" name="Valider" id="button" value="Valider" />
       
      </form>

	</div>
	<?php
			if (isset($errorMessage)) echo '<br />',$errorMessage;
		?>
	</div>	
		
	<?php include_once("footer.php");?>
	
</body>

</html>