package me.jvegaf.Agenda.Services;

import me.jvegaf.Agenda.Storage.OLDDBRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveContactUseCase {

    private OLDDBRepository OLDDBRepository;

    public RemoveContactUseCase(OLDDBRepository rep) {
        this.OLDDBRepository = rep;
    }

    public void execute(Integer id){
        String sql = "DELETE FROM contactos WHERE id=?";

        Connection conn = this.OLDDBRepository.getConnection();
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                boolean result = ps.execute();
                if (!result) throw new SQLException();
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
    }
}
