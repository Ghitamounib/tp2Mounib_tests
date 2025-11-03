package ma.emsi.mounib;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.Map;

public class Test2 {

    public static void main(String[] args) {

        String cle = System.getenv("GEMINI_KEY");

        ChatModel modele = GoogleAiGeminiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .build();

        PromptTemplate template = PromptTemplate.from("Traduis le texte suivant en anglais : {{texte}}");

        Prompt prompt = template.apply(Map.of("texte", "J’aime apprendre de nouvelles technologies."));

        System.out.println(modele.chat(prompt.text()));

    }
}
