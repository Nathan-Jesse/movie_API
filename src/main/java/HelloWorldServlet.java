import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
@WebServlet(name="HelloWorldServlet", urlPatterns = "/hello-world")

public class HelloWorldServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Hello, World!");

//        try {
//            PrintWriter out = response.getWriter();
//            out.println("I swear I will not make another Hello World App. :) ");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }





    }

}