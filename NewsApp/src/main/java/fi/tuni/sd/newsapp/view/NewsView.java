/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.view;

import fi.tuni.sd.newsapp.datamodels.Article;
import javafx.event.ActionEvent;
import java.util.List;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * NewsView class is responsible for creating and displaying the UI components 
 * for news articles and their details. It includes methods to display individual 
 * articles, a list of top news for a specific country, and allows users to read 
 * articles by opening them in a web browser.
 * 
 * It also handles asynchronous loading of images for each article and sets the 
 * appropriate styles and effects for the UI components.
 * 
 * @author Savidya
 */
public class NewsView {

    /**
     * Creates the UI component for displaying an individual news article.
     *
     * @param article A news article containing title, description, and image URL.
     * @return An HBox containing the UI elements for the article (image, title, description, link).
     */
    public HBox createArticleView(Article article) {
        var newArticle = new HBox(15);

        newArticle.setPrefSize(480, 200);
        newArticle.setMaxWidth(480);
        newArticle.setPadding(new Insets(15));
        newArticle.setStyle("-fx-background-color: #FFFFFF; -fx-border-radius: 10; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1),  3, 0.2, 0, 3);");
        newArticle.setAlignment(Pos.CENTER);

        var shadow = new DropShadow(5, Color.GRAY);

        loadImageAsync(article.getImage(), newArticle, shadow);

        var content = new VBox(10);
        var title = new TextFlow();
        Text titleText = new Text(article.getTitle());
        titleText.setFont(Font.font("Arial", 16));
        titleText.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");
        titleText.setWrappingWidth(250);
        title.getChildren().add(titleText);

        var description = new TextFlow();
        var descriptionText = new Text(article.getDescription());
        descriptionText.setFont(Font.font("Arial", 14));
        descriptionText.setStyle("-fx-text-fill: #555555;");
        descriptionText.setWrappingWidth(250);
        description.getChildren().add(descriptionText);

        var link = new Hyperlink("Read more");
        link.setStyle("-fx-text-fill: #1E90FF;");
        link.setOnAction((ActionEvent e) -> openArticleInBrowser(article.getUrl()));

        content.getChildren().addAll(title, description, link);

        newArticle.getChildren().add(content);
        newArticle.setOnMouseEntered(e -> newArticle.setStyle("-fx-background-color: #F8F8F8; -fx-border-radius: 10; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 3, 0.2, 0, 3);"));
        newArticle.setOnMouseExited(e -> newArticle.setStyle("-fx-background-color: #FFFFFF; -fx-border-radius: 10; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 2, 0.1, 0, 3);"));

        return newArticle;
    }

    /**
     * Asynchronously loads an image and adds it to the article view.
     *
     * @param imageUrl The URL of the image to load
     * @param newArticle The HBox to add the ImageView to
     * @param shadow The shadow effect to apply on the ImageView
     */
    private void loadImageAsync(String imageUrl, HBox newArticle, DropShadow shadow) {
        Task<Image> imageLoadTask = new Task<>() {
            @Override
            protected Image call() {
                return new Image(imageUrl);
            }
        };

        imageLoadTask.setOnSucceeded(e -> {
            var articleImg = imageLoadTask.getValue();
            var articleImgView = new ImageView(articleImg);
            articleImgView.setFitHeight(200);
            articleImgView.setFitWidth(180);
            articleImgView.setPreserveRatio(true);
            articleImgView.setEffect(shadow);
            newArticle.getChildren().add(0, articleImgView);
        });

        new Thread(imageLoadTask).start();
    }

    /**
     * Creates the UI component for the top 10 news articles of a specific
     * country
     *
     * @param articles News article list
     * @param countryName Selected country
     * @return
     */
    public ScrollPane createArticlesView(List<Article> articles, String countryName) {
        var title = new Label("Top 10 News of " + countryName);
        title.setFont(Font.font("Arial", 20));
        title.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");
        title.setAlignment(Pos.CENTER);

        var articlesGrid = new GridPane();
        articlesGrid.setPadding(new Insets(20));
        articlesGrid.setHgap(20);
        articlesGrid.setVgap(20);
        articlesGrid.add(title, 0, 0, 2, 1);

        var column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);

        articlesGrid.getColumnConstraints().addAll(column1, column2);

        for (int i = 0; i < articles.size(); i++) {
            int columnIndex = i % 2;
            int rowIndex = i / 2 + 1;
            articlesGrid.add(createArticleView(articles.get(i)), columnIndex, rowIndex);
        }

        ScrollPane scrollPane = new ScrollPane(articlesGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(20));
        return scrollPane;
    }

    /**
     * Opens the article URL in a new WebView
     *
     * @param url The URL of the article to open
     */
    private void openArticleInBrowser(String url) {
        var browser = new WebView();
        var webEngine = browser.getEngine();
        webEngine.load(url);

        var secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(browser);
        var secondScene = new Scene(secondaryLayout, 700, 700);
        var stage = new Stage();
        stage.setScene(secondScene);
        stage.show();
    }

}
