package org.utadactividades;

public class Main {

    public static void main(String[] args) {

        DBConnect.createTable();
        UseXML.readXML();
        UseXML.insertInDB();

    }

}