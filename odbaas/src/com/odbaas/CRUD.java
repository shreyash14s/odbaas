/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.odbaas;

/**
 *
 * @author sanjay
 */
public class CRUD
{
    String dbname;
    String table;
    Database db ;
    CRUD(String dbname)
    {
        this.dbname=dbname;
        db= new Database();
        
    }
    void setTable(String table)
    {
        this.table=table;
    }
}
