package weibo.comn.yc.bean;

import java.io.Serializable;

public class Topic implements Serializable {

    private String topicid ;
    private String content ;
    private String userid ;
    private String posttime ;

    private String elapseTime ;
    private String username ;

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    
    public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}

	public String getElapseTime() {
        return elapseTime;
    }

    public void setElapseTime(String elapseTime) {
        this.elapseTime = elapseTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
