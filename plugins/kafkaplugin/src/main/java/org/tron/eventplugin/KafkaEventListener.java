package org.tron.eventplugin;

import org.pf4j.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tron.common.logsfilter.IPluginEventListener;
import java.util.Objects;

@Extension
public class KafkaEventListener implements IPluginEventListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaEventListener.class);

    @Override
    public void setServerAddress(String address) {

        if (Objects.isNull(address) || address.length() == 0){
            return;
        }

        MessageSenderImpl.getInstance().setServerAddress(address);

        // MessageSenderImpl should never init until server address is set
        MessageSenderImpl.getInstance().init();
    }

    @Override
    public void setTopic(int eventType, String topic) {
        MessageSenderImpl.getInstance().setTopic(eventType, topic);
    }

    @Override
    public void handleBlockEvent(Object data) {

        if (Objects.isNull(data)){
            return;
        }

        MessageSenderImpl.getInstance().getTriggerQueue().offer((String)data);
    }

    @Override
    public void handleTransactionTrigger(Object data) {
        if (Objects.isNull(data)){
            return;
        }

        MessageSenderImpl.getInstance().getTriggerQueue().offer((String)data);
    }

    @Override
    public void handleContractLogTrigger(Object data) {
        if (Objects.isNull(data)){
            return;
        }

        MessageSenderImpl.getInstance().getTriggerQueue().offer((String)data);
    }

    @Override
    public void handleContractEventTrigger(Object data) {
        if (Objects.isNull(data)){
            return;
        }

        MessageSenderImpl.getInstance().getTriggerQueue().offer((String)data);
    }
}