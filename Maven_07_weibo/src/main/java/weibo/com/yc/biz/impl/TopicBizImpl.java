package weibo.com.yc.biz.impl;

import redis.clients.jedis.Jedis;
import weibo.com.yc.biz.TopicBiz;
import weibo.comn.yc.bean.Topic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TopicBizImpl  implements TopicBiz {


    Jedis jedis = new Jedis();

    @Override
    public Topic post(Topic topic) {
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String topicid =  jedis.incr("global:topicid").toString();//存最新的topicid  topicid自增长

        //以hash结构存Redis中
        Map<String,String> map = new HashMap<String,String>();
        map.put("userid",topic.getUserid());
        /*得到对应用户编号的用户名              key                                  */
        String username = jedis.get("user:userid:" + topic.getUserid() + ":username");//取对应用户编号的用户名
        /*再将用户名存入map*/
        map.put("username",username );
        map.put("time" ,df.format(d));
        map.put("content",topic.getContent());
        /* key = topic:topicid:1    value = map*/
        jedis.hmset("topic:topicid:" + topicid, map);//存mou号de贴子信息

        topic.setTopicid(topicid);

        //将消息推给粉丝
        /*1.先找出粉丝*/
        Set<String> fans = jedis.smembers("following:" + topic.getUserid()) ;//存mou号用户所有的关注用户id
        for (String fanid :
                fans) {
            /*           key  = receivepost:1   value = topicid */
            jedis.lpush("receivepost:" +fanid ,topicid);//存mou号用户所有待读 信息id
        }
        return topic ;
    }
}
