package me.jvegaf.Agenda.Services;

import me.jvegaf.Agenda.Storage.OLDDBRepository;
import me.jvegaf.Agenda.Model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllContacstUseCase {

    private OLDDBRepository OLDDBRepository;

    public GetAllContacstUseCase(OLDDBRepository rep) {
        this.OLDDBRepository = rep;
    }

    public ArrayList<Contact> execute(){
        ArrayList<Contact> contactsList = null;

        String sql = "SELECT id, nombre, telefono, correo FROM `contactos`";

        Connection cn = OLDDBRepository.getConnection();

        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                contactsList = new ArrayList<Contact>();
                while (rs.next()) {
                    Contact contact = new Contact(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getString(4)
                    );

                    contactsList.add(contact);
                }
                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    cn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return contactsList;
    }
}
