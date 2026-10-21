
package BookServlets;

import Bean.BookBean;
import BookDAO.BookDataBaseOperation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/showallbooks")
public class ShowAllBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=UTF-8");

        BookDataBaseOperation dao = new BookDataBaseOperation();
        ArrayList<BookBean> books = dao.getAllBooks();

        try (PrintWriter out = res.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>All Books</title>");
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("</head><body>");

            out.println("<div class='container'>");
            out.println("<h1>All Books</h1>");

            if (books.isEmpty()) {
                out.println("<p class='subtitle'>No books found in the database.</p>");
            } else {
                out.println("<p class='subtitle'>Total books: "
                        + books.size() + "</p>");

                out.println("<table>");
                out.println("<thead><tr>");
                out.println("<th>Book ID</th>");
                out.println("<th>Book Name</th>");
                out.println("<th>Book Price</th>");
                out.println("</tr></thead><tbody>");

                for (BookBean bb : books) {
                    out.println("<tr>");
                    out.println("<td>" + bb.getBookId() + "</td>");
                    out.println("<td>" + escapeHtml(bb.getBookName()) + "</td>");
                    out.println("<td>" + bb.getBookPrice() + "</td>");
                    out.println("</tr>");
                }

                out.println("</tbody></table>");
            }

            out.println("<a href='ShowAllBooks.html' class='back-link'>Refresh Book List</a>");
            out.println("<a href='Index.html' class='back-link'>Back to Home</a>");

            out.println("</div></body></html>");
        }
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
