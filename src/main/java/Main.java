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

classes:
    InputStream belongs to the .io package, and deals with a raw stream of bytes from a source.
    IOException belongs to the .io package, deals with known exceptions that may occur within the input process.
    JOptionpane belongs to the .javex.swing package, and is used to pop up a dialog box containing options.
    InterruptedException belongs to the java.lang package, and is thrown when a 'thread' is interrupted in some way
    String belongs to the java.lang package. It is used to display strings of characters.
    Math belongs to java.lang package, it contains methods related to mathematical operations.
    URI belongs to java.net package.URI, it's used to create and parse Uniform Resource Identifiers
    HTTPRequest belongs to java.net.http package, used to format http requests.
    HTTPClient belongs java.net.http package, it provides a starting point from which HTTP responses may be sent and and corresponding requests, recieved.
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

interfaces:
    WindowConstants, which contains integer constants, is accessed, it sets the default close behaviour of a window.


6- PACKAGES USED IN CODE

For each package used in the code, give a brief description of the purpose of that package and the kinds of classes
that are defined in it

.imageio: contains classes and interfaces for accessing i/o API.
Classes defined in this package:
ImageReader, ImageWriter, ImageTranscoder

.ImageIO: contains static 'convenience' methods and interfaces for encoding/decoding image input and output.
Classes defined in this package:

.io: used for handling data input and output operations. Includes methods relating to file handling and data streams.
Classes defined in this package:

.javax.swing: contains components used to create GUIs.
Classes defined in this package:
JFrame, JLabel, and ImageIcon

.java.awt:  Abstract Window Toolkit which is also used to create GUIs.
Classes defined in this package:
Frame, Button, Label

.java.net: used for implementing networking applications and performing API calls.
Classes defined in this package:
.URI, .HTTP classes (Client, Requests, Response), Sockets, Network Interfaces

.java.net.http: specific package used to recieve and send HTTP requests.
contains the classes HTTPCLient, HTTPRequests, HTTPResponse

.java.net.URI: contains methods related to constructing and implementing URIs.

 */

