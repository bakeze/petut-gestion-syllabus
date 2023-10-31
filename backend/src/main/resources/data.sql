INSERT INTO Utilisateur(nom, prenom, username, password,role) VALUES
    ('Pacatte', 'Jean-Marie', 'jmpecate@gmail.com', 'passer2020', 'DIRECTEUR_ETUDES'),
    ('Defossez', 'Adrien', 'jmpecate@gmail.com', 'passer2020', 'DIRECTEUR_ETUDES'),
    ('Arnaud', 'Charron', 'arnaudcharron@gmail.com', 'passer2020', 'RESPONSABLE_ANNEE'),
    ('Singer', 'Nicolas', 'basilkh', 'passer2020', 'ENSEIGNANT');


INSERT INTO Annee(Annee,UTILISATEUR_ID) VALUES 
    (2022,1),
    (2023,1),
    (2021,1);

INSERT INTO Ue(intitule, codeue, creditects, numsemestre, foad, prerequis,ANNEE_ID, formation) VALUES 
    ('Sciences fondamentales 1', 'A3-SF1', '7', '1', '0', 'Enseignement de specialite de mathematiques terminale de lycee general Enseignement de specialite de physiques terminale de lycee general', '1', 'FIA-3'),
    ('Informatique et systèmes dinformation 1', 'A3-ISI1', '10', '1','0', 'Enseignement de specialite de informatique ', '1', 'FIA-3'),
    ('Langues et sociétés 1', 'A3-LS1', '8', '1','0', 'Enseignement de specialite des langues mm Dr Bakayoko  de lycee general Enseignement de specialite de physiques terminale de lycee general','1', 'FIA-3'),
    ('Travail en entreprise 1', 'A3-TE1', '5', '1','0', 'Aucun','1', 'FIA-3'),
    ('Sciences fondamentales 2', 'A3-SF2', '6', '2','0', 'Enseignement de specialite de mathematiques terminale de lycee general Enseignement de specialite de physiques terminale de lycee general',  '1', 'FIA-3'),
    ('Informatique et systèmes dinformation 2', 'A3-ISI2', '7', '2','0', 'Enseignement de specialite de informatique ','1', 'FIA-3'),
    ('Langues et sociétés 2', 'A3-LS2', '7', '2','0', 'Enseignement de specialite des langues mm Dr Bakayoko  de lycee general Enseignement de specialite de physiques terminale de lycee general', '1', 'FIA-3'),
    ('Travail en entreprise 2', 'A3-TE2', '10', '2','0', 'Aucun','1', 'FIA-3');
   


