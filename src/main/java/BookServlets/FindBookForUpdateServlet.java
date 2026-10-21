
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

@WebServlet("/findbookforupdate")
public class FindBookForUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=UTF-8");

        String idValue = req.getParameter("bookId");

        try {
            int bookId = Integer.parseInt(idValue);

            BookDataBaseOperation dao =
                    new BookDataBaseOperation();

            BookBean bb = dao.getBookById(bookId);

            try (PrintWriter out = res.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<title>Edit Book</title>");
                out.println("<link rel='stylesheet' href='style.css'>");
                out.println("</head><body>");
                out.println("<div class='container'>");

                if (bb == null) {
                    out.println("<h1>Book Not Found</h1>");
                    out.println("<p class='subtitle'>No book exists with ID: "
                            + bookId + "</p>");
                    out.println("<a href='UpdateBook.html' class='back-link'>Try Another ID</a>");
                } else {
                    out.println("<h1>Edit Book Details</h1>");
                    out.println("<p class='subtitle'>Update the book name or price.</p>");

                    out.println("<form action='updatebook' method='post'>");

                    out.println("<label>Book ID</label>");
                    out.println("<input type='number' name='bookId' value='"
                            + bb.getBookId()
                            + "' readonly>");

                    out.println("<label for='bookName'>Book Name</label>");
                    out.println("<input type='text' id='bookName' name='bookName' value='"
                            + escapeHtml(bb.getBookName())
                            + "' required maxlength='200'>");

                    out.println("<label for='bookPrice'>Book Price</label>");
                    out.println("<input type='number' id='bookPrice' name='bookPrice' value='"
                            + bb.getBookPrice()
                            + "' min='0' step='0.01' required>");

                    out.println("<button type='submit'>Save Changes</button>");
                    out.println("</form>");

                    out.println("<a href='Index.html' class='back-link'>Back to Home</a>");
                }

                out.println("</div></body></html>");
            }

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
