
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

@WebServlet("/showbook")
public class ShowBookByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=UTF-8");

        String idValue = req.getParameter("bookId");

        try (PrintWriter out = res.getWriter()) {

            int bookId = Integer.parseInt(idValue);

            BookDataBaseOperation dao =
                    new BookDataBaseOperation();

            BookBean bb = dao.getBookById(bookId);

            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Book Details</title></head><body>");
            out.println("<h1>Book Details</h1>");

            if (bb != null) {
                out.println("<table border='1' cellpadding='10'>");
                out.println("<tr><th>Book ID</th><th>Book Name</th><th>Book Price</th></tr>");
                out.println("<tr>");
                out.println("<td>" + bb.getBookId() + "</td>");
                out.println("<td>" + escapeHtml(bb.getBookName()) + "</td>");
                out.println("<td>" + bb.getBookPrice() + "</td>");
                out.println("</tr></table>");
            } else {
                out.println("<p>No book found with ID: "
                        + bookId + "</p>");
            }

            out.println("<br><a href='ShowBookById.html'>Search Another Book</a>");
            out.println("<br><a href='Index.html'>Back to Home</a>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Please enter a valid book ID.");
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
