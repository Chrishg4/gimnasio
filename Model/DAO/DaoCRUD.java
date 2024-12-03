/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.DAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sofia
 */
public abstract class DaoCRUD<Dto> extends DaoCRD<Dto>  {

    public DaoCRUD(Connection connection) {
        super(connection);
    }

    public abstract boolean update (Dto dto) throws SQLException;
  


}
