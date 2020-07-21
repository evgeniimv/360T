package entity;

import service.MessageService;

/**
 * The <code>Player</code> class is a simple entity
 * for chat-like applications.
 * The main responsibility of this <code>entity.Player</code>
 * is sending messages for another speakers
 *
 * @author Evgenii Mukhamadiarov
 * @since 07/21/2020
 */
public class Player implements MessageService {
    public final static int COUNTER_LIMIT = 10;
    private int messageSentCounter = 0;
    private int messageReceivedCounter = 0;

    /**
     * Sends message to <code>recipient</code> and replies
     * with combined <code>sentMessage</code> and recipient <code>messageSentCounter</code>
     *
     * @param sentMessage a <code>String</code> value
     * @param recipient   a <code>String</code> value
     * @return a <code>String</code> value
     */
    public String sendMessage(String sentMessage, Player recipient) {
        this.messageSentCounter++;
        recipient.messageReceivedCounter++;
        return sentMessage + " " + recipient.messageSentCounter;
    }

    /**
     * Return current <code>messageSentCounter</code> value
     *
     * @return a <code>int</code> value
     */
    public int getMessageSentCounter() {
        return this.messageSentCounter;
    }

    /**
     * Return current <code>messageReceivedCounter</code> value
     *
     * @return a <code>int</code> value
     */
    public int getMessageReceivedCounter() {
        return this.messageReceivedCounter;
    }
}
