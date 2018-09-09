package weibo.com.yc.web.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import weibo.com.yc.biz.UserBiz;
import weibo.com.yc.biz.impl.UserBizImpl;
import weibo.com.yc.utils.Encrypt;
import weibo.com.yc.utils.YcConstants;
import weibo.com.yc.web.model.JsonModel;
import weibo.comn.yc.bean.User;

@WebServlet("/user.action")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userbiz = new UserBizImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("login".equals(op)) {
			loginOp(req, resp);
		} else if ("checklogin".equals(op)) {
			checklogin(req, resp);
		} else if ("reg".equals(op)) {
			regOp(req, resp);
		} else if ("getNumbersOfFansAndCare".equals(op)) { 
			getNumbersOfFansAndCare(req, resp);
		}else if ("getnew50user".equals(op)) {
			getnew50user(req, resp);
		}else if ("followtype".equals(op)) {
			followtype(req, resp);
		}else if ("followOrNot".equals(op)) {
			followOrNot(req, resp);
		}

	}
	/**
	 * 判断是否关注
	 * @param req
	 * @param resp
	 */
	private void followOrNot(HttpServletRequest req, HttpServletResponse resp) {
		 User user = super.getReqParamObj(req, User.class);
		 String userid = userbiz.isExists(user);//通过用户名判断用户是否存在，存在则返回userid
		 
		 //得当前登录的用户
		 User currrentuser = (User) req.getSession().getAttribute(YcConstants.LOGINUSER);
		
	/*	 if(userbiz.isFollowed(userid, currrentuser.getUserid())){
			 
		 }*/
	}
		/**
		 * 
		 * @param req
		 * @param resp
		 * @throws IOException
		 */
	private void followtype(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		 User user = super.getReqParamObj(req, User.class);
		 String userid = userbiz.isExists(user);//通过用户名判断用户是否存在，存在则返回userid
		 
		 //得当前登录的用户
		 User currrentuser = (User) req.getSession().getAttribute(YcConstants.LOGINUSER);
		 //判断是否关注过
		boolean isFollowed = userbiz.isFollowed(userid, currrentuser.getUserid());
		
		 JsonModel jm = new JsonModel();
		jm.setCode(1);
		Map<String,String> map =	new HashMap<String ,String>();
		map.put("isFollowed", String.valueOf(isFollowed?1:0));
		map.put("userid", userid);
		map.put("currentuser",currrentuser.getUserid());
		jm.setObj(map);
		super.writeJson(resp, jm);
	}

	private void getnew50user(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	 List<User> list = userbiz.getNew50User();
	 JsonModel  jm = new JsonModel();
	 jm.setCode(1);
	 jm.setObj(list);
	 super.writeJson(resp, jm);
		
	}

	/**
	 * 得到粉丝和自己关注的数量
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void getNumbersOfFansAndCare(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Jedis  jedis = new Jedis();
		User currentuser = (User) req.getSession().getAttribute(YcConstants.LOGINUSER);
        String fans = "0";
        if(jedis.scard("follower:"+currentuser.getUserid()) != null){
        	fans = jedis.scard("follower:" + currentuser.getUserid() ).toString();
        }
        String cares = "0";
        if(jedis.scard("following:" + currentuser.getUserid()) != null ){
        	cares = jedis.scard("following:" + currentuser.getUserid()).toString();
        }
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("fans", fans);
        map.put("cares", cares);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(map);
        super.writeJson(resp, jm);
	}

	/**
	 * 注册
	 * 
	 * @param req
	 * @param resp
	 */
	private void regOp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JsonModel jm = new JsonModel();
		User resuser = super.getReqParamObj(map, User.class);
		String password2 = req.getParameter("password2");// 得到重复密码
		// 判断密码和 确认密码是否相同
		if (resuser.getPassword() == null || !resuser.getPassword().equals(password2)) {
			jm.setCode(0);
			jm.setMsg("you hava  typed defferent passwords");
		} else {
			if (userbiz.isExists(resuser) == null) {// 判断用户是否存在 存在则登录 否则登录失败
				resuser.setPassword(Encrypt.md5(resuser.getPassword()));
				resuser = userbiz.regUser(resuser);
				jm.setCode(1);
				jm.setObj(resuser);
			} else {
				jm.setCode(0);
				jm.setMsg("user hava been exixts");
			}
		}
		super.writeJson(resp, jm);

	}

	/**
	 * 检测 登录状态
	 * 
	 * @param req
	 * @param resp
	 */
	private void checklogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JsonModel jm = new JsonModel();
		if (session.getAttribute(YcConstants.LOGINUSER) == null) {
			jm.setCode(0);
		} else {
			jm.setCode(1);
			User user = (User) session.getAttribute(YcConstants.LOGINUSER);
			jm.setObj(user);

		}
		super.writeJson(resp, jm);

	}

	/**
	 * 登录
	 * 
	 * @param req
	 * @param resp
	 */
	private void loginOp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JsonModel jm = new JsonModel();
		User resuser = super.getReqParamObj(map, User.class);
		resuser.setPassword(Encrypt.md5(resuser.getPassword()));
		resuser = userbiz.loginUser(resuser);
		if (resuser != null) {
			session.setAttribute(YcConstants.LOGINUSER, resuser);
			jm.setCode(1);
			jm.setObj(resuser);

		} else {
			jm.setCode(0);
			jm.setMsg("wrong username or password , try again");
		}

		super.writeJson(resp, jm);
	}

}
