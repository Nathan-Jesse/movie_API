package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MySqlMoviesDao implements MoviesDao {

    private Connection connection = null;

    public MySqlMoviesDao(Config config){

    }

    @Override
    public List<Movie> all() throws SQLException {
        return null;
    }

    @Override
    public Movie findOne(int id) {
        return null;
    }

    @Override
    public void insert(Movie movie) {

    }

    @Override
    public void insertAll(Movie[] movies) throws SQLException {

    }

    @Override
    public void update(Movie movie) throws SQLException {

    }

    @Override
    public void destroy(int id) throws SQLException {

    }
}
