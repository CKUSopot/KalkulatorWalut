import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Zadanie 7 – BorderLayout: Kalkulator walut
 * Stwórz aplikację KalkulatorWalut z BorderLayout, w której:
 * • w regionie NORTH znajduje się etykieta "Kalkulator walut" (wyśrodkowana),
 * • w regionie CENTER umieszczony jest panel (JPanel) z FlowLayout zawierający: etykietę
 * "Kwota (PLN):", pole tekstowe i przycisk "Przelicz na EUR",
 * • w regionie SOUTH znajduje się etykieta statusu "Gotowy".
 * Po kliknięciu przycisku przelicz złotówki na euro (przyjmij kurs: 1 EUR = 4,25 PLN) i wyświetl wynik w
 * etykiecie statusu na dole okna.
 */

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorWalut extends JFrame {
    private JTextField poleKwota;
    private JLabel etykietaStatusu;
    private JButton przyciskPrzelicz;

    public KalkulatorWalut() {
        // Konfiguracja podstawowa okna głównego
        setTitle("Kalkulator Walut (PLN -> EUR)");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // === REGION NORTH: Nagłówek okna ===
        JLabel etykietaNaglowek = new JLabel("Kalkulator walut", SwingConstants.CENTER);
        etykietaNaglowek.setFont(new Font("Arial", Font.BOLD, 18));
        etykietaNaglowek.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(etykietaNaglowek, BorderLayout.NORTH);

        // === REGION CENTER: Panel roboczy z FlowLayout ===
        JPanel panelSrodkowy = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        JLabel etykietaKwota = new JLabel("Kwota (PLN):");
        poleKwota = new JTextField(8);
        przyciskPrzelicz = new JButton("Przelicz na EUR");

        panelSrodkowy.add(etykietaKwota);
        panelSrodkowy.add(poleKwota);
        panelSrodkowy.add(przyciskPrzelicz);
        add(panelSrodkowy, BorderLayout.CENTER);

        // === REGION SOUTH: Pasek statusu ===
        etykietaStatusu = new JLabel("Gotowy", SwingConstants.CENTER);
        etykietaStatusu.setFont(new Font("Arial", Font.PLAIN, 12));
        etykietaStatusu.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        add(etykietaStatusu, BorderLayout.SOUTH);

        // Obsługa kliknięcia przycisku przeliczenia
        przyciskPrzelicz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String tekstKwota = poleKwota.getText().trim();
                    double pln = Double.parseDouble(tekstKwota);

                    // Warunek sprawdzający logiczną poprawność kwoty finansowej
                    if (pln < 0) {
                        throw new IllegalArgumentException("Kwota nie może być ujemna!");
                    }

                    // Przeliczenie złotówek na euro (kurs: 1 EUR = 4.25 PLN)
                    double eur = pln / 4.25;

                    // Wyświetlenie sformatowanego wyniku w pasku statusu
                    etykietaStatusu.setText(String.format("Wynik: %.2f PLN to %.2f EUR", pln, eur));
                    etykietaStatusu.setForeground(new Color(0, 100, 0));

                } catch (NumberFormatException ex) {
                    // Przechwycenie błędu błędnego formatu liczby
                    etykietaStatusu.setText("Błąd: Wpisz poprawną kwotę!");
                    etykietaStatusu.setForeground(Color.RED);
                    poleKwota.requestFocus();
                    poleKwota.selectAll();

                } catch (IllegalArgumentException ex) {
                    // Przechwycenie błędu ujemnej wartości liczbowej
                    etykietaStatusu.setText("Błąd: " + ex.getMessage());
                    etykietaStatusu.setForeground(Color.RED);
                    poleKwota.requestFocus();
                    poleKwota.selectAll();
                }
            }
        });
        setVisible(true);
    }
    public static void main(String[] args) {
        new KalkulatorWalut();
    }
}