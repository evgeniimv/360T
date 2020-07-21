package service;

import entity.Player;

/**
 * The <code>MessageService</code> interface is a simple
 * set of general functions to manage messages
 *
 * @author Evgenii Mukhamadiarov
 * @since 07/21/2020
 */
public interface MessageService {

    /**
     * Sends message to recipient and returns <code>String</code>
     * reply from recipient
     *
     * @param sentMessage a <code>String</code> value
     * @param recipient   a <code>String</code> value
     * @return a <code>String</code> value
     */
    String sendMessage(String sentMessage, Player recipient);
}
