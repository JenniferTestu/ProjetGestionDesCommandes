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

// Si le formulaire a été envoyé
if(isset($_POST['valider'])){

	// Si les boutons ont été coché
	if(isset($_POST['dest_client']) && isset($_POST['fact_client'])){
				
		// Si l'utilisateur a choisi un destinataire autre que lui
		if($_POST['dest_client']=='false'){
			
			// Si le formulaire est vide
			if(empty($_POST['nom_dest']) || empty($_POST['prenom_dest']) || empty($_POST['adresse_dest']) || empty($_POST['cp_dest']) || empty($_POST['ville_dest']) || empty($_POST['pays_dest'])){
				$erreur = 'Formulaire incomplet';
			}else{
				$nom_dest = mysqli_real_escape_string($con,$_POST['nom_dest']);
				$prenom_dest = mysqli_real_escape_string($con,$_POST['prenom_dest']);
				$adresse_dest = mysqli_real_escape_string($con,$_POST['adresse_dest']);
				$cp_dest = mysqli_real_escape_string($con,$_POST['cp_dest']);
				$ville_dest = mysqli_real_escape_string($con,$_POST['ville_dest']);
				$pays_dest = mysqli_real_escape_string($con,$_POST['pays_dest']);
				// On stock les informations dans une session dest
				$_SESSION['dest'] = array('nom' => $nom_dest, 'prenom' => $prenom_dest, 'adresse' => $adresse_dest.' '.$cp_dest.' '.$ville_dest ,'pays' => $pays_dest);
			}
			
		}
		
		// Si l'utilisateur a choisi un payeur autre que lui
		if($_POST['fact_client']=='false'){
		
			// Si le formulaire est vide
			if(empty($_POST['nom_fact']) || empty($_POST['prenom_fact']) || empty($_POST['adresse_fact']) || empty($_POST['cp_fact']) || empty($_POST['ville_fact']) || empty($_POST['pays_fact'])){
				$erreur = 'Formulaire incomplet';
			}else{
				$nom_fact = mysqli_real_escape_string($con,$_POST['nom_fact']);
				$prenom_fact = mysqli_real_escape_string($con,$_POST['prenom_fact']);
				$adresse_fact = mysqli_real_escape_string($con,$_POST['adresse_fact']);
				$cp_fact = mysqli_real_escape_string($con,$_POST['cp_fact']);
				$ville_fact = mysqli_real_escape_string($con,$_POST['ville_fact']);
				$pays_fact = mysqli_real_escape_string($con,$_POST['pays_fact']);
				// On stock les informations dans une session fact
				$_SESSION['fact'] = array('nom' => $nom_fact, 'prenom' => $prenom_fact, 'adresse' => $adresse_fact.' '.$cp_fact.' '.$ville_fact ,'pays' => $pays_fact);
			}
		
		}
		
		// On renvoie a la page de paiement
		header('Location: paiement.php');		
		exit();

	}else{
		$erreur = 'Vous avez oublié de cocher';
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
	
	<?php
		if (isset($erreur)) echo '<br />',$erreur;
	?>
	
	<div id="contenu">
	
		<form style="display:flex" method="post" action="coordonnes.php">
			<div style="margin:0px 70px">	
				<h3>Qui sera le destinataire ?</h3>
				<input type="radio" name="dest_client" value=true onclick="document.getElementById('champs_dest').style.display ='none';">C'est moi
				<input type="radio" name="dest_client" value=false onclick="document.getElementById('champs_dest').style.display ='block';">C'est quelqu'un d'autre
					<br />
					<br />
					<div id="champs_dest">
						Tous les champs sont obligatoires
						<br /><br />
						Nom :<br />
						<input name="nom_dest" type="text" id="nom" size="40" />
						<br /><br />
						Prenom :<br />
						<input name="prenom_dest" type="text" id="prenom" size="40" />
						<br /><br />
						Adresse :<br />
						<input name="adresse_dest" type="text" id="adresse" size="40" />
						<br /><br />
						Code postal :<br />
						<input name="cp_dest" type="text" id="cp" size="40" />
						<br /><br />
						Ville :<br />
						<input name="ville_dest" type="text" id="ville" size="40" />
						<br /><br />
						Pays :<br />
						<input name="pays_dest" type="text" id="pays" size="40" />
					</div>
					<br /><br />
			</div>
			
			<div style="margin:0px 70px">
				<h3>Qui recevra la facture ?</h3>
				<input type="radio" name="fact_client" value=true onclick="document.getElementById('champs_fact').style.display ='none';">C'est moi
				<input type="radio" name="fact_client" value=false onclick="document.getElementById('champs_fact').style.display ='block';">C'est quelqu'un d'autre
					<br />
					<br />
					<div id="champs_fact" >
						Tous les champs sont obligatoires
						<br /><br />
						Nom :<br />
						<input name="nom_fact" type="text" id="nom" size="40" />
						<br /><br />
						Prenom :<br />
						<input name="prenom_fact" type="text" id="prenom" size="40" />
						<br /><br />
						Adresse :<br />
						<input name="adresse_fact" type="text" id="adresse" size="40" />
						<br /><br />
						Code postal :<br />
						<input name="cp_fact" type="text" id="cp" size="40" />
						<br /><br />
						Ville :<br />
						<input name="ville_fact" type="text" id="ville" size="40" />
						<br /><br />
						Pays :<br />
						<input name="pays_fact" type="text" id="pays" size="40" />
					</div>
					<br /><br />
			</div>
			<input style="align-self: flex-end" type="submit" name="valider" id="button" value="Paiement" />
		</form>
	</div>	
		
	<?php include_once("footer.php");?>
	
</body>

</html>