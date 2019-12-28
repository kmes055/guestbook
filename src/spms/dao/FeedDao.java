package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import spms.vo.Feed;

//public interface FeedDao {
//	List<Feed> selectNextFeed() throws Exception;
//	int upload(Feed feed) throws Exception;
//	int modify(Feed feed) throws Exception;
//	boolean check(Feed feed) throws Exception;
//}

public class FeedDao {
	Connection connection = null;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public List<Feed> selectNextFeed() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.createStatement();
			String query = "SELECT FNO, EMAIL, CONTENT FROM FEED ORDER BY FNO DESC";
			rs = stmt.executeQuery(query);
			
			ArrayList<Feed> feeds = new ArrayList<Feed>();
			
			while (rs.next()) {
				String content = rs.getString("CONTENT");
				content = content.replace("\n", "<br>");
				feeds.add(new Feed()
						.setFno(rs.getInt("FNO"))
						.setEmail(rs.getString("EMAIL"))
						.setContent(content));
			}
			return feeds;
		} catch(Exception e) {
			throw e;
		}
	}

	public int upload(Feed feed) throws Exception {
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement("INSERT INTO FEED VALUES (NULL, ?, ?, NOW(), NOW(), ?);");
			stmt.setString(1, feed.getEmail());
			stmt.setString(2, feed.getPwd());
			stmt.setString(3, feed.getContent());
			
			stmt.executeUpdate();
			return 0;
		} catch(Exception e) {
			throw e;
		}
	}
	
	public int modify(Feed feed) throws Exception {
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement("UPDATE FEED SET CONTENT=?, MOD_DATE=NOW() WHERE FNO=?;");
			stmt.setString(1, feed.getContent());
			stmt.setString(2, "" + feed.getFno());
			
			stmt.executeUpdate();
			return 0;
		} catch(Exception e) {
			throw e;
		}
	}
	
	public boolean check(Feed feed) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.prepareStatement("SELECT FNO FROM FEED WHERE FNO=? AND PWD=?;");
			stmt.setString(1, "" + feed.getFno());
			stmt.setString(2, feed.getPwd());
			rs = stmt.executeQuery();
			return rs.next();
		} catch(Exception e) {
			throw e;
		}
	}
}
