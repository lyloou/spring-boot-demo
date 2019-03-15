package com.lyloou.demo.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ZkWatcherClient implements Runnable, Watcher {
    private static final Logger logger = LoggerFactory.getLogger(ZkWatcherClient.class);

    private String zkServer;
    private String watchedNode;
    //private int zkTimeout;
    private int zkWatchInterval;
    private ZooKeeper zooKeeper;

    public ZkWatcherClient(String zkServer, String watchedNode, int zkTimeout, int interval) throws IOException {
        this.zkServer = zkServer;
        this.watchedNode = watchedNode;
        //this.zkTimeout = zkTimeout;
        this.zkWatchInterval = interval;
        this.zooKeeper = new ZooKeeper(zkServer, zkTimeout, this);
    }

    public void init() {
        try {
            Thread thread = new Thread(this);
            thread.start();
        } catch (Exception e) {
            logger.error("init ZkWatcherClient, error:", e);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            String nodeValue = new String(zooKeeper.getData(watchedNode, true, null));
            logger.info("========the latest zookeeper node status is:" + nodeValue);
            updateNodeStatusToDB(nodeValue);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                logger.info("========web-zookeeper-watcher is watching...");
                zooKeeper.exists(watchedNode, this);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                logger.info("========web-zookeeper-watcher sleep...");
                Thread.sleep(zkWatchInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查状态并写DB
     *
     * @param nodeValue
     */
    public void updateNodeStatusToDB(String nodeValue) {
        //TODO 检查是否在数据库中已经存在，不要写重复数据，如果不存在就写数据库
        //TODO node节点的格式是什么样的？
        logger.info("========writing DB:" + nodeValue);
    }

    public static void main(String[] args) throws Exception {
        ZkWatcherClient client = new ZkWatcherClient("192.168.103.18:2181", "/node", 2000, 2000);
        Thread thread = new Thread(client);
        thread.start();

    }

}