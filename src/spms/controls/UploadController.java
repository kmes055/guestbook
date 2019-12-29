package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.FeedDao;
import spms.vo.Feed;

@Component("/upload.do")
public class UploadController implements Controller, DataBinding {
	FeedDao feedDao;

	public UploadController setFeedDao(FeedDao feedDao) {
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
		feedDao.upload(feed);
		
		return "redirect:main.do";
	}
	
}
