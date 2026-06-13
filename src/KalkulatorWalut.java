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

public class KalkulatorWalut extends JFrame {
    private JLabel lblTytul;
    private JLabel lblKwota;
    private JTextField txtKwota;
    private JButton btnAdd;
    private JLabel lblStatus;

    public KalkulatorWalut() {
        setTitle("Kalkulator Walut");
        setSize(400, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //BorderLayout layout = new BorderLayout(5, 5);//jawna definicja
        //setLayout(layout);
        setLayout(new BorderLayout(5, 5));//sposób anonimowy
        setLocationRelativeTo(null);
        lblTytul = new JLabel("Kalkulator Walut", SwingConstants.CENTER);//wyśrodkowuję napis
        add(lblTytul, BorderLayout.NORTH);//napis w północnym rejonie

        //panel w rejonie centr
        JPanel panelCentr = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(panelCentr, BorderLayout.CENTER);

        //kontrolki dla panelu centralnego
        lblKwota = new JLabel("Kwota PLN", SwingConstants.CENTER);
        txtKwota = new JTextField(8);
        btnAdd = new JButton("Przelicz na EUR");

        panelCentr.add(lblKwota);
        panelCentr.add(txtKwota);
        panelCentr.add(btnAdd);

        lblStatus = new JLabel("Gotowy", SwingConstants.CENTER);
        add(lblStatus, BorderLayout.SOUTH);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    double pln = Double.parseDouble(txtKwota.getText());
                    double eur = pln / 4.25;
                    lblStatus.setText(String.format("EUR: %.2f", eur));
                }catch (NumberFormatException ex){
                    lblStatus.setText("Błąd! Liczba powinna być rzeczywistą.");
                    txtKwota.setText("");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new KalkulatorWalut();
    }
}
