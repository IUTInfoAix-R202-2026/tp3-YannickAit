package fr.univ_amu.iut.exercice5;

import java.io.IOException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/** Composant graphique réutilisable représentant une carte de site de suivi VigieChiro. */
public class SiteCarte extends HBox {

  @FXML private Label labelCarre;
  @FXML private Label labelNom;
  @FXML private Label labelBadge;
  @FXML private Label labelNbPoints;
  @FXML private Label labelNbPassages;

  private final StringProperty numeroCarre = new SimpleStringProperty(this, "numeroCarre", "");
  private final StringProperty nomConvivial = new SimpleStringProperty(this, "nomConvivial", "");
  private final IntegerProperty nombrePoints = new SimpleIntegerProperty(this, "nombrePoints", 0);
  private final IntegerProperty nombrePassages =
      new SimpleIntegerProperty(this, "nombrePassages", 0);
  private final IntegerProperty joursDepuisDernierPassage =
      new SimpleIntegerProperty(this, "joursDepuisDernierPassage", -1);

  public SiteCarte() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("SiteCarte.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    try {
      loader.load();
    } catch (IOException e) {
      throw new RuntimeException("Erreur lors du chargement de SiteCarte.fxml", e);
    }
  }

  @FXML
  private void initialize() {
    labelCarre.textProperty().bind(numeroCarre);
    labelNom.textProperty().bind(nomConvivial);
    labelNbPoints.textProperty().bind(nombrePoints.asString().concat(" points d'écoute"));
    labelNbPassages.textProperty().bind(nombrePassages.asString().concat(" passages"));

    joursDepuisDernierPassage.addListener((obs, oldVal, newVal) -> majBadge(newVal.intValue()));

    majBadge(getJoursDepuisDernierPassage());
  }

  private void majBadge(int jours) {
    labelBadge.getStyleClass().removeAll("badge-fresh", "badge-stale", "badge-cold");

    if (jours < 0) {
      labelBadge.setText("Jamais utilisé");
      labelBadge.getStyleClass().add("badge-cold");
    } else if (jours < 7) {
      labelBadge.setText("Il y a " + jours + "j");
      labelBadge.getStyleClass().add("badge-fresh");
    } else if (jours <= 30) {
      labelBadge.setText("Il y a " + jours + "j");
      labelBadge.getStyleClass().add("badge-stale");
    } else {
      labelBadge.setText("Il y a " + jours + "j");
      labelBadge.getStyleClass().add("badge-cold");
    }
  }

  public StringProperty numeroCarreProperty() {
    return numeroCarre;
  }

  public String getNumeroCarre() {
    return numeroCarre.get();
  }

  public void setNumeroCarre(String value) {
    numeroCarre.set(value);
  }

  public StringProperty nomConvivialProperty() {
    return nomConvivial;
  }

  public String getNomConvivial() {
    return nomConvivial.get();
  }

  public void setNomConvivial(String value) {
    nomConvivial.set(value);
  }

  public IntegerProperty nombrePointsProperty() {
    return nombrePoints;
  }

  public int getNombrePoints() {
    return nombrePoints.get();
  }

  public void setNombrePoints(int value) {
    nombrePoints.set(value);
  }

  public IntegerProperty nombrePassagesProperty() {
    return nombrePassages;
  }

  public int getNombrePassages() {
    return nombrePassages.get();
  }

  public void setNombrePassages(int value) {
    nombrePassages.set(value);
  }

  public IntegerProperty joursDepuisDernierPassageProperty() {
    return joursDepuisDernierPassage;
  }

  public int getJoursDepuisDernierPassage() {
    return joursDepuisDernierPassage.get();
  }

  public void setJoursDepuisDernierPassage(int value) {
    joursDepuisDernierPassage.set(value);
  }
}
