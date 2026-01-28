import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
Add to the “Types used in this code” comment a note for every type
(class/record/enum/interface) referred to in the code. For each
type, state...

4.1. Which package that type/class belongs to.
4.2. A brief description of the purpose of that type.
Types used in this code:

InputStream belongs to the .io package, and deals with a raw stream of bytes.
IOException belongs to the .io package, deals with known exceptions that may occur within the input process.
JOptionpane belongs to the .javex.swing package, and is used to pop up a dialog box containing options.
InterruptedException belongs to the java.lang package, and
String belongs to the java.lang package. It is used to display strings of characters.
Math belongs to java.lang package, it contains methods related to mathematical operations.
URI belongs to java.net package.URI, it's used to create and parse Uniform Resource Identifiers
HTTPRequest belongs to java.net.http package, used to format http requests.
HTTPResponse belongs to java.net.http package, contains methods pertaining to the header/body/status/HTTPRequest of a response
recieved via HTTP.
BodyHandlers belongs to java.net.http package, and are used specifically in dealing with the body of an HTTPResponse.
Jframe also belongs to the .javex.swing package and is used to display content in a frame.
Colour belongs to the java.awt package and is used to change the colour and opacity of graphical components.
Image belongs to the java.awt package and is an "abstract superclass" representing images.
IMageIO belongs to javex.imageio package and contains methods for encoding/decoding images and locating image readers and writers.
JLabel belongs to .javex.swing.JLabel package and is used for displaying images
ImageIcon belongs to .javex.swing.ImageIcon
BorderLayout belongs to .java.awt package, and is used for altering the border of a graphical component.

 */

void main() {
    try {
        var avatarStream = getRandomAvatarStream();
        showAvatar(avatarStream); //the argument passed is a reference
    } catch (IOException | InterruptedException e) {
        JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //.showmessagedialog is a class method. .getmessage is an instance method. //error message is a class variable
    }
}
    //METHOD CALL TO GETRANDOMAVATARSTREAM(): picks random number, calls API using random number, returns body of the API response as an InputStream
    //METHOD CALL TO SHOWAVATAR: instantiates a Jframe object, populates it with the PNG from the API response, displays it. no return value.
    //CALL TO SHOWMESSAGEDIALOG: displays a message.
    // CALL TO .GETMESSAGE(): returns a message associated with the particular error e.

InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    // Pick a random style
    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
    var style = styles[(int)(Math.random() * styles.length)];
    //.random() is a class method. It generates a "random" positive double between 0-1.
    // .length is an instance variable.
    // Generate a random seed
    var seed = (int)(Math.random() * 10000); //.random() is a call to an class method

    //CALL TO .RANDOM (): returns another random double.

    // Create an HTTP request for a random avatar
    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed)); //.create is a class method .formatted is an instance method.
    var request = HttpRequest.newBuilder(uri).build(); //.newbuilder is a class method, build() is an instance method

    //METHOD CALL TO .CREATE(): Creates a URI, or string of characters used to ID a resource in this case the avatar
    //METHOD CALL TO .FORMATTED(): formats the request string as per the args passed.
    //METHOD CALL TO .NEWBUILDER(): creates an HTTPS builder object, using the uri created,
    // .BUILD(): builds and returns HTTPS request obj

    try (var client = HttpClient.newHttpClient()) { //.newHttpClient() is a class method
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream()); //send is an instance method, .bodyhandlers (??) is a class variable. .ofinput steam is a class method.
        return response.body(); //.body is an  instance variable.
    }
}
    //METHOD CALL TO .NEW HTTPCLIENT(): returns HTTP client- this is the component that recieves the HTTP response.
    //METHOD CALL TO .SEND(): sends the formatted request using the client.
    //METHOD CALL TO .OFINPUTSTREAM : returns ('a streaming body handler').

void showAvatar(InputStream imageStream) {
    JFrame frame = new JFrame("PNG Viewer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //instance method, /class method
    frame.setResizable(false); //instance method
    frame.setSize(200, 200); //instance method
    frame.getContentPane().setBackground(Color.BLACK); //all instance methods, then .BLACK is class variable.

    //CALL to JFRAME() constructor: creates new Jframe titled 'PNG viewer'.
    //CALL TO SETDEFAULTCLOSEOPERATION: sets behaviour for when user closes jframe, in this case to exit program.
    //CALL TO SETRESIZABLE: prevents user from resizing frame.
    //CALL TO SETSIZE: sets width/height of jFrame.
    //CALL TO GETCONTENTPANE : returns content pane for Jframe
    //CALL TO SETBACKGROUNDCOLOUR: sets background colour of Pane to black.

    try {
        // Load the PNG image
        Image image = ImageIO.read(imageStream); //.read is class metgod

        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        frame.add(imageLabel, BorderLayout.CENTER); //instance method, class variable/constant?

    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);  //class method, instance method, class var
    }

    frame.setVisible(true); //instance method
}
    //CALL TO READ : interprets the InputStream recieved to create the avatar image.
    //CALL TO JLABEL() : constructs new JLabel
    //CALL TO IMAGEICON(): constructs new ImageIcon using the avatar image
    //CALL TO .ADD(): adds the jlabel with image icon to the Frame)
    //CALL TO .SHOWMESSAGEDIALOG() : shows dialog (in this case error message that occurs if img was not successfully loaded)
    //CALL TO .GETMESSAGE() : returns msg associated with error.
    //CALL TO .SETVISIBLE: makes the frame visible!!!