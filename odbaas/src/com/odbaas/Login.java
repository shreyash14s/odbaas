/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.odbaas;
import com.ansu.iam.client.IAMClient;
import com.ansu.iam.client.IAMResponse;
import com.ansu.iam.client.exception.IAMClientErrorException;
import com.ansu.iam.client.exception.IAMInputException;
import com.ansu.iam.client.exception.IAMServerErrorException;

/**
 *
 * @author sanjay
 */
public class Login 
{
    private final String appId="83976235-c4de-427a-8e79-7daf1e94e899";
    private final String internalDB="internal";
    private final String tableName="Users";
    private IAMClient client;
    private Database db;
    private String user_name;
    private String uid;
    private String token;
    public String getUser_name()
    {
        return user_name;
    }

    public String getUid()
    {
        return uid;
    }
    
    Login()
    {
        this.client= new IAMClient();
        this.db=new Database();
    }
    Login(String token)
    {
        this();
        this.token=token;       
        String myQuery= "SELECT * from "+tableName+" where token='"+token+"';";
        if(db.isTrue(internalDB, myQuery))
        {
            this.uid= db.selectOne(internalDB, myQuery,"uid");
            this.user_name=db.selectOne(internalDB, myQuery,"user");
            System.out.println(uid);
            System.out.println(user_name);
        }
        else
        {
            this.uid="";
            this.user_name="";
        }
        
    }
    Login(String user_name,String password)
    {
        this();
        this.user_name=user_name;
        String myQuery= "SELECT * from "+tableName+" where user='"+user_name+"';";
        if(db.isTrue(internalDB, myQuery))
        {
            System.out.println("Existing User-Name!");
            this.uid= db.selectOne(internalDB, myQuery,"uid");
            
            System.out.println(uid);
            this.token=getToken(appId, uid, password);
            System.out.println(token);
            if (token.length()>0)
            {
                myQuery = "UPDATE "+tableName+" SET token='"+token+"' WHERE uid='"+uid+"';";
                System.out.println(db.update(internalDB, myQuery));
            }
            else
            {
                System.out.println("Token error:");
            }
            
        }
        else
        {
            this.uid = signUp(user_name, password);
            System.out.println(uid);
            this.token=getToken(appId, uid, password);
            if (token.length()>0)
            {
                myQuery="INSERT INTO "+tableName+" values ('"+token+"','"+user_name+"','"+uid+"')"+";";
                System.out.println(myQuery);
                System.out.println(db.insert(internalDB, myQuery));
            }                       
        }              
    }
    
    String signUp(String user_name,String password)
    {
        String value="";
        try
        {
            IAMResponse response= client.registerUser(appId, user_name, password);
            value=response.getPayload();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return value;
    }
    public String getToken()
    {
        return token;
    }
    private String getToken(String appId,String uid,String password)
    {
        String token="";
        try
        {
            IAMResponse response = client.generateToken(appId, uid, password);
            token=response.getPayload();
        }
        catch(Exception e)
        {
            token="";
        }
        return token;
    }
    boolean isTokenValid()
    {
        boolean val;
        try
        {
            IAMResponse response = client.validateToken(appId, token);
            val=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            val=false;
        }
        return val;
    }
}
