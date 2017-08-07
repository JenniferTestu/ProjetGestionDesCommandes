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

// Si on clique sur l'un des boutons de paiement
if(isset($_POST['cb']) || isset($_POST['paypal']) || isset($_POST['cheque']) || isset($_POST['espece'])){

	$paiement = "";
	$errorMessage = '';
	 
	  // On stock le choix fait dans la variable paiement
	  if(isset($_POST['cb'])){
		$paiement = "Carte bleue";
	  }else if(isset($_POST['paypal'])){
		$paiement = "Paypal"; 	  
	  }else if(isset($_POST['cheque'])){
		$paiement = "Cheque";
	  }else if(isset($_POST['espece'])){
		$paiement = "Especes";
	  }
		// On enregistre les informations a propos de la commande, cad methode de paiement, id du client, la date 
		$sql = 'INSERT INTO info_commandes (paiement,id_client,traite,date,nom_dest,prenom_dest,adresse_dest,pays_dest,nom_fact,prenom_fact,adresse_fact,pays_fact) VALUES("'.$paiement.'", '.(int)$_SESSION['id'].', false, NOW(),"","","","","","","","")';
	  
		// Si la requete aboutie 
		if (mysqli_query($con,$sql)){
			
			// On recupere dans la base de données l'id de la commande que l'on vient d'enregistrer
			$query = mysqli_query($con,'SELECT MAX(id) FROM info_commandes WHERE id_client =  '.(int)$_SESSION['id'].'');
			$row=mysqli_fetch_row($query);
			$id_commande = $row[0]; // 1ere ligne du resultat
			
			// Pour chaque produit du panier
			foreach ($_SESSION["cart_array"] as $each_item) {
				
				$sql2 = mysqli_query($con,'SELECT * FROM produits WHERE id='.$each_item['item_id'].' LIMIT 1');
				while ($row = mysqli_fetch_array($sql2)) {
					$prix = $row["prix"];
				}
				
				// On enregistre les produits commandés
				mysqli_query($con,'INSERT INTO produits_commandes VALUES('.(int)$id_commande.', '.(int)$each_item['item_id'].', "'.$each_item['item_taille'].'", '.(int)$each_item['quantity'].', '.(double)$prix.')');
				
				// On déduit la quantité dans les stocks
				mysqli_query($con,'UPDATE `stocks` SET nombre = nombre - '.(int)$each_item['quantity'].' WHERE id_produit = '.(int)$each_item['item_id'].'  AND taille = "'.$each_item['item_taille'].'"');
			}
		
			// Si l'utilisateur a indiqué un destinateur different de lui
			if(isset($_SESSION['dest'])){
				// On enregistre les informations
				mysqli_query($con,'UPDATE info_commandes SET nom_dest = "'.$_SESSION['dest']['nom'].'", prenom_dest = "'.$_SESSION['dest']['prenom'].'", adresse_dest = "'.$_SESSION['dest']['adresse'].'", pays_dest = "'.$_SESSION['dest']['pays'].'" WHERE id = '.(int)$id_commande.'');
			}
				
			// Si l'utilisateur a indiqué un payeur different de lui
			if(isset($_SESSION['fact'])){
				// On enregistre les informations
				mysqli_query($con,'UPDATE info_commandes SET nom_fact = "'.$_SESSION['fact']['nom'].'", prenom_fact = "'.$_SESSION['fact']['prenom'].'", adresse_fact = "'.$_SESSION['fact']['adresse'].'", pays_fact = "'.$_SESSION['fact']['pays'].'" WHERE id = '.(int)$id_commande.'');
			}
			
			header('Location: index.php');	// On affiche la page d'accueil du site	
			unset($_SESSION['cart_array']); // On vide le panier
			exit();

		} else { // Sinon on affiche l'erreur
			echo "Error: " . $sql . "<br>" . mysqli_error($con);
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
		<h3>Choisissez votre moyen de paiement</h3>
		<form id="form_connexion" name="form1" method="post" action="paiement.php">
		
		<input type="submit" name="cb" id="button" value="Carte bleue" />
		<input type="submit" name="paypal" id="button" value="PayPal" />
		<input type="submit" name="cheque" id="button" value="Chèque" />
		<input type="submit" name="espece" id="button" value="Espèce" />
		
		</form>

	</div>
	<?php
			if (isset($errorMessage)) echo '<br />',$errorMessage;
		?>
	</div>	
		
	<?php include_once("footer.php");?>
	
</body>

</html>