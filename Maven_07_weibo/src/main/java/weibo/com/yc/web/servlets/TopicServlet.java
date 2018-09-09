package weibo.com.yc.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import weibo.com.yc.biz.TopicBiz;
import weibo.com.yc.biz.impl.TopicBizImpl;
import weibo.com.yc.web.model.JsonModel;
import weibo.comn.yc.bean.Topic;
import weibo.comn.yc.bean.User;

@WebServlet("/topic.action")
public class TopicServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private TopicBiz topicbiz = new TopicBizImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("postTopic".equals(op)) {
			postTopic(req, resp);
		} else if ("getTopic".equals(op)) {
			getTopic(req, resp);
		}
	}

	/**
	 * 显示发布的帖子
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void getTopic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Jedis jedis = new Jedis();
		User user = (User) req.getSession().getAttribute("loginuser");
		List<Topic> topics = new ArrayList<Topic>();
		SortingParams sp = new SortingParams();
		sp = sp.desc();
		List<String> newTopicid = jedis.sort("receivepost:" + user.getUserid(), sp);
		for (String ntid : newTopicid) {
			List<String> ll = jedis.hmget("topic:topicid:" + ntid, "userid", "time", "content", "username");
            Topic t = new Topic();
            t.setTopicid(ntid);
            t.setUserid(ll.get(0));
            t.setPosttime(ll.get(1));
            t.setContent(ll.get(2));
            t.setUsername(ll.get(3));
            topics.add(t);
		}
		JsonModel jm = new JsonModel();
		jm.setCode(1);
		jm.setObj(topics);
		super.writeJson(resp, jm);

	}

	/**
	 * 发布帖子
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void postTopic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JsonModel jm = new JsonModel();
		Topic topic = super.getReqParamObj(map, Topic.class);
		topic = topicbiz.post(topic);
		jm.setCode(1);
		jm.setObj(topic);
		super.writeJson(resp, jm);

	}

}
