package weibo.com.yc.biz.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import weibo.com.yc.biz.UserBiz;
import weibo.comn.yc.bean.User;

import java.util.ArrayList;
import java.util.List;

public class UserBizImpl implements UserBiz {
	Jedis jedis = new Jedis();

	@Override
	public String isExists(User user) {
		String userid = jedis.get("user:username:" + user.getUsername() + ":userid");
		return userid;
	}

	@Override
	public User regUser(User user) {
		String userid = getNewUserid() ;
		jedis.set("user:userid:" + userid + ":username", user.getUsername());
		jedis.set("user:userid:" + userid+":password", user.getPassword());
		user.setUserid(userid);
		//多存一个用户根据用户名查用户的编号
		jedis.set("user:username:"+ user.getUsername() + ":userid", user.getUserid());
		//存最新注册的10个用户
		jedis.lpush("newuserlink", userid);
		jedis.ltrim("newuserlink", 0, 9);
		return user;
	}

	@Override
	public String getNewUserid() {
		return jedis.incr("global:userid").toString();//得到新的用户编号
	}

	@Override
	public User loginUser(User user) {
		String userid = jedis.get("user:username:" + user.getUsername() +":userid");
		if(userid == null || "".equals(userid)){
			return null ;
		}
		String realpassword = jedis.get("user:userid:" + userid + ":password");
		if(realpassword != null && realpassword.equals(user.getPassword())){
			user.setUserid(userid);
			return user;
		}
		return null;
	}

	@Override
	public List<User> getNew50User() {
		List<User> list = new ArrayList<User>();

		SortingParams params = new SortingParams();// 定义一个排序
		params = params.desc(); // 设置数值排列顺序为降序
		/* 得到所有用户的用户名 key = user:userid:*:username *：表示所有id编号 */
		params = params.get("user:userid:*:username");
		/* 在新用户列表newuserlink中存入新用户名 key = newuserlink value = params */
		List<String> username = jedis.sort("newuserlink", params);
		/* 遍历新用户名列表 存入对象 再将对象存入list 中 */
		for (String s : username) {
			User u = new User();
			u.setUsername(s);
			list.add(u);
		}

		return list;
	}

	@Override
	public boolean isFollowed(String userid, String currentuserid) {
		/* 判断userid是否在currentuserid 的 following:currentuserid 关注列表中 */
		return jedis.sismember("following:" + currentuserid, userid);
	}

	@Override
	public void follow(String userid, String currentuserid) {
		// 先关注 当前用户currentuserid的关注集合列表中加入userid
		jedis.sadd("following:" + currentuserid, userid);
		// 对方的userid粉丝集合中要加入currentuserid
		jedis.sadd("follower:" + userid, currentuserid);

	}

	@Override
	public void unfollow(String userid, String currentuserid) {

		// 先取消关注，当前用户currentuserid的关注集合中减去userid
		jedis.srem("following:" + currentuserid, userid);
		// 对方userid的粉丝集合中也要减去currentuserid
		jedis.srem("follower:" + userid, currentuserid);
	}
}
