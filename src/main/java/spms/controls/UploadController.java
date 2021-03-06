package spms.controls;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spms.bind.DataBinding;
import spms.dao.FeedDao;
import spms.junit.EmailChecker;
import spms.vo.Feed;

@Component("/upload.do")
public class UploadController implements Controller, DataBinding {
	FeedDao feedDao;
	EmailChecker emailChecker;
	String errorMessage = "";
	
	@PostConstruct
    public void init() {
		emailChecker = new EmailChecker();
    }

	@Autowired
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
		
		if (!emailChecker.emailTest(feed.getEmail())) {
			errorMessage = "이메일 형식이 잘못됐습니다";
		}else if (feed.getPwd().length() < 4){
			errorMessage = "패스워드는 4자 이상이어야 합니다";
		}else if (feed.getContent().equals("")) {
			errorMessage = "내용을 1자 이상 입력해주세요";
		}else {
			feedDao.upload(feed);
			return "redirect:main.do";
		}
		model.put("errorMessage", errorMessage);
		return "feed/uploadError.jsp";
	}
	
}
