package com.qf.rules;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

public class CustomerRule extends AbstractLoadBalancerRule {


    private int limit = 2; // 5的意思就是每个服务调用五次

    private int num = 0; //表示当前服务调用的次数

    private Server currentServer;  //当前调用的服务

    /***
     * 服务消费具体调用哪个服务取决于该方法返回哪个服务。
     */
    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    public Server choose(ILoadBalancer lb, Object key) {
        // 返回所有的服务，包括可用和不可用的
//        List<Server> allServers = lb.getAllServers();

        // 返回可用的服务
        List<Server> reachableServers = lb.getReachableServers();

        // 首次调用时候，currentServer为空
        if(null == currentServer && null != reachableServers && reachableServers.size() > 0) {
            currentServer = reachableServers.get(0);  //将第一个服务赋值给当前服务
            num++; // 既然返回，肯定是要调用，次数加1
            return currentServer;
        }

        int size = reachableServers.size(); // 可用服务的个数

        for(int i = 0; i < size; i++) {
            Server server = reachableServers.get(i); // 遍历的每个值

            if(currentServer.getId().equals(server.getId())) { //
                /**
                 *如果当前服务被调用的次数已经达到五次, 就要使用下一个服务:
                 *   1. list集合有下一个
                 *   2. list没有下一个，就回到第一个
                 */
                if(num >= limit) {
                    num = 0;  //超过次数，首先要清零
                    // 获取下一个

                    // list.get() 该方法如果超过索引会出现异常
                    // server = reachableServers.get(++i);

                    // 表示已经到达最后一个服务
                    if(i == (size - 1)){
                        currentServer = reachableServers.get(0);
                        num++; //返回之前要加一次
                        return currentServer;
                    }else{
                        currentServer = reachableServers.get(++i);
                        num++;
                        return currentServer;
                    }
                }else{  //表示没有达到五次
                    num++;
                    return currentServer;
                }
            }
        }
        return null;
    }

    //
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(23);
        list.add(34);
        System.out.println(list.get(3));
    }
}
