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

/*
**	Si l'utilisateur veut ajouter un produit au panier
*/

// On prend l'id du produit et la taille choisie
if (isset($_POST['pid']) && isset($_POST['taille'])) {
    $pid = $_POST['pid'];
	$taille = $_POST['taille'];
	$wasFound = false;
	$i = 0;
	
	// Si la session du panier n'existe pas ou vide, alors on la crée
	if (!isset($_SESSION["cart_array"]) || count($_SESSION["cart_array"]) < 1) { 
		$_SESSION["cart_array"] = array(0 => array("item_id" => $pid, "quantity" => 1, "item_taille" => $taille));
	} else {
		// Si le panier contient au moins un produit
		foreach ($_SESSION["cart_array"] as $each_item) { 
		      $i++;
		      while (list($key, $value) = each($each_item)) {
				  if ($key == "item_id" && $value == $pid) { // Si le produit est deja dans le panier 
					  if(in_array($taille, $each_item, true)){ // Si la taille est dans le tableau correspondant au produit
						  // Alors on ajuste la quantite
						  array_splice($_SESSION["cart_array"], $i-1, 1, array(array("item_id" => $pid, "quantity" => $each_item['quantity'] + 1, "item_taille" => $taille)));
						  $wasFound = true;
						}
				  }
		      } 
	       }
		   if ($wasFound == false) { // Si le produit n'a pas été trouvé
				// On ajoute le produit au panier
			   array_push($_SESSION["cart_array"], array("item_id" => $pid, "quantity" => 1,"item_taille" => $taille));
		   }
	}
	// Affichage du panier
	header("location: panier.php"); 
    exit();
}
?>
<?php 
/*
**	Si l'utilisateur veut vider le panier
*/

if (isset($_GET['cmd']) && $_GET['cmd'] == "emptycart") {
    unset($_SESSION["cart_array"]);
}
?>
<?php 

/*
**	Si l'utilisateur veut ajuster la quantité d'un produit du panier
*/

if (isset($_POST['item_to_adjust']) && $_POST['item_to_adjust'] != "") {
    // On recupere le produit a ajuster ainsi que la taille
	$item_to_adjust = $_POST['item_to_adjust'];
	$taille_to_adjust = $_POST['taille_to_adjust'];
	$quantity = $_POST['quantity'];
	$quantity = preg_replace('#[^0-9]#i', '', $quantity);
	
	// Limite de la quantité cad entre 1 et 10
	if ($quantity >= 10) { $quantity = 10; }
	if ($quantity < 1) { $quantity = 1; }
	if ($quantity == "") { $quantity = 1; }
	
	// On vérifie que la quantité demandé est dispo
	$sql=mysqli_query($con,'SELECT nombre FROM stocks WHERE id_produit="'.$item_to_adjust.'" AND taille="'.$taille_to_adjust.'"');;
	while($row=mysqli_fetch_assoc($sql)) // On lit les resultats
	{
		$quantityMax = $row["nombre"];
		if($quantity>$quantityMax){
			$quantity = $quantityMax;
		}
	}
	
	$i = 0;
		
	// Si le panier contient au moins un produit
	foreach ($_SESSION["cart_array"] as $each_item) { 
		      $i++;
		      while (list($key, $value) = each($each_item)) {
				  if ($key == "item_id" && $value == $item_to_adjust) {	// Si le produit est deja dans le panier 
					  if(in_array($taille_to_adjust, $each_item, true)){	// Si la taille est dans le tableau correspondant au produit
						  // Alors on ajuste la quantite
						  array_splice($_SESSION["cart_array"], $i-1, 1, array(array("item_id" => $item_to_adjust, "quantity" => $quantity, "item_taille" => $taille_to_adjust)));
					  }
				  }
		      }
	}
}
?>
<?php 

/*
**	Si l'utilisateur veut enlever un produit du panier
*/
if (isset($_POST['index_to_remove']) && $_POST['index_to_remove'] != "") {
	// On accede au tableau et on retire l'element
 	$key_to_remove = $_POST['index_to_remove'];
	if (count($_SESSION["cart_array"]) <= 1) {
		unset($_SESSION["cart_array"]);
	} else {
		unset($_SESSION["cart_array"]["$key_to_remove"]);
		sort($_SESSION["cart_array"]);
	}
}
?>
<?php 
/*
**	Code de l'affichage des informations du produit
*/

