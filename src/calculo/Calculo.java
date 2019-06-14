package calculo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author rodri
 */
public final class Calculo {

    private List<Double> dados;
    private double media;
    private double variancia;
    private double desvioPadrao;

    public Calculo() {
        this.importarDados();
    }

    public double calculaMedia() {
        double soma = 0;
        for (double d : this.dados) {
            soma += d;

        }
        media = soma / dados.size();
        return media;
    }

    public double calculaModa() {
        int nVezes = 0;
        double moda = 0;
        int comparaVezes = 0;
        for (int p = 0; p < this.dados.size(); p++) {
            nVezes = 0;
            for (int k = p + 1; k < this.dados.size(); k++) {
                if (this.dados.get(p).equals(this.dados.get(k))) {
                    nVezes++;
                }
            }
            if (nVezes > comparaVezes) {
                moda = this.dados.get(p);
                comparaVezes = nVezes;
            }
        }
        return moda;
    }

    public double calculaMediana() {
        Collections.sort(dados);
        if (dados.size() % 2 == 0) {
            return ((dados.get(dados.size() / 2)) + (dados.get((dados.size() / 2) + 1))) / 2;
        } else {
            return dados.get((dados.size() + 1) / 2);
        }
    }

    public double calculaVariancia() {
        double soma = 0;
        media = this.calculaMedia();
        for (double d : this.dados) {
            soma += (d - media) * (d - media);
        }
        variancia = soma / this.dados.size();
        return Double.valueOf(new DecimalFormat("#,00").format(variancia));
    }

    public double calculaDesvioPadrao() {
        return Double.valueOf(new DecimalFormat("#,00").format(Math.sqrt(this.calculaVariancia())));
    }

    public void importarDados() {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/jsonFiles"));
        fc.setSelectedFile(new File("src/jsonFiles/dados.json"));
        int returnVal = fc.showOpenDialog(null);
        File file = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        if (file != null) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Calculo.class.getName()).log(Level.SEVERE, null, ex);
            }
            Type listType = new TypeToken<ArrayList<Double>>() {
            }.getType();
            dados = new Gson().fromJson(bufferedReader, listType);
        }
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public List<Double> getDados() {
        return dados;
    }

    public void setDados(List<Double> dados) {
        this.dados = dados;
    }

    public double getVariancia() {
        return variancia;
    }

    public void setVariancia(double variancia) {
        this.variancia = variancia;
    }

    public double getDesvioPadrao() {
        return desvioPadrao;
    }

    public void setDesvioPadrao(double desvioPadrao) {
        this.desvioPadrao = desvioPadrao;
    }

}
