import com.google.gson.Gson;
import data.DaoFactory;
import data.Movie;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name="MovieServlet", urlPatterns = "/movies")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");

        try{
            PrintWriter out = response.getWriter();

            Movie movie = new Movie(2, "King Kong", "1955",
                    "Nathan", " Fay Ray", "3448383",
                    "There Aint One", "fighting gorilla",
                    "gorilla on skyscraper");

            // turn into a json string
            String movieString = new Gson().toJson(movie);

            // inject into response
            out.println(movieString);

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        PrintWriter out = null;

        try{ out = response.getWriter();
            // get the steam of characters from the request (eventually becomes our movie)
            BufferedReader reader = request.getReader();

            // turn that stream into an array of Movies
            Movie[] movies = new Gson().fromJson(reader, Movie[].class);

            // SOUT out properties of each movie so we know the objects made it into our code
            for (Movie movie : movies){
                System.out.println(movie.getImdbID());
                System.out.println(movie.getTitle());
                System.out.println(movie.getDirector());
                System.out.println(movie.getActors());
                System.out.println(movie.getGenre());
                System.out.println(movie.getImdbID());
                System.out.println(movie.getPlot());
                System.out.println(movie.getPoster());
                System.out.println("************************************");
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        // write a meaningful response body and set the status code to 200
        out.println(new Gson().toJson("{message: \"Movies Post was successful\"}"));
        response.setStatus(200);
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        PrintWriter out = null;

        try {
            out = response.getWriter();
            Movie movie = new Gson().fromJson(request.getReader(), Movie.class);
            DaoFactory.getMoviesDao(DaoFactory.ImplType.IN_MEMORY).update(movie);
        } catch (SQLException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(400);
            e.printStackTrace();
            return;
        }

        out.println(new Gson().toJson("{message: \"Movie UPDATE was successful\"}"));
        response.setStatus(200);

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        PrintWriter out = null;

        try {
            var id = new Gson().fromJson(request.getReader(), int.class);
            DaoFactory.getMoviesDao(DaoFactory.ImplType.IN_MEMORY).destroy(id);
        } catch (SQLException | IOException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        }

        out.println(new Gson().toJson("{message: \"Movie DELETE was successful\"}"));

    }
}

