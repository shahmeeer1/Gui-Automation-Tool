<h1 align="center">
  <img src="https://github.com/shahmeeer1/Photoelectric-effect-simulator/blob/main/phet.png" width="200">
  <br>
  Gui Task Automation Tool
  <br>
</h1>

<h4 align="center">Desktop task automation with OpenCV support</a>.</h4>

<p align="center">
  <img src="https://img.shields.io/badge/language-Java-orange.svg" alt="Language">
  <img src="https://img.shields.io/badge/GUI-JavaFX-lightgrey.svg" alt="GUI">
</p>

## About The Project

This project is a lightweight desktop application for creating and executing scripts to automate gui interactions using computer vision and mouse inputs.

The scripts are structured as finite state machine tapes making it possible to create complex algorithms whilst ensuring reliable task execution.

## Features

*   **Script Creation:** Easily implement complex algorithms as automation scripts by adding individual actions.
*   **Mouse input automation:** Automate mouse clicks and movement using absolute and relative coordinates.
*   **Image Detection:** Search for dynamic gui elements on screen using OpenCV for template matching.
*   **Timing:** Adds delays between actions to add human-like behaviour.
*   **Script Management:** View and manage scripts in a clear and structured fashion.
*   **GUI Based:** An easy to use, modern user interface built with JavaFX.

## Tech Stack

*   **Language:** Java
*   **Management/Dependencies:** Maven
*   **Computer Vision:** OpenCV tempalte matching
*   **Testing:**: JUnit

## Installation

Follow these steps to set up the project locally:

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/shahmeeer1/Gui-Automation-Tool.git
    ```

2.  **Install dependencies:**
    ```bash
    mvn clean install
    ```

3.  **Start the application:**
    ```bash
    mvn javafx:run
    ```

## Usage

Once the application launches, you can start making scripts:
*   Each action is represented as an independent state. Click an action to add it to your script.
*   Mouse Actions: In the pop-up window, configure behaviour of the action.
    * **Click Action:**
       * Enter (X,Y) coordinates of point to click.
       * Press SPACE to autofill the coordinates relative to the screen’s top-left corner.
       * Or, toggle the 'Current Position' checkbox to click at the cursors position at execution time.
       * Select which mouse button to click from the dropdown menu.
    * **Move Mouse Action:**:
       * Move mouse cursor to entered (X,Y) coordinates.
       * Optional: specify cursor starting position before moving to entered coordinates
       * Alternatively, move the cursor by Δ(X,Y) at execution time.
  * **Delay Action:**
       * Add a delay (in milliseconds) to your script.
       * This feature can be used to humanize script behaviour or add throttling.
  * **Image Detection:**
       * Load an image to be detected on screen.
       * Choose an aditional action to be executed if image is detected.
       * Click chosen mouse button at top left or center of the detected image.
       * Or, move the mouse cursor to hover over the specified part of the detected image.
       * Define transition to next state using labels depending on whether image is found or not.
*   The script’s actions are listed in a table for easy reference.
*   Use the up and down button to reorder execution sequence.
*   Assign labels to actions to define more complex transitions between states.
