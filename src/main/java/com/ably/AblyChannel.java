package com.ably;

import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.Message;
import org.springframework.stereotype.Component;

@Component
public class AblyChannel {

    public void subscribe() {
        AblyRealtime ably = null;
        try {
            ably = new AblyRealtime("skQ_GQ.vCEJqQ:99LYsyIYoJ4sadj4");
            Channel channel = ably.channels.get("test");
            channel.subscribe(new Channel.MessageListener() {
                @Override
                public void onMessage(Message message) {
                    System.out.println("Received `" + message.name + "` message with data: " + message.data);
                }
            });
        } catch (AblyException e) {
            e.printStackTrace();
        }

    }
}
