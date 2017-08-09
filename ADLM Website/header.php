<?php 

if(!isset($_SESSION)) {
     session_start();
}
// Script pour les erreurs
error_reporting(E_ALL);
ini_set('display_errors', '1');
// Connexion à la base de données    
include "scripts/connect_to_mysql.php"; 
// Gestion des accents
mysqli_query($con,"SET NAMES 'utf8'");
?>

<header>
	<div id="reseaux">
		<a href="https://www.facebook.com/autourdelamoto/" target="_blank"><img id="img-fb" src="style/fb.png" align="absmiddle">Facebook</a><br /> 
		<a href="mailto:autourdelamoto@hotmail.fr"><img id="img-courrier" src="style/courrier.png" align="absmiddle">Contact</a>
	</div>
	
	<a href="index.php"><img id="logo" src="style/logo.png"></a>
	
	<div id="panier">
		<a href="panier.php"><img id="img-panier" src="style/panier.png" align="absmiddle">Panier</a>
	</div>
		
</header>

<nav>
		<ul>
		  <li><a href="#homme">Homme</a>
			<ul>
				<li><a href="./produit_liste.php?categorie=Homme&souscategorie=Sportswear">Sportswear</a></li>
				<li><a href="./produit_liste.php?categorie=Homme&souscategorie=Gants">Gants</a></li>
				<li><a href="./produit_liste.php?categorie=Homme&souscategorie=Chaussures">Chaussures</a></li>
			</ul>
		  </li>
		  <li><a href="#femme">Femme</a>
			<ul>
				<li><a href="./produit_liste.php?categorie=Femme&souscategorie=Sportswear">Sportswear</a></li>
				<li><a href="./produit_liste.php?categorie=Femme&souscategorie=Gants">Gants</a></li>
				<li><a href="./produit_liste.php?categorie=Femme&souscategorie=Chaussures">Chaussures</a></li>
			</ul>
		  </li>
		  <li><a href="#enfant">Enfant</a>
			<ul>
				<li><a href="./produit_liste.php?categorie=Enfant&souscategorie=Sportswear">Sportswear</a></li>
				<li><a href="./produit_liste.php?categorie=Enfant&souscategorie=Gants">Gants</a></li>
				<li><a href="./produit_liste.php?categorie=Enfant&souscategorie=Chaussures">Chaussures</a></li>
				<li><a href="./produit_liste.php?categorie=Enfant&souscategorie=Accessoires">Accessoires</a></li>
			</ul>
		  </li>
		  <li><a href="#divers">Divers</a>
			<ul>
				<li><a href="./produit_liste.php?categorie=Divers&souscategorie=Bijoux">Bijoux</a></li>
				<li><a href="./produit_liste.php?categorie=Divers&souscategorie=Porte cles">Porte clés</a></li>
				<li><a href="./produit_liste.php?categorie=Divers&souscategorie=Stickers">Stickers</a></li>
				<li><a href="./produit_liste.php?categorie=Divers&souscategorie=Coques">Coques de téléphone</a></li>
			</ul>
		  </li>
		  <li><a href="#divers">Equipement</a>
			<ul>
				<li><a href="./produit_liste.php?categorie=Equipement&souscategorie=Poignees">Poignées</a></li>
				<li><a href="./produit_liste.php?categorie=Equipement&souscategorie=Decorations">Décorations</a></li>
			</ul>
		  </li>
		  <li id="identification">
			<?php 
				if(isset($_SESSION['prenom'])){
					echo '<a href="./espace.php">'.$_SESSION['prenom'].'</a>';
				}else{
					echo '<a href="./connexion.php">S\'identifier</a>';
				}
				
			?>
		  </li>
		  <li id="inscription">
			<?php 
				if(isset($_SESSION['prenom'])){
					echo '<a href="./deconnexion.php">Déconnexion</a>';
				}else{
					echo '<a href="./inscrire.php">S\'inscrire</a>';
				}
				
			?>
		  </li>
		  <li id="faq"><a href="./faq.php">F.A.Q</a>
		  </li>
		  <li id="faq"><a href="#">Evenements</a>
		  </li>
		</ul>
</nav>