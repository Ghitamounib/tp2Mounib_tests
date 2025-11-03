package ma.emsi.mounib;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;

import java.util.Scanner;

public class Test6 {
    interface AssistantMeteo {
        String chat(String message);
    }

    public static void main(String[] args) {
        String apiKey = System.getenv("GEMINI_KEY");

        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.3)
                .logRequestsAndResponses(true)  // LOGGING ACTIVÉ
                .build();

        AssistantMeteo assistant = AiServices.builder(AssistantMeteo.class)
                .chatModel(model)
                .tools(new MeteoTool())  // OUTIL AJOUTÉ
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== ASSISTANT MÉTÉO (tapez 'fin' pour quitter) ===");
            while (true) {
                System.out.println("==================================================");
                System.out.print("Vous : ");
                String question = scanner.nextLine().trim();
                if (question.isBlank()) continue;
                if ("fin".equalsIgnoreCase(question)) break;

                String reponse = assistant.chat(question);
                System.out.println("Assistant : " + reponse);
                System.out.println("==================================================\n");
            }
        }
    }
}