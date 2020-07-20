public class Chat {
    public static void main(String[] args) {
        new Chat().letsChat();
    }

    private void letsChat() {
        final Player firstPlayer = new Player();
        final Player secondPlayer = new Player();
        String response = "message";

        while (firstPlayer.getMessageSentCounter() < Player.COUNTER_LIMIT || firstPlayer.getMessageReceivedCounter() < Player.COUNTER_LIMIT) {
            response = firstPlayer.sendMessage(response, secondPlayer);
            response = secondPlayer.sendMessage(response, firstPlayer);
        }
    }

}
