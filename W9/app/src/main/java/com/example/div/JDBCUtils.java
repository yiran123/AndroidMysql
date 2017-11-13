package com.example.div;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.*;




public class JDBCUtils {
	private String ip = "192.168.0.36";
	private String user ="yiranpan";
	private String password ="yiran1228";
	private String database = "mytest";
	private String url="jdbc:mysql://"+ip+":3306/"+database+"?useUnicode=true&characterEncoding=utf-8";
	private static JDBCUtils jdbcUtils;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private JDBCUtils(){
		
	}
	public static JDBCUtils getInstance() {
		if(jdbcUtils==null)
			synchronized (JDBCUtils.class) {
				if(jdbcUtils==null)
					jdbcUtils = new JDBCUtils();
			}
		return jdbcUtils;
	}
	public Connection getConnection(final TextView tv){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("123",e.getMessage());
			//Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
			tv.post(new Runnable() {
				@Override
				public void run() {
					tv.setText(e.getMessage());
				}
			});
		}
		return null;
	}
	public Connection getConnection(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("123",e.getMessage());
			//Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();

		}
		return null;
	}
	public void freeRes(Connection conn, Statement st,ResultSet rs) {
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(st!=null)
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		System.out.println(getInstance().getConnection());
		Test test = new Test();
		test.score = 0;
		test.userId =333;
		test.id= (int) (System.currentTimeMillis()%10000);
		test.dateToken = new Timestamp(System.currentTimeMillis());
		final boolean b = SqlOpertion.uploadTest(test);

	}
}
