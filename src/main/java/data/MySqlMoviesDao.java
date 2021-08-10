package data;

import com.mysql.cj.jdbc.Driver;


import java.io.ObjectInputFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlMoviesDao implements MoviesDao {

    private Connection connection = null;

    public MySqlMoviesDao(ObjectInputFilter.Config config) {
        try {
            DriverManager.registerDriver(new Driver());

            this.connection = DriverManager.getConnection(
                    config.getUrl(), // <-- WHERE IS THE DB?
                    config.getUser(), // <-- WHO IS ACCESSING?
                    config.getPassword() // <-- WHAT IS THEIR PASSWORD?
            );

        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Movie> all() throws SQLException {
        return null;
        // TODO: Get ALL the movies
    }

    @Override
    public Movie findOne(int id) {
        return null;
        // TODO: Get one movie by id
    }

    @Override
    public void insert(Movie movie) {
        // TODO: Insert one movie
    }

    public void insertAll(Movie[] movies) throws SQLException {

        // Build sql template
        StringBuilder sql = new StringBuilder("INSERT INTO movies (" +
                "id, title, poster) " +
                "VALUES ");


        // Add a interpolation template for each element in movies list
        sql.append("(?, ?, ?), ".repeat(movies.length));

        // Create a new String and take off the last comma and whitespace
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));

        // Use the sql string to create a prepared statement
        PreparedStatement statement = connection.prepareStatement(sql.toString());

        // Add each movie to the prepared statement using the index of each sql param: '?'
        // This is done by using a counter
        // You could use a for loop to do this as well and use its incrementor
        int counter = 0;
        for (Movie movie : movies) {
            statement.setInt((counter * 9) + 1, movie.getId());
            statement.setString((counter * 9) + 2, movie.getTitle());
            statement.setString((counter * 9) + 3, movie.getPoster());
            counter++;
        }
        statement.executeUpdate();
    }

    @Override
    public void update(Movie movie) throws SQLException {
        //TODO: Update a movie here!
    }

    @Override
    public void destroy(int id) throws SQLException {
        //TODO: Annihilate a movie
    }
}
