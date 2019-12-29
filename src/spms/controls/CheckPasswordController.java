package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.FeedDao;
import spms.vo.Feed;

@Component("/check.do")
public class CheckPasswordController implements Controller, DataBinding {
	FeedDao feedDao;
	
	public CheckPasswordController setFeedDao(FeedDao feedDao) {
		this.feedDao = feedDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] { "feed", spms.vo.Feed.class };
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Feed feed = (Feed)model.get("feed");
		if (feed.getPwd() == null) {
			model.put("wrongMessage", "");
			return "feed/check.jsp";
		}
		if(feedDao.check(feed)) {
			return "feed/modify.jsp";
		}else {
			model.put("wrongMessage", "비밀번호가 틀렸습니다.");
			return "feed/check.jsp";
		}
	
	}
}
