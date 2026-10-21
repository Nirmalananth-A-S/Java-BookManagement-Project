
package BookServlets;

import BookDAO.BookDataBaseOperation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deletebook")
public class DeleteBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");

        String context = req.getContextPath();

        try {
            int bookId = Integer.parseInt(req.getParameter("bookId"));

            if (bookId <= 0) {
                showResult(res, context, false,
                        "Invalid Book ID",
                        "Please enter a Book ID greater than zero.");
                return;
            }

            BookDataBaseOperation dao = new BookDataBaseOperation();
            int result = dao.deleteBook(bookId);

            if (result > 0) {
                showResult(res, context, true,
                        "Book Deleted Successfully!",
                        "The selected book has been removed from your database.");
            } else {
                showResult(res, context, false,
                        "Book Not Found",
                        "No book was found with ID " + bookId + ". Please check the ID and try again.");
            }

        } catch (NumberFormatException e) {
            showResult(res, context, false,
                    "Invalid Input",
                    "Please enter a valid numeric Book ID.");
        }
    }

    private void showResult(HttpServletResponse res,
                            String context,
                            boolean success,
                            String title,
                            String message) throws IOException {

        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>" + title + " | BookVault</title>");

        out.println("<style>");
        out.println("*{box-sizing:border-box;margin:0;padding:0;}");
        out.println("body{font-family:Arial,sans-serif;background:#f1f5f9;color:#1e293b;min-height:100vh;}");
        out.println(".header{background:#111827;color:white;padding:22px 8%;}");
        out.println(".brand{font-size:25px;font-weight:bold;}");
        out.println(".brand span{color:#60a5fa;}");
        out.println(".main{min-height:75vh;display:flex;align-items:center;justify-content:center;padding:30px 16px;}");
        out.println(".card{background:white;width:100%;max-width:560px;padding:38px;border-radius:18px;text-align:center;box-shadow:0 10px 35px rgba(15,23,42,.08);}");
        out.println(".icon{width:68px;height:68px;border-radius:50%;display:flex;align-items:center;justify-content:center;margin:0 auto 20px;font-size:34px;font-weight:bold;background:" + (success ? "#dcfce7;color:#15803d" : "#fee2e2;color:#b91c1c") + ";}");
        out.println("h1{font-size:27px;margin-bottom:12px;}");
        out.println(".subtitle{color:#64748b;line-height:1.6;margin-bottom:28px;}");
        out.println(".actions{display:flex;flex-wrap:wrap;gap:10px;justify-content:center;}");
        out.println(".btn{display:inline-block;text-decoration:none;padding:12px 17px;border-radius:9px;font-weight:bold;font-size:14px;}");
        out.println(".primary{background:#2563eb;color:white;}");
        out.println(".secondary{background:#e2e8f0;color:#1e293b;}");
        out.println(".footer{text-align:center;color:#64748b;font-size:13px;padding:20px;}");
        out.println("@media(max-width:480px){.card{padding:25px 18px;}h1{font-size:23px;}.btn{width:100%;}}");
        out.println("</style>");

        out.println("</head><body>");

        out.println("<header class='header'><div class='brand'>Book<span>Vault</span></div></header>");
        out.println("<main class='main'><section class='card'>");

        out.println("<div class='icon'>" + (success ? "&#10003;" : "&#33;") + "</div>");
        out.println("<h1>" + title + "</h1>");
        out.println("<p class='subtitle'>" + escapeHtml(message) + "</p>");

        out.println("<div class='actions'>");
        out.println("<a class='btn primary' href='" + context + "/DeleteBook.html'>Delete Another Book</a>");
        out.println("<a class='btn secondary' href='" + context + "/viewallbooks'>View All Books</a>");
        out.println("<a class='btn secondary' href='" + context + "/Index.html'>Back to Dashboard</a>");
        out.println("</div>");

        out.println("</section></main>");
        out.println("<footer class='footer'>BookVault &mdash; Book Management System</footer>");
        out.println("</body></html>");
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
