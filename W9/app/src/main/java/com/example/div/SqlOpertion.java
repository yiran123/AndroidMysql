package com.example.div;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class SqlOpertion {

    /**
     * regist failed will return null
     *
     * @param user
     * @return
     */
    public static boolean regist(User user) {
        Connection conn = JDBCUtils.getInstance().getConnection();
        String sql = "select * from user where username =?";
        String sql2 = "insert into user (username,password,firstname,lastname) values(?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, user.userName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                JDBCUtils.getInstance().freeRes(conn, pstmt, rs);
                return false;
            }
            pstmt = (PreparedStatement) conn.prepareStatement(sql2);
            pstmt.setString(1, user.userName);
            pstmt.setString(2, user.password);
            pstmt.setString(3, user.firstName);
            pstmt.setString(4, user.lastName);
            int i = pstmt.executeUpdate();
            JDBCUtils.getInstance().freeRes(conn, pstmt, null);
            return i == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * login failed will return null
     *
     * @param userName
     * @param pwd
     * @return
     */
    public static boolean login(String userName, String pwd) {
        Connection conn = JDBCUtils.getInstance().getConnection();
        String sql = "select * from user where username =? and password = ?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                JDBCUtils.getInstance().freeRes(conn, pstmt, rs);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean uploadProblem(Problem problem) {

        Connection conn = JDBCUtils.getInstance().getConnection();

        int i = 0;
        String sql = "insert into problem (problemno,operand1,operand2,operation,AnsweredCorrectly,testid) values(?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, problem.problemNo);
            pstmt.setInt(2, problem.operand1);
            pstmt.setInt(3, problem.operand2);
            pstmt.setString(4, problem.operation + "");
            pstmt.setInt(5, problem.answeredCorrectly);
            pstmt.setInt(6, problem.testId);
            i = pstmt.executeUpdate();
            JDBCUtils.getInstance().freeRes(conn, pstmt, null);
            return i == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean uploadTest(Test test) {

        Connection conn = JDBCUtils.getInstance().getConnection();

        int i = 0;
        String sql = "insert into test (id,DateToken,Score,UserID) values(?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, test.id);
            pstmt.setTimestamp(2, test.dateToken);
            pstmt.setInt(3, test.score);
            pstmt.setInt(4, test.userId);
            i = pstmt.executeUpdate();
            JDBCUtils.getInstance().freeRes(conn, pstmt, null);
            return i == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateTest(Test test) {

        Connection conn = JDBCUtils.getInstance().getConnection();

        int i = 0;
        String sql = "update  test set score = ? where id = ?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, test.score);
            pstmt.setInt(2, test.id);
            i = pstmt.executeUpdate();
            JDBCUtils.getInstance().freeRes(conn, pstmt, null);
            return i == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<User> getTop5UserScores() {
        String sqlString = "select sum(score) ,userid,username from test as a,user as b" +
                " where a.userid=b.id group by a.userid order by userid desc limit 0,5;";
        Connection conn = JDBCUtils.getInstance().getConnection();
        PreparedStatement pstmt;
        List<User> list = new ArrayList<User>();
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sqlString);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.score = rs.getInt(1);
                user.id = rs.getInt(2);
                user.userName = rs.getString(3);
                list.add(user);
            }
            JDBCUtils.getInstance().freeRes(conn, pstmt, null);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
