import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

abstract class Head extends JPanel {
    protected BufferedImage image;
    protected String description;
    protected static final int HEAD_X = 50;
    protected static final int HEAD_Y = 50;

    public Head(String description) {
        this.description = description;
        loadImage();
    }

    protected abstract void loadImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, HEAD_X, HEAD_Y, this);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}

class HeadOnly extends Head {
    public HeadOnly(String description) {
        super(description);
    }

    @Override
    protected void loadImage() {
        try {
            image = ImageIO.read(new File("Head.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class HeadWithEars extends HeadOnly {
    private BufferedImage leftEarImage;
    private BufferedImage rightEarImage;
    private static final int LEFT_EAR_X_OFFSET  = -60;
    private static final int LEFT_EAR_Y_OFFSET  = -40;
    private static final int RIGHT_EAR_X_OFFSET = 120;
    private static final int RIGHT_EAR_Y_OFFSET = -40;

    public HeadWithEars(String description) {
        super(description);
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        try {
            leftEarImage  = ImageIO.read(new File("left ear.png"));
            rightEarImage = ImageIO.read(new File("right ear.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (leftEarImage != null) {
            g.drawImage(leftEarImage, HEAD_X + LEFT_EAR_X_OFFSET, HEAD_Y + LEFT_EAR_Y_OFFSET, this);
        }
        if (rightEarImage != null) {
            g.drawImage(rightEarImage, HEAD_X + RIGHT_EAR_X_OFFSET, HEAD_Y + RIGHT_EAR_Y_OFFSET, this);
        }
    }
}

class HeadWithEarsAndMouth extends HeadWithEars {
    private BufferedImage mouthImage;
    private static final int MOUTH_X_OFFSET = 15;
    private static final int MOUTH_Y_OFFSET = 110;

    public HeadWithEarsAndMouth(String description) {
        super(description + "(Nose Color Randomly Added).\n");
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        try {
            mouthImage = ImageIO.read(new File("mouth.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mouthImage != null) {
            g.drawImage(mouthImage, HEAD_X + MOUTH_X_OFFSET, HEAD_Y + MOUTH_Y_OFFSET, this);
        }
    }
}

class HeadWithEarsMouthAndNose extends HeadWithEarsAndMouth {
    private BufferedImage blackNose;
    private BufferedImage redNose;
    private boolean useBlackNose;
    private static final int NOSE_X_OFFSET = 10;
    private static final int NOSE_Y_OFFSET = 100;

    public HeadWithEarsMouthAndNose(String description) {
        super(description + "Does the Mickey mouse have a red or black nose? What happened to him!.\n");
        int randomChoice = (int)(Math.random() * 2);
        useBlackNose = (randomChoice == 0);
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        try {
            blackNose = ImageIO.read(new File("black nose.png"));
            redNose   = ImageIO.read(new File("red nose.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (blackNose != null && redNose != null) {
            if (useBlackNose) {
                g.drawImage(blackNose, HEAD_X + NOSE_X_OFFSET, HEAD_Y + NOSE_Y_OFFSET, this);
            } else {
                g.drawImage(redNose, HEAD_X + NOSE_X_OFFSET, HEAD_Y + NOSE_Y_OFFSET, this);
            }
        }
    }

    @Override
    public String toString() {
        String noseColor = useBlackNose ? "Black Nose." : "Red Nose.";
        return super.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Character Head Builder");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 350);
            frame.setLayout(new BorderLayout());
            HeadWithEarsMouthAndNose finalHead = new HeadWithEarsMouthAndNose("Building the character:\n");
            frame.add(finalHead, BorderLayout.CENTER);
            JLabel descriptionLabel = new JLabel("<html>" + finalHead.toString().replace("\n", "<br>") + "</html>");
            descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            frame.add(descriptionLabel, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }
}
