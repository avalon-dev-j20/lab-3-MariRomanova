package colorpicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Color Picker");
        //Color Mycolor = new Color (100, 200, 150);

        JPanel generalPanel = new JPanel();

        JPanel panel = new JPanel(new GridLayout(3,2)); // jpanel для регулирования цвета
        //panel.setBorder((BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK))));

        JPanel colorpanel = new JPanel();// jpanel для отображения цвета
        colorpanel.setBorder((BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK))));
        generalPanel.setBorder(new EmptyBorder(20,20,20,20));



        //слайдеры для регулирования цвета
        JSlider red = new JSlider(JSlider.HORIZONTAL,0,255, 125);
        JSlider green = new JSlider(0,255, 125);
        JSlider blue = new JSlider(0,255, 125);
        //отображение меток на шкале
        red.setMinorTickSpacing(17);
        red.setPaintTicks(true);
        red.setPaintLabels(true);
        green.setMinorTickSpacing(17);
        green.setPaintTicks(true);
        green.setPaintLabels(true);
        blue.setMinorTickSpacing(17);
        blue.setPaintTicks(true);
        blue.setPaintLabels(true);
        //отображение значений на шкале
        Hashtable labelTable = new Hashtable();
        labelTable.put( 0 , new JLabel("0") );
        labelTable.put(255, new JLabel("255") );

        red.setLabelTable( labelTable );
        green.setLabelTable( labelTable );
        blue.setLabelTable( labelTable );

        //отображение цвета
        colorpanel.setBackground(new Color(125,125,125));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(generalPanel, BorderLayout.CENTER);
        generalPanel.setLayout(new GridLayout(1,2));
        generalPanel.add(colorpanel);
        generalPanel.add(panel);


        //добавляем на панель слайдеры иназвания
        panel.add(new JLabel("Red: ",   JLabel.RIGHT));
        panel.add(red);
        panel.add(new JLabel("Green: ",  JLabel.RIGHT));
        panel.add(green);
        panel.add(new JLabel("Blue: ", JLabel.RIGHT));
        panel.add(blue);

        //Добавляем на слайдеры изменение цвета
        red.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                Color currentColor = colorpanel.getBackground();
                colorpanel.setBackground( new Color (source.getValue(), currentColor.getGreen(),currentColor.getBlue()));
            }
        });
        green.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                Color currentColor = colorpanel.getBackground();
                colorpanel.setBackground( new Color (currentColor.getRed(), source.getValue(),currentColor.getBlue()));
            }
        });
        blue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                Color currentColor = colorpanel.getBackground();
                colorpanel.setBackground( new Color (currentColor.getRed(), currentColor.getGreen(), source.getValue()));
            }
        });


        //создание всплывающей подсказки
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                colorpanel.setToolTipText((Integer.toHexString(colorpanel.getBackground().getRGB())).toUpperCase());
            }
        } ;
        colorpanel.addMouseListener(ma);
    }




}
