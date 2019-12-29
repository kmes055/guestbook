package spms.dao;

import spms.vo.Feed;

public interface FeedDao {
	java.util.List<Feed> selectNextFeed() throws Exception;
	int upload(Feed feed) throws Exception;
	int modify(Feed feed) throws Exception;
	boolean check(Feed feed) throws Exception;
}
