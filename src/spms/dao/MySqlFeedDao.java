//package spms.dao;
//
//import java.util.List;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//
//import spms.vo.Feed;
//
//public class MySqlFeedDao implements FeedDao {
//	SqlSessionFactory sqlSessionFactory;
//	
//	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
//		this.sqlSessionFactory = sqlSessionFactory;
//	}
//
//	@Override
//	public List<Feed> selectNextFeed() throws Exception {
//		SqlSession sess = sqlSessionFactory.openSession();
//		try {
//			return sess.selectList("spms.dao.FeedDao.selectNextFeed");
//		} finally {
//			sess.close();
//		}
//	}
//
//	@Override
//	public int upload(Feed feed) throws Exception {
//		SqlSession sess = sqlSessionFactory.openSession();
//		try {
//			int ret = sess.insert("spms.dao.FeedDao.upload", feed);
//			sess.commit();
//			return ret;
//		} finally {
//			sess.close();
//		}
//	}
//
//	@Override
//	public int modify(Feed feed) throws Exception {
//		SqlSession sess = sqlSessionFactory.openSession();
//		try {
//			int ret = sess.update("spms.dao.FeedDao.modify", feed);
//			sess.commit();
//			return ret;
//		} finally {
//			sess.close();
//		}
//	}
//
//	@Override
//	public boolean check(Feed feed) throws Exception {
//		SqlSession sess = sqlSessionFactory.openSession();
//		try {
//			sess.update("spms.dao.FeedDao.check", feed);
//			return true;
//		} finally {
//			sess.close();
//		}
//	}
//	
//	
//}