void main() {
    try {
        var avatarStream = getRandomAvatarStream();
        showAvatar(avatarStream); //the argument passed is a reference
    } catch (IOException | InterruptedException e) {
        JOptionPane.showMessageDialog(null, "Failed to load avatar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    //class vs instance categorization
    //.showmessagedialog is a class method.
    // .getmessage is an instance method.
    // error message is a class variable

    //METHOD CALL TO GETRANDOMAVATARSTREAM(): picks random number, calls API using random number, returns body of the API response as an InputStream
    //METHOD CALL TO SHOWAVATAR: instantiates a Jframe object, populates it with the PNG from the API response, displays it. no return value.
    //METHOD CALL TO SHOWMESSAGEDIALOG: displays a message.
    //METHOD CALL TO .GETMESSAGE(): returns a message associated with the particular error e.

    //variable avatarStream: type: InputStream, reference to particular instance of InputStream


InputStream getRandomAvatarStream() throws IOException, InterruptedException {
    // Pick a random style
    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
    var style = styles[(int)(Math.random() * styles.length)];
    //.random() is a class method. It generates a "random" positive double between 0-1.
    // .length is an instance variable.

    var seed = (int)(Math.random() * 10000); //.random() is a call to an class method


    //variable styles: type: Array (of Strings), variable is reference to areas in memory containing the array's bytes.
    //variable style: type: String, reference to an index range within the styles Array which contains a particular String object.
    //variable seed: type: integer, which is a primitive data type.

    //CALL TO .RANDOM (): returns another random double.

    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
    var request = HttpRequest.newBuilder(uri).build(); //.newbuilder is a class method, build() is an instance method

    //.create is a class method
    //.formatted is an instance method.
    //.newbuilder is a class method
    // build() is an instance method

    //METHOD CALL TO .CREATE(): Creates a URI, or string of characters used to ID a resource in this case the avatar
    //METHOD CALL TO .FORMATTED(): formats the request string as per the args passed.
    //METHOD CALL TO .NEWBUILDER(): creates an HTTP builder object, using the URI created,
    // .BUILD(): builds and returns HTTP request object

    //variable uri: type: URI - object
    //variable request: type: HTTPrequest - object

    try (var client = HttpClient.newHttpClient()) {
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        return response.body(); //.body is an  instance variable.
    }
}

    //.newHttpClient() is a class method
    //send is an instance method,
    // .bodyhandlers (??) is a class variable.
    // .ofinput steam is a class method.

    //METHOD CALL TO .NEW HTTPCLIENT(): returns HTTP client- this is the component that recieves the HTTP response.
    //METHOD CALL TO .SEND(): sends the formatted request using the client.
    //METHOD CALL TO .OFINPUTSTREAM : returns ('a streaming body handler').

    //variable client = HTTPClient - instance of HTTPClient object
    //variable response = instance of HTTPResponse as an InputStream object, so reference.

void showAvatar(InputStream imageStream) {
    JFrame frame = new JFrame("PNG Viewer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setSize(200, 200);
    frame.getContentPane().setBackground(Color.BLACK);

//class VS instance methods and variables

    //setDefaultCloseOperation : instance method
    //setResizable : instance method
    //setSize : instance method
    //getContentPane : instance method
    //setBackgroundCOlour : instance method
    // .BLACK : class variable (constant) within the class Colour

//METHOD CALLS
    //CALL to JFRAME() constructor: creates new Jframe titled 'PNG viewer'.
    //CALL TO SETDEFAULTCLOSEOPERATION: sets behaviour for when user closes jframe, in this case to exit program.
    //CALL TO SETRESIZABLE: prevents user from resizing frame.
    //CALL TO SETSIZE: sets width/height of jFrame.
    //CALL TO GETCONTENTPANE : returns content pane for Jframe
    //CALL TO SETBACKGROUNDCOLOUR: sets background colour of Pane to black.

    //variable frame = type: JFrame, reference to new Jframe object.

    try {
        // Load the PNG image
        Image image = ImageIO.read(imageStream);

        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        frame.add(imageLabel, BorderLayout.CENTER);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);  //class method, instance method, class var
    }

    frame.setVisible(true);
}
//class VS instance methods
    //.read is class method
    //CONSTRUCTORS???
    //.add is instance method
    //.CENTER is class variable/constant
    //.showmessagedialog is instance method.
    //.getmessage is instance method

//METHOD CALLS
    //CALL TO READ : interprets the InputStream recieved to create the avatar image.
    //CALL TO JLABEL() : constructs new JLabel
    //CALL TO IMAGEICON(): constructs new ImageIcon using the avatar image
    //CALL TO .ADD(): adds the jlabel with image icon to the Frame)
    //CALL TO .SHOWMESSAGEDIALOG() : shows dialogue (in this case error message that occurs if img was not successfully loaded)
    //CALL TO .GETMESSAGE() : returns msg associated with error.
    //CALL TO .SETVISIBLE: makes the frame visible!!!

    //variable image: as the name implies, it is a reference to an instance of an Image object. Its class/type is Image!
    //variable imageLabel: type: Jlabel, object instance.
    //variable e : reference to specific IOException instance.

    //ARGUMENTS USED TO CALL SHOWAVATAR
    //ShowAvatar is only called once, using the argument AvatarStream which is a reference to an InputStream of bytes.
    // But I think maybe you were meaning the arguments passed to the functions within the ShowAvatar function definition:
    // 1) setDefaultCloseOperation : constant integer EXIT_ON_CLOSE (value of 3). This is a primitive data type.
    // 2) setResizable : boolean false (primitive).
    // 3) setSize : two integers (200 and 200) - primitives.
    // 4) setBackgroundCOlour: reference to a Colour object.
    // 5) .read : reference to an ImageStream.

    // 8) .add : reference to Jlabel imageLabel, constant string object (via reference) Borderlayout.center
    // 9) .showMessageDialog : reference to Jframe object, raw string object, result of function call on specific error which return a String object, constant integer of error message (0 in this case) which is a primitive.
    // 10) .setVisible: boolean true, primitive.