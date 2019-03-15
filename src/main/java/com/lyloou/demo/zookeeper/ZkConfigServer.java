package com.lyloou.demo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Desc:
 * Author:sunguoli
 * Date:15/10/6
 * Time:下午2:04
 */
public class ZkConfigServer implements Watcher {
    ZooKeeper zkServer = null;
    String zkNode;

    ZkConfigServer(String zkServer, String zkNode, int zkTimeout) {
        this.zkNode = zkNode;
        try {
            this.zkServer = new ZooKeeper(zkServer, zkTimeout, this);
            Stat st = this.zkServer.exists(zkNode, true);
            if (st == null) {
                this.zkServer.create(zkNode, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.zkServer = null;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void updateConfig(String str) {
        try {
            Stat s = this.zkServer.exists(this.zkNode, true);
            this.zkServer.setData(this.zkNode, str.getBytes(), s.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.toString());
        try {
            this.zkServer.exists(zkNode, true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ZkConfigServer configServer = new ZkConfigServer("localhost:2181", "/node", 2000);
        configServer.updateConfig("haha");
    }

}