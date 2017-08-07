<?php 
	// Connexion a la base de données 
	include "scripts/connect_to_mysql.php"; 
	// Gestion des accents
	mysqli_query($con,"SET NAMES 'utf8'");
	$dynamicList = "";
	$messagesParPage=2; // Nombre de produit par page
	 
	$retour_total=mysqli_query($con,'SELECT COUNT(*) AS total FROM produits, stocks WHERE id = id_produit AND nombre > 0'); // On compte le nombre de produits qui existent dans les stocks 
	$donnees_total=mysqli_fetch_assoc($retour_total); // On range le resultat sous la forme d'un tableau
	$total=$donnees_total['total']; // On recupere le total
	 
	// On compte le nombre de pages
	$nombreDePages=ceil($total/$messagesParPage);
	 
	if(isset($_GET['page'])) // Si la variable $_GET['page'] existe
	{
		 $pageActuelle=intval($_GET['page']);
	 
		 if($pageActuelle>$nombreDePages) // Si la valeur de la pageActuelle est plus grande que  le nombreDePages
		 {
			  $pageActuelle=$nombreDePages;
		 }
	}
	else // Sinon
	{
		 $pageActuelle=1; // La page actuelle est la premiere
	}
	 
	$premiereEntree=($pageActuelle-1)*$messagesParPage;
	 
	// On recupere les produits de la page actuelle
	$retour_messages=mysqli_query($con,'SELECT * FROM produits ORDER BY id DESC LIMIT '.$premiereEntree.', '.$messagesParPage.'');
	 
	while($row=mysqli_fetch_assoc($retour_messages)) // On lit les resultats
	{
		 //On affiche les produits dans des tableaux
		 	 $id = $row["id"];
				 $product_name = $row["nom_produit"];
				 $price = $row["prix"];
				 $date_added = strftime("%b %d, %Y", strtotime($row["date_ajout"]));
				 $dynamicList .= '<div class="produit">
			
			  <div valign="top"><a href="produit.php?id=' . $id . '"><img style="border:#666 1px solid;" src="images_inventaire/' . $id . '.jpg" alt="' . $product_name . '" height="102" border="1" /></a></div><br>
			  <div valign="top">' . $product_name . '
				<br>' . $price . ' €
				<br><a href="produit.php?id=' . $id . '">Voir en détails</a></div>
			
		  </div>';
	}
	 
	$pagination = '<p align="center">Page : '; // On centre la liste des pages
	for($i=1; $i<=$nombreDePages; $i++)
	{
		 // Si il s'agit de la page actuelle
		 if($i==$pageActuelle) 
		 {
			 $pagination .= ' <font color="red"> '.$i.' </font> '; // On met le numero en rouge
		 }	
		 else
		 {
			  $pagination .= ' <a href="produit_liste.php?page='.$i.'">'.$i.'</a> ';
		 }
	}
	echo '</p>';
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

	<p><?php echo $dynamicList; ?></p>
	
	</div>	
	
	<p id="pagination"><?php echo $pagination; ?><br /></p>
	
	<?php include_once("footer.php");?>
	
</body>

</html>