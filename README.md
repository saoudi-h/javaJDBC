
[//]: # (![JavaUtils]&#40;images/javaJDBC.png&#41;)

[//]: # ()
[//]: # (})


# javaUtils - Bibliothèque Java pour les structures de données
javaJDBC est un mini projet qui regroupe une mise en pratique du cours JDBC.
## Structures de données disponibles
## Fonctionnalités

## Utilisation


```bash
git clone https://github.com/saoudi-h/javaJDBC.git
cd javaJDBC
 
```

## Tests
Ce projet utilise JUnit pour les tests unitaires. Les tests sont situés dans le répertoire src/test/java. Vous pouvez exécuter les tests à l'aide de la commande suivante :

```bash
mvn test
```

## Javadoc
Ce projet a était docummenté en français. Vous pouvez génèrer la doc avec la commande suivante :

```bash
mvn javadoc:javadoc
```
Le plugin Maven Javadoc générera la documentation dans le dossier `target/site/apidocs/`
## Génération du fichier JAR exécutable

Ce projet est configuré avec le plugin Maven maven-jar-plugin pour générer un fichier JAR exécutable. Vous pouvez générer le JAR en exécutant la commande suivante :
```bash
mvn verify
```
Le fichier JAR exécutable sera généré dans le répertoire target, situé a la racine du projet.
```bash
cd target
java -jar javaJDBC-1.0-SNAPSHOT.jar
```

## Licence

Ce mini projet est sous licence [MIT](https://opensource.org/licenses/MIT).

## Contributeurs

![Développé par Hakim Saoudi dans le cadre de sa formation de concepteur développeur d'application.
](images/hakimsaoudi_javaUtils.png)
