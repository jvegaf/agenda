package me.jvegaf.Agenda.Services;

import me.jvegaf.Agenda.JDBC.DBRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveContactUseCase {

    private DBRepository dbRepository;

    public RemoveContactUseCase(DBRepository rep) {
        this.dbRepository = rep;
    }

    public void execute(Integer id){
        String sql = "DELETE FROM contactos WHERE id=?";

        Connection conn = this.dbRepository.getConnection();
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
