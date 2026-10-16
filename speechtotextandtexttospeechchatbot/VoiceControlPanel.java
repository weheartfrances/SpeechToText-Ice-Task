/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package speechtotextandtexttospeechchatbot;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author frances
 */
public class VoiceControlPanel extends JFrame {
    
}     private JSlider speedSlider;
    private JSlider pitchSlider;
    private JSlider volumeSlider;
    private JTextField textField;

    public VoiceControlPanel() {
        setTitle("Voice Control Studio");
        setSize(500, 380);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel heading = new JLabel("VOICE CONTROL STUDIO");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel description = new JLabel("Customise your chatbot's voice");
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        textField = new JTextField("Hello! Welcome to my voice chatbot.");
        textField.setMaximumSize(new Dimension(450, 35));

        speedSlider = new JSlider(80, 200, 120);
        pitchSlider = new JSlider(50, 150, 100);
        volumeSlider = new JSlider(0, 10, 8);

        panel.add(heading);
        panel.add(Box.createVerticalStrut(5));
        panel.add(description);
        panel.add(Box.createVerticalStrut(20));

        addSlider(panel, "Speech Speed", speedSlider);
        addSlider(panel, "Pitch", pitchSlider);
        addSlider(panel, "Volume", volumeSlider);

        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Text to speak:"));
        panel.add(Box.createVerticalStrut(5));
        panel.add(textField);
        panel.add(Box.createVerticalStrut(15));

        JButton speakButton = new JButton("▶ Speak");
        speakButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        speakButton.addActionListener(e -> {
            String text = textField.getText().trim();

            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Please enter some text first."
                );
                return;
            }

            SpeechToTextAndTextToSpeechChatbot.speechRate =
                    speedSlider.getValue();

            SpeechToTextAndTextToSpeechChatbot.speechPitch =
                    pitchSlider.getValue();

            SpeechToTextAndTextToSpeechChatbot.speechVolume =
                    volumeSlider.getValue();

            SpeechToTextAndTextToSpeechChatbot.textToSpeech(text);
        });

        panel.add(speakButton);
        add(panel);

        setVisible(true);
    }

    private void addSlider(
            JPanel panel, String name, JSlider slider) {

        JLabel label = new JLabel(name + ": " + slider.getValue());
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        slider.setMajorTickSpacing(
            name.equals("Volume") ? 2 : 30
        );
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setAlignmentX(Component.LEFT_ALIGNMENT);

        slider.addChangeListener(e ->
            label.setText(name + ": " + slider.getValue())
        );

        panel.add(label);
        panel.add(slider);
        panel.add(Box.createVerticalStrut(8));
    }

