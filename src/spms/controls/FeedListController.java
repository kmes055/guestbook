package spms.controls;

import java.util.Map;

import spms.dao.FeedDao;

public class FeedListController implements Controller {
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		FeedDao feedDao = (FeedDao)model.get("feedDao");
		model.put("feeds", feedDao.selectNextFeed());
		
		return "feed/main.jsp";
	}
}
