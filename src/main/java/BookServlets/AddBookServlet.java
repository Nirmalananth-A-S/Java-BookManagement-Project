package BookServlets;

import Bean.BookBean;
import BookDAO.BookDataBaseOperation;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/addbook")
public class AddBookServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String bookName = req.getParameter("bookName");
        double bookPrice = Double.parseDouble(req.getParameter("bookPrice"));

        BookBean bb = new BookBean();
        bb.setBookId(bookId);
        bb.setBookName(bookName);
        bb.setBookPrice(bookPrice);

        int k = new BookDataBaseOperation().insertBookData(bb);
        if (k>0){
            res.setContentType("text/html");
            res.getWriter().println("Data Inserted");
            RequestDispatcher rd = req.getRequestDispatcher("Index.html");
            rd.include(req, res);
        }
        else {
            res.setContentType("text/html");
            res.getWriter().println("Data Not Inserted");
        }
    }
}
