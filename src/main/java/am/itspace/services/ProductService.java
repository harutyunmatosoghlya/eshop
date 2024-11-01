package am.itspace.services;

import am.itspace.db.DBConnectionProvider;
import am.itspace.model.Category;
import am.itspace.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Product product) {
        String sql = "INSERT INTO product (name, description, price, quantity, category_id) VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getCategory().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProduct(int id, String name, String description, Double price, Integer quantity, Category category) {
        String sqlSelect = "SELECT * FROM product WHERE id = " + id;
        String sqlUpdate = "UPDATE product SET name = ?, description = ?, price = ?, quantity = ?, category_id = ? WHERE id = ?";
        try (ResultSet resultSet = connection.createStatement().executeQuery(sqlSelect)) {
            if (resultSet.next()) {
                if (name == null || name.isEmpty()) {
                    name = resultSet.getString("name");
                }
                if (description == null || description.isEmpty()) {
                    description = resultSet.getString("description");
                }
                if (price == null || price == 0.0) {
                    price = resultSet.getDouble("price");
                }
                if (quantity == null || quantity == 0) {
                    quantity = resultSet.getInt("quantity");
                }
                if (category == null) {
                    int categoryId = resultSet.getInt("category_id");
                    category = CategoryService.getCategoryById(categoryId);
                }
            } else {
                System.out.println("Продукт с ID " + id + " не найден.");
                return;
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, description);
                preparedStatement.setDouble(3, price);
                preparedStatement.setInt(4, quantity);
                preparedStatement.setInt(5, category.getId());
                preparedStatement.setInt(6, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id = " + id;
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int absoluteQuantity() {
        String sql = "SELECT SUM(quantity) FROM product";
        int absoluteQuantity = 0;
        try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            if (resultSet.next()) {
                absoluteQuantity = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absoluteQuantity;
    }

    public int countProducts() {
        String sql = "SELECT COUNT(*) FROM product";
        int productCount = 0;
        try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            if (resultSet.next()) {
                productCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCount;
    }

    public double getMaxPrice() {
        String sql = "SELECT MAX(price) FROM product";
        double maxPrice = 0;
        try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            if (resultSet.next()) {
                maxPrice = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxPrice;
    }

    public double getMinPrice() {
        String sql = "SELECT MIN(price) FROM product";
        double minPrice = 0;
        try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            if (resultSet.next()) {
                minPrice = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return minPrice;
    }

    public double getAveragePrice() {
        String sql = "SELECT AVG(price) FROM product";
        double averagePrice = 0.0;
        try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            if (resultSet.next()) {
                averagePrice = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averagePrice;
    }

    public static Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE id = " + id;
        try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            if (resultSet.next()) {
                return Product.builder().id(resultSet.getInt("id")).name(resultSet.getString("name")).description(resultSet.getString("description")).price(resultSet.getDouble("price")).quantity(resultSet.getInt("quantity")).category(CategoryService.getCategoryById(resultSet.getInt("category_id"))).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                products.add(Product.builder().id(resultSet.getInt("id")).name(resultSet.getString("name")).description(resultSet.getString("description")).price(resultSet.getDouble("price")).quantity(resultSet.getInt("quantity")).category(CategoryService.getCategoryById(resultSet.getInt("category_id"))).build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}