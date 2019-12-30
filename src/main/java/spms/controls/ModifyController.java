package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spms.bind.DataBinding;
import spms.dao.FeedDao;
import spms.vo.Feed;

@Component("/modify.do")
public class ModifyController implements Controller, DataBinding {
	FeedDao feedDao;
	
	@Autowired
	public ModifyController setFeedDao(FeedDao feedDao) {
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
		feedDao.modify(feed);
		return "redirect:main.do";
	}
}
