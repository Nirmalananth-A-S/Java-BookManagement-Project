package BookDAO;

import Bean.BookBean;
import DatabaseDetails.CreateConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDataBaseOperation {

    //Add Book Operation
    public int insertBookData(BookBean bb){
        int k = 0;
        try {
            Connection con = CreateConnection.getCon();
            PreparedStatement ps = con.prepareStatement
                    ("INSERT INTO book VALUES (?, ?, ?)");
            ps.setInt(1, bb.getBookId());
            ps.setString(2, bb.getBookName());
            ps.setDouble(3, bb.getBookPrice());

            k = ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return k;
    }

    // Show one book using its ID

    public BookBean getBookById(int bookId) {
        BookBean bb = null;

        String sql = "SELECT bookId, bookName, bookPrice FROM book WHERE bookId = ?";

        try (Connection con = CreateConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bb = new BookBean();
                    bb.setBookId(rs.getInt("bookId"));
                    bb.setBookName(rs.getString("bookName"));
                    bb.setBookPrice(rs.getDouble("bookPrice"));
                }
            }

        } catch (Exception e) {
            System.err.println("Error while fetching book ID: " + bookId);
            e.printStackTrace();
        }

        return bb;
    }


    // Show all books
    public ArrayList<BookBean> getAllBooks() {
        ArrayList<BookBean> books = new ArrayList<>();

        String sql = "SELECT bookId, bookName, bookPrice FROM book";

        try (Connection con = CreateConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BookBean bb = new BookBean();

                bb.setBookId(rs.getInt("bookId"));
                bb.setBookName(rs.getString("bookName"));
                bb.setBookPrice(rs.getDouble("bookPrice"));

                books.add(bb);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }


    // Update an existing book
    public int updateBookData(BookBean bb) {
        int k = 0;

        String sql = "UPDATE book SET bookName = ?, bookPrice = ? "
                + "WHERE bookId = ?";

        try (Connection con = CreateConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bb.getBookName());
            ps.setDouble(2, bb.getBookPrice());
            ps.setInt(3, bb.getBookId());

            k = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return k;
    }


    // Delete an existing book using its ID
    public int deleteBook(int bookId) {
        int k = 0;

        String sql = "DELETE FROM book WHERE bookId = ?";

        try (Connection con = CreateConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            k = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return k;
    }


}
