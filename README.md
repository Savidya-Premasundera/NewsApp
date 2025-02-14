# NewsApp

This is a Java project for displaying news from various countries.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

-   [Java](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html): JDK 17 or higher
-   [Apache Maven](https://maven.apache.org/): Version 3.9.0 or higher.
-   [NetBeans IDE](https://netbeans.apache.org/) (optional): Version 16 or higher

## Run the Project

### 1. Running from the Terminal

You can run the project from the terminal using Maven.

1. **Clone the repository**:

    ```bash
    git clone <repository-url>
    cd insert-group-name/NewsApp
    ```

2. **Build the project**:

    ```bash
    mvn clean install
    ```

3. **Run the project**:
    ```bash
    mvn javafx:run
    ```
4. **Run tests**:
    ```bash
    mvn test
    ```

### 2. Running in NetBeans

NetBeans will handle all dependencies and run the application. To run the project in NetBeans:

1. **Open the Project**:

    - Open NetBeans IDE.
    - Click `File` > `Open Project`.
    - Navigate to the folder where the project is located and select it.

2. **Build and Run**:
    - Use the following buttons in the NetBeans toolbar:
        - **Build Project**: Click the `Build` button (hammer icon) to compile and build the project.
        - **Run Project**: Click the `Run` button (green play icon) to execute the application.
    - Alternatively, right-click on the project in the `Projects` window:
        - Select `Clean and Build` to build the project.
        - After building, right-click again and select `Run`.

---

### User Manual

1. Users can search for a specific country from the available list. Upon selection, the country’s details, including its location displayed on a map, will be shown. Relevant news for the selected country will also be fetched and displayed.

2. The news language is automatically set based on the selected country's primary language. Users can manually change the language through the language selection dropdown. If news is available in the selected language, it will be displayed accordingly.

3. User can choose a news category from the list of available options, and relevant news in that category will be displayed for the selected country.

4. News can also be filtered by specifying a "from" and "to" date range, with the default range being from the past week.

5. User can close the application using the “Exit” button.

Upon reopening the app, the app restores the user’s last searched country and selected filters, displaying the corresponding news. If it's the first time the app is launched, it will default to showing general news for the United States in English.
