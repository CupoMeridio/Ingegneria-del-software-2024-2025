package it.unisa.diem.team02;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;
import static javafx.application.Application.launch;

/**
 * Classe principale dell'applicazione JavaFX che gestisce il caricamento
 * delle viste FXML e l'avvio dell'interfaccia utente.
 */
public class App extends Application {

    /** Scena principale dell'applicazione */
    private static Scene scene;

    /**
     * Metodo di avvio dell'applicazione JavaFX.
     * 
     * @param stage Lo stage principale della finestra.
     * @throws IOException Se il file FXML non può essere caricato.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LoginView"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Rubrica");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/imgs/icon.png")));
        stage.show();
    }

    /**
     * Cambia la radice della scena con un nuovo file FXML.
     * 
     * @param fxml Il nome del file FXML da caricare (senza estensione).
     * @throws IOException Se il file FXML non può essere caricato.
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carica un file FXML specificato e lo restituisce come nodo radice.
     * 
     * @param fxml Il nome del file FXML da caricare (senza estensione).
     * @return Il nodo radice del file FXML caricato.
     * @throws IOException Se il file FXML non può essere caricato.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Metodo principale che avvia l'applicazione JavaFX.
     * 
     * @param args Argomenti della riga di comando.
     */
    public static void main(String[] args) {
        launch();
    }
}
