/// NO AI WAS USED!

import java.util.Scanner;
import java.util.Random;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File.*;

Scanner scanner = new Scanner(System.in);
Random random = new Random();

/**
 * updates hangman String array and returns it
 * @param faults number of incorrect guesses user has made.
 * @param current_hangman latest version of hangman array.
 * @return The updated hangman array of strings
 */
String[] update_hangman(int faults, String [] current_hangman) {

    switch(faults){
        case 0:
            break;
        case 1:
            current_hangman[2] = current_hangman[2].substring(0, 3) + "O";
            break;
        case 2:
            current_hangman[3] = current_hangman[3].substring(0, 3) + "|";
            break;
        case 3:
            current_hangman[4] = current_hangman[4].substring(0, 3) + "|";
            break;
        case 4:
            current_hangman[5] = current_hangman[5].substring(0, 2) + "/ ";
            break;
        case 5:
            current_hangman[5] = current_hangman[5].substring(0, 3) + " \\";
            break;
        case 6:
            current_hangman[3] = current_hangman[3].substring(0, 1) + "/" + current_hangman[3].substring(2); //i realize the substring is maybe not the "best" method here for such a small range. but it works.
            break;
        case 7:
            current_hangman[3] = current_hangman[3].substring(0, 4) + " \\";
            break;
    }
return(current_hangman);
}

/**
 * Prints hangman graphics and information
 * @param current_word_array current array of guessed/unguessed character strings.
 * @param current_hangman latest version of hangman array.
 * @param already_guessed array of characters incorrectly guessed
 */
void printSituation(String[] current_hangman, String[] current_word_array, ArrayList<String> already_guessed) {
    System.out.println(String.join("\n", current_hangman));
    for(String item : current_word_array) {
        System.out.print(item);
    }
    System.out.println("\nALREADY GUESSED: " + ("" + (already_guessed)));
}

/**
 * prompts for input
 * @return integer representing difficulty choice
 */
int chooseDifficulty(){
    System.out.println("PICK ONE OF THE FOLLOWING OPTIONS:\n -Difficulty Low: 1\n -Difficulty Medium: Enter 2" );
    String input_difficulty = scanner.nextLine();
    if (!((input_difficulty.equals("1"))||((input_difficulty.equals("2"))))){
        System.out.println("Incorrect input. Try again?");
        return(chooseDifficulty());}
    return(Integer.parseInt(input_difficulty));
}

/**
 * @param choice represents selected difficulty, 1 being easy, 2 being harder
 * @return word at randomly-generated index (between 1 and 9)
 */
String pickRandomWord(int choice) {

    List<String> default_words_easy = List.of("apple", "daisy", "table", "mint", "wish", "tea", "pine", "horse", "taxes", "stick");
    List<String> default_words_harder = List.of("ternary", "dynamite", "variable", "somersault", "periwinkle", "benzene", "carousel", "malicious", "acerbic", "erythromycin");
    int random_int = random.nextInt(10); //generates a number from 0-9.
    String random_word ;

    if(choice ==1){random_word = default_words_easy.get(random_int);
    }
    else random_word = default_words_harder.get(random_int);

   return(random_word);
    }

/**
 * Inserts guess letter into correct index of
 * user-viewed word(if the guess is present in secret word).
 *
 * @param current_word_array current array of guessed/unguessed character strings.
 * @param guess user's guessed letter or word
 * @param secret_word word hidden from user
 * @return the updated word array.
 */
String[] fillTheBlanks(String[] current_word_array, String guess, String secret_word){
    for(int i= 0; i< secret_word.length(); i++){
        if((""+ secret_word.charAt(i)).equals(guess.toLowerCase())){
            current_word_array[i]=guess;}
    }
    return(current_word_array);
}

/**
 * Prompts user for guess,
 * @param secret_word word hidden from user
 * @param already_guessed arraylist of strings (single characters)
 *For the purpose of this program it represents letters the user has incorrectly guessed.
 * @return the updated word array, OR keeps calling itself until the guess meets the constraints.
 */

