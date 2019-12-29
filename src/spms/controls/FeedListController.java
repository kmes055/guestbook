package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.FeedDao;
import spms.vo.Feed;

@Component("/main.do")
public class FeedListController implements Controller, DataBinding {
	FeedDao feedDao;
	
	public FeedListController setFeedDao(FeedDao feedDao) {
		this.feedDao = feedDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] { "feed", spms.vo.Feed.class };
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("feeds", feedDao.selectNextFeed());
		
		return "feed/main.jsp";
	}
}
