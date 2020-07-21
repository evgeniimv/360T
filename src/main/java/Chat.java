import entity.MultiPlayer;
import entity.Player;
import util.PrintLogoUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The <code>Chat</code> class is a simple realisation
 * for chat-like applications.
 * The main responsibility of this <code>entity.Player</code>
 * is sending messages for another speakers
 *
 * @author Evgenii Mukhamadiarov
 * @since 07/21/2020
 */
public class Chat {

    public static void main(String[] args) throws InterruptedException {
        final Chat chat = new Chat();

        System.out.println("Lets talk in pair!");
        chat.letsDialogChat();

        TimeUnit.SECONDS.sleep(4);

        System.out.println("Now lets talk in groups");
        chat.lestGroupChat(5);
    }

    /**
     * Starts a dialog between two speakers till the initiator
     * reached the limits - 10 messages sent and 10 messages received.
     */
    private void letsDialogChat() {
        final Player initiator = new Player();
        final Player secondPlayer = new Player();
        String response = "message";

        while (initiator.getMessageSentCounter() < Player.COUNTER_LIMIT || initiator.getMessageReceivedCounter() < Player.COUNTER_LIMIT) {
            response = initiator.sendMessage(response, secondPlayer);
            System.out.println("Response from second actor: " + response);
            response = secondPlayer.sendMessage(response, initiator);
            System.out.println("Response from initiator: " + response);
            System.out.println("Initiator sent - " + initiator.getMessageSentCounter() + " and received - "
                    + initiator.getMessageReceivedCounter() + " message(s)");
        }
        PrintLogoUtils.printGracefulFinalisationLogo();
    }

    private void lestGroupChat(int numberOfParticipants) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(numberOfParticipants);

        for (int i = 0; i < numberOfParticipants; i++) {
            final MultiPlayer speaker = new MultiPlayer();
            executorService.submit(speaker);
        }
        ThreadPoolExecutor pool = (ThreadPoolExecutor) executorService;
        while (pool.getActiveCount() > 0) {
            System.out.println("Waiting to close threadPool, active threads: " + pool.getActiveCount());
            TimeUnit.SECONDS.sleep(5);
        }

        System.out.println("Finishing the group chat. Current active speakers: " + pool.getActiveCount());
        executorService.shutdown();
    }
}