INSERT INTO Matiere(intitule, codematiere, heurecm, heuretd, foad, descripenseignement, objectifpedagogique, UE_ID, UTILISATEUR_ID) VALUES 
    ('Mathématiques appliquées', 'A3-SF1-1', '6', '16', '0', 'Cet enseignement aborde létude des mathematique appliquee, la trigonométrie', 'Maitriser les maths', '1', '1'),
    ('Mathématiques appliquées', 'A3-SF1-1', '6', '16', '0', 'Cet enseignement aborde létude des mathematique appliquee, la trigonométrie', 'Maitriser les maths', '1', '1'),
    ('Probabilités et statistiques', 'A3-SF1-2', '6', '16','0', 'Cet enseignement aborde létude des probalilie appliquee, la trigonométrie', 'Maitriser les proba', '1','2'),
    ('Biologie', 'A3-SF1-3', '6', '14','0', 'Cet enseignement aborde létude de la Biologie appliquee, la trigonométrie', 'Maitriser la biologie', '1','2'),
    ('Systèmes dinformation', 'A3-ISI1-1', '10', '24','0', 'Cet enseignement aborde létude des S I, la trigonométrie', 'Maitriser les SI', '2', '1'),
    ('Programmation objet', 'A3-ISI1-2', '8', '20','0', 'Cet enseignement aborde létude de la poo appliquee, la trigonométrie', 'Maitriser la poo', '2','1'),
    ('Méthodes du génie logiciel', 'A3-ISI1-3', '8', '20','0', 'Cet enseignement aborde létude de methode du génie log appliquee, la trigonométrie', 'Maitriser genie log', '2','3'),
    ('Gestion de projets', 'A3-ISI1-4', '8', '20','0', 'Cet enseignement aborde létude de la Gestion de projet , la trigonométrie', 'Maitriser la gestion de projet', '2','2'),
    ('Anglais', 'A3-LS1-1', '0', '40','0', 'Cet enseignement aborde létude de langlais, la trigonométrie', 'Maitriser langlais', '3', '1'),
    ('Economie et sociologie de la santé', 'A3-LS1-2', '6', '12','0', 'Cet enseignement aborde létude de leconomie de la sante, la trigonométrie', 'Maitriser leconomie de la santé', '3','1'),
    ('Connaissance de lenvironnement professionnel', 'A3-LS1-3', '6', '12','0', 'Cet enseignement aborde létude de Connaissance de lenvironnement professionnel appliquee, la trigonométrie', 'Maitriser Connaissance de lenvironnement professionnel', '3','3'),
    ('Voltaire', 'A3-LS1-4', '0', '2','0', 'Cet enseignement aborde létude de Voltaire , la trigonométrie', 'Maitriser le Voltaire', '3','2'),
    ('Travail en entreprise', 'A3-TE1-1', '0', '0','0', 'Stage en milleur hospitalier', 'Pérode de 1 mois', '4','2'),
    ('Mathématiques appliquées', 'A3-SF2-1', '6', '16','0', 'Cet enseignement aborde létude des mathematique appliquee, la trigonométrie', 'Maitriser les maths', '5', '1'),
    ('Probabilités et statistiques', 'A3-SF2-2', '6', '16','0', 'Cet enseignement aborde létude des probalilie appliquee, la trigonométrie', 'Maitriser les proba', '5','2'),
    ('Anthropocène', 'A3-SF2-3', '8', '10','0', 'Cet enseignement aborde létude de la Biologie appliquee, la trigonométrie', 'Maitriser la biologie', '5','2'),
    ('Systèmes Architecture et Technologies des applications  web', 'A3-ISI2-1', '12', '34','0', 'Cet enseignement aborde létude des Systèmes Architecture et Technologies des applications  web, la trigonométrie', 'Systèmes Architecture et Technologies des applications  web', '6', '1'),
    ('Outils dingéniérie logicielle', 'A3-ISI2-2', '10', '18','0', 'Cet enseignement aborde létude de la poo appliquee, la trigonométrie', 'Maitriser la poo', '6','1'),
    ('Réseaux et Télécoms', 'A3-ISI2-3', '12', '12','0', 'Cet enseignement aborde létude de methode du génie log appliquee, la trigonométrie', 'Maitriser genie log', '6','3'),
    ('Anglais', 'A3-LS2-1', '0', '40','0', 'Cet enseignement aborde létude de langlais, la trigonométrie', 'Maitriser langlais', '7', '1'),
    ('Communication', 'A3-LS2-2', '10', '10','0', 'Cet enseignement aborde létude de la Communication', 'Maitriser Communication leconomie de la santé', '7', '1'),
    ('Introduction au droit et à léthique ', 'A3-LS2-3', '6', '14','0', 'Cet enseignement aborde létude de Connaissance de lenvironnement professionnel appliquee, la trigonométrie', 'Maitriser Connaissance de lenvironnement professionnel', '7','3'),
    ('Voltaire', 'A3-LS2-4', '0', '2','0', 'Cet enseignement aborde létude de Voltaire , la trigonométrie', 'Maitriser le Voltaire', '7', '2'),
    ('Travail en entreprise', 'A3-TE2-1', '0', '0','0', 'Stage en milleur hospitalier', 'Pérode de 1 mois', '8', '2');
