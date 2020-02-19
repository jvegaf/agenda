package me.jvegaf.Agenda.Services;

import me.jvegaf.Agenda.Storage.OLDDBRepository;
import me.jvegaf.Agenda.Model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindContactUseCase {

    private OLDDBRepository OLDDBRepository;

    public FindContactUseCase(OLDDBRepository rep) {
        this.OLDDBRepository = rep;
    }

    public Contact execute(Integer id){

        String sql = "SELECT nombre, telefono, correo FROM contactos where id=?";
        Contact contacto = null;
        Connection conn = OLDDBRepository.getConnection();

        if (conn != null){
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    contacto = new Contact(
                            id,
                            rs.getString(1),
                            rs.getInt(2),
                            rs.getString(3)
                    );
                }
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return contacto;
    }
}
