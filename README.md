# Memory Card Game
## Description
This Game is a Classic Memory Card Game where the players needs to match cards with their corresponding Cards that will serve as the introduction to the spring Java Framework

## Technologies Used

1. Spring Java Framework
2. MySQL Database

## Game Description

### Levels
As stated previously it is a Memory Card Game, it will house three levels: <br>
1. ***Level 1:*** 4 Cards and *2* Tries
2. ***Level 2:*** 8 Cards and *3* Tries
3. ***Level 3:*** 16 Cards and *4* Tries

### User Interface
The Game will have two Interfaces: <br>
1. The **Main Menu:** Where the Player can pick the difficulty level and choose a new game or load an old one
2. The **Game Interface:** Where the Cards will be displayed for the player to play the game

<br>

# Contribution Guidelines

1. Select an Issue by Assigning it to yourself
2. Create a Branch to work on said issue, the name of the branch goes as follows:  <br>
<br> 
    **feat/nameOfIssue** if its a feature <br>
    **fix/nameOfFix** if its a fix <br> 

3. Commit Messages must also respect the **feat** and **fix** conditions, be concise and precise when comititng, write only what changes the commit brought
4. Once finished with a certain feature, create a **Pull Request** to merge your branch with the main branch.


## Game Workflow

1. Player enters their name and password on the main menu
2. Player is shown their previous saved games and a **New Game** button
3. If the player clicks a saved game, that game's data is loaded and restored
4. If the player clicks **New Game**, they pick a difficulty level (Easy / Medium / Hard)
5. The game starts
6. During the game, the player can:
   - **Save** — saves current progression to the database
   - **Quit** — exits the game without saving




## Side Notes
Usually a Project Leader needs to review and accept the **PR**, but since there are only two of us, this isn't necessary unless specifically asked for.
