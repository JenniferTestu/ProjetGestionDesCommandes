<?php 
// Afficher les 6 derniers articles ajoutés
// Connexion à la base de données  
include "scripts/connect_to_mysql.php"; 
mysqli_query($con,"SET NAMES 'utf8'");
$dynamicList = "";
if($sql = mysqli_query($con,"SELECT * FROM produits ORDER BY date_ajout DESC LIMIT 6")){
	$productCount = mysqli_num_rows($sql); // Nombre de lignes retournées
	if ($productCount > 0) {
		while($row = mysqli_fetch_array($sql)){ 
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
	} else {
		$dynamicList = "Aucun produit pour le moment";
	}
}
mysqli_close($con);
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
	
	<h3>LES NOUVEAUTES !!</h3>
	
	<div id="contenu">
		
		<p id="liste"><?php echo $dynamicList; ?></p>

	</div>	
		
	<?php include_once("footer.php");?>
	
</body>

</html>