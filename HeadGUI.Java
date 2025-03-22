// import javax.swing.*;
// import java.awt.*;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;
// import javax.imageio.ImageIO;


// abstract class Head extends JPanel {

//     protected BufferedImage image;
//     protected String description;

//     public HeadPart(String description) {
//         this.description = description;
//         loadImage();  // Force each subclass to load its specific image
//     }

//     // Subclasses MUST implement this to load their required image(s).
//     protected abstract void loadImage();

//     // This draws the current part's image at (0,0).
//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         if (image != null) {
//             g.drawImage(image, 0, 0, this);
//         }
//     }

//     // Returns the cumulative description string.
//     @Override
//     public String toString() {
//         return description;
//     }
// }

// /**
//  * 1) HeadOnly class: loads the base head image.
//  */
// class HeadOnly extends HeadPart {

//     public HeadOnly(String description) {
//         super(description + "1) Base head added.\n");
//     }

//     @Override
//     protected void loadImage() {
//         try {
//             // Load the base head
//             image = ImageIO.read(new File("C:\\Users\\acbea\\OneDrive\\Desktop\\Java\Project 3\\project-3-gui-inheritance-project-EricLi168Ita\\New Project (3)\\Head.png"));
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }

// /**
//  * 2) HeadWithEars extends HeadOnly:
//  *    Adds left ear & right ear on top of the base head.
//  */
// class HeadWithEars extends HeadOnly {

//     private BufferedImage leftEarImage;
//     private BufferedImage rightEarImage;

//     public HeadWithEars(String description) {
//         super(description + "2) Left + Right ears added.\n");
//     }

//     @Override
//     protected void loadImage() {
//         // First, load the parent image (just the head).
//         super.loadImage();

//         // Then load the ears.
//         try {
//             leftEarImage  = ImageIO.read(new File("left ear.png"));
//             rightEarImage = ImageIO.read(new File("right ear.png"));
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Draw the head (super) plus both ears on top.
//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         if (leftEarImage != null && rightEarImage != null) {
//             g.drawImage(leftEarImage, 0, 0, this);
//             g.drawImage(rightEarImage, 0, 0, this);
//         }
//     }
// }

// /**
//  * 3) HeadWithEarsAndMouth extends HeadWithEars:
//  *    Adds the mouth on top of the ears + head.
//  */
// class HeadWithEarsAndMouth extends HeadWithEars {

//     private BufferedImage mouthImage;

//     public HeadWithEarsAndMouth(String description) {
//         super(description + "3) Mouth added.\n");
//     }

//     @Override
//     protected void loadImage() {
//         // Load parent images (head + ears).
//         super.loadImage();
//         // Then load the mouth.
//         try {
//             mouthImage = ImageIO.read(new File("mouth.png"));
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Draw everything from the parent, plus the mouth.
//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         if (mouthImage != null) {
//             g.drawImage(mouthImage, 0, 0, this);
//         }
//     }
// }

// /**
//  * 4) HeadWithEarsMouthAndNose extends HeadWithEarsAndMouth:
//  *    Adds a nose, randomly black or red.
//  */
// class HeadWithEarsMouthAndNose extends HeadWithEarsAndMouth {

//     private BufferedImage blackNose;
//     private BufferedImage redNose;
//     private boolean useBlackNose; // random choice

//     public HeadWithEarsMouthAndNose(String description) {
//         super(description + "4) Nose added (random).\n");

//         // Randomly decide which nose to use
//         int randomChoice = (int)(Math.random() * 2); // 0 or 1
//         useBlackNose = (randomChoice == 0);
//     }

//     @Override
//     protected void loadImage() {
//         // Load parent images (head + ears + mouth).
//         super.loadImage();

//         // Load both possible nose images
//         try {
//             blackNose = ImageIO.read(new File("black nose.png"));
//             redNose   = ImageIO.read(new File("red nose.png"));
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Draw the chosen nose last, on top of everything else
//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         if (blackNose != null && redNose != null) {
//             if (useBlackNose) {
//                 g.drawImage(blackNose, 0, 0, this);
//             } else {
//                 g.drawImage(redNose, 0, 0, this);
//             }
//         }
//     }

//     // Override toString to indicate which nose was chosen
//     @Override
//     public String toString() {
//         String noseColor = useBlackNose ? "Black Nose." : "Red Nose.";
//         return super.toString() 
//                + "   => Nose Color: " + noseColor + "\n";
//     }
// }

// /**
//  * Main GUI class to display the final assembled character.
//  */
// public class HeadGUI {

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             JFrame frame = new JFrame("Character Head Builder");
//             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//             frame.setSize(300, 350);
//             frame.setLayout(new BorderLayout());

//             // Build the final object (head + ears + mouth + random nose)
//             HeadWithEarsMouthAndNose finalHead = 
//                 new HeadWithEarsMouthAndNose("Building the character:\n");

//             // Show the image in the center
//             frame.add(finalHead, BorderLayout.CENTER);

//             // Show the description in a label at the bottom
//             JLabel descriptionLabel = new JLabel("<html>"
//                   + finalHead.toString().replace("\n", "<br>")
//                   + "</html>");
//             descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
//             frame.add(descriptionLabel, BorderLayout.SOUTH);

//             frame.setVisible(true);
//         });
//     }
// }
