package com.example.bottomline.service;


import com.example.bottomline.model.Trie;
import com.example.bottomline.model.TrieNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class NamesService {

    static Trie prefixTree;

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:mem:matan";

    //  Database credentials
    final String USER = "sa";
    final String PASS = "";

    private Connection conn;
    private Statement stmt;


    public NamesService(){
        prefixTree = new Trie();
        conn = null;
        stmt = null;
    }

    public void insertToDB(String name) throws SQLException {
        try{
            String sql = "INSERT INTO BOY_NAME (name) VALUES ('" + name + "');\n";
            stmt.executeUpdate(sql);
        }catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public void insertData(){
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connected database successfully...");

            // STEP 3: Execute a query
            stmt = conn.createStatement();

            readFile();

            System.out.println("Inserted records into the table...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(
                SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }


    public static List<String> getNamesByPrefix(String prefix){
        List<String> names = new ArrayList<>();
        if(prefixTree.startsWith(prefix)==true)
        {
            TrieNode tn = prefixTree.searchNode(prefix);
            prefixTree.wordsFinderTraversal(tn,0);
            names = prefixTree.displayFoundWords();
        }
        return names;
    }


    void readFile(){
        try {
            File myObj = new File("BoyNames.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String name = myReader.nextLine();
                //System.out.println(name);
                prefixTree.insert(name);
                insertToDB(name);
            }
            myReader.close();
        } catch (FileNotFoundException | SQLException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
