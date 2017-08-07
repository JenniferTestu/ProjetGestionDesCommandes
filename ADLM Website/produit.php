<?php 
// Afficher les erreurs
error_reporting(E_ALL);
ini_set('display_errors', '1');
?>
<?php 
$dynamicList = "";
// On recupere l'id du produit
if (isset($_GET['id'])) {
	// Connexion a la base de données 
    include "scripts/connect_to_mysql.php"; 
	// Gestion des accents
	mysqli_query($con,"SET NAMES 'utf8'");
	$id = preg_replace('#[^0-9]#i', '', $_GET['id']); 
	// On verifie si le produit existe et on prend ses informations
	$sql = mysqli_query($con,"SELECT * FROM produits WHERE id='$id' LIMIT 1");
	$productCount = mysqli_num_rows($sql); // On compte le nombre de resultat
    if ($productCount > 0) {
		while($row = mysqli_fetch_array($sql)){ 
			 $product_name = $row["nom_produit"];
			 $price = $row["prix"];
			 $details = $row["details"];
			 $category = $row["categorie"];
			 $subcategory = $row["souscategorie"];
			 $date_added = strftime("%b %d, %Y", strtotime($row["date_ajout"]));
        }
		
		// On verifie si le produit est en stock et on renvoie les tailles dispo
		$sql2=mysqli_query($con,"SELECT taille FROM stocks WHERE id_produit='$id' AND nombre>0");
	 
		while($row=mysqli_fetch_assoc($sql2)){ // On lit les resultats
			$liste_taille = $row["taille"];
			$dynamicList .= '<input type="radio" name="taille" value='.$liste_taille.'>'.$liste_taille;		
		}
		
	} else {
		echo "Cet article n'existe pas";
	    exit();
	}
		
} else {
	echo "Problème de données";
	exit();
}
mysqli_close($con);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><?php echo $product_name; ?></title>
<link rel="stylesheet" href="style/style.css" type="text/css" media="screen" />
</head>
<body>
<div align="center" id="mainWrapper">
  <?php include_once("header.php");?>
  <div id="pageContent">
  <table width="100%" border="0" cellspacing="0" cellpadding="15">
  <tr>
    <td width="19%" valign="top"><img src="images_inventaire/<?php echo $id; ?>.jpg" height="188" alt="<?php echo $product_name; ?>" /><br />
      <a href="images_inventaire/<?php echo $id; ?>.jpg">Agrandir l'image</a></td>
    <td width="81%" valign="top"><h3><?php echo $product_name; ?></h3>
      <p><?php echo $price; ?> &euro;<br />
        <br />
        <?php echo "$subcategory $category"; ?> <br />
<br />
        <?php echo $details; ?>
<br />
        </p>
      <form id="form1" name="form1" method="post" action="panier.php">
        <input type="hidden" name="pid" id="pid" value="<?php echo $id; ?>" />
		<?php echo $dynamicList; ?><br /><br />
        <input type="submit" name="button" id="button" value="Ajouter au panier" />
      </form>
      </td>
    </tr>
</table>
  </div>
  <?php include_once("footer.php");?>
</div>
</body>
</html>