package spms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spms.vo.Feed;

@Component("feedDao")
public class MySqlFeedDao implements FeedDao {
	SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public List<Feed> selectNextFeed() throws Exception {
		SqlSession sess = sqlSessionFactory.openSession();
		try {
			return sess.selectList("spms.dao.FeedDao.selectNextFeed");
		} finally {
			sess.close();
		}
	}
	
	public int upload(Feed feed) throws Exception {
		SqlSession sess = sqlSessionFactory.openSession();
		try {
			int count = sess.insert("spms.dao.FeedDao.upload", feed);
			sess.commit();
			return count;
		} finally {
			sess.close();
		}
	}

	public int modify(Feed feed) throws Exception {
		SqlSession sess = sqlSessionFactory.openSession();
		try {
			int count = sess.update("spms.dao.FeedDao.modify", feed);
			sess.commit();
			return count;
		} finally {
			sess.close();
		}
	}

	public boolean check(Feed feed) throws Exception {
		SqlSession sess = sqlSessionFactory.openSession();
		try {
			Feed result = (Feed)sess.selectOne("spms.dao.FeedDao.check", feed);
			return result != null;
		} finally {
			sess.close();
		}
	}
}
