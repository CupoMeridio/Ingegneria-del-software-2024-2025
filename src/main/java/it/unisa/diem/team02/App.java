
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
 * @brief Classe principale dell'applicazione JavaFX.
 * @details Gestisce il caricamento delle viste FXML e l'avvio dell'interfaccia utente.
 * @author team02
 */

public class App extends Application {

    /** Scena principale dell'applicazione */
    private static Scene scene;

    /**
    * @brief Metodo principale che avvia l'applicazione JavaFX.
    * 
    * Questo metodo configura e mostra la finestra principale dell'applicazione. 
    * Carica la scena iniziale definita nel file FXML "LoginView" e applica alcune
    * configurazioni alla finestra, come il titolo, l'icona e lo stato massimizzato.
    * 
    * @param stage Lo stage principale dell'applicazione, fornito da JavaFX.
    * @throws IOException Se si verifica un errore durante il caricamento del file FXML.
    * 
    * @details 
    * - La scena iniziale viene caricata dal file "LoginView.fxml".
    * - La finestra viene impostata su "massimizzata".
    * - Il titolo della finestra è impostato su "Rubrica".
    * - L'icona della finestra viene caricata dal percorso `/imgs/icon.png`.
    * - Dopo la configurazione, la finestra viene resa visibile con `stage.show()`.
    * 
    * @note Assicurarsi che il file FXML "LoginView.fxml" e l'icona "/imgs/icon.png"
    * siano presenti e accessibili all'interno del progetto.
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
    * @brief Cambia la radice della scena corrente.
    * 
    * Questo metodo permette di aggiornare il contenuto della scena attuale caricando
    * un nuovo layout da un file FXML specificato.
    * 
    * @param fxml Il nome del file FXML da caricare come nuova radice della scena.
    * @throws IOException Se si verifica un errore durante il caricamento del file FXML.
    * 
    * @details 
    * - Il metodo utilizza il file FXML specificato per aggiornare la struttura visiva
    *   della scena corrente.
    * - Il file FXML deve essere accessibile e correttamente formattato.
    */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
    * @brief Carica un file FXML e restituisce la struttura visiva associata.
    * 
    * Questo metodo utilizza un oggetto `FXMLLoader` per caricare un file FXML specificato
    * dal percorso relativo e restituisce il nodo radice dell'albero di oggetti definito nel file.
    * 
    * @param fxml Il nome del file FXML (senza estensione) da caricare. 
    *             Il file deve trovarsi nella directory `/fxml` all'interno del progetto.
    * @return Il nodo radice del file FXML caricato.
    * @throws IOException Se si verifica un errore durante il caricamento del file FXML.
    * 
    * @details 
    * - Il percorso del file FXML viene costruito aggiungendo il nome del file alla directory `/fxml`.
    * - Il metodo si aspetta che il file abbia l'estensione `.fxml`.
    * - L'oggetto `FXMLLoader` è configurato per utilizzare la classe principale del progetto (`App`) 
    *   come base per il caricamento delle risorse.
    * 
    * @note Assicurarsi che il file FXML specificato esista e sia correttamente formattato.
    *       Eventuali errori del file FXML genereranno un'eccezione.
    */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
    * @brief Metodo principale dell'applicazione.
    * 
    * Questo metodo avvia l'applicazione JavaFX chiamando il metodo `launch()`.
    * 
    * @param args Gli argomenti della riga di comando. Non vengono utilizzati in questa implementazione.
    * 
    * @details 
    * - Il metodo `launch()` avvia il ciclo di vita di JavaFX, che gestisce la creazione dello stage principale
    *   e l'invocazione del metodo `start(Stage stage)`.
    * 
    */
    public static void main(String[] args) {
        launch();
    }
}
