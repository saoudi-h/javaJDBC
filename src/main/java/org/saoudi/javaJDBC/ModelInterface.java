package org.saoudi.javaJDBC;

import java.sql.SQLException;

public interface ModelInterface<T> {
    /**
     * Recherche et renvoie un objet de type T correspondant à l'identifiant donné.
     *
     * @param id L'identifiant de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException Si une erreur SQL se produit.
     */
    T find(int id) throws SQLException;

    /**
     * Recherche et renvoie un utilisateur correspondant à l'email donné.
     *
     * @param email l'adresse email de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException Si une erreur SQL se produit.
     */
    T find(String email) throws SQLException;

    /**
     * Recherche et renvoie un utilisateur correspondant à l'identifiant donné par by dans la valeur est value.
     *
     * @param by    l'attribue qui identifie un utilisateur unique.
     * @param value la valeur de l'attribue by.
     * @return L'utilisateur correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException Si une erreur SQL se produit.
     */
    T find(String by, String value) throws SQLException;


    /**
     * Renvoie tous les utilisateurs de la table "user".
     *
     * @return Un tableau d'utilisateurs.
     * @throws SQLException Si une erreur SQL se produit.
     */
    T[] findAll() throws SQLException;

    /**
     * Crée un nouvel utilisateur dans la table "user".
     *
     * @param user L'utilisateur à créer.
     * @return L'utilisateur créé avec son identifiant mis à jour.
     * @throws SQLException Si une erreur SQL se produit.
     */
    T create(T user) throws SQLException;

    /**
     * Met à jour les informations d'un utilisateur dans la table "user".
     *
     * @param user L'utilisateur à mettre à jour.
     * @throws SQLException Si une erreur SQL se produit.
     */
    void update(T user) throws SQLException;

    /**
     * Supprime un utilisateur de la table "user".
     *
     * @param user L'utilisateur à supprimer.
     * @return true si la suppression a réussi, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    boolean delete(T user) throws SQLException;

    /**
     * Enregistre un utilisateur dans la table "user".
     * Effectue une opération d'insertion avec mise à jour si l'utilisateur existe déjà.
     *
     * @param user L'utilisateur à enregistrer.
     * @return true si l'enregistrement a réussi, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    T save(T t) throws SQLException;

    /**
     * Vérifie si un utilisateur avec l'email donné existe dans la base de données.
     *
     * @param email L'identifiant de l'utilisateur à vérifier.
     * @return true si l'utilisateur existe, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    boolean exists(String email) throws SQLException;

    /**
     * Vérifie si un utilisateur avec l'identifiant donné existe dans la base de données.
     *
     * @param id L'identifiant de l'utilisateur à vérifier.
     * @return true si l'utilisateur existe, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    boolean exists(int id) throws SQLException;

    /**
     * Vérifie si un utilisateur avec l'identifiant 'by' existe dans la base de données.
     *
     * @param by    le nom de l'attribut par lequel on identifiera l'utilisateur à vérifier.
     * @param value la valeur de l'identifiant de l'utilisateur indiqué par by.
     * @return true si l'utilisateur existe, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
     boolean exists(String by, String value) throws SQLException;


    /**
     * Compte le nombre total d'utilisateurs dans la base de données.
     *
     * @return Le nombre total d'utilisateurs.
     * @throws SQLException Si une erreur SQL se produit.
     */
    int count() throws SQLException;
}
