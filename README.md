
Welcome to the CubeQE user manual
The CubeQE application is in prototype state, so there is no installation but a configuration to do to exploit it.
In order to use CubeQE, we will describe the installations and the classes to use.

## 1. Installations
Install a Java IDE (IntelliJ, Eclipse, Netbeans)
Install JDK 11
Install Scala 2.12.9
Install Apache Tomcat 9.0
Download source code from GitHub

## 2. Classes to use
Run the "DirectoryCreation"  class (under src/java/Services) by changing the endpoint to the desired one (dbPedia, DogFood, etc.) to create a backup directory.
Run the "Scenario_Analytic" class (under src/java/Services/Scenarios) for Scenario 1.
Run the "Scenario_logOnly" class (under src/java/Services/Scenarios) for Scenario 2.
After scenario 2 runs, run the "Scenario_Enrichment" class (under src/java/Services/Scenarios)  for scenario 3.
Create an artifact for the web application and launch it.
View the results.

Note: Scenario execution takes a lot of time, including Scenario 2 for dbpedia, which takes up to 5 days.

------------------------------------------------------------------------------------------------------------------------
Bienvenue dans le manuel d’utilisation de CubeQE
L’application CubeQE est à l’état de prototype, il n’y a donc pas d’installation mais une configuration à faire pour l’exploiter.
Afin d’utiliser CubeQE, nous allons vous décrire les installations ainsi que les classes à utiliser.

## 1.  Installations
Installer un IDE Java (IntelliJ, Eclipse, Netbeans)
Installer JDK 11
Installer Scala 2.12.9
Installer Apache Tomcat 9.0
Télécharger le code source à partir de GitHub

## 2. Classes à utiliser
Exécuter la classe  “DirectoryCreation” en changeant le endpoint en fonction de celui voulu (dbPedia, DogFood, etc.) pour créer un répertoire de sauvegarde.
Exécuter la classe “Scenario_Analytic” pour le scénario 1.
Exécuter la classe “Scenario_logOnly” pour le scénario 2.
Après exécution du scénario 2, exécuter la classe “Scenario_Enrichment” pour le scénario 3.
Créer un artifact pour l’application web et le lancer.
Visualiser les résultats.

Remarque : l’exécution des scénarios prend énormément de temps, notamment le scénario 2 pour dbpedia qui prend jusqu’à 5 jours.
