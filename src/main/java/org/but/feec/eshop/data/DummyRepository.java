package org.but.feec.eshop.data;

import org.but.feec.eshop.api.dummy.DummyBasicView;
import org.but.feec.eshop.config.DataSourceConfig;
import org.but.feec.eshop.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DummyRepository {
    public List<DummyBasicView> getDummyBasicView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT string" +
                             " FROM dummy_table" +
                             " ORDER BY string");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            List<DummyBasicView> dummyBasicViews = new ArrayList<>();
            while (resultSet.next()) {
                dummyBasicViews.add(mapToDummyBasicView(resultSet));
            }
            return dummyBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("Strings basic view could not be loaded.", e);
        }
    }

    public void createString(DummyBasicView dummyBasicView) {
        String string = dummyBasicView.getString();
        String insertQuerySQL = "INSERT INTO dummy_table (string) VALUES('" + string + "');";

        try(Connection connection = DataSourceConfig.getConnection()){
            Statement statement = connection.createStatement();

            statement.executeUpdate(insertQuerySQL);

        }catch (SQLException e){
            throw new DataAccessException("Strings create exception.", e);
        }
    }

    private DummyBasicView mapToDummyBasicView(ResultSet rs) throws SQLException {
        DummyBasicView dummyBasicView = new DummyBasicView();
        dummyBasicView.setString(rs.getString("string"));
        return dummyBasicView;
    }
}
