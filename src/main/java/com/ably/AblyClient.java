package com.ably;

import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.CompletionListener;
import io.ably.lib.realtime.ConnectionStateListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ErrorInfo;
import org.springframework.stereotype.Component;

@Component
public class AblyClient {

    public void create() {
        try {
            AblyRealtime ably = new AblyRealtime("skQ_GQ.vCEJqQ:99LYsyIYoJ4sadj4");
            final Channel channel = ably.channels.get("test");
            ably.connection.on(new ConnectionStateListener() {
                @Override
                public void onConnectionStateChanged(ConnectionStateChange state) {
                    System.out.println("New state is " + state.current.name());

                    switch (state.current) {
                        case connected: {

                            try {
                                channel.publish("greeting", "Hello World!", new CompletionListener() {
                                    @Override
                                    public void onSuccess() {
                                        System.out.println("Message successfully sent");
                                    }

                                    @Override
                                    public void onError(ErrorInfo reason) {
                                        System.err.println("Unable to publish message; err = " + reason.message);
                                    }
                                });
                            } catch (AblyException e) {
                                e.printStackTrace();
                            }


                            System.out.println("connected");
                            break;
                        }
                        case failed: {
                            System.out.println("Connection failed");
                            break;
                        }
                    }
                }
            });


            /*ably.connection.on(ConnectionState.connected, new ConnectionStateListener() {
                @Override
                public void onConnectionStateChanged(ConnectionStateChange state) {
                }
            });*/

        } catch (AblyException e) {
            e.printStackTrace();
        }
    }
}
