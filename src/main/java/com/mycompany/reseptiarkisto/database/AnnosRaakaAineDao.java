
package com.mycompany.reseptiarkisto.database;

import com.mycompany.reseptiarkisto.domain.AnnosRaakaAine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AnnosRaakaAineDao implements Dao<AnnosRaakaAine, Integer> {
    private Database database;

    public AnnosRaakaAineDao(Database db) {
        this.database = db;
    }

    @Override
    public AnnosRaakaAine findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnnosRaakaAine> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(AnnosRaakaAine object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
