# GameSearch

This Java application serves as a simple video game catalogue with basic CRUD (Create, Read, Update, Delete) functionality. Users can view, add, delete, and search for video games, and the application provides sorting options based on various criteria such as name, genre, release date, rating, and download size.
A sample database of video games 'datagames.txt' is provided to demonstrate the functionality of the program.
## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Viewing Games:** The main interface displays a list of video games retrieved from a data file (`datagames.txt`).
- **Sorting:** Users can sort the displayed games based on different criteria such as name, genre, release date, rating, and download size. This is achieved through algorithms such as the merge sort and binary search.
- **Adding Games:** Users can add new games to the catalogue, providing details such as name, genre, release date, rating, and download size. An option to add an image for the game is also available.
- **Deleting Games:** Users can delete a game from the catalogue by entering its name.
- **Searching:** The application allows users to search for a specific game by name, providing details if the game is found.
- **Graphical User Interface (GUI):** The application features a graphical user interface built using Java's Swing library.

## Getting Started

To run the application locally, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/your-repository.git
   cd your-repository
   ```

2. **Compile and Run:**
   ```bash
   javac Main.java
   java Main
   ```

3. **Interact with the GUI:**
   Once the application is running, the graphical user interface will appear. You can perform actions such as sorting, adding, deleting, and searching for games.

## Usage

- **Sorting:**
  - Use the dropdown menu to select a sorting criterion (e.g., name, genre, release date).
  - The sorted list of games will be displayed in the main text area.

- **Adding a Game:**
  - Click the "Add a Game" button.
  - Enter the required details (name, genre, release date, rating, size).
  - Optionally, choose to add an image for the game.
  - Confirm the addition.

- **Deleting a Game:**
  - Click the "Delete a game" button.
  - Enter the name of the game you want to delete.
  - Confirm the deletion.

- **Searching for a Game:**
  - Enter the name of the game in the search bar.
  - Click the "Search" button.
  - Details of the game will be displayed if found.

## Contributing

Contributions are welcome! If you have suggestions, improvements, or bug fixes, please submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute the code.
