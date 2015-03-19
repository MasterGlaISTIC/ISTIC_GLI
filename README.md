# ISTIC_GLI
#ISTIC 2014/2015
#Information
L’objectif de ce TP est de créer un composant graphique qui permet l’affichage de camemberts interactifs (tel qu’illustré dans la figure ) en suivant le pattern MVC (Model-View-Controller). Il est donc
nécessaire de proposer un modèle (qui contiendra les données adaptées à la vue), une vue avec une implémentation AWT/Swing permettant la représentation des données du modèle, et un controlleur permettant
d’agir interactivement avec ce camembert. Les fonctionnalités sont :
–
le modèle est représenté par un titre (qui décrit le graphique), un ensemble de champs (les intitulés
de chaque champ), un ensemble optionnel de descriptions (une phrase qui décrit chacun des intitulés)
et un ensemble de valeurs numériques (les valeurs correspondant à chacun des champs) :
–
la vue affiche en son centre l’intitulé et le total des valeurs numériques des champs;
–
la vue affiche un quartier de camembert par champ (la taille du quartier est un ratio de la valeur
du champ sur le total des valeurs), ainsi qu’un descripteur
–
lorsque l’utilisateur clique sur un quartier, le quartier est mis en valeur et sont affichés la valeur
numérique associé au quartier ainsi que la description. De plus deux boutons apparaissent pour
naviguer vers le quartier suivant ou précédent (utile si certains quartiers sont difficiles à sélectionner
car trop petits)
#Contributeurs
Mohssine AMHACHI & Abdelhak CHAABI
