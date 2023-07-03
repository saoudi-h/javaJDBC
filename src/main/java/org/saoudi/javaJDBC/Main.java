package org.saoudi.javaJDBC;

import com.saoudi.javaUtils.ConsoleColor;
import com.saoudi.javaUtils.List;
import com.saoudi.javaUtils.Point;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static com.saoudi.javaUtils.ConsoleColor.*;
import static com.saoudi.javaUtils.ConsoleColor.printFromArray;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        printProjectName();

        userModelExamples();

        printSignature();
    }

    public static void userModelExamples() throws SQLException {
        /////////////////////////////////////////////////////////////////////////////////////
        // Exemples d'utilisation de notre Model User
        /////////////////////////////////////////////////////////////////////////////////////
        printBigTitleBox("Exemples d'utilisation de notre Model User");


        printTitleBox("█ create");
        // create UserModel instance
        System.out.println("\n//Create UserModel instance");
        printCode(
                "         UserModel model = new UserModel();");
        UserModel model = new UserModel();

        // Creation of three Users
        System.out.println("\n// Creation of three Users");
        printCode(
                "        User celine = new User(-1,\"celine\",\"dion\",User.Role.USER);\n" +
                        "        User robert = new User(-1,\"robert\",\"1991\",User.Role.USER);\n" +
                        "        User ducky = new User(-1,\"ducky\",\"azerty\",User.Role.ADMIN);"
        );

        User celine = new User(-1, "celine", "dion", User.Role.USER);
        User robert = new User(-1, "robert", "1991", User.Role.USER);
        User ducky = new User(-1, "ducky", "azerty", User.Role.ADMIN);

        System.out.println(celine);
        System.out.println(robert);
        System.out.println(ducky);

        //Save users in the database
        System.out.println("\n//Save users in the database");
        printCode(
                "        celine = model.create(celine);\n" +
                        "        robert = model.create(robert);\n" +
                        "        ducky = model.create(ducky);");
        celine = model.create(celine);
        robert = model.create(robert);
        ducky = model.create(ducky);


        printTitleBox("█ update");
        // Update users
        System.out.println("\n// Update users");
        printCode(
                "        celine.setUser(\"Lara\");\n" +
                        "        robert.setPassword(\"1234\");\n" +
                        "        ducky.setRole(User.Role.USER);");
        celine.setUser("Lara");
        robert.setPassword("1234");
        ducky.setRole(User.Role.USER);

        System.out.println(celine);
        System.out.println(robert);
        System.out.println(ducky);


        printCode(
                "       model.update(celine);\n" +
                        "       model.update(robert);\n" +
                        "       model.update(ducky);");

        model.update(celine);
        model.update(robert);
        model.update(ducky);


        printTitleBox("█ findOne");

        // test user at id=0
        System.out.println("\n// Find user at id=0,1,2 ");
        printCode(
                    "        System.out.println(model.findOne(celine.getId()));\n" +
                    "        System.out.println(model.findOne(robert.getId()));\n" +
                    "        System.out.println(model.findOne(ducky.getId()));");
        System.out.println(model.findOne(celine.getId()));
        System.out.println(model.findOne(robert.getId()));
        System.out.println(model.findOne(ducky.getId()));

        printTitleBox("█ findAll");

        // Find all users
        System.out.println("\n// Find all users");
        printCode(
                        "       User[] users = model.findAll();\n" +
                        "       for(User u:users){\n" +
                        "           System.out.println(u);\n" +
                        "       }");
        User[] users = model.findAll();
        for (User u : users) {
            System.out.println(u);
        }

        printTitleBox("█ delete");


        // delete one user
        System.out.println("\n// delete one user");
        printCode(
                "       System.out.println(model.delete(celine));\n" +
                        "       System.out.println(model.delete(robert));\n" +
                        "       System.out.println(model.delete(ducky));"
        );
        System.out.println(model.delete(celine));
        System.out.println(model.delete(robert));
        System.out.println(model.delete(ducky));

    }

    private static void printProjectName() {
        printFromArray(new String[]{

                "                                                                                                   ",
                "                      ██╗ █████╗ ██╗   ██╗ █████╗      ██╗██████╗ ██████╗  ██████╗                 ",
                "                      ██║██╔══██╗██║   ██║██╔══██╗     ██║██╔══██╗██╔══██╗██╔════╝                 ",
                "                      ██║███████║██║   ██║███████║     ██║██║  ██║██████╔╝██║                      ",
                "                 ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║██   ██║██║  ██║██╔══██╗██║                      ",
                "                 ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║╚█████╔╝██████╔╝██████╔╝╚██████╗                 ",
                "                  ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝ ╚════╝ ╚═════╝ ╚═════╝  ╚═════╝                 ",
                "                                                                                                   ",
        });
    }

    private static void printSignature() {
        printFromArray(new String[]{
                "                                                                                                   ",
                "       ██╗  ██╗ █████╗ ██╗  ██╗██╗███╗   ███╗    ███████╗ █████╗  ██████╗ ██╗   ██╗██████╗ ██╗     ",
                "       ██║  ██║██╔══██╗██║ ██╔╝██║████╗ ████║    ██╔════╝██╔══██╗██╔═══██╗██║   ██║██╔══██╗██║     ",
                "       ███████║███████║█████╔╝ ██║██╔████╔██║    ███████╗███████║██║   ██║██║   ██║██║  ██║██║     ",
                "       ██╔══██║██╔══██║██╔═██╗ ██║██║╚██╔╝██║    ╚════██║██╔══██║██║   ██║██║   ██║██║  ██║██║     ",
                "       ██║  ██║██║  ██║██║  ██╗██║██║ ╚═╝ ██║    ███████║██║  ██║╚██████╔╝╚██████╔╝██████╔╝██║     ",
                "       ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝╚═╝     ╚═╝    ╚══════╝╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚═════╝ ╚═╝     ",
                "                                                                                                   "}
        );
    }
}