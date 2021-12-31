package org.but.feec.eshop.data;

import org.but.feec.eshop.api.product.*;
import org.but.feec.eshop.config.DataSourceConfig;
import org.but.feec.eshop.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public ProductAuthView findProductByEmail(String email) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT email, password" +
                             " FROM public.user u" +
                             " WHERE u.email = ? ORDER BY u.id_user")
        ) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToProductAuth(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find product by ID with addresses failed.", e);
        }
        return null;
    }

    public ProductDetailView findProductDetailedView(Long productId) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT t.id_product, t.name, p.type_name, n.genre_name, c.country_name, t.year, t.lenght, t.description" +
                             " FROM product t" +
                             " LEFT JOIN country c ON t.id_country = c.id_country" +
                             " LEFT JOIN type p ON t.id_type = p.id_type" +
                             " LEFT JOIN genre g ON t.id_product = g.id_producte" +
                             " JOIN genre_name n ON g.id_genre_name = n.id_genre_name" +
                             " WHERE t.id_product = ? ORDER BY t.id_product")
        ) {
            preparedStatement.setLong(1, productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToProductDetailView(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find product by ID with addresses failed.", e);
        }
        return null;
    }



    public List<ProductBasicView> getProductBasicView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT t.id_product, name, p.type_name, year, lenght, c.country_name, t.description, t.id_type, t.id_country" +
                             " FROM product t" +
                             " LEFT JOIN type p ON t.id_type = p.id_type" +
                             " LEFT JOIN country c ON t.id_country = c.id_country ORDER BY t.id_product");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            List<ProductBasicView> productBasicViews = new ArrayList<>();
            while (resultSet.next()) {
                productBasicViews.add(mapToProductBasicView(resultSet));
            }
            return productBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("Products basic view could not be loaded.", e);
        }
    }

    public void createProduct(ProductCreateView productCreateView) {
        String insertProductSQL = "INSERT INTO product (id_product, id_type, id_country, name, year, lenght, description) VALUES (DEFAULT,?,?,?,?,?,?)";
        try (Connection connection = DataSourceConfig.getConnection();
             // would be beneficial if I will return the created entity back
             PreparedStatement preparedStatement = connection.prepareStatement(insertProductSQL, Statement.RETURN_GENERATED_KEYS)) {
            // set prepared statement variables
            preparedStatement.setLong(1, productCreateView.getCurrencyid());
            preparedStatement.setLong(2, productCreateView.getHasprice());
            preparedStatement.setString(3, productCreateView.getProduct());
            preparedStatement.setLong(4, productCreateView.getShopid());
            preparedStatement.setLong(5, productCreateView.getCategoryid());
            preparedStatement.setString(6, productCreateView.getDetail());
            preparedStatement.setLong(7, productCreateView.getPrice());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DataAccessException("Creating product failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Creating product failed operation on the database failed.");
        }
    }

    public void deleteProduct(ProductDeleteView productDeleteView){
        String deleteProductSQL = "DELETE FROM product t WHERE t.id_product = ?";
        String checkIfExists = "SELECT id_product FROM product t WHERE t.id_product = ? ORDER BY t.id_product";
        try (Connection connection = DataSourceConfig.getConnection();
             // would be beneficial if I will return the created entity back
             PreparedStatement preparedStatement = connection.prepareStatement(deleteProductSQL, Statement.RETURN_GENERATED_KEYS)) {
            // set prepared statement variables
            preparedStatement.setLong(1, productDeleteView.getId());

            try {
                // TODO set connection autocommit to false
                /* HERE */
                try (PreparedStatement ps = connection.prepareStatement(checkIfExists, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setLong(1, productDeleteView.getId());
                    ps.execute();
                } catch (SQLException e) {
                    throw new DataAccessException("This product for delete do not exists.");
                }

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new DataAccessException("Deleting product failed, no rows affected.");
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
            throw new DataAccessException("Deleting product failed operation on the database failed.");
        }
    }

    public void editProduct(ProductEditView productEditView) {
        String insertProductSQL = "UPDATE product t SET name = ?, id_type = ?, year = ?, lenght = ?, id_country = ? WHERE t.id_title = ?";
        String checkIfExists = "SELECT id_product FROM product t WHERE t.id_product = ? ORDER BY t.id_product";
        try (Connection connection = DataSourceConfig.getConnection();
             // would be beneficial if I will return the created entity back
             PreparedStatement preparedStatement = connection.prepareStatement(insertProductSQL, Statement.RETURN_GENERATED_KEYS)) {
            // set prepared statement variables
            preparedStatement.setLong(1, productEditView.getCurrencyid());
            preparedStatement.setLong(2, productEditView.getHasprice());
            preparedStatement.setString(3, productEditView.getProduct());
            preparedStatement.setLong(4, productEditView.getShopid());
            preparedStatement.setLong(5, productEditView.getCategoryid());
            preparedStatement.setString(6, productEditView.getDetail());
            preparedStatement.setLong(7, productEditView.getPrice());

            try {
                // TODO set connection autocommit to false
                /* HERE */
                try (PreparedStatement ps = connection.prepareStatement(checkIfExists, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setLong(1, productEditView.getId());
                    ps.execute();
                } catch (SQLException e) {
                    throw new DataAccessException("This product for edit do not exists.");
                }

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new DataAccessException("Creating product failed, no rows affected.");
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
            throw new DataAccessException("Creating product failed operation on the database failed.");
        }
    }


    /**
     * <p>
     * Note: In practice reflection or other mapping frameworks can be used (e.g., MapStruct)
     * </p>
     */
    private ProductAuthView mapToProductAuth(ResultSet rs) throws SQLException {
        ProductAuthView product = new ProductAuthView();
        product.setEmail(rs.getString("email"));
        product.setPassword(rs.getString("password"));
        return product;
    }

    private ProductBasicView mapToProductBasicView(ResultSet rs) throws SQLException {
        ProductBasicView productBasicView = new ProductBasicView();
        productBasicView.setId(rs.getLong("id_product"));
        productBasicView.setProduct(rs.getString("name"));
        productBasicView.setDetail(rs.getString("detail"));
        productBasicView.setHasprice(rs.getString("has_price"));
        productBasicView.setPrice(rs.getString("price"));
        productBasicView.setShopid(rs.getString("shop_id"));
        productBasicView.setCategoryid(rs.getString("category_id"));
        productBasicView.setCurrencyid(rs.getString("currency_id"));
        return productBasicView;
    }

    private ProductDetailView mapToProductDetailView(ResultSet rs) throws SQLException {
        ProductDetailView productDetailView = new ProductDetailView();
        productDetailView.setId(rs.getLong("id_product"));
        productDetailView.setProduct(rs.getString("name"));
        productDetailView.setHasprice(rs.getLong("type_name"));
        productDetailView.setCurrencyid(rs.getLong("genre_name"));
        productDetailView.setShopid(rs.getLong("country_name"));
        productDetailView.setCategoryid(rs.getLong("year"));
        productDetailView.setDetail(rs.getString("lenght"));
        return productDetailView;
    }

}

