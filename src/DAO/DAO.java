/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Chu Ai Duc
 */
abstract public class DAO<Entity> {
     protected ArrayList<Entity> list=new ArrayList<>();
     abstract public int addToDB(Entity ent);
     abstract public void reLoad();
     abstract public int updateToDB(Entity ent);
     abstract public int deleteFromDB(Entity ent);
     abstract public Entity find(Serializable id);
     public ArrayList<Entity> laydanhsach(){
          return list;
     }
}
