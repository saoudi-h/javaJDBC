package org.saoudi.javaJDBC;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        // initialisation

        UserModel model = new UserModel();

        // test create
        User celine = new User(-1,"celine","dion",User.Role.USER);

        celine = model.create(celine);
        System.out.println("#".repeat(50));

        // test update celine
        celine.setUser("Lara");
        System.out.println(celine);
        System.out.println("#".repeat(50));
        model.update(celine);

        // test findOne
        System.out.println(model.findOne(2));


        System.out.println("#".repeat(50));
        // test findAll
        User[] users = model.findAll();
        for(User u:users){
            System.out.println(u);
        }

        // supression de céline dion
        if(model.delete(celine)){
            System.out.println("céline a bien été supprimé");
        }else{
            System.out.println("Erreur de supression");
        }

    }
}