$button = "";
$cartOutput = "";
$cartTotal = "";
$pp_checkout_btn = '';
$product_id_array = '';
if (!isset($_SESSION["cart_array"]) || count($_SESSION["cart_array"]) < 1) {
    $cartOutput = "<h2 align='center'>Votre panier est vide</h2>";
} else {
	// Start PayPal Checkout Button
	$pp_checkout_btn .= '<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
    <input type="hidden" name="cmd" value="_cart">
    <input type="hidden" name="upload" value="1">
    <input type="hidden" name="business" value="you@youremail.com">';
	// Pour chaque produit du panier
	$i = 0; 
    foreach ($_SESSION["cart_array"] as $each_item) { 
		$item_id = $each_item['item_id'];
		$item_taille = $each_item['item_taille'];
		$sql = mysqli_query($con,"SELECT * FROM produits WHERE id='$item_id' LIMIT 1");
		while ($row = mysqli_fetch_array($sql)) {
			$product_name = $row["nom_produit"];
			$price = $row["prix"];
			$details = $row["details"];
		}
		$pricetotal = $price * $each_item['quantity'];
		$cartTotal = $pricetotal + $cartTotal;
        $pricetotal = number_format($pricetotal, 2, ',', ' ') . ' €';
		// Dynamic Checkout Btn Assembly
		$x = $i + 1;
		$pp_checkout_btn .= '<input type="hidden" name="item_name_' . $x . '" value="' . $product_name . '">
        <input type="hidden" name="amount_' . $x . '" value="' . $price . '">
        <input type="hidden" name="quantity_' . $x . '" value="' . $each_item['quantity'] . '">  ';
		// Create the product array variable
		$product_id_array .= "$item_id-".$each_item['quantity'].","; 
		// Ligne produit
		$cartOutput .= "<tr>";
		$cartOutput .= '<td><a href="produit.php?id=' . $item_id . '">' . $product_name . '</a><br /><img src="images_inventaire/' . $item_id . '.jpg" alt="' . $product_name. '" height="52" border="1" /></td>';
		$cartOutput .= '<td>' . $item_taille . '</td>';
		$cartOutput .= '<td>' . $details . '</td>';
		$cartOutput .= '<td>' . number_format($price, 2, ',', ' ') . ' €</td>';
		$cartOutput .= '<td><form action="panier.php" method="post">
		<input name="quantity" type="text" value="' . $each_item['quantity'] . '" size="1" maxlength="2" />
		<input name="adjustBtn' . $item_id . '" type="submit" value="Changer" />
		<input name="item_to_adjust" type="hidden" value="' . $item_id . '" />
		<input name="taille_to_adjust" type="hidden" value="' . $item_taille . '" />
		</form></td>';
		//$cartOutput .= '<td>' . $each_item['quantity'] . '</td>';
		$cartOutput .= '<td>' . $pricetotal . '</td>';
		$cartOutput .= '<td><form action="panier.php" method="post"><input name="deleteBtn' . $item_id . '" type="submit" value="X" /><input name="index_to_remove" type="hidden" value="' . $i . '" /></form></td>';
		$cartOutput .= '</tr>';
		$i++; 
    } 

	$cartTotal = '<div style="font-size:18px; margin-top:12px;" align="right">Cout Total : '.number_format($cartTotal, 2, ',', ' ').' € TTC</div>';
	$button = "<form method='post'><input type='submit' name='Valider_p' id='button' value='Valider votre panier' /></form>";
    // Finish the Paypal Checkout Btn
	$pp_checkout_btn .= '<input type="hidden" name="custom" value="' . $product_id_array . '">
	<input type="hidden" name="notify_url" value="https://www.yoursite.com/storescripts/my_ipn.php">
	<input type="hidden" name="return" value="https://www.yoursite.com/checkout_complete.php">
	<input type="hidden" name="rm" value="2">
	<input type="hidden" name="cbt" value="Return to The Store">
	<input type="hidden" name="cancel_return" value="https://www.yoursite.com/paypal_cancel.php">
	<input type="hidden" name="lc" value="US">
	<input type="hidden" name="currency_code" value="USD">
	<input type="image" src="http://www.paypal.com/en_US/i/btn/x-click-but01.gif" name="submit" alt="Make payments with PayPal - its fast, free and secure!">
	</form>';
}
?>
<?php
// Lors de la validation
if(isset($_POST['Valider_p']))
  {
	  // Si la session du client existe
	  if(isset($_SESSION['id'])){
		  // On passe a la page des coordonnées
		header('Location: coordonnes.php');
	  }else{
		 // Sinon on le redirige vers la page de connexion
		$_SESSION['redir']=true;
		header('Location: connexion.php');		
	  }
	  exit();
  }
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Votre panier</title>
<link rel="stylesheet" href="style/style.css" type="text/css" media="screen" />
</head>
<body>
<div align="center" id="mainWrapper">
  <?php include_once("header.php");?>
  <div id="pageContent">
    <div style="margin:24px; text-align:left;">
	
    <br />
    <table width="100%" border="1" cellspacing="0" cellpadding="6">
      <tr>
        <td  bgcolor="#E31010"><strong>Produit</strong></td>
		<td  bgcolor="#E31010"><strong>Taille</strong></td>
        <td  bgcolor="#E31010"><strong>Description</strong></td>
        <td  bgcolor="#E31010"><strong>Prix unitaire</strong></td>
        <td  bgcolor="#E31010"><strong>Quantité</strong></td>
        <td  bgcolor="#E31010"><strong>Total</strong></td>
        <td  bgcolor="#E31010"><strong>Enlever</strong></td>
      </tr>
     <?php echo $cartOutput; ?>
     <!-- <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr> -->
    </table>
    <?php echo $cartTotal; ?>
    <br />
	<br />
	<?php echo $button; ?>
	<?php //echo $pp_checkout_btn; ?>
    <br />
    <br />
    <a href="panier.php?cmd=emptycart">Cliquez ici pour vider le panier</a>
    </div>
   <br />
  </div>
  <?php include_once("footer.php");?>
</div>
</body>
</html>