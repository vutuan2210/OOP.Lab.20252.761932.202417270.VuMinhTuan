package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.InvalidMediaException;
import hust.soict.dsai.aims.exception.LimitExceededException;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Disc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MediaStore extends JPanel{
    private Media media;
    private Cart cart;

    public MediaStore(Media media) {
        this(media, null);
    }

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + "$");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCart = new JButton("Add to cart");
        addToCart.addActionListener(e -> addMediaToCart());
        container.add(addToCart);

        if (media instanceof Playable) {
            JButton play = new JButton("Play");
            play.addActionListener(e -> playMedia());
            container.add(play);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void addMediaToCart() {
        try {
            if (cart != null) {
                cart.addMedia(media);
                JOptionPane.showMessageDialog(this, "Added to cart: " + media.getTitle());
            }
            System.out.println("Added to cart: " + media.getTitle());
            if (cart != null) {
                System.out.println("Current cart quantity: " + cart.getQuantityOrdered());
            }
        } catch (LimitExceededException | InvalidMediaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Cannot add to cart",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void playMedia() {
        if (!(media instanceof Playable)) {
            return;
        }

        try {
            ((Playable) media).play();
            JDialog dialog = new JDialog(getOwnerFrame(), "Play", true);
            JTextArea content = new JTextArea(getPlayMessage());
            content.setEditable(false);
            content.setLineWrap(true);
            content.setWrapStyleWord(true);

            dialog.add(new JScrollPane(content));
            dialog.setSize(320, 180);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        } catch (PlayerException e) {
            System.err.println(e.getMessage());
            System.err.println(e.toString());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Illegal Media Length",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private Frame getOwnerFrame() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof Frame) {
            return (Frame) window;
        }
        return null;
    }

    private String getPlayMessage() {
        StringBuilder message = new StringBuilder();
        message.append("Playing: ").append(media.getTitle());

        if (media instanceof DigitalVideoDisc) {
            DigitalVideoDisc dvd = (DigitalVideoDisc) media;
            message.append("\nDVD length: ").append(dvd.getLength());
        } else if (media instanceof CompactDisc) {
            CompactDisc cd = (CompactDisc) media;
            message.append("\nCD length: ").append(cd.getLength());
        } else if (media instanceof Disc) {
            Disc disc = (Disc) media;
            message.append("\nDisc length: ").append(disc.getLength());
        }

        return message.toString();
    }
}
