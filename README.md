# gestion ecole

_etudiant_
- create **ok**
- get all **ok**
- get one **ok** => message si id n'existe pas
- update **ok**
- delete **ok**

_enseignant_
- create **ok**
- get all **ok** => show his courses
- get one **ok** => show his courses, => message si id  n'existe pas
- update **ok** => les cours ne sont pas mis ici
- delete **ok** => delete with cours

_cours_
- create **ok**
- get all **show enseignants**
- get one **show enseignants** => message si id etudiant n'existe pas
- update **ok** => update enseignant
- delete **ok**

_inscription_ (peuplé)
- create **ok**
- get all **ok**
- get one **ok** => message si id etudiant n'existe pas
- update **ok**
- delete **ok**
- getEtudiantsByCours **ok** => recuperer tous les etudiants inscrits dans un cours
- getCoursByEtudiant **ok** => les cours dont l'etudiants est inscrit
