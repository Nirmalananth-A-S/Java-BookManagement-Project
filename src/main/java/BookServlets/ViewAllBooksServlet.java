
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

@WebServlet("/viewallbooks")
public class ViewAllBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=UTF-8");

        PrintWriter pw = res.getWriter();

        BookDataBaseOperation dao =
                new BookDataBaseOperation();

        ArrayList<BookBean> books = dao.getAllBooks();

        pw.println("<!DOCTYPE html>");
        pw.println("<html lang='en'>");
        pw.println("<head>");
        pw.println("<meta charset='UTF-8'>");
        pw.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        pw.println("<title>All Books | BookVault</title>");

        pw.println("<style>");
        pw.println("* { box-sizing: border-box; margin: 0; padding: 0; }");
        pw.println("body { font-family: Arial, sans-serif; background: #f1f5f9; color: #0f172a; }");
        pw.println("header { background: #111827; color: white; padding: 24px 7%; }");
        pw.println(".brand { font-size: 25px; font-weight: bold; }");
        pw.println(".subtitle { color: #cbd5e1; font-size: 12px; margin-top: 7px; letter-spacing: 1.5px; }");
        pw.println(".container { max-width: 1100px; margin: 35px auto; padding: 0 20px; }");
        pw.println(".top { display: flex; justify-content: space-between; align-items: center; gap: 15px; flex-wrap: wrap; margin-bottom: 25px; }");
        pw.println("h1 { font-size: 28px; margin-bottom: 8px; }");
        pw.println(".muted { color: #64748b; font-size: 14px; }");
        pw.println(".btn { display: inline-block; padding: 11px 16px; border-radius: 7px; background: #2563eb; color: white; text-decoration: none; font-size: 14px; }");
        pw.println(".btn:hover { background: #1d4ed8; }");
        pw.println(".panel { background: white; border: 1px solid #e2e8f0; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 15px rgba(15,23,42,.04); }");
        pw.println(".table-wrap { overflow-x: auto; }");
        pw.println("table { width: 100%; border-collapse: collapse; min-width: 480px; }");
        pw.println("th, td { padding: 16px 20px; text-align: left; border-bottom: 1px solid #e2e8f0; }");
        pw.println("th { background: #f8fafc; color: #475569; font-size: 12px; text-transform: uppercase; letter-spacing: .7px; }");
        pw.println("tbody tr:hover { background: #f8fafc; }");
        pw.println("tbody tr:last-child td { border-bottom: none; }");
        pw.println(".empty { padding: 45px 20px; text-align: center; color: #64748b; }");
        pw.println("footer { text-align: center; padding: 25px; color: #64748b; font-size: 12px; }");
        pw.println("@media(max-width: 600px) { h1 { font-size: 23px; } th, td { padding: 13px; } }");
        pw.println("</style>");

        pw.println("</head>");
        pw.println("<body>");

        pw.println("<header>");
        pw.println("<div class='brand'>📚 BookVault</div>");
        pw.println("<div class='subtitle'>BOOK MANAGEMENT SYSTEM</div>");
        pw.println("</header>");

        pw.println("<main class='container'>");

        pw.println("<div class='top'>");
        pw.println("<div>");
        pw.println("<h1>All Books</h1>");
        pw.println("<p class='muted'>Browse your book collection.</p>");
        pw.println("</div>");
        pw.println("<a class='btn' href='Index.html'>← Back to Dashboard</a>");
        pw.println("</div>");

        pw.println("<section class='panel'>");

        if (books.isEmpty()) {

            pw.println("<div class='empty'>");
            pw.println("<h3>No books found</h3>");
            pw.println("<p style='margin-top:10px'>Add a book to see it listed here.</p>");
            pw.println("<p style='margin-top:20px'><a class='btn' href='AddBook.html'>+ Add Book</a></p>");
            pw.println("</div>");

        } else {

            pw.println("<div class='table-wrap'>");
            pw.println("<table>");
            pw.println("<thead><tr>");
            pw.println("<th>Book ID</th>");
            pw.println("<th>Book Name</th>");
            pw.println("<th>Book Price</th>");
            pw.println("</tr></thead>");
            pw.println("<tbody>");

            for (BookBean bb : books) {
                pw.println("<tr>");
                pw.println("<td>" + bb.getBookId() + "</td>");
                pw.println("<td>" + escapeHtml(bb.getBookName()) + "</td>");
                pw.println("<td>" + String.format(java.util.Locale.US, "%.2f", bb.getBookPrice()) + "</td>");
                pw.println("</tr>");
            }

            pw.println("</tbody>");
            pw.println("</table>");
            pw.println("</div>");
        }

        pw.println("</section>");
        pw.println("</main>");

        pw.println("<footer>BookVault · Java Servlet · JDBC · MySQL</footer>");
        pw.println("</body>");
        pw.println("</html>");
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}
