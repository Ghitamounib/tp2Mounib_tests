package ma.emsi.mounib;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class Test1 {

    public static void main(String[] args) {

        String cle = System.getenv("GEMINI_KEY");
        ChatModel modele = GoogleAiGeminiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .build();

        String reponse = modele.chat("Quelle est la superficie de la France ?");
        System.out.println(reponse);
    }
}

