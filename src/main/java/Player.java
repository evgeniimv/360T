public class Player {
    public final static byte COUNTER_LIMIT = 10;
    private byte messageSentCounter = 0;
    private byte messageReceivedCounter = 0;


    public byte getMessageSentCounter() {
        return this.messageSentCounter;
    }

    public byte getMessageReceivedCounter() {
        return this.messageReceivedCounter;
    }

    public String sendMessage(String sentMessage, Player recipient) {
        recipient.messageReceivedCounter++;
        return sentMessage + ++messageSentCounter;
    }
}
