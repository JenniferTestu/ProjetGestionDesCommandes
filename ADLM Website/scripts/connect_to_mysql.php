<?php  
// Nom de l'hote
$bdd_hote = "localhost"; 
// Pseuso de l'admin
$bdd_pseudo = "root";  
// Mot de passe de l'admin
$bdd_mdp = "";  
// Nom de la base de données
$bdd_nom = "ecommerce"; 

// Lancement de la connexion
$con = mysqli_connect("$bdd_hote","$bdd_pseudo","$bdd_mdp","$bdd_nom") or die ("could not connect to mysql");             
?>