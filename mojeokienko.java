import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by adste_000 on 2014-11-05.
 */
public class mojeokienko extends JFrame implements ActionListener, ChangeListener
{
   JButton bszybciej, bwolniej, bZamknij, bTworcy, bkto;
    JLabel lTworcy, lTempo, lTime;
    JSlider sTempo, sTime;

   private int duration=60;
    private double bpm=60.0;

    BeatGenerator generator;


    public mojeokienko()
    {
        generator = new BeatGenerator();

        setSize(300, 500);
        setTitle("Super Metronom");
        setLayout(null);

        bszybciej =new JButton("BPM +");
        bszybciej.setBounds(20, 20 ,100, 20);
        add(bszybciej);
        bszybciej.addActionListener(this);

        bkto=new JButton("Twórcy");
        bkto.setBounds(80,100,100,20);
        add(bkto);
        bkto.addActionListener(this);

        lTworcy = new JLabel();
        lTworcy.setBounds(40, 110, 200, 40);
        add(lTworcy);


        bwolniej =new JButton("BPM -");
        bwolniej.setBounds(20, 60 ,100, 20);
        add(bwolniej);
        bwolniej.addActionListener(this);

        bZamknij =new JButton("Zamknij");
        bZamknij.setBounds(140, 20 ,100, 20);
        add(bZamknij);
        bZamknij.addActionListener(this);

        bTworcy = new JButton("START!");
        bTworcy.setBounds(140, 60 ,100, 20);
        add(bTworcy);
        bTworcy.addActionListener(this);



        sTempo = new JSlider(SwingConstants.VERTICAL, 40, 240, 140);
        sTempo.setBounds(80, 140, 100, 300);
        sTempo.setMajorTickSpacing(20);
        sTempo.setMinorTickSpacing(5);
        sTempo.setPaintLabels(true);
        sTempo.setPaintTicks(true);
        sTempo.addChangeListener(this);

        add(sTempo);
        lTempo = new JLabel();
        lTempo.setBounds(10, 230,80,40);
        add(lTempo);


        sTime = new JSlider(SwingConstants.VERTICAL,0, 180, 60);
        sTime.setBounds(160, 140, 100, 300);
        sTime.setMajorTickSpacing(60);
        sTime.setMinorTickSpacing(20);
        sTime.setPaintLabels(true);
        sTime.setPaintTicks(true);
        sTime.addChangeListener(this);
        add(sTime);
        lTime = new JLabel();
        lTime.setBounds(10, 180,80,40);
        add(lTime);
//        autorzy = new JInternalFrame();
//        autorzy.setTitle("Autorzy");
//        autorzy.setBounds(20,20,100,100);
//        autorzy.setVisible(true);
//        add(autorzy);


        // nadanie wartosci poczatkowych
        bpm=sTempo.getValue();
        lTempo.setText("BPM "+ bpm);
        duration=sTime.getValue();
        lTime.setText(duration + "[s]");
    }

public static void main(String[] args)
{
    mojeokienko okienko=new mojeokienko();
    okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    okienko.setVisible(true);
}


    @Override
    public void actionPerformed(ActionEvent e)
    {
    Object źródło = e.getSource();
        if (źródło==bszybciej)
        {
            sTempo.setValue(sTempo.getValue()+ 1);
     System.out.println("Aga jest fajna!");
    }
    else if (źródło==bwolniej) {
            sTempo.setValue(sTempo.getValue()- 1);
            System.out.println("Adam to najlepszy na świecie programista!");
        }
    else if (źródło==bZamknij){
            System.exit(0);
        }
        else if (źródło==bkto){
            lTworcy.setText("Agnieszka Żak i Adam Stelmach");
        }
        else {
            if (źródło == bTworcy) {

                generateBeats(bpm, duration);
            }

        }

    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
       bpm=sTempo.getValue();
        lTempo.setText("BPM "+ bpm);
        duration=sTime.getValue();
        lTime.setText(duration+"[s]");
    }
    public void generateBeats(double bpm, int duration) {

        if(generator.isAlive()) {

            bTworcy.setText("START");
            generator.stop();


        } else {

            bTworcy.setText("STOP");

            generator = new BeatGenerator();

            generator.set(bpm, duration);
            generator.start();

        }

    }
}