String prompt_for_guess (String[] current_word_array, String secret_word, ArrayList<String> already_guessed){
    System.out.println("Enter a letter, or-if you're feeling lucky-enter a word guess. Note: your guess must be as long as the secret word.");
    String guess = scanner.nextLine();

    String regex_for_full_word = "[a-zA-Z]{" + ("" + secret_word.length()) + "}"; //regex for full word of A-Z, size is however long the length of the secret word array is.
    if((!(Pattern.matches("^[a-zA-Z]$", guess))&&(!(Pattern.matches(regex_for_full_word, guess))))){
        System.out.println("Invalid input :(");
        return(prompt_for_guess(current_word_array,secret_word,already_guessed));
    }
    if (already_guessed.contains(guess)||(Arrays.asList(current_word_array).contains(guess))){ // is it in the word already? is it in the already guessed?
        System.out.println("You already guessed that.");
        return(prompt_for_guess(current_word_array, secret_word, already_guessed));
    }
        return(guess);
}

/**
 * Inserts provided string into new file using a BufferedWriter.
 * @param result any string with appropriate encoding, but in this case the win/loss info string.
 */
void writeResultToFile (String result) {
    try {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("hall_of_fame.txt"));
                writer.newLine();
                writer.write(result);
                writer.close();
                System.out.println(
                "Check out your awesome record file! Send it to all your friends!");
    }
    catch (IOException exception) {
        System.out.println("An error occurred in writing to the file :((("
                + exception.getMessage());
    }
}


/**
 * Contains above-declared functions and uses them to play
 * the hangman game.
 * @return string containing information on game outcome (win, loss, guesses)
 */
String main_game_loop() {

    String[] hangman = {"---+", "|  |   ", "|         ", "|          ", "|         ", "|        ", "|         ", "======="};
    System.out.println("H A N G M A N\n to pick a word or phrase for your friend to guess, enter it now. It must be at least 2 letters. Otherwise, enter anything else to begin selecting                           your random word. ");

    String secretword;

    String usrinput = scanner.nextLine();
    if(Pattern.matches("^[a-zA-Z !?/=+(),'.]{3,25}$", usrinput)){
        secretword = usrinput.toLowerCase();
        System.out.println("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n"); //hides the input from user, assuming standard zoom on screen.
    } else {
        secretword = pickRandomWord(chooseDifficulty());
    }

    ArrayList<String> already_guessed = new ArrayList<>();

    String[] visible_word = new String[secretword.length()];//creates array used to display the results to player.

    for(int j=0; j < secretword.length(); j++) {
        String c = String.valueOf(secretword.charAt(j));

        if(Pattern.matches("^[a-zA-Z]$", c)){
            visible_word[j] ="_";
        }
        else{visible_word[j]=c;
        }
    }
    int total_guesses = 0;
    int faults = 0;

    printSituation((update_hangman(faults, hangman)), visible_word, already_guessed);

    while((faults<7) && !(String.join("", visible_word)).equals(secretword)) {
        String guess = prompt_for_guess(visible_word, secretword, already_guessed);
        total_guesses += 1;
        if (guess.length() == secretword.length()) {
            if (guess.equals(secretword)) {
                break;
            }
            faults +=1;
            continue;
        }

        String pre_fill = String.join("",visible_word);

        String post_fill = (String.join("", (fillTheBlanks(visible_word, guess, secretword))));

        if(pre_fill.equals(post_fill)){ //if the two strings are equal, then guess was not present.
            already_guessed.add(guess);
            faults +=1;
        }

        else {
            int index = secretword.indexOf(guess);
            visible_word[index] = guess;
        }
        printSituation((update_hangman(faults, hangman)), visible_word, already_guessed);
    }

    String messagetowrite ;
    if (already_guessed.size() < 7){
        System.out.println("You won and correctly guessed the word/phrase! Nice!");
        messagetowrite = "You won and guessed the word '" + secretword+ "'in " + ("" + total_guesses) + " guesses";
    }
    else {
        System.out.println("You lost. Better luck next time?");
        messagetowrite = "You lost...The word/phrase was '" + secretword + "'." ;
    }
    return(messagetowrite);
}

void main() {
    writeResultToFile(main_game_loop());
        }
