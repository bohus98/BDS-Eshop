package org.but.feec.eshop.data;

import org.but.feec.eshop.api.user.*;
import org.but.feec.eshop.config.DataSourceConfig;
import org.but.feec.eshop.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public UserAuthView findUserByEmail(String email) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT email, password" +
                             " FROM public.user u" +
                             " WHERE u.email = ? ORDER BY u.id_user")
        ) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToUserAuth(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find user by ID with addresses failed.", e);
        }
        return null;
    }

    public UserDetailView findUserDetailedView(Long userId) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT user_id, user_name, email, account_name, account_password" +
                             " FROM public.user u" +
                             " LEFT JOIN address a ON u.id_address = a.id_address" +
                             " WHERE u.id_user = ? ORDER BY u.id_user")
        ) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToUserDetailView(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find user by ID with addresses failed.", e);
        }
        return null;
    }

    /**
     * What will happen if we do not use LEFT JOIN? What users will be returned? Ask your self and repeat JOIN from the presentations
     *
     * @return list of users
     */
    public List<UserBasicView> getUsersBasicView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT user_id, user_name, email, account_name, account_password" +
                             " FROM public.user u" +
                             " LEFT JOIN address a ON u.id_address = a.id_address ORDER BY u.id_user");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            List<UserBasicView> userBasicViews = new ArrayList<>();
            while (resultSet.next()) {
                userBasicViews.add(mapToUserBasicView(resultSet));
            }
            return userBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("Users basic view could not be loaded.", e);
        }
    }

    public void createUser(UserCreateView userCreateView) {
        if(userCreateView.getAccountName() != null){
            String insertUserSQL = "INSERT INTO public.user (user_id, user_name, email, account_name, account_password) VALUES (DEFAULT,?,?,?,?)";
            try (Connection connection = DataSourceConfig.getConnection();
                 // would be beneficial if I will return the created entity back
                 PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS)) {
                // set prepared statement variables
                preparedStatement.setString(1, userCreateView.getUserName());
                preparedStatement.setString(2, userCreateView.getAccountName());

                preparedStatement.setString(3, userCreateView.getEmail());
                preparedStatement.setString(4, String.valueOf(userCreateView.getPwd()));


                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new DataAccessException("Creating user failed, no rows affected.");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Creating user failed operation on the database failed.");
            }
        }
        else{
            String insertUserSQL = "INSERT INTO public.user (user_id, user_name, email, account_name, account_password) VALUES (DEFAULT,?,?,?,?)";
            try (Connection connection = DataSourceConfig.getConnection();
                 // would be beneficial if I will return the created entity back
                 PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS)) {
                // set prepared statement variables
                preparedStatement.setString(1, userCreateView.getUserName());
                preparedStatement.setString(2, userCreateView.getAccountName());
                preparedStatement.setString(3, userCreateView.getEmail());
                preparedStatement.setString(4, String.valueOf(userCreateView.getPwd()));

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new DataAccessException("Creating user failed, no rows affected.");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Creating user failed operation on the database failed.");
            }
        }
    }

    public void deleteUser(UserDeleteView usersDeleteView){
        String deleteUserSQL = "DELETE FROM public.user u WHERE u.id_user = ?";
        String checkIfExists = "SELECT email FROM public.user u WHERE u.id_user = ? ORDER BY u.id_user";
        try (Connection connection = DataSourceConfig.getConnection();
             // would be beneficial if I will return the created entity back
             PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL, Statement.RETURN_GENERATED_KEYS)) {
            // set prepared statement variables
            preparedStatement.setLong(1, usersDeleteView.getId());

            try {
                // TODO set connection autocommit to false
                /* HERE */
                try (PreparedStatement ps = connection.prepareStatement(checkIfExists, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setLong(1, usersDeleteView.getId());
                    ps.execute();
                } catch (SQLException e) {
                    throw new DataAccessException("This user for delete do not exists.");
                }

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new DataAccessException("Deleting user failed, no rows affected.");
                }
                // TODO commit the transaction (both queries were performed)
                /* HERE */
            } catch (SQLException e) {
                // TODO rollback the transaction if something wrong occurs
                /* HERE */
            } finally {
                // TODO set connection autocommit back to true
                /* HERE */
            }
        } catch (SQLException e) {
            throw new DataAccessException("Deleting user failed operation on the database failed.");
        }
    }

    public void editUser(UserEditView userEditView) {
        if(userEditView.getAccountName() != null){
            String insertUserSQL = "UPDATE public.user u SET email = ?, user_name = ?, account_name = ?, account_password = ? WHERE u.id_user = ?";
            String checkIfExists = "SELECT email FROM public.user u WHERE u.id_user = ? ORDER BY u.id_user";
            try (Connection connection = DataSourceConfig.getConnection();
                 // would be beneficial if I will return the created entity back
                 PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS)) {
                // set prepared statement variables
                preparedStatement.setString(1, userEditView.getEmail());
                preparedStatement.setString(2, userEditView.getUserName());
                preparedStatement.setString(3, userEditView.getAccountName());
                preparedStatement.setString(4, userEditView.getAccountPassword());
                preparedStatement.setLong(5, userEditView.getId());

                try {
                    // TODO set connection autocommit to false
                    /* HERE */
                    try (PreparedStatement ps = connection.prepareStatement(checkIfExists, Statement.RETURN_GENERATED_KEYS)) {
                        ps.setLong(1, userEditView.getId());
                        ps.execute();
                    } catch (SQLException e) {
                        throw new DataAccessException("This user for edit do not exists.");
                    }

                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows == 0) {
                        throw new DataAccessException("Creating user failed, no rows affected.");
                    }
                    // TODO commit the transaction (both queries were performed)
                    /* HERE */
                } catch (SQLException e) {
                    // TODO rollback the transaction if something wrong occurs
                    /* HERE */
                } finally {
                    // TODO set connection autocommit back to true
                    /* HERE */
                }
            } catch (SQLException e) {
                throw new DataAccessException("Creating user failed operation on the database failed.");
            }
        }
        else{
            String insertUserSQL = "UPDATE public.user u SET email = ?, user_name = ?, account_name = ?, account_password = ? WHERE u.user_id = ?";
            String checkIfExists = "SELECT email FROM public.user u WHERE u.user_id = ? ORDER BY u.user_id";
            try (Connection connection = DataSourceConfig.getConnection();
                 // would be beneficial if I will return the created entity back
                 PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS)) {
                // set prepared statement variables
                preparedStatement.setString(1, userEditView.getEmail());
                preparedStatement.setString(2, userEditView.getUserName());
                preparedStatement.setString(3, userEditView.getAccountName());
                preparedStatement.setString(4, userEditView.getAccountPassword());
                preparedStatement.setLong(5, userEditView.getId());

                try {
                    // TODO set connection autocommit to false
                    /* HERE */
                    try (PreparedStatement ps = connection.prepareStatement(checkIfExists, Statement.RETURN_GENERATED_KEYS)) {
                        ps.setLong(1, userEditView.getId());
                        ps.execute();
                    } catch (SQLException e) {
                        throw new DataAccessException("This user for edit do not exists.");
                    }

                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows == 0) {
                        throw new DataAccessException("Creating user failed, no rows affected.");
                    }
                    // TODO commit the transaction (both queries were performed)
                    /* HERE */
                } catch (SQLException e) {
                    // TODO rollback the transaction if something wrong occurs
                    /* HERE */
                } finally {
                    // TODO set connection autocommit back to true
                    /* HERE */
                }
            } catch (SQLException e) {
                throw new DataAccessException("Creating user failed operation on the database failed.");
            }
        }
    }


    /**
     * <p>
     * Note: In practice reflection or other mapping frameworks can be used (e.g., MapStruct)
     * </p>
     */
    private UserAuthView mapToUserAuth(ResultSet rs) throws SQLException {
        UserAuthView user = new UserAuthView();
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    }

    private UserBasicView mapToUserBasicView(ResultSet rs) throws SQLException {
        UserBasicView userBasicView = new UserBasicView();
        userBasicView.setId(rs.getLong("user_id"));
        userBasicView.setUserName(rs.getString("user_name"));
        userBasicView.setEmail(rs.getString("email"));
        userBasicView.setAccountName(rs.getString("account_name"));
        userBasicView.setAccountPassword(rs.getString("account_password"));
        return userBasicView;
    }

    private UserDetailView mapToUserDetailView(ResultSet rs) throws SQLException {
        UserDetailView userDetailView = new UserDetailView();
        userDetailView.setId(rs.getLong("id_user"));
        userDetailView.setUserName(rs.getString("user_name"));
        userDetailView.setAccountName(rs.getString("account_name"));
        userDetailView.setEmail(rs.getString("email"));
        userDetailView.setAccountPassword(rs.getString("street_name"));
        return userDetailView;
    }

}

