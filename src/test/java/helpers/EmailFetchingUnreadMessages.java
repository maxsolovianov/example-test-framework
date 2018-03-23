package helpers;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.Arrays;
import java.util.Properties;

public class EmailFetchingUnreadMessages {

    private static final String PROTOCOL = "imaps";
    private static final String IMAP_HOST_NAME = "imap.googlemail.com";
    private static final String IMAP_AUTH_USER = "testexecutionuser@gmail.com";
    private static final String IMAP_AUTH_PWD  = "testexecutionuser123";
    private static final String ACCESS_FOLDER = "INBOX";

    public static boolean isPresentSubject(String stringToCompare) throws Exception {

        Boolean isPresent = null;

        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", PROTOCOL);

        Session session = Session.getDefaultInstance(props, null);

        Store store = session.getStore(PROTOCOL);
        store.connect(IMAP_HOST_NAME, IMAP_AUTH_USER, IMAP_AUTH_PWD);

        Folder inbox = store.getFolder(ACCESS_FOLDER);
        inbox.open(Folder.READ_WRITE);

        // fetching unseen messages from inbox folder
        Message messages[] = inbox.search(new FlagTerm(new Flags(
                Flags.Flag.SEEN), false));
        System.out.println("No. of Unread Messages : " + messages.length);

        // sort messages from recent to oldest
        Arrays.sort(messages, (m1, m2) -> {
            try {
                return m2.getSentDate().compareTo(m1.getSentDate());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });

        // foreach
        for (Message message : messages) {

            // replacing all empty spaces.
            if ((message.getSubject().replaceAll("\\s+", "").
                    equalsIgnoreCase(stringToCompare.replaceAll("\\s+", "")))) {
                isPresent = true;
            }
            // all marked as seen
            inbox.setFlags(messages, new Flags(Flags.Flag.SEEN), true);
        }
        return isPresent;
    }
}