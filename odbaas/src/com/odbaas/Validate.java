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

public class Validate extends Login
{
    
    Validate(String token)
    {
        
        super(token);
        
    }
    public boolean isValid()
    {
        if (!((this.getUser_name().length())>0))
        {
            return false;
        }
        
        return this.isTokenValid();
    }
    public String getUser()
    {
        return this.getUser_name();
    }
}
