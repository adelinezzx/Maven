package weibo.com.yc.biz;

import weibo.comn.yc.bean.User;

import java.util.List;

public interface UserBiz {

    public String isExists(User user) ;

    public User regUser(User user) ;

    /*获取新用户id*/
    public String getNewUserid() ;

    public User loginUser(User user);

    public List<User>  getNew50User();

    /*userid 是否被 currentuserid 关注过了
    * 即：following
    * */
    public boolean isFollowed(String userid,String currentuserid);

    public void follow(String userid, String currentuserid);

    public void unfollow(String userid , String currentuserid);

}
