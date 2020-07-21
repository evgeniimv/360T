package entity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class MultiPlayer implements Runnable {
    private final static byte COUNTER_LIMIT = 10;
    private final static Queue<String> mailBox = new LinkedList<String>();
    private byte messageSentCounter = 0;
    private byte messageReceivedCounter = 0;

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (getMessageSentCounter() < COUNTER_LIMIT) {
                    synchronized (mailBox) {
                        mailBox.add("someMessage" + getMessageSentCounter());
                    }
                    this.messageSentCounter++;
                    System.out.println("I sent new message: " + Thread.currentThread().getName() + ". Total sent: " + getMessageSentCounter());
                    TimeUnit.MILLISECONDS.sleep(500);
                }
                if (getMessageReceivedCounter() < COUNTER_LIMIT) {
                    final String message;
                    synchronized (mailBox) {
                        message = mailBox.poll();
                    }
                    if (message == null) {
                        TimeUnit.SECONDS.sleep(2);
                        continue;
                    }
                    this.messageReceivedCounter++;
                    System.out.println("I took new message: " + message + ". Thread: " + Thread.currentThread().getName()
                            + ". Total received: " + getMessageReceivedCounter());
                    TimeUnit.MILLISECONDS.sleep(500);
                }
                if (getMessageSentCounter() >= COUNTER_LIMIT && getMessageReceivedCounter() >= COUNTER_LIMIT) {
                    System.out.println("I reached the limits:" + Thread.currentThread().getName()
                            + ". Total sent: " + getMessageSentCounter() + ". Total received: " + getMessageReceivedCounter());
                    Thread.currentThread().interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Return current <code>messageSentCounter</code> value
     *
     * @return a <code>byte</code> value
     */
    public byte getMessageSentCounter() {
        return this.messageSentCounter;
    }

    /**
     * Return current <code>messageReceivedCounter</code> value
     *
     * @return a <code>byte</code> value
     */
    public byte getMessageReceivedCounter() {
        return this.messageReceivedCounter;
    }
}
