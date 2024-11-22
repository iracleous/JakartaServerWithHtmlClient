package gr.athtech.work1.repository;

import gr.athtech.work1.model.Product;

import java.sql.*;

public class ProductRepository {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/eshop2024";
    private static final String USER = "root";
    private static final String PASSWORD = "P@ssw0rd!@#$";

    public Product createProduct(Product product)  {
        // SQL query
        String insertQuery = "INSERT INTO Product (name, price, inventoryQuantity) VALUES (?, ?, 1)";

        // Database connection
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());

            // Execute update
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                // Retrieve the generated key
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long insertedId = generatedKeys.getLong(1);
                        System.out.println("User was saved successfully with ID: " + insertedId);
                        product.setId(insertedId);
                    } else {
                        System.out.println("User was saved, but no ID was returned.");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return product;
    }

}
