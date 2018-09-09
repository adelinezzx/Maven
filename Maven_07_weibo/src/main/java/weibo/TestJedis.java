package weibo;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestJedis {

    @Test
    public void test(){
        Jedis jedis = new Jedis();
        String string = jedis.set("name","adeline");
        System.out.println(string);
        jedis.close();
    }
}
