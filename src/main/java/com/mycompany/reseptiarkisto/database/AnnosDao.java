/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reseptiarkisto.database;

import com.mycompany.reseptiarkisto.domain.Annos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnosDao implements Dao<Annos, Integer> {

    private Database database;

    public AnnosDao(Database database) {
        this.database = database;
    }

    @Override
    public Annos findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Annos WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Annos o = new Annos(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Annos> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Annos;");

        ResultSet rs = stmt.executeQuery();
        List<Annos> annokset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            annokset.add(new Annos(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return annokset;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

    public void save(Annos object) throws SQLException {
        Connection conn = this.database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Annos (nimi)"
                + "VALUES(?)");
        
        stmt.setString(1, object.getNimi());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

}
