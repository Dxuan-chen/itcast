package cn.itcast.jedis.test;

import cn.itcast.jedis.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis的测试类
 */
public class JedisTest {

    @Test
    public void test01(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set("username","zhangsan");
        //关闭连接
        jedis.close();
    }

    //string 数据结构操作
    @Test
    public void test02(){

        Jedis jedis = new Jedis();
        jedis.set("username","zhangsan");
        //关闭连接
        String username = jedis.get("username");
        System.out.println(username);

        //存储指定过期时间的 key value
        jedis.setex("activecode",20,"hehe");

        jedis.close();
    }

    //hash 数据结构操作
    @Test
    public void test03(){

        Jedis jedis = new Jedis();
        jedis.hset("user","name","lisi");
        jedis.hset("user","age","23");
        jedis.hset("user","gender","female");

        String name = jedis.hget("user","name");
        System.out.println(name);

        //获取hash的所有map中的数据
        Map<String,String> user = jedis.hgetAll("user");
        Set<String> keySet = user.keySet();
        for (String key : keySet) {
            String value = user.get(key);
            System.out.println(key+":"+value);
        }

        jedis.close();
    }

    //list 数据结构操作
    @Test
    public void test04(){
        Jedis jedis = new Jedis();

        jedis.lpush("mylist","a","b","c");//从左边存（c b a）
        jedis.rpush("mylist","a","b","c");

        List<String> mylist = jedis.lrange("mylist",0,-1);
        System.out.println(mylist);

        //弹出
        String element1 = jedis.lpop("mylist");
        System.out.println(element1);

        String element2 = jedis.rpop("mylist");
        System.out.println(element2);

        List<String> mylist2 = jedis.lrange("mylist",0,-1);
        System.out.println(mylist2);

        jedis.close();
    }

    //set 数据结构操作
    @Test
    public void test05(){
        Jedis jedis = new Jedis();

        jedis.sadd("myset","java","php","c++");

        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);

        jedis.close();
    }

    //sortedset 数据结构操作
    @Test
    public void test06(){
        Jedis jedis = new Jedis();

        //key socre value
        jedis.zadd("mysortedset",3,"亚瑟");
        jedis.zadd("mysortedset",60,"后羿");
        jedis.zadd("mysortedset",50,"孙悟空");

        Set<String> mysortedset = jedis.zrange("mysortedset",0,-1);
        System.out.println(mysortedset);

        jedis.close();
    }

    //jedis连接池使用
    @Test
    public void test07(){
        //0.创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);

        //1.创建Jedis连接池对象
        JedisPool jedispool = new JedisPool();

        //2.获取连接
        Jedis jedis = jedispool.getResource();

        //3.使用
        jedis.set("hehe","heihei");

        //4.关闭 归还到连接池中
        jedis.close();

    }

    @Test
    public void test08(){
        //通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();



        //3. 使用
        jedis.set("hello","world");


        //4. 关闭 归还到连接池中
        jedis.close();;
    }


}
