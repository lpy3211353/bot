package com.lpy;

import com.lpy.util.RedisUtil;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotOnlineEvent;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public final class QiaoFuBot extends JavaPlugin {
    public static final QiaoFuBot INSTANCE = new QiaoFuBot();
    private static final RedisUtil redis = RedisUtil.getInstance();

    private QiaoFuBot() {
        super(new JvmPluginDescriptionBuilder("com.lpt.lpt-bot", "0.1.0")
                .name("QiaoFu Bot")
                .author("liangpengyu")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("QiaoFu bot!");

        GlobalEventChannel.INSTANCE.subscribeAlways(BotOnlineEvent.class, event ->
                Objects.requireNonNull(Bot.getInstance(2652446227L).getFriend(931731014)).sendMessage("鞘小伏已登陆"));

        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, event -> {
            if (event.getSender().getId() == 931731014) {
                event.getSender().sendMessage(System.getProperty("user.dir"));
                try {
                    Image image = ExternalResource.uploadAsImage(new File("img/bleach.png"), event.getSubject());
                    event.getSubject().sendMessage(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
            if (event.getGroup().getId() == 476597934) {
                if (event.getSender().getId() == 1049943339) {
                    if ("撒泡泡".equals(event.getMessage().contentToString().trim())) {
                        event.getGroup().sendMessage("撒毒毒");
                    }
                }
            }
        });

        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
            if (check(event.getSender().getId())) {
                return;
            }
            if (event.getGroup().getId() == 567422687) {
                if (event.getSender().getId() == 497192388) {
                    redis.set(String.valueOf(497192388), "1", 60);
                    event.getGroup().sendMessage("铸币lzl");
                }
                if (event.getSender().getId() == 970817283) {
                    redis.set(String.valueOf(970817283), "1", 60);
                    event.getGroup().sendMessage("铸币粘结");
                }
            }
        });

        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {

            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
            String str = simpleDateFormat.format(date);
            int hour = Integer.parseInt(str);

            if (event.getGroup().getId() == 683756526) {
                if (check(event.getGroup().getId())) {
                    return;
                }
                redis.set(String.valueOf(event.getGroup().getId()), "1", 600);
                if (hour >= 0 && hour <= 6) {
                    event.getGroup().sendMessage("夜深了，想那老师了");
                }
                if (hour > 6 && hour <= 12) {
                    event.getGroup().sendMessage("太阳晒屁股了，想马老师了");
                }
                if (hour > 12 && hour <= 17) {
                    event.getGroup().sendMessage("天亮了，想黄老师了");
                }
                if (hour == 18) {
                    event.getGroup().sendMessage("落日了，想那老师了");
                }
                if (hour > 18) {
                    event.getGroup().sendMessage("晚上了，想那老师了");
                }
            }
        });

    }

    private boolean check(String id) {
        return redis.isNotNull(id);
    }


    private boolean check(long id) {
        return redis.isNotNull(id);
    }
}