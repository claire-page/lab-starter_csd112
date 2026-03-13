# Answers to Lab Questions

Place here your answers to the reflection questions in the lab instructions.

## Question 1
Why *can* you change the type of the returned **value** in `promptForPlayer` without changing the return **type** in the function signature?
### Answer
This works because of subtype polymorphism - a HumanPlayer instance is also a Player instance through inheritance, and can be treated as such. From what I understand this is one of the reasons subtype polymorphism is so useful. 
If I replace HumanPlayer with another value to be returned, the compiler does not let that slide! Yay for polymorphism!
![img.png](img.png)

## Question 2
Explain why the call to `getNextMove` initially causes an error until you add the abstract method to the `Player` class. Your answer should involve a discussion of static (compile-time) vs dynamic (run-time) types. (HINT: What is the compile-time vs run-time type of the `player` variable in `TicTacToeGame.doNextTurn`?)
### Answer 
![img_1.png](img_1.png)
the variable player's static type, shown above, restricts what methods may be called on it. The runtime type of the player variable is HumanPlayer(the only instantiation of Player
in the program so far), but when the compiler checks for a getNextMove method in the Player class, there's nothing there! What a bummer. Good thing abstract methods and overriding are a thing. 
Once you add the abstract method to the Player class and the @Override note to the method in the subclass, the compiler trusts that we've got it covered.

## Question 3
**Explain in detail how it is possible that neither our main game loop nor our TicTacToeGame class need change at all when adding new Player types to our game.**  
Your discussion must include an explanation of how the single call to getNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are. 
Your answer should involve discussion of polymorphism and dynamic method dispatch.


### Answer
Encapsulation is a big part of it- all the nitty gritty implementations are hidden away from the user (and the main methods themselves even), so we don't have to change the game logic to
accommodate our changes. "WhoseTurn" is an instance variable of the game containing a player object ->  initialized as x and switched after a valid move has been made. The game refers to this variable to
find out whose turn it is...and as for the fact that robots and humans are treated the same, we have deliberately implemented them as subclases of Player, meaning they share some methods and fields. Subtype polymorphism enables them to be
treated as Players. In this situation, polymorphism also enables dynamic method dispatch, where the JRE can determine which 'version' of a called method to use. Honestly not sure what else there is to say about it other than that.


