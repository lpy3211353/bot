package com.lpy;

import com.lpy.util.RedisUtil;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;

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
        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, event -> {
            if (event.getSender().getId() == 931731014) {
                event.getSender().sendMessage("测试");
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
    }

    private boolean check(String id) {
        return redis.isNotNull(id);
    }

    private boolean check(long id) {
        return redis.isNotNull(id);
    }
}