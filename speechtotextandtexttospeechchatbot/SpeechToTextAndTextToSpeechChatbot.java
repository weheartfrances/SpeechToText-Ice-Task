package speechtotextandtexttospeechchatbot;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SpeechToTextAndTextToSpeechChatbot {

    public static int speechRate = 120;
    public static int speechPitch = 100;
    public static int speechVolume = 8;

    public static void main(String[] args) {

       
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                System.out.println("Choose an option: ");
                System.out.println("1. Text to Speech");
                System.out.println("2. Chatbot");
                System.out.println("3. All available voices");
                System.out.println("4. Exit");
                System.out.println("5. Voice Control Studio");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter text to convert to speech:");
                        String text = scanner.nextLine();
                        textToSpeech(text);
                        break;
                    case 2:
                        chatbot();
                        break;
                    case 3:
                        Voices();
                        break;
                    case 4:
                        running = false;  // Exit the loop
                        System.out.println("Exiting...");
                        break;
                    case 5:
                       new VoiceControlPanel();
                       break;    
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        scanner.close();
    }
    
    public static void Voices(){
    System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory," +
                "com.sun.speech.freetts.en.us.cmu_time_awb.AlanVoiceDirectory");

        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();

        System.out.println("Available voices:");
        for (Voice v : voices) {
            System.out.println(v.getName());
        }
    }
    

    public static void textToSpeech(String text) {
    System.setProperty("freetts.voices",
            "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory," +
            "com.sun.speech.freetts.en.us.cmu_time_awb.AlanVoiceDirectory");
    
    //Fetching the voice to be used from the TTS engine
    VoiceManager voiceManager = VoiceManager.getInstance();
    Voice voice = voiceManager.getVoice("kevin16");

    if (voice != null) {
        voice.allocate();
        
        // Set parameters to approximate TTS
        voice.setRate(speechRate);
        voice.setPitch(speechPitch);
        voice.setVolume(speechVolume / 10.0f); // Medium volume
        
        voice.speak(text);
        voice.deallocate();
    } else {
        System.err.println("Voice not found.");
    }
}

    public static void chatbot() {
        List<String[]> responses = new ArrayList<>();
        responses.add(new String[]{"hello", "Hi there!"});
        responses.add(new String[]{"bye", "Goodbye!"});

        Scanner scanner = new Scanner(System.in);
        System.out.println("Start chatting with the bot (type 'exit' to end):");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();
            if (userInput.equals("exit")) {
                System.out.println("Bot: Goodbye!");
                break;
            }

            String response = "I'm not sure how to respond to that.";
            for (String[] pair : responses) {
                if (pair[0].equals(userInput)) {
                    response = pair[1];
                    break;
                }
            }

            System.out.println("Bot: " + response);
            //Allows Chatbot to provide auditory responses
            textToSpeech(response);
        }
    }
}