
package com.mycompany.reseptiarkisto.database;

import com.mycompany.reseptiarkisto.domain.RaakaAine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RaakaAineDao implements Dao<RaakaAine, Integer> {
    private Database database;

    public RaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public RaakaAine findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT id, nimi FROM RaakaAine WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        RaakaAine raakaAine = new RaakaAine(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return raakaAine;
    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {
        
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine");

        ResultSet rs = stmt.executeQuery();
        List<RaakaAine> aineet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            aineet.add(new RaakaAine(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return aineet;
    }
    
    public RaakaAine findOneByAnnosId(int annosId) throws SQLException {

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT RaakaAine.id, RaakaAine.nimi"
                    + "FROM RaakaAine, Annos"
                    + "WHERE RaakaAine.");
            stmt.setInt(1, annosId);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                return new RaakaAine(result.getInt("id"), result.getString("nimi"));
            }
        }

        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void save(RaakaAine object) throws SQLException {
        Connection conn = this.database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO RaakaAine (nimi)"
                + "VALUES(?)");
        
        stmt.setString(1, object.getNimi());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
}
