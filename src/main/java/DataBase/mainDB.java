/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

/**
 *
 * @author Mattia Sanzari
 */
public class mainDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*<?php
            $host='localhost';
            $port = '5432';
            $db= 'esercizio2';
            $username= 'www';
            $password= 'pwww';
            //echo "Prima connessione";
            $connection_string = "host=$host port=$port dbname=$db user=$username password=$password";
*/
        DbFunctions db= new DbFunctions();
        db.ConnectionDB("Rubrica Database", "postgres", "pwww");
    }
    
}
