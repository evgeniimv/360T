package entity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * The <code>MultiPlayer</code> class is a simple representation
 * of multi-player speaker.
 * The main goal of speaker is participate in a group conversation. Each
 * speaker has limits - 10 messages to send and 10 to receive. When speaker reaches
 * the limits, he leaves the conversation.
 *
 * @author Evgenii Mukhamadiarov
 * @since 07/21/2020
 */
public class MultiPlayer implements Runnable {
    private final static byte COUNTER_LIMIT = 10;
    private final static Queue<String> mailBox = new LinkedList<String>();
    private byte messageSentCounter;
    private byte messageReceivedCounter;

    /**
     * Entry point into the group chat. When method runs, speaker starts
     * taking messages from the mailbox and posting new messages back to it.
     * When speaker has sent and received 10 messages, he leaves the conversation.
     */
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (this.messageSentCounter < COUNTER_LIMIT) {
                    synchronized (mailBox) {
                        mailBox.add("someMessage" + this.messageSentCounter);
                    }
                    this.messageSentCounter++;
                    System.out.println("I sent new message: " + Thread.currentThread().getName() + ". Total sent: " + this.messageSentCounter);
                    TimeUnit.MILLISECONDS.sleep(500);
                }
                if (this.messageReceivedCounter < COUNTER_LIMIT) {
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
                            + ". Total received: " + this.messageReceivedCounter);
                    TimeUnit.MILLISECONDS.sleep(500);
                }
                if (this.messageSentCounter >= COUNTER_LIMIT && this.messageReceivedCounter >= COUNTER_LIMIT) {
                    System.out.println("I reached the limits:" + Thread.currentThread().getName()
                            + ". Total sent: " + this.messageSentCounter + ". Total received: " + this.messageReceivedCounter);
                    Thread.currentThread().interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
