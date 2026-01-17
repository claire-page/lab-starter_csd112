/// HANGMAN.a la java

import javax.swing.*;
import java.util.Scanner;
import java.util.Random;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

Scanner scanner = new Scanner(System.in);
Random random = new Random();

ArrayList<String> make_new_hangman() {
    ArrayList<String> hangman_arraylist = new ArrayList<>();
    hangman_arraylist.add("_____ ");
    hangman_arraylist.add("|");
    hangman_arraylist.add("|");
    hangman_arraylist.add("|");
    hangman_arraylist.add("|");
    hangman_arraylist.add("|");
    hangman_arraylist.add("=======");

    return(hangman_arraylist);
}


void printBoard(ArrayList<String> current_hangman, String[] secret_word){
    System.out.println(String.join("\n", current_hangman));
    for(int x=0; x<secret_word.length; x++) {
        System.out.print("_ ");
    }
    System.out.println("\n"+ secret_word.length + "-LETTER WORD");
}
int chooseGameMode (){
    System.out.println("Enter 1 for Solo\nEnter 2 for Collaborative");
    String input_gamemode = scanner.nextLine();
    if (!(Pattern.matches("^[1|2]$",input_gamemode))){ //this regex matches a one-character string containing either "1" or "2"
        System.out.println("incorrect input.");
        return(chooseGameMode());
    }
    return(Integer.parseInt(input_gamemode));
}

int chooseDifficulty(){
    System.out.println("PICK ONE OF THE FOLLOWING OPTIONS:\n -Difficulty Low: 1\n -Difficulty Medium: Enter 2" );
    String input_difficulty = scanner.nextLine();
    if (!(Pattern.matches("^[1|2]$", input_difficulty))){
        System.out.println("incorrect input.");
        return(chooseDifficulty());}
    return(Integer.parseInt(input_difficulty));
}

String[] pickRandomWord(int choice) {

    List<String> default_words_easy = List.of("apple", "daisy", "table", "mint", "wish", "tea", "pine", "horse", "taxes", "slick");
    List<String> default_words_hard = List.of("ternary", "dynamic", "variable", "somersault", "periwinkle", "wintery", "carousel", "malicious", "acerbic", "erythromycin");
    int random_int = random.nextInt(11);
    String random_word ;

    switch(choice) {
        case 2: random_word =(default_words_hard.get(random_int));
        break;
            default : random_word = (default_words_easy.get(random_int)); //otherwise, easy mode is selected.
    }
    return(random_word.split("")); //return the array of the string split
}



//perhaps combine the two boolean functions? or should it be you can guess

boolean checkForLetterInWord(String guess_letter, String[] word_split){
    boolean is_present = false;
    for(int i= 0; i < word_split.length ; i++) {
        if(word_split[i].equals(guess_letter)){
            is_present = true;
            break;
        }
    }
    return(is_present);
}

boolean checkGuess(String guess, String[] secret_word){
    String[] array_guess = guess.toLowerCase().split("");
    return(Arrays.equals(array_guess, secret_word));
}

String[] guessed_array = "guess".split("");

void main(String[] args){
    printBoard(make_new_hangman(), guessed_array);
}

//String updateHangman (String current_hangman_state, boolean result, String guess){
//    if
//}
//    if(result == false){


//program flow:
// 1. solo/collab
// if solo: pick difficulty.
// if collab: enter your names. Game is turn-based.
//Player 1
//
///would like to have quit options
//would like to have go back options.

//game summary is written to the file.

//function to put letter in correct box according to
/// listarray add letter in letter zone for letter in word.
