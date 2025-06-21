package dao;

import Core.Entities.Account;
import Core.Entities.Book;
import Core.Entities.BookRequest;
import Core.Entities.BorrowRecord;
import Core.Interfaces.IRequest;
import JDBC.DBUtils;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author ChanRoi
 */
public class RequestDAO implements IRequest {
    private ArrayList<BookRequest> requestBook = new ArrayList<>();
    private ArrayList<BorrowRecord> borrowedBook = new ArrayList<>();

    public RequestDAO() {
        loadRequestBooks();
        loadBorrowedBooks();
    }

    public void loadRequestBooks() {
        BookDAO bookDAO = new BookDAO();
        requestBook = bookDAO.RequestBook();
    }

    public void loadBorrowedBooks() {
        BookDAO bookDAO = new BookDAO();
        borrowedBook = bookDAO.BorrowedBook();
    }

    public ArrayList<BookRequest> RequestBook() {
        return requestBook;
    }

    public BookRequest getBookRequest(int id) {
        for (BookRequest br : requestBook) {
            if (br.getId() == id) return br;
        }
        return null;
    }

    public int getApprove(int requestId) {
    int result = 0;
    Connection cn = null;
    Account user = getUser(requestId);
    if (user == null || "blocked".equalsIgnoreCase(user.getStatus())) return 0;

    try {
        cn = DBUtils.getConnection();
        cn.setAutoCommit(false);

        String updateRequestSql = "UPDATE book_requests SET status = 'approved' WHERE id = ?";
        PreparedStatement psUpdate = cn.prepareStatement(updateRequestSql);
        psUpdate.setInt(1, requestId);
        int updatedRows = psUpdate.executeUpdate();
        psUpdate.close();

        if (updatedRows > 0) {
            BookRequest request = getBookRequest(requestId);
            if (request != null) {
                int userId = request.getUserId();
                int bookId = request.getBookId();
                Date borrowDate = new Date(System.currentTimeMillis());
                Date dueDate = new Date(System.currentTimeMillis() + 14L * 24 * 60 * 60 * 1000);

                String insertBorrowSql = "INSERT INTO borrow_records (user_id, book_id, borrow_date, due_date, return_date, status) VALUES (?, ?, ?, ?, NULL, 'borrowed')";
                PreparedStatement psInsert = cn.prepareStatement(insertBorrowSql);
                psInsert.setInt(1, userId);
                psInsert.setInt(2, bookId);
                psInsert.setDate(3, borrowDate);
                psInsert.setDate(4, dueDate);
                int inserted = psInsert.executeUpdate();
                psInsert.close();

                if (inserted > 0) {
                    String updateInventorySql = "UPDATE books SET available = available - 1 WHERE id = ? AND available > 0";
                    PreparedStatement psInventory = cn.prepareStatement(updateInventorySql);
                    psInventory.setInt(1, bookId);
                    int inventoryUpdated = psInventory.executeUpdate();
                    psInventory.close();

                    if (inventoryUpdated > 0) {
                        cn.commit();
                        result = 1;
                        request.setStatus("approved");
                        loadBorrowedBooks();
                    } else {
                        cn.rollback();
                    }
                } else {
                    cn.rollback();
                }
            } else {
                cn.rollback();
            }
        } else {
            cn.rollback();
        }
    } catch (Exception e) {
        try { if (cn != null) cn.rollback(); } catch (Exception ex) {}
    } finally {
        try {
            if (cn != null) cn.setAutoCommit(true);
            if (cn != null) cn.close();
        } catch (Exception e) {}
    }

    return result;
}


    public int getReject(int requestId) {
        int result = 0;
        Connection cn = null;
        Account user = getUser(requestId);
        if (user == null || "blocked".equalsIgnoreCase(user.getStatus())) return 0;

        try {
            cn = DBUtils.getConnection();
            cn.setAutoCommit(false);

            String sql = "UPDATE book_requests SET status = 'rejected' WHERE id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, requestId);
            int updated = ps.executeUpdate();
            ps.close();

            if (updated > 0) {
                BookRequest request = getBookRequest(requestId);
                if (request != null) {
                    request.setStatus("rejected");
                    result = 1;
                    cn.commit();
                } else {
                    cn.rollback();
                }
            } else {
                cn.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try { if (cn != null) cn.rollback(); } catch (Exception ex) {}
        } finally {
            try { if (cn != null) cn.setAutoCommit(true); cn.close(); } catch (Exception e) {}
        }

        return result;
    }

    public Account getUser(int requestId) {
        Account user = null;
        try (Connection cn = DBUtils.getConnection()) {
            String sql = "SELECT U.* FROM users U JOIN book_requests BR ON U.id = BR.user_id WHERE BR.id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new Account(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("status")
                );
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